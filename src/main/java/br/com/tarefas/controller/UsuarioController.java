package br.com.tarefas.controller;

import br.com.tarefas.dto.UsuarioDTO;
import br.com.tarefas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody UsuarioDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.adicionarUsuario(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable long id, @Valid @RequestBody UsuarioDTO dto){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dto));
    }

    @GetMapping("/{usuarioId}/convite")
    public ResponseEntity<UsuarioDTO> recuperaConvites(@PathVariable long usuarioId){
        return ResponseEntity.ok((UsuarioDTO) usuarioService.buscarTarefasConvidado(usuarioId));
    }
}
