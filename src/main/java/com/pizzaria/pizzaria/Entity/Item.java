package com.pizzaria.pizzaria.Entity;

import com.pizzaria.pizzaria.Entity.Enums.Tamanho;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho")
    private Tamanho tamanho;

    @ManyToMany
    @JoinTable(name = "itemSabor", joinColumns = @JoinColumn(name = "idSabor"), inverseJoinColumns = @JoinColumn(name = "idItem"))
    private List<Sabor> sabor = new ArrayList<>();
}