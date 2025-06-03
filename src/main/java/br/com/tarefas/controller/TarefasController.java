package br.com.tarefas.controller;

import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.repositories.TarefaRepository;
import br.com.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> recuperarTarefa(@PathVariable long id){
        return ResponseEntity.ok(tarefaService.recuperarTarefa(id));
    }

    @PostMapping
    public ResponseEntity<?> adicionarTarefa(@RequestBody Tarefa tarefa){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.adicionarTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> recuperaTarefas(){
        return ResponseEntity.ok(tarefaService.recuperaTarefas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizaTarefa(@PathVariable long id, @RequestBody Tarefa tarefa){
        return ResponseEntity.ok(tarefaService.atualizaTarefa(id, tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefa> deletarTarefa(@PathVariable long id){
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
