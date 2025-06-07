package br.com.tarefas.service;

import br.com.tarefas.dto.TarefaDTO;
import br.com.tarefas.entity.Tarefa;
import br.com.tarefas.exception.TarefaNotFound;
import br.com.tarefas.repositories.TarefaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TarefaDTO recuperarTarefa(long id){
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        return modelMapper.map(tarefaOp.orElseThrow(
                () -> new TarefaNotFound("Tarefa com ID "+id+" não encontrada")),
                TarefaDTO.class
        );
    }

    public TarefaDTO adicionarTarefa(TarefaDTO tarefa){
        Tarefa tarefaEntity = modelMapper.map(tarefa, Tarefa.class);
        return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
    }

    public List<TarefaDTO> recuperaTarefas(){
        return modelMapper.map(tarefaRepository.findAll(), new TypeToken<List<TarefaDTO>>() {}.getType());
    }

    public TarefaDTO atualizaTarefa(long id, TarefaDTO tarefa){
        Tarefa tarefaEntity = modelMapper.map(tarefa, Tarefa.class);
        Optional<Tarefa> tarefaOp = tarefaRepository.findById(id);
        if (tarefaOp.isPresent()){
            tarefaEntity.setId(id);
            return modelMapper.map(tarefaRepository.save(tarefaEntity), TarefaDTO.class);
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
