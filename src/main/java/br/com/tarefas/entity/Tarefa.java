package br.com.tarefas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String local;
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "criadorId")
    private Usuario criador;

    @OneToMany(mappedBy = "tarefa")
    private List<Convidado> convidados;
}
