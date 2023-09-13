package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Enums.Tamanho;
import com.pizzaria.pizzaria.Entity.Pedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private Long id;

    private Tamanho tamanho;

    private String nome;


    public ItemDTO(){

    }

    public ItemDTO(Long id,Tamanho tamanho, String nome) {
        this.id = id;
        this.tamanho = tamanho;
        this.nome = nome;
    }
}