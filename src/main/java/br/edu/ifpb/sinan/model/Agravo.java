package br.edu.ifpb.sinan.model;

import br.edu.ifpb.sinan.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_agravos")
public class Agravo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "O tipo de notificação não pode ser nulo.")
    private TipoNotificacaoEnum tipoNotificacao;

    @NotBlank (message = "O agravo não pode estar em branco.")
    private String agravo;

    @NotNull(message = "A data da notificação não pode ser nula.")
    @PastOrPresent(message = "A data da notificação não pode ser futura.")
    private LocalDate dataNotificacao;

    @NotNull (message = "O UF da notificação não pode ser vazio.")
    @Size(min = 2, max = 2, message = "O UF da notificação deve ter exatamente 2 caracteres.")
    private UfEnum ufNotificacao;


    private LocalDate dataPrimSintoma;
    @NotBlank(message = "O nome do paciente não pode estar em branco.")
    @Size(min = 2, max = 100, message = "O nome do paciente deve ter entre 2 e 100 caracteres.")
    private String nomePaciente;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    @PastOrPresent(message = "A data de nascimento não pode ser futura.")
    private LocalDate dataNascimento;

    @NotNull (message = "O sexo do paciente não pode ser nulo.")
    private SexoEnum sexo;

    private GestanteEnum gestante;
    private String nomeMae;

    @NotNull (message = "O município de residência não pode ser vazio.")
    @Size(min = 2, max = 2, message = "O UF de residência deve ter exatamente 2 caracteres.")
    private UfEnum ufResidencia;
    private String municipioResidencia;

}
