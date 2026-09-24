import json
import re
import time
from pathlib import Path
from urllib.parse import quote

from config import MATERIAL_DIR, RAG_INDEX_PATH
from .embeddings import gerar_embedding, preparar_documento


CHUNK_SIZE = 1800
CHUNK_OVERLAP = 250


def limpar_markdown(texto: str) -> str:
    """
    Remove partes de formatação do Markdown para deixar
    o conteúdo mais adequado para gerar embeddings.
    """

    texto = re.sub(r"```.*?```", " ", texto, flags=re.DOTALL)

    texto = re.sub(r"!\[([^\]]*)\]\([^)]+\)", r"\1", texto)

    texto = re.sub(r"\[([^\]]+)\]\([^)]+\)", r"\1", texto)

    texto = re.sub(r"^#{1,6}\s*", "", texto, flags=re.MULTILINE)

    texto = re.sub(r"[*_`~]", "", texto)

    texto = re.sub(r"\n{3,}", "\n\n", texto)

    return texto.strip()


def titulo_do_markdown(texto: str, caminho: Path) -> str:
    """
    Tenta descobrir o título do Markdown.
    """

    match = re.search(
        r"^\s*#\s+(.+?)\s*$",
        texto,
        flags=re.MULTILINE,
    )

    if match:
        return match.group(1).strip()

    return caminho.stem.replace("-", " ").replace("_", " ").title()


def criar_chunks(
    texto: str,
    tamanho: int = CHUNK_SIZE,
    overlap: int = CHUNK_OVERLAP,
):
    """
    Divide o conteúdo em pedaços menores.

    O overlap mantém uma pequena parte do chunk anterior
    para não perder contexto entre as divisões.
    """

    texto = texto.strip()

    if not texto:
        return []

    chunks = []

    inicio = 0
    tamanho_texto = len(texto)

    while inicio < tamanho_texto:

        fim = min(inicio + tamanho, tamanho_texto)

        chunk = texto[inicio:fim].strip()

        if chunk:
            chunks.append(chunk)

        if fim >= tamanho_texto:
            break

        inicio = fim - overlap

    return chunks


def url_do_material(caminho: Path) -> str:
    """
    Monta a URL pública correspondente ao Markdown.
    """

    try:
        relativo = caminho.relative_to(MATERIAL_DIR)
    except ValueError:
        relativo = caminho.name

    relativo = str(relativo).replace("\\", "/")

    return (
        "http://127.0.0.1:3000/"
        "aula.html?arquivo="
        + quote(relativo)
    )


def criar_documentos():
    """
    Lê todos os arquivos Markdown da pasta material
    e transforma cada chunk em um documento.
    """

    documentos = []

    arquivos = sorted(MATERIAL_DIR.rglob("*.md"))

    for caminho in arquivos:

        texto_original = caminho.read_text(
            encoding="utf-8",
            errors="ignore",
        )

        titulo = titulo_do_markdown(
            texto_original,
            caminho,
        )

        texto = limpar_markdown(texto_original)

        chunks = criar_chunks(texto)

        fonte = str(
            caminho.relative_to(MATERIAL_DIR)
        ).replace("\\", "/")

        url = url_do_material(caminho)

        for numero, chunk in enumerate(chunks):

            documentos.append(
                {
                    "id": f"{fonte}::chunk-{numero}",
                    "fonte": fonte,
                    "titulo": titulo,
                    "url": url,
                    "chunk": numero,
                    "texto": chunk,
                }
            )

    return documentos


def carregar_indice_existente():
    """
    Carrega o índice já existente.

    Se não existir, começa com uma lista vazia.
    """

    if not RAG_INDEX_PATH.exists():
        return []

    try:
        conteudo = RAG_INDEX_PATH.read_text(
            encoding="utf-8"
        )

        if not conteudo.strip():
            return []

        indice = json.loads(conteudo)

        if not isinstance(indice, list):
            print(
                "⚠️ O index.json não contém uma lista válida."
            )
            return []

        return indice

    except json.JSONDecodeError:
        print(
            "⚠️ O index.json está inválido."
        )

        print(
            "O arquivo será reconstruído."
        )

        return []


def salvar_indice(indice):
    """
    Salva o índice imediatamente.

    Isso evita perder todo o progresso caso a API
    limite a quota ou o programa seja interrompido.
    """

    RAG_INDEX_PATH.parent.mkdir(
        parents=True,
        exist_ok=True,
    )

    arquivo_temporario = RAG_INDEX_PATH.with_suffix(
        ".tmp"
    )

    arquivo_temporario.write_text(
        json.dumps(
            indice,
            ensure_ascii=False,
        ),
        encoding="utf-8",
    )

    arquivo_temporario.replace(
        RAG_INDEX_PATH
    )


def construir_indice():
    """
    Constrói o índice RAG.

    O processo é retomável:
    chunks que já possuem embedding são ignorados.
    """

    documentos = criar_documentos()

    total = len(documentos)

    print()
    print("=" * 70)
    print("CONSTRUÇÃO DO ÍNDICE RAG")
    print("=" * 70)
    print()
    print(f"Documentos/chunks encontrados: {total}")
    print()

    indice = carregar_indice_existente()

    existentes = {
        item.get("id")
        for item in indice
        if item.get("id")
        and item.get("embedding")
    }

    processados = len(existentes)

    print(
        f"Chunks já processados: {processados}"
    )

    restantes = [
        documento
        for documento in documentos
        if documento["id"] not in existentes
    ]

    print(
        f"Chunks restantes: {len(restantes)}"
    )

    print()

    if not restantes:

        print(
            "✅ Todos os chunks já possuem embedding."
        )

        salvar_indice(indice)

        return

    for documento in restantes:

        numero_atual = (
            processados + 1
        )

        print(
            f"[{numero_atual}/{total}] "
            f"{documento['fonte']} :: "
            f"chunk {documento['chunk']}"
        )

        texto_preparado = preparar_documento(
            documento["titulo"],
            documento["texto"],
        )

        try:

            vetor = gerar_embedding(
                texto_preparado
            )

            item = {
                "id": documento["id"],
                "fonte": documento["fonte"],
                "titulo": documento["titulo"],
                "url": documento["url"],
                "chunk": documento["chunk"],
                "texto": documento["texto"],
                "embedding": vetor.tolist(),
            }

            indice.append(item)

            # Salva imediatamente.
            salvar_indice(indice)

            processados += 1

            print(
                f"   ✓ Embedding salvo "
                f"({processados}/{total})"
            )

            print()

        except Exception as erro:

            mensagem = str(erro)

            if (
                "429" in mensagem
                or "RESOURCE_EXHAUSTED" in mensagem
                or "quota" in mensagem.lower()
            ):

                print()
                print("=" * 70)
                print("⚠️ LIMITE DA API ATINGIDO")
                print("=" * 70)
                print()
                print(
                    "O progresso já foi salvo."
                )
                print()
                print(
                    f"Chunks concluídos: "
                    f"{processados}/{total}"
                )
                print()
                print(
                    "Aguarde a liberação da quota "
                    "e execute novamente:"
                )
                print()
                print(
                    "python -m rag.indexer"
                )
                print()
                print(
                    "O programa continuará de onde parou."
                )
                print()

                return

            print()
            print(
                "❌ Erro ao gerar embedding:"
            )
            print(erro)
            print()

            print(
                "O progresso anterior foi salvo."
            )

            print(
                "Você pode executar novamente "
                "para continuar."
            )

            return

    salvar_indice(indice)

    print()
    print("=" * 70)
    print("✅ ÍNDICE RAG CONCLUÍDO")
    print("=" * 70)
    print()
    print(
        f"Total de chunks: {len(indice)}"
    )
    print()
    print(
        f"Arquivo gerado:"
    )
    print(
        RAG_INDEX_PATH
    )
    print()


if __name__ == "__main__":
    construir_indice()