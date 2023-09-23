package com.pizzaria.pizzaria.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SaborDTO {

    private Long id;

    private String nome;

    private List<ItemDTO> itemDTOS;

    public SaborDTO(){

    }

    public SaborDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}