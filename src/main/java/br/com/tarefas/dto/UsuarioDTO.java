package br.com.tarefas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private long id;
    private String nome;
    private String email;
    private String telefone;
}
