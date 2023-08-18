package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Pedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private Long id;

    private String tamanho;

    private Boolean entrega;

    private Pedido pedido;

    public ItemDTO(){

    }

    public ItemDTO(String tamanho, Boolean entrega, Pedido pedido) {
        this.tamanho = tamanho;
        this.entrega = entrega;
        this.pedido = pedido;
    }
}