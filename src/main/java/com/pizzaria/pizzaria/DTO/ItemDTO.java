package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Enums.Tamanho;
import com.pizzaria.pizzaria.Entity.Sabor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDTO {

    private Long id;

    private Tamanho tamanho;

    private String nome;

    private List<Sabor> sabor;


    public ItemDTO(){

    }

    public ItemDTO(Long id,Tamanho tamanho, String nome, List<Sabor> sabor) {
        this.id = id;
        this.tamanho = tamanho;
        this.nome = nome;
        this.sabor = sabor;
    }
}