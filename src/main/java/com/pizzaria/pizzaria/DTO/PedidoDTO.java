package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Enums.Estado;
import com.pizzaria.pizzaria.Entity.Item;
import com.pizzaria.pizzaria.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private Long id;
    private String nome;

    private String obs;

    private Boolean entrega;

    private Usuario idUsuario;

    private Estado estado;

    private List<Item> idItem;

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