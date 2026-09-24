import json
from pathlib import Path

import numpy as np

from config import RAG_INDEX_PATH
from .embeddings import gerar_embedding, preparar_pergunta


def similaridade_cosseno(vetor_a: np.ndarray, vetor_b: np.ndarray) -> float:
    """Compara a direção de dois vetores. Maior = mais semanticamente próximo."""
    denominador = np.linalg.norm(vetor_a) * np.linalg.norm(vetor_b)
    if denominador == 0:
        return 0.0
    return float(np.dot(vetor_a, vetor_b) / denominador)


def carregar_indice() -> list[dict]:
    if not RAG_INDEX_PATH.exists():
        raise FileNotFoundError(
            "Índice RAG não encontrado. Execute: python -m rag.indexer"
        )

    return json.loads(RAG_INDEX_PATH.read_text(encoding="utf-8"))


def buscar_trechos(pergunta: str, k: int = 5) -> list[dict]:
    """Recupera os k chunks semanticamente mais próximos da pergunta."""
    indice = carregar_indice()
    vetor_pergunta = gerar_embedding(preparar_pergunta(pergunta))

    resultados = []

    for item in indice:
        vetor_documento = np.array(item["embedding"], dtype=float)
        resultados.append({
            "id": item["id"],
            "fonte": item["fonte"],
            "titulo": item["titulo"],
            "url": item["url"],
            "texto": item["texto"],
            "similaridade": similaridade_cosseno(
                vetor_pergunta,
                vetor_documento,
            ),
        })

    resultados.sort(
        key=lambda item: item["similaridade"],
        reverse=True,
    )

    return resultados[:k]


if __name__ == "__main__":
    pergunta = input("Pergunta: ").strip()

    if not pergunta:
        raise SystemExit("Digite uma pergunta.")

    resultados = buscar_trechos(pergunta, k=5)

    print("\n[Trechos recuperados]\n")

    for posicao, item in enumerate(resultados, start=1):
        print(
            f"{posicao}. {item['similaridade']:.4f} | "
            f"{item['fonte']} | {item['titulo']}"
        )
        print(item["texto"][:500])
        print("-" * 70)
