package com.pizzaria.pizzaria.dto;

import com.pizzaria.pizzaria.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

    private Long id;
    private String username;

    private String password;


    public ContaDTO(){

    }

    public ContaDTO(Long id, String email, String senha) {
        this.id = id;
        this.username = email;
        this.password = senha;
    }
}