package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Cliente;
import com.pizzaria.pizzaria.Entity.Enums.Estado;
import com.pizzaria.pizzaria.Entity.Funcionario;
import com.pizzaria.pizzaria.Entity.Item;
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

    private Cliente idCliente;

    private Funcionario idFuncionario;

    private Estado estado;

    private List<Item> idItem;

    public PedidoDTO(){

    }

    public PedidoDTO(Long id, String nome, String obs, Cliente idCliente, Funcionario idFuncionario, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.obs = obs;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.estado = estado;
    }
}