package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.exception.TarefaNotFound;
import br.com.tarefas.mapper.TarefaMapper;
import br.com.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public TarefaDTO recuperarTarefa(long id){
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);

        Tarefa tarefa = tarefaOp.orElseThrow(() -> new TarefaNotFound("Tarefa com o ID "+ id+ "não encontrada"));
        return tarefaMapper.toDTO(tarefa);
    }

    public TarefaDTO adicionarTarefa(TarefaDTO tarefa){
        Tarefa tarefaEntity = tarefaMapper.toEntity(tarefa);
        return tarefaMapper.toDTO(tarefaRepository.save(tarefaEntity));
    }

    public List<TarefaDTO> recuperaTarefas(){
        return tarefaMapper.toDTOList(tarefaRepository.findAll());
    }

    public TarefaDTO atualizaTarefa(long id, TarefaDTO tarefa){
        Tarefa tarefaEntity = tarefaMapper.toEntity(tarefa);
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        if (tarefaOp.isPresent()){
            tarefaEntity.setId(id);
            return tarefaMapper.toDTO(tarefaRepository.save(tarefaEntity));
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
