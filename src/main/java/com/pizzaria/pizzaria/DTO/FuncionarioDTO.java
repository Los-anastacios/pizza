package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Conta;
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

    private String CPF;

    private Conta conta;
}
