package br.edu.ifpb.sinan.service;

import br.edu.ifpb.sinan.model.Agravo;
import br.edu.ifpb.sinan.repository.AgravoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgravoService {

    private final AgravoRepository notificacaoRepository;

    public AgravoService(AgravoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public List<Agravo> getAllNotificacoes() {
        return notificacaoRepository.findAll();
    }

    public Agravo salvarNotificacao(Agravo notificacao){
        return notificacaoRepository.save(notificacao);
    }

    public Agravo getNotificacaoById(Long id) {
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada com o ID: " + id));
    }

    public void deleteNotificacao(Long id) {
        Agravo notificacao = getNotificacaoById(id);
        notificacaoRepository.delete(notificacao);
    }

    public Agravo atualizarNotificacao(Long id, Agravo novosDados) {
        if (notificacaoRepository.existsById(id)) {
            novosDados.setId(id);
            return notificacaoRepository.save(novosDados);
        }
        return null;
    }
}
