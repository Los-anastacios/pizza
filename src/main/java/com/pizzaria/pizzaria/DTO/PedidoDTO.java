package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {
    private String nome;

    private String obs;

    private Usuario usuario;

    public PedidoDTO(){

    }

    public PedidoDTO(String nome, String obs, Usuario usuario) {
        this.nome = nome;
        this.obs = obs;
        this.usuario = usuario;
    }
}