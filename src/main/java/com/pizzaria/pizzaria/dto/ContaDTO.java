package com.pizzaria.pizzaria.dto;

import com.pizzaria.pizzaria.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

    private Long id;
    private String email;

    private String senha;

    //@JsonIgnore
    private Cliente cliente;

    public ContaDTO(){

    }

    public ContaDTO(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }
}