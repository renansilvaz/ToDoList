package br.com.tarefas.service;

import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.exception.TarefaNotFound;
import br.com.tarefas.repositories.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa recuperarTarefa(long id){
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        return tarefaOp.orElseThrow(() -> new TarefaNotFound("Tarefa com ID "+id+" não encontrada"));
    }

    public Tarefa adicionarTarefa(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> recuperaTarefas(){
        return tarefaRepository.findAll();
    }

    public Tarefa atualizaTarefa(long id, Tarefa tarefa){
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        if (tarefaOp.isPresent()){
            tarefa.setId(id);
            return tarefaRepository.save(tarefa);
        }
        throw new TarefaNotFound("Tarefa com ID "+id+" não encontrada :(");
    }

    public void deletarTarefa(long id){
        if(!tarefaRepository.existsById(id)){
            throw new TarefaNotFound("Tarefa com ID "+id+" não encontrada :(");
        }
        tarefaRepository.deleteById(id);
    }
}
