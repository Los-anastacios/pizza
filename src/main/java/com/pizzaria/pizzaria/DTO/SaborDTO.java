package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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