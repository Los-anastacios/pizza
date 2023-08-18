package com.pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Pedido idPedido;

    private String tamanho;

    private Boolean entrega;

    @ManyToMany
    @JoinTable(name = "itemSabor", joinColumns = @JoinColumn(name = "idSabor"), inverseJoinColumns = @JoinColumn(name = "idItem"))
    private List<Sabor> sabor = new ArrayList<>();
}