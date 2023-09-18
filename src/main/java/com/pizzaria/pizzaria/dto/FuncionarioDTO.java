package com.pizzaria.pizzaria.dto;

import com.pizzaria.pizzaria.entity.ContaFuncionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    private Long id;

    private String nome;

    private String cpf;

    private ContaFuncionario contaFuncionario;
}
