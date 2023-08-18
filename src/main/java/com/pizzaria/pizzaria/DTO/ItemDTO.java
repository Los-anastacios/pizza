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

    private Pedido idPedido;

    public ItemDTO(){

    }

    public ItemDTO(Long id,String tamanho, Boolean entrega, Pedido idPedido) {
        this.id = id;
        this.tamanho = tamanho;
        this.entrega = entrega;
        this.idPedido = idPedido;
    }
}