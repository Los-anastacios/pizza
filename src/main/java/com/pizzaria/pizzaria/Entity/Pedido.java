package com.pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "obs")
    private String obs;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name ="estado")
    private Estado estado;


}