# 🚀 Disruptive Architectures — IA, IoT & Generative AI

Projeto acadêmico desenvolvido para a disciplina **Disruptive Architectures: IA e IoT**, com foco na aplicação prática de **Inteligência Artificial Generativa, RAG (Retrieval-Augmented Generation), APIs e bases de conhecimento**.

O projeto apresenta uma plataforma web para disponibilização dos conteúdos da disciplina e conta com a **DA.IA**, uma assistente virtual capaz de responder perguntas utilizando os materiais do próprio curso como base de conhecimento.

---

## 🌐 Projeto Online

### Front-end
🔗 https://ultima-cp-disruptive-architectures.vercel.app/index.html

### Back-end / API
🔗 https://twotdsa.onrender.com

### Status do RAG
🔗 https://twotdsa.onrender.com/rag/status

---

## 🤖 DA.IA

A **DA.IA** é a assistente de Inteligência Artificial do projeto.

Seu objetivo é facilitar o acesso aos conteúdos da disciplina por meio de perguntas em linguagem natural.

Em vez de procurar manualmente entre diversos arquivos Markdown, o aluno pode perguntar diretamente:

```text
O que é IoT?
```

A DA.IA consulta a base de conhecimento através do sistema RAG e gera uma resposta usando o conteúdo recuperado.

Além da resposta, o sistema disponibiliza as fontes utilizadas, permitindo que o aluno consulte o material original.

---

## 🧠 RAG — Geração Aumentada de Recuperação

O projeto implementa uma arquitetura de **RAG (Retrieval-Augmented Generation)**.

O RAG permite que a Inteligência Artificial use informações recuperadas de uma base de conhecimento antes de gerar a resposta.

### Fluxo

```
Pergunta do aluno
        ↓
Preparação da pergunta
        ↓
Geração do embedding
        ↓
Busca por similaridade
        ↓
Recuperação dos trechos relevantes
        ↓
Construção do contexto
        ↓
Google Gemini
        ↓
Resposta da DA.IA
        ↓
Fontes utilizadas
```

Os conteúdos utilizados como base de conhecimento estão armazenados na pasta:

```
material/
```

Os arquivos são escritos em Markdown e são processados pelo sistema de indexação.

Atualmente, o índice RAG possui **198 chunks**.

O índice está localizado em:

```
ia_nossa/rag/data/index.json
```

---

## 🔎 Recuperação de informações

Para cada pergunta feita pelo usuário, o sistema:

1. Recebe a pergunta.
2. Prepara a pergunta para a tarefa de question answering.
3. Gera um embedding da pergunta.
4. Compara o embedding com os embeddings dos conteúdos.
5. Calcula a similaridade entre os vetores.
6. Seleciona os trechos mais relevantes.
7. Monta o contexto recuperado.
8. Envia o contexto para o modelo Gemini.
9. Gera a resposta.
10. Retorna também as fontes utilizadas.

A recuperação usa **similaridade de cosseno**.

---

## 🛡️ Confiabilidade

A DA.IA possui regras para utilizar as informações recuperadas da base de conhecimento.

O sistema orienta a IA a:

- utilizar informações sustentadas pelo contexto recuperado;
- não inventar informações;
- não preencher lacunas com informações não encontradas no material;
- informar quando uma informação não estiver disponível;
- apresentar as fontes utilizadas na resposta.

Quando a informação não é encontrada, a IA pode informar:

> Não encontrei essa informação no material do curso.

---

## 🏗️ Arquitetura

```
┌─────────────────────────────┐
│          USUÁRIO            │
│           / ALUNO           │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│       FRONT-END             │
│          Vercel             │
│                              │
│ • Conteúdos                 │
│ • Busca                     │
│ • Filtros                   │
│ • Visualização Markdown     │
│ • Chat da DA.IA              │
└──────────────┬──────────────┘
               │
               │ HTTPS
               ▼
┌─────────────────────────────┐
│         BACK-END            │
│           Render            │
│          FastAPI            │
└──────────────┬──────────────┘
               │
       ┌───────┴────────┐
       │                │
       ▼                ▼
┌─────────────┐  ┌──────────────────┐
│  Markdown   │  │       RAG        │
│  /material  │  │                  │
│             │  │ Embeddings       │
└─────────────┘  │ Retrieval        │
                 │ Similaridade     │
                 │ Contexto         │
                 └────────┬─────────┘
                          │
                          ▼
                 ┌──────────────────┐
                 │  Google Gemini   │
                 │ Generative AI    │
                 └──────────────────┘
```

---

## 🔌 API

O back-end foi desenvolvido utilizando **FastAPI**.

### `GET /`
Verifica se a API está funcionando.

```
https://twotdsa.onrender.com/
```

### `GET /api/materials`
Retorna os materiais disponíveis na base.

```
https://twotdsa.onrender.com/api/materials
```

### `GET /api/material`
Retorna o conteúdo de um arquivo Markdown específico.

Exemplo:

```
/api/material?arquivo=aulas/iot/index.md
```

A API realiza o processamento do Markdown e retorna o conteúdo preparado para exibição no front-end.

### `POST /ask`
Endpoint usado pela DA.IA.

Exemplo de requisição:

```json
{
  "pergunta": "O que é IoT?"
}
```

O endpoint executa o pipeline RAG e retorna:

```json
{
  "resposta": "Resposta gerada pela DA.IA...",
  "fontes": []
}
```

As fontes contêm informações sobre os trechos recuperados, como arquivo, título, URL e similaridade.

### `GET /rag/status`
Verifica se o índice RAG está disponível.

```
https://twotdsa.onrender.com/rag/status
```

---

## 📚 Organização dos conteúdos

Os conteúdos da disciplina são armazenados em arquivos Markdown.

As categorias utilizadas pelo projeto incluem:

- IoT
- Inteligência Artificial
- IA generativa
- Pontos de controle
- Agenda
- Materiais

A aplicação lê esses arquivos e disponibiliza seus conteúdos através da API.

---

## 🗂️ Estrutura do projeto

```
CP_5_6-IOT IA/
│
├── material/
│   ├── agenda/
│   ├── aulas/
│   │   ├── IA/
│   │   ├── genAI/
│   │   ├── iot/
│   │   └── checkpoint/
│   └── ...
│
├── ia_nossa/
│   ├── api.py
│   ├── config.py
│   ├── main.py
│   ├── tools.py
│   ├── SYSTEM_PROMPT.py
│   ├── content_api.py
│   ├── schemas.py
│   ├── requirements.txt
│   │
│   └── rag/
│       ├── __init__.py
│       ├── README.md
│       ├── embeddings.py
│       ├── indexer.py
│       ├── retriever.py
│       ├── pipeline.py
│       │
│       └── data/
│           └── index.json
│
├── index.html
├── aula.html
└── ...
```

---

## 🧩 Componentes do RAG

### `embeddings.py`
Responsável pela preparação dos textos e geração dos embeddings.

Principais funções:

- `preparar_pergunta()`
- `preparar_documento()`
- `gerar_embedding()`

### `indexer.py`
Responsável pela criação da base vetorial.

Suas principais funções são:

- localizar os arquivos Markdown;
- dividir os conteúdos em chunks;
- gerar embeddings;
- armazenar os embeddings;
- criar o índice;
- permitir a continuidade do processo de indexação.

O resultado é armazenado em:

```
rag/data/index.json
```

### `retriever.py`
Responsável pela recuperação dos conteúdos mais relevantes.

Fluxo:

```
Pergunta
   ↓
Embedding
   ↓
Comparação
   ↓
Similaridade de cosseno
   ↓
Ordenação
   ↓
Top-K resultados
```

### `pipeline.py`
Responsável por integrar o retrieval com a generation.

O pipeline:

```
Pergunta
   ↓
Retriever
   ↓
Trechos relevantes
   ↓
Contexto
   ↓
Gemini
   ↓
Resposta
```

---

## 🛠️ Tecnologias utilizadas

### Front-end
- HTML5
- CSS3
- JavaScript

### Back-end
- Python
- FastAPI
- Uvicorn
- Pydantic
- Mistune

### Inteligência Artificial
- Google Gemini
- IA generativa
- Embeddings
- Similaridade de cosseno

### Dados
- Markdown
- JSON
- Embeddings

### Implantação
- Vercel — Front-end
- Render — Back-end

---

## ▶️ Executando localmente

### 1. Acessar a pasta da API
```bash
cd ia_nossa
```

### 2. Criar ambiente virtual

**Windows:**
```bash
python -m venv .venv
.venv\Scripts\activate
```

**Linux/macOS:**
```bash
python -m venv .venv
source .venv/bin/activate
```

### 3. Instalar dependências
```bash
pip install -r requirements.txt
```

### 4. Configurar chave de API

Criar o arquivo:

```
ia_nossa/.env
```

Adicionar:

```
GEMINI_API_KEY=SUA_CHAVE_AQUI
```

⚠️ **Nunca publique sua chave de API no GitHub.**

### 5. Executar a API

Dentro da pasta `ia_nossa`:

```bash
python -m uvicorn api:app --reload
```

A API estará disponível em:

```
http://127.0.0.1:8000
```

---

## 🧪 Testando o RAG

### Testando embeddings
```bash
python -m rag.embeddings
```

### Testar recuperação
```bash
python -m rag.retriever
```

### Testar o pipeline completo
```bash
python -m rag.pipeline
```

Exemplo:

```
Pergunta: O que é IoT?
```

O sistema irá:

```
Pergunta
   ↓
Embedding
   ↓
Busca
   ↓
Trechos relevantes
   ↓
Contexto
   ↓
Gemini
   ↓
Resposta
```

---

## 🔄 Atualizando a base de conhecimento

Quando novos arquivos Markdown forem adicionados à pasta:

```
material/
```

o índice RAG pode ser atualizado executando:

```bash
python -m rag.indexer
```

O índice atualizado fica em:

```
ia_nossa/rag/data/index.json
```

Esse arquivo precisa estar disponível junto ao projeto para que a API consiga utilizar a base de conhecimento.

---

## 🚀 Implantação

### Front-end

O front-end está hospedado na **Vercel**:

```
https://ultima-cp-disruptive-architectures.vercel.app/index.html
```

O aplicativo usa a API de produção:

```js
const API_BASE = 'https://twotdsa.onrender.com';
```

### Back-end

A API está hospedada no **Render**:

```
https://twotdsa.onrender.com
```

O servidor utiliza FastAPI com Uvicorn.

A variável de ambiente utilizada para acessar o Gemini é:

```
GEMINI_API_KEY
```

A chave deve ser configurada nas variáveis de ambiente do serviço de hospedagem.

---

## 🔐 Segurança

O projeto utiliza variável de ambiente para armazenar a chave da API.

```
GEMINI_API_KEY=SUA_CHAVE
```

O arquivo `.env` não deve ser enviado para o GitHub.

Também é importante manter o comportamento da DA.IA restrito às informações disponíveis na base de conhecimento e aos recursos definidos pelo projeto.

---

## 🎯 Objetivos do projeto

O projeto tem como objetivos demonstrar:

- aplicação de Inteligência Artificial Generativa;
- implementação prática de RAG;
- geração e utilização de embeddings;
- recuperação semântica de informações;
- integração entre front-end e back-end;
- desenvolvimento de API REST;
- utilização de Markdown como base de conhecimento;
- geração de respostas contextualizadas;
- apresentação das fontes utilizadas pela IA;
- deploy de uma aplicação completa.

---

## 🔄 Fluxo completo da aplicação

```
                  ALUNO
                    │
                    ▼
              ┌───────────┐
              │ Front-end │
              └─────┬─────┘
                    │
                    ▼
                 POST /ask
                    │
                    ▼
              ┌───────────┐
              │ Retriever │
              └─────┬─────┘
                    │
                    ▼
               Embeddings
                    │
                    ▼
            Similaridade Cosseno
                    │
                    ▼
              Top-K Chunks
                    │
                    ▼
             Contexto RAG
                    │
                    ▼
              Google Gemini
                    │
                    ▼
               Resposta
                    │
                    ▼
                 Fontes
                    │
                    ▼
              ┌───────────┐
              │ Front-end │
              └───────────┘
```

---

## 📌 Links do projeto

| Recurso | Link |
|---|---|
| 🌐 Front-end | https://ultima-cp-disruptive-architectures.vercel.app/index.html |
| ⚙️ API | https://twotdsa.onrender.com |
| 🧠 Status RAG | https://twotdsa.onrender.com/rag/status |
| 💻 GitHub | https://github.com/ZeDio |

---

## 👨‍💻 Projeto acadêmico

Desenvolvido por:

**José Diogo — ZeDio** 

GitHub: https://github.com/ZeDio

**Andre Colombo — AndreColombo** 

GitHub: https://github.com/AndreColombo

**Vitor Dalmagro — VitorDalmagro** 

GitHub: https://github.com/VitorDalmagro

**Arthur — ArthurCPV** 

GitHub: https://github.com/ArthurCPV

---

## 📚 Disciplina

**Arquiteturas disruptivas: IA e IoT**

Projeto desenvolvido para fins acadêmicos, explorando a integração entre Inteligência Artificial Generativa, RAG, APIs e aplicações web.
