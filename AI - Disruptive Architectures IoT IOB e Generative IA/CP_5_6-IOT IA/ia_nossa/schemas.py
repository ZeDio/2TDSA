from typing import Literal, Optional
from pydantic import BaseModel, Field

class FichaAtendimentoCurso(BaseModel):
    nome_aluno: Optional[str] = Field(
        default=None,
        description="Nome do aluno, caso tenha informado."
    )
    tipo_duvida: Literal[
        "aula",
        "laboratorio",
        "checkpoint",
        "agenda",
        "requisitos",
        "outro"
    ] = Field(
        description="Assunto principal da dúvida do aluno."
    )
    dentro_do_escopo: bool = Field(
        description="Indica se a solicitação está dentro do escopo da disciplina."
    )
    resposta: str = Field(
        description="Resumo da resposta dada ao aluno."
    )
