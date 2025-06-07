package br.com.tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaDTO {

    private long id;
    @NotBlank(message = "Titulo é obrigatório")
    private String titulo;
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    @NotBlank(message = "Local é obrigatório")
    private String local;
    @NotNull(message = "Data e hora são obrigatórios")
    private LocalDateTime dataHora;
}
