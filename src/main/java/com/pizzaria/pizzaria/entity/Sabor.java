package com.pizzaria.pizzaria.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sabor extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

}