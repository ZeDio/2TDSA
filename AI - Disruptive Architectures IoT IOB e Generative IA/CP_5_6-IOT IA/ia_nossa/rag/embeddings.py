import numpy as np

from config import client, EMBEDDING_MODEL


def preparar_pergunta(pergunta: str) -> str:
    """Prepara uma pergunta para o modelo de embeddings.

    A busca é assimétrica: a pergunta é uma consulta, não um documento.
    """
    return f"task: question answering | query: {pergunta}"


def preparar_documento(titulo: str, conteudo: str) -> str:
    """Prepara um chunk do material antes de gerar seu embedding."""
    return f"title: {titulo} | text: {conteudo}"


def gerar_embedding(texto: str) -> np.ndarray:
    """Transforma um texto em vetor numérico."""
    resultado = client.models.embed_content(
        model=EMBEDDING_MODEL,
        contents=texto,
    )

    return np.array(
        resultado.embeddings[0].values,
        dtype=float,
    )


if __name__ == "__main__":
    texto = "Este é um teste de embedding da DA.IA."
    vetor = gerar_embedding(texto)

    print("Texto:")
    print(texto)
    print("\nTipo:")
    print(type(vetor))
    print("\nDimensões:")
    print(vetor.shape)
    print("\nPrimeiros valores:")
    print(vetor[:8])
