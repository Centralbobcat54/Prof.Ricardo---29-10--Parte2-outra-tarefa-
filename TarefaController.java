package br.com.curso.listadetarefas.api.tarefa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    @GetMapping
    public List<Tarefa> listar() { return tarefaService.listarTodas(); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criar(@RequestBody Tarefa t) { return tarefaService.criarTarefa(t); }
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa t) {
        return tarefaService.atualizarTarefa(id, t)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return tarefaService.deletarTarefa(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
