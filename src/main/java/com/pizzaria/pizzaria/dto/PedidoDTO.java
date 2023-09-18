package com.pizzaria.pizzaria.dto;

import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.entity.enums.Estado;
import com.pizzaria.pizzaria.entity.Funcionario;
import com.pizzaria.pizzaria.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private Long id;

    private String obs;

    private Boolean entrega;

    private Cliente idCliente;

    private Funcionario idFuncionario;


    private Estado estado;

    private List<Item> idItem;

    public PedidoDTO(){

    }

    public PedidoDTO(Long id, String obs, Cliente idCliente, Funcionario idFuncionario,Boolean entrega, Estado estado) {
        this.id = id;
        this.obs = obs;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.entrega =entrega;
        this.estado = estado;
    }
}