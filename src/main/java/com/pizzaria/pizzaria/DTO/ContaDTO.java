package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

    private Long id;
    private String email;

    private String senha;

    private Cliente cliente;

    public ContaDTO(){

    }

    public ContaDTO(Long id, String email, String senha, Cliente cliente) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cliente = cliente;
    }
}