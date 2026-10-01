package br.edu.ifpb.sinan.service;

import br.edu.ifpb.sinan.model.Agravo;
import br.edu.ifpb.sinan.repository.AgravoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgravoService {

    private final AgravoRepository agravoRepository;

    public AgravoService(AgravoRepository agravoRepository) {
        this.agravoRepository = agravoRepository;
    }

    public List<Agravo> getAllAgravos() {
        return agravoRepository.findAll();
    }

    public Agravo salvarAgravo(Agravo agravo){
        return agravoRepository.save(agravo);
    }

    public Agravo getAgravoById(Long id) {
        return agravoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agravo não encontrado com o ID: " + id));
    }

    public void deleteAgravo(Long id) {
        Agravo agravo = getAgravoById(id);
        agravoRepository.delete(agravo);
    }

    public Agravo atualizarAgravo(Long id, Agravo novosDados) {
        if (agravoRepository.existsById(id)) {
            novosDados.setId(id);
            return agravoRepository.save(novosDados);
        }
        return null;
    }
}
