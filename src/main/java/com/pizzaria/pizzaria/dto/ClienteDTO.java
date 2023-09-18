package com.pizzaria.pizzaria.dto;

import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    private String nome;

    private String cpf;

    private List<Endereco> enderecos;

    private Conta conta;

}