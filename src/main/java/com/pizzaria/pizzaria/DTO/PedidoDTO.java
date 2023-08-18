package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Estado;
import com.pizzaria.pizzaria.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

    private Long id;
    private String nome;

    private String obs;

    private Usuario idUsuario;

    private Estado estado;

    public PedidoDTO(){

    }

    public PedidoDTO(Long id,String nome, String obs, Usuario idUsuario, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.obs = obs;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }
}