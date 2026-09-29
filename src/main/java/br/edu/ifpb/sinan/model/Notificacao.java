package br.edu.ifpb.sinan.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
