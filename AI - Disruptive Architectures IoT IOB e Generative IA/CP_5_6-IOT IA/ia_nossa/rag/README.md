# RAG da DA.IA

## Fluxo

```text
material/*.md
    ↓
rag/indexer.py
    ↓
chunks + embeddings
    ↓
rag/data/index.json
    ↓
rag/retriever.py
    ↓
Top K por similaridade de cosseno
    ↓
rag/pipeline.py
    ↓
Gemini + contexto
    ↓
resposta + fontes
```

## Primeira execução

Na pasta `ia_nossa`:

```bash
pip install -r requirements.txt
python -m rag.embeddings
python -m rag.indexer
```

O `indexer` lê todos os Markdown de `../material` e cria `rag/data/index.json`.
Cada chunk recebe um embedding. Essa etapa consome chamadas da API Gemini.

## Testar somente a recuperação

```bash
python -m rag.retriever
```

Isso mostra os chunks mais próximos da pergunta e a similaridade de cosseno.

## Rodar a API

```bash
python -m uvicorn api:app --reload
```

Depois o frontend usa `POST /ask`.

## Recriar o índice

Se os Markdown forem alterados, rode novamente:

```bash
python -m rag.indexer
```
