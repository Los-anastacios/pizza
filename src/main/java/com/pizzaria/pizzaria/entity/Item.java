package com.pizzaria.pizzaria.entity;

import com.pizzaria.pizzaria.entity.enums.Tamanho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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