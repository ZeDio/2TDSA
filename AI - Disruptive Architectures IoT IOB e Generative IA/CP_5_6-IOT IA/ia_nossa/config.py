import os
from google import genai
from pathlib import Path
from dotenv import load_dotenv

# Carrega o arquivo .env (se existir) para dentro das variáveis de ambiente.
# Procura um .env na pasta ia_nossa/ (mesma pasta deste arquivo).
load_dotenv(Path(__file__).resolve().parent / ".env")

# Chave de API (substitua pela sua ou use variáveis de ambiente)
API_KEY = os.getenv("GEMINI_API_KEY")

if not API_KEY:
    raise RuntimeError(
        "GEMINI_API_KEY não encontrada. Crie um arquivo .env dentro da "
        "pasta ia_nossa/ com a linha: GEMINI_API_KEY=sua_chave_aqui "
        "(ou defina a variável de ambiente GEMINI_API_KEY antes de rodar)."
    )

# Inicialização do cliente Google GenAI
client = genai.Client(api_key=API_KEY)

# Modelo usado para gerar as respostas.
MODEL_NAME = "gemini-3.1-flash-lite"

# Modelo usado pelo RAG para transformar textos em vetores.
EMBEDDING_MODEL = "gemini-embedding-2"

# Materiais ficam fora de ia_nossa, na pasta material/ do projeto.
BASE_DIR = Path(__file__).resolve().parent.parent
MATERIAL_DIR = BASE_DIR / "material"

# Índice persistente criado pelo RAG.
RAG_DIR = Path(__file__).resolve().parent / "rag"
RAG_DATA_DIR = RAG_DIR / "data"
RAG_INDEX_PATH = RAG_DATA_DIR / "index.json"

# URL base do site publicado, usada nas fontes retornadas.
SITE_BASE_URL = "https://arnaldojr.github.io/DisruptiveArchitectures"
