package br.com.tarefas.repositories;

import br.com.tarefas.entity.Convidado;
import br.com.tarefas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
    List<Convidado> findByUsuario(long usuarioId);
}
