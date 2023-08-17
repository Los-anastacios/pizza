package com.pizzariaBackEnd.pizzariaBackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sabor", schema = "public")
public class Sabor{


    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Getter@Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter@Setter
    @ManyToMany(mappedBy = "sabor")
    private List<Item> item = new ArrayList<>();

    public Sabor(){

    }

    public Sabor(Long id,String nome, List<Item> item){
        this.id = id;
        this.nome = nome;
        this.item = item;
    }
}
