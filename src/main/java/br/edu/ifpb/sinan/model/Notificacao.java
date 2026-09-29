package br.edu.ifpb.sinan.model;

import br.edu.ifpb.sinan.enums.GestanteEnum;
import br.edu.ifpb.sinan.enums.SexoEnum;
import br.edu.ifpb.sinan.enums.TipoNotificacaoEnum;
import br.edu.ifpb.sinan.enums.UfEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_notificacao")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TipoNotificacaoEnum tipoNotificacao;
    private String agravo;
    private LocalDate dataNotificacao;
    private UfEnum ufNotificacao;
    private String municipioNotificacao;
    private LocalDate dataPrimSintoma;
    private String nomePaciente;
    private LocalDate dataNascimento;
    private SexoEnum sexo;
    private GestanteEnum gestante;
    private String nomeMae;
    private UfEnum ufResidencia;
    private String municipioResidencia;

}
