from pathlib import Path

from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import HTMLResponse
from pydantic import BaseModel, Field

from config import RAG_INDEX_PATH
from rag.pipeline import responder_com_rag

# API original dos conteúdos
from content_api import router as content_router


app = FastAPI(
    title="DA.IA API",
    description="API da DA.IA com RAG",
    version="1.0.0",
)


# ============================================================
# CORS
# ============================================================

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# ============================================================
# ROTAS ORIGINAIS DO MATERIAL
# ============================================================
#
# Mantém exatamente o contrato que o frontend já utilizava:
#
# GET /api/materials
# GET /api/material
#
# A implementação está em content_api.py
#

app.include_router(content_router)


# ============================================================
# MODELO DO /ask
# ============================================================

class PerguntaRequest(BaseModel):
    pergunta: str = Field(
        ...,
        min_length=1,
        description="Pergunta feita pelo usuário",
    )

    k: int = Field(
        default=5,
        ge=1,
        le=10,
        description="Quantidade de trechos recuperados pelo RAG",
    )


# ============================================================
# ROTA PRINCIPAL DA IA
# ============================================================

@app.post("/ask")
def ask(pergunta: PerguntaRequest):

    # Verifica se o índice RAG existe
    if not RAG_INDEX_PATH.exists():
        raise HTTPException(
            status_code=503,
            detail=(
                "Índice RAG não encontrado. "
                "Execute: python -m rag.indexer"
            ),
        )

    try:
        # Executa o pipeline RAG
        resposta, trechos = responder_com_rag(
            pergunta.pergunta,
            k=pergunta.k,
        )

        # Monta as fontes para o frontend
        fontes = []

        for trecho in trechos:
            fontes.append(
                {
                    "titulo": trecho["titulo"],
                    "fonte": trecho["fonte"],
                    "url": trecho["url"],
                    "similaridade": round(
                        trecho["similaridade"],
                        4,
                    ),
                }
            )

        return {
            "resposta": resposta,
            "fontes": fontes,
        }

    except Exception as erro:

        print("Erro no /ask:")
        print(erro)

        raise HTTPException(
            status_code=500,
            detail="Erro ao processar a pergunta.",
        )


# ============================================================
# STATUS DO RAG
# ============================================================

@app.get("/rag/status")
def rag_status():

    if not RAG_INDEX_PATH.exists():
        return {
            "status": "offline",
            "indice_existe": False,
            "mensagem": (
                "Índice RAG não encontrado. "
                "Execute: python -m rag.indexer"
            ),
        }

    return {
        "status": "online",
        "indice_existe": True,
        "arquivo": str(RAG_INDEX_PATH),
    }


# ============================================================
# HOME DA API
# ============================================================

@app.get("/")
def home():

    return {
        "status": "online",
        "message": "DA.IA API funcionando!",
        "rag": RAG_INDEX_PATH.exists(),
    }


# ============================================================
# AULA.HTML
# ============================================================

@app.get("/aula.html")
def aula():

    arquivo = (
        Path(__file__).resolve().parent.parent
        / "aula.html"
    )

    if not arquivo.exists():
        raise HTTPException(
            status_code=404,
            detail="aula.html não encontrado.",
        )

    return HTMLResponse(
        arquivo.read_text(encoding="utf-8")
    )