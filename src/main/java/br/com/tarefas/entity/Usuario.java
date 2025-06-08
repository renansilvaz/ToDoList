package br.com.tarefas.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "usuario")
    private List<Convidado> convites;

    @OneToMany(mappedBy = "criador")
    private List<Tarefa> tarefasCriadas;
}
