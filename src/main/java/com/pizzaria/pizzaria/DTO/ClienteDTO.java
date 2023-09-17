package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Conta;
import com.pizzaria.pizzaria.Entity.Endereco;
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

    private String CPF;

    private List<Endereco> enderecos;

    private Conta conta;

}