package br.com.tarefas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "convidado")
@Table(name = "convidado")
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "tarefaId")
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;
}
