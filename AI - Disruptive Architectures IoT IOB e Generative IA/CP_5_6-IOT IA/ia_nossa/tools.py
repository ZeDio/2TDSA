"""Compatibilidade com a estrutura antiga.

A busca da DA.IA agora é feita pelo RAG em ia_nossa/rag/retriever.py.
"""

from rag.retriever import buscar_trechos

ultima_busca = []


def buscar_material(pergunta: str) -> dict:
    global ultima_busca

    trechos = buscar_trechos(pergunta, k=5)
    ultima_busca = trechos

    return {
        "resultados": [
            {
                "titulo": item["titulo"],
                "url": item["url"],
                "texto": item["texto"],
                "similaridade": item["similaridade"],
            }
            for item in trechos
        ]
    }


tools_list = [buscar_material]
