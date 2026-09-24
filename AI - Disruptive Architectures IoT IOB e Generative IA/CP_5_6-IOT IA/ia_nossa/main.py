from rag.pipeline import responder_com_rag


def interagir_com_daia():
    print("\n--- DA.IA com RAG ---")
    print("Digite 'sair' para encerrar.\n")
    print(
        "DA.IA: Olá! Sou a assistente do site de Disruptive Architectures: "
        "IA e IoT. Minhas respostas usam o material do curso."
    )

    while True:
        pergunta = input("\nVocê: ").strip()

        if pergunta.lower() in {"sair", "encerrar", "parar"}:
            print("DA.IA: Até mais! Bons estudos.")
            break

        if not pergunta:
            continue

        try:
            resposta, fontes = responder_com_rag(pergunta, k=5)
            print(f"\nDA.IA: {resposta}")

            print("\nFontes recuperadas:")
            for fonte in fontes:
                print(
                    f"- {fonte['similaridade']:.4f} | "
                    f"{fonte['fonte']} | {fonte['titulo']}"
                )

        except Exception as erro:
            print(f"\nErro: {type(erro).__name__}: {erro}")


if __name__ == "__main__":
    interagir_com_daia()
