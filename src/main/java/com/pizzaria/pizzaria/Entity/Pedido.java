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
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "obs")
    private String obs;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;


}