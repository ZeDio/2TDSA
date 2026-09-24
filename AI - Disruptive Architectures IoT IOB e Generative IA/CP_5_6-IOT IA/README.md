# Bem vindo disciplina de Disruptive Architectures: IA e IoT

Olá pessoal, bem vindos!! Neste repositório você irá encontrar os conteúdos ministrados em sala de aula assim como dicas, exemplos e laboratórios. 

## Para acompanhar os roteiros práticos 

Acesse o site:

- [website: https://arnaldojr.github.io/DisruptiveArchitectures/](https://arnaldojr.github.io/DisruptiveArchitectures/)


## Como clonar o repositório

``` bash
$ # no terminal digite
$ git clone https://github.com/arnaldojr/DisruptiveArchitectures/

```
## Site principal + Markdown

O projeto agora possui uma página principal (`index.html`) que lê automaticamente os arquivos `.md` dentro de `material/` através da API.

- Os conteúdos aparecem em cards com título e prévia.
- Ao clicar em um card, o Markdown é convertido para HTML em `aula.html`.
- Imagens e links relativos dos Markdown são ajustados automaticamente.
- A agenda também é renderizada a partir de `material/agenda/agenda.md`.
- A DA.IA continua disponível pelo botão de chat da página principal.

### Executar

Entre na pasta da API:

```bash
cd ia_nossa
pip install -r requirements.txt
python -m uvicorn api:app --reload
```

Depois acesse:

```text
http://127.0.0.1:8000/
```
