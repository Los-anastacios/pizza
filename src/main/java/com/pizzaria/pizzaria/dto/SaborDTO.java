package com.pizzaria.pizzaria.dto;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class SaborDTO {

    private Long id;

    private String nome;

    public SaborDTO(){

    }

    public SaborDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}