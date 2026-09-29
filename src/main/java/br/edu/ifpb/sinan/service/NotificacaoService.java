package br.edu.ifpb.sinan.service;

import br.edu.ifpb.sinan.model.Notificacao;
import br.edu.ifpb.sinan.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public List<Notificacao> getAllNotificacoes() {
        return notificacaoRepository.findAll();
    }

    public Notificacao salvarNotificacao(Notificacao notificacao){
        return notificacaoRepository.save(notificacao)
    }
}
