from config import client, MODEL_NAME
from SYSTEM_PROMPT import SYSTEM_PROMPT
from .retriever import buscar_trechos


SYSTEM_PROMPT_RAG = SYSTEM_PROMPT + """

### REGRA RAG
Você receberá um CONTEXTO recuperado automaticamente da base de conhecimento.

- Responda usando apenas informações sustentadas pelo CONTEXTO.
- Não use conhecimento próprio para completar lacunas sobre a disciplina.
- Não invente datas, conteúdos, requisitos ou procedimentos.
- Se o CONTEXTO não sustentar a resposta, diga claramente: 
  "Não encontrei essa informação no material do curso."
- Quando fizer uma afirmação factual baseada no contexto, indique a fonte no formato [arquivo.md].
- Não trate a pontuação de similaridade como certeza ou prova.
"""


def montar_contexto(trechos: list[dict]) -> str:
    blocos = []

    for trecho in trechos:
        blocos.append(
            f"FONTE: [{trecho['fonte']}]\n"
            f"TÍTULO: {trecho['titulo']}\n"
            f"CONTEÚDO: {trecho['texto']}"
        )

    return "\n\n---\n\n".join(blocos)


def responder_com_rag(
    pergunta: str,
    k: int = 5,
) -> tuple[str, list[dict]]:
    """Executa as duas fases do RAG: recuperação e geração."""
    trechos = buscar_trechos(pergunta, k=k)
    contexto = montar_contexto(trechos)

    response = client.interactions.create(
        model=MODEL_NAME,
        system_instruction=SYSTEM_PROMPT_RAG,
        input=(
            f"CONTEXTO RECUPERADO:\n{contexto}\n\n"
            f"PERGUNTA DO ALUNO:\n{pergunta}"
        ),
    )

    return response.output_text, trechos


if __name__ == "__main__":
    pergunta = input("Pergunta: ")

    resposta, trechos = responder_com_rag(
        pergunta,
        k=5,
    )

    print("\n[Resposta da DA.IA]\n")
    print(resposta)

    print("\n[Fontes recuperadas]\n")

    for trecho in trechos:
        print(
            f"- {trecho['fonte']} "
            f"(similaridade: {trecho['similaridade']:.4f})"
        )