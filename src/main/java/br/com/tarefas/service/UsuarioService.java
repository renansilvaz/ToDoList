package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.dto.UsuarioDTO;
import br.com.tarefas.entity.Convidado;
import br.com.tarefas.entity.Usuario;
import br.com.tarefas.exception.UsuarioNotFound;
import br.com.tarefas.mapper.TarefaMapper;
import br.com.tarefas.mapper.UsuarioMapper;
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
    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioDTO adicionarUsuario(UsuarioDTO dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(long id, UsuarioDTO atualizado){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFound("Usuario não encontrado"));

        usuario.setNome(atualizado.getNome());
        usuario.setEmail(atualizado.getEmail());
        usuario.setTelefone(atualizado.getTelefone());
        return usuarioMapper.toDTO(usuario);
    }

    public List<TarefaDTO> buscarTarefasConvidado(long usuarioId){
        List<Convidado> convites = convidadoRepository.findByUsuario(usuarioId);
        return convites.stream()
                .map(Convidado::getTarefa)
                .map(tarefaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
