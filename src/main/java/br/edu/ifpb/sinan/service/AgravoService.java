package br.edu.ifpb.sinan.service;

import br.edu.ifpb.sinan.enums.GestanteEnum;
import br.edu.ifpb.sinan.enums.SexoEnum;
import br.edu.ifpb.sinan.model.Agravo;
import br.edu.ifpb.sinan.repository.AgravoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

    public List<Agravo> listarAgravos(Boolean duplicadas) {
        List<Agravo> todos = agravoRepository.findAll();

        if (Boolean.TRUE.equals(duplicadas)) {
            return todos.stream()
                    .filter(a1 -> todos.stream().anyMatch(a2 -> ehDuplicado(a1, a2)))
                    .toList();
        }

        return todos;
    }

    public Agravo salvarAgravo(Agravo agravo) {
        validarEAjustarRegras(agravo);
        return agravoRepository.save(agravo);
    }

    public Agravo getAgravoById(Long id) {
        return agravoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agravo não encontrado com o ID: " + id));
    }

    public void deleteAgravo(Long id) {
        Agravo agravo = getAgravoById(id);
        agravoRepository.delete(agravo);
    }

    public Agravo atualizarAgravo(Long id, Agravo novosDados) {
        if (!agravoRepository.existsById(id)) {
            throw new IllegalArgumentException("Agravo não encontrado com o ID: " + id);
        }
        novosDados.setId(id);
        validarEAjustarRegras(novosDados);
        return agravoRepository.save(novosDados);
    }


    private void validarEAjustarRegras(Agravo agravo) {
        //Validação Idade
        if (agravo.getDataNascimento() == null && agravo.getIdade() == null) {
            throw new IllegalArgumentException("A Idade é obrigatória quando a Data de Nascimento não for informada.");
        }
        if (agravo.getDataNascimento() != null) {
            agravo.setIdade(Period.between(agravo.getDataNascimento(), LocalDate.now()).getYears());
        }

        //Regra de Gestante
        if (agravo.getSexo() != SexoEnum.FEMININO) {
            agravo.setGestante(GestanteEnum.NAO_SE_APLICA);
        } else {
            if (agravo.getIdade() != null && agravo.getIdade() < 7) {
                agravo.setGestante(GestanteEnum.NAO_SE_APLICA);
            } else if (agravo.getGestante() == null) {
                throw new IllegalArgumentException("Campo 'gestante' é obrigatório para mulheres com 7 anos ou mais.");
            }
        }

        // Regra de Residência
        if (agravo.getPaisResidencia() != null && agravo.getPaisResidencia().trim().equalsIgnoreCase("Brasil")) {
            if (agravo.getUfResidencia() == null) {
                throw new IllegalArgumentException("A UF de residência é obrigatória para residentes no Brasil.");
            }
            if (agravo.getMunicipioResidencia() == null || agravo.getMunicipioResidencia().isBlank()) {
                throw new IllegalArgumentException("O município de residência é obrigatório quando a UF de residência é preenchida.");
            }
        }

        //Regra de Encerramento e Classificação Final
        if (agravo.getDataEncerramento() != null && (agravo.getClassificacaoFinal() == null || agravo.getClassificacaoFinal().isBlank())) {
            throw new IllegalArgumentException("A classificação final é obrigatória quando a data de encerramento é informada.");
        }
    }


    private boolean ehDuplicado(Agravo a1, Agravo a2) {
        if (a1.getId() != null && a1.getId().equals(a2.getId())) {
            return false;
        }

        if (a1.getNomeMae() == null || a1.getNomeMae().isBlank() ||
            a2.getNomeMae() == null || a2.getNomeMae().isBlank()) {
            return false;
        }

        boolean mesmoAgravo = a1.getAgravo().trim().equalsIgnoreCase(a2.getAgravo().trim());
        boolean mesmoPaciente = a1.getNomePaciente().trim().equalsIgnoreCase(a2.getNomePaciente().trim());
        boolean mesmaMae = a1.getNomeMae().trim().equalsIgnoreCase(a2.getNomeMae().trim());

        boolean mesmaDataNasc = (a1.getDataNascimento() != null && a2.getDataNascimento() != null)
                ? a1.getDataNascimento().equals(a2.getDataNascimento())
                : true;

        long diferencaDias = Math.abs(ChronoUnit.DAYS.between(a1.getDataNotificacao(), a2.getDataNotificacao()));

        return mesmoAgravo && mesmoPaciente && mesmaMae && mesmaDataNasc && (diferencaDias <= 3);
    }
}