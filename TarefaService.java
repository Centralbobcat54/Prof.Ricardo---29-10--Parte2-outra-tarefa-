package br.com.curso.listadetarefas.api.tarefa;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }
    public List<Tarefa> listarTodas() { return tarefaRepository.findAll(); }
    public Tarefa criarTarefa(Tarefa tarefa) { return tarefaRepository.save(tarefa); }
    public Optional<Tarefa> atualizarTarefa(Long id, Tarefa t) {
        return tarefaRepository.findById(id).map(e -> {
            e.setTitulo(t.getTitulo());
            e.setDescricao(t.getDescricao());
            e.setConcluida(t.isConcluida());
            return tarefaRepository.save(e);
        });
    }
    public boolean deletarTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
