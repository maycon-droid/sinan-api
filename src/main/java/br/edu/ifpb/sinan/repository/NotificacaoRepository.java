package br.edu.ifpb.sinan.repository;

import br.edu.ifpb.sinan.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
