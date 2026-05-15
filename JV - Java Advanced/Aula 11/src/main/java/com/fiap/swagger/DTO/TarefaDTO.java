package com.fiap.swagger.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "TarefaId",
        description = "Dados para criar ou atualizar uma tarefa"
)
public class TarefaDTO {
    @NotBlank(message = "O titulo da tarefa é obrigatorio")
    @Size(min = 3, max = 100, message = "O titulo deve ter entre 3 e 100 caracteres")
    @Schema(
            description = "Titulo da Tarefa",
            example = "Fazer compras do mes",
            required = true,
            minLength = 3,
            maxLength = 100
    )
    private String titulo;

    @Size(max = 500, message = "A descrição ter no maximo 500 caracteres")
    @Schema(
            description = "Descrição detalhada da tarefa",
            example = "Comprar arroz, feijão, macarrão e carne para a semana",
            maxLength = 500,
            required = false
    )
    private String descricao;

    @Schema(
            description = "Status da tarefa",
            example = "false",
            defaultValue = "false",
            required = false
    )
    private boolean concluida = false;
}