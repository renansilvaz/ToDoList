package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Convidado;
import br.com.tarefas.entity.Usuario;
import br.com.tarefas.mapper.TarefaMapper;
import br.com.tarefas.repositories.ConvidadoRepository;
import br.com.tarefas.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ConvidadoRepository convidadoRepository;
    @Autowired
    private TarefaMapper tarefaMapper;

    public Usuario adicionarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizarUsuario(long id, Usuario usuarioAtualizado){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setTelefone(usuarioAtualizado.getTelefone());
        return usuario;
    }

    public List<TarefaDTO> buscarTarefasConvidado(long usuarioId){
        List<Convidado> convites = convidadoRepository.findByUsuario(usuarioId);
        return convites.stream()
                .map(Convidado::getTarefa)
                .map(tarefaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
