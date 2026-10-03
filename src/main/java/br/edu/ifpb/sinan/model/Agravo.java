package br.edu.ifpb.sinan.model;

import br.edu.ifpb.sinan.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_agravos")
public class Agravo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //DADOS GERAIS
    @NotNull(message = "O número da notificação é obrigatório.")
    private Long numeroNotificacao;

    @NotNull(message = "O tipo de notificação é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoNotificacaoEnum tipoNotificacao;

    @NotBlank(message = "O agravo/doença é obrigatório.")
    private String agravo;

    @NotNull(message = "A data de notificação é obrigatória.")
    @PastOrPresent(message = "A data de notificação não pode ser futura.")
    private LocalDate dataNotificacao;

    @NotNull(message = "A UF de notificação é obrigatória.")
    @Enumerated(EnumType.STRING)
    private UfEnum ufNotificacao;

    @NotBlank(message = "O município de notificação é obrigatório.")
    private String municipioNotificacao;

    @NotBlank(message = "A unidade notificadora é obrigatória.")
    private String unidadeNotificadora;

    //DADOS PESSOAIS
    @NotNull(message = "A data dos primeiros sintomas é obrigatória.")
    private LocalDate dataPrimeirosSintomas;

    @NotBlank(message = "O nome do paciente é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome do paciente deve ter entre 2 e 100 caracteres.")
    private String nomePaciente;

    @PastOrPresent(message = "A data de nascimento não pode ser futura.")
    private LocalDate dataNascimento;

    private Integer idade; // Calculado ou preenchido caso dataNascimento seja nula

    @NotNull(message = "O sexo do paciente é obrigatório.")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Enumerated(EnumType.STRING)
    private GestanteEnum gestante; // Validação condicional no Service (RN02)

    @Enumerated(EnumType.STRING)
    private RacaCorEnum racaCor;

    @Enumerated(EnumType.STRING)
    private EscolaridadeEnum escolaridade;

    private String cartaoSus;
    private String nomeMae; // Utilizado na verificação de duplicidades (RN01)

    //DADOS DE RESIDÊNCIA
    @NotBlank(message = "O país de residência é obrigatório.")
    private String paisResidencia;

    @Enumerated(EnumType.STRING)
    private UfEnum ufResidencia; // Obrigatório se paisResidencia == "Brasil"

    private String municipioResidencia; // Obrigatório se ufResidencia informada
    private String distritoResidencia;
    private String bairroResidencia;
    private String logradouroResidencia;
    private String numeroResidencia;
    private String complementoResidencia;
    private String geocampo1;
    private String geocampo2;
    private String pontoReferencia;
    private String cep;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private ZonaEnum zonaResidencia;

    //DADOS DE INVESTIGAÇÃO E CONCLUSÃO
    @NotNull(message = "A data de investigação é obrigatória.")
    private LocalDate dataInvestigacao;

    private String classificacaoFinal;
    private String criterioConfirmacao;

    @Enumerated(EnumType.STRING)
    private AutoctoneEnum autoctoneMunicipio;

    @Enumerated(EnumType.STRING)
    private UfEnum ufInfeccao;

    private String paisInfeccao;
    private String municipioInfeccao;
    private String distritoInfeccao;
    private String bairroInfeccao;

    @Enumerated(EnumType.STRING)
    private RelacionadoTrabalhoEnum relacionadoTrabalho;

    @Enumerated(EnumType.STRING)
    private EvolucaoCasoEnum evolucaoCaso;

    private LocalDate dataObito;
    private LocalDate dataEncerramento;

    //RESPONSÁVEL PELA INVESTIGAÇÃO
    private String unidadeInvestigadora;
    private String codigoUnidadeInvestigadora;
    private String nomeInvestigador;
    private String funcaoInvestigador;
}