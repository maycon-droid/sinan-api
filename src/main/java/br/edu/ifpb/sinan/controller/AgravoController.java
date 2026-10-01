package br.edu.ifpb.sinan.controller;

import org.springframework.web.bind.annotation.*;
import br.edu.ifpb.sinan.model.Agravo;
import br.edu.ifpb.sinan.service.AgravoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/notificacoes")
public class AgravoController {
    private final AgravoService notificacaoService;

    public AgravoController(AgravoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Agravo>> getAllNotificacoes() {
        List<Agravo> notificacoes = notificacaoService.getAllNotificacoes();
        return ResponseEntity.ok(notificacoes);
    }

    @PostMapping 
    public ResponseEntity<Agravo> salvarNotificacao(@Valid @RequestBody Agravo notificacao) {
        Agravo savedNotificacao = notificacaoService.salvarNotificacao(notificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotificacao);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Agravo> getNotificacaoById(@PathVariable Long id) {
        Agravo notificacao = notificacaoService.getNotificacaoById(id);
        if(notificacao != null) {
            return ResponseEntity.ok(notificacao);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agravo> atualizarNotificacao(@PathVariable Long id,@Valid @RequestBody Agravo novosDados) {
        Agravo notificacaoAtualizada = notificacaoService.atualizarNotificacao(id, novosDados);
        if(notificacaoAtualizada != null) {
            return ResponseEntity.ok(notificacaoAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacao(@Valid @PathVariable Long id) {
        Agravo notificacao = notificacaoService.getNotificacaoById(id);
        if(notificacao == null) {
            return ResponseEntity.notFound().build();
        }
        notificacaoService.deleteNotificacao(id);
        return ResponseEntity.noContent().build();
    }
}
