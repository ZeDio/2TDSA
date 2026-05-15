package com.fiap.swagger.Controller;

import com.fiap.swagger.Repository.TarefaRepository;
import com.fiap.swagger.model.Tarefas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    @Operation(
            summary = "Listar todas as tarefas",
            description = "Retorna uma lista completa de todas as tarefas cadastradas no sistema."
    )
    @ApiResponse(
            responseCode =  "200",
            description = "Lista de tarefas retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tarefas.class))
    )
    public ResponseEntity<List<Tarefas>> listarTodas(){
        List<Tarefas> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Retorna uma tarefa especifica baseada no seu identificaro unico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "tarefa não encontrada")
    })
    public ResponseEntity<Tarefas> buscarPorId(
    )
}