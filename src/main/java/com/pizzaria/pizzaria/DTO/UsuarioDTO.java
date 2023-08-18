package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String telefone;

    private String CPF;

    private List<Endereco> enderecos;

    public UsuarioDTO(){

    }

    public UsuarioDTO( String nome, String telefone, String CPF, List<Endereco> enderecos) {
        this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
        this.enderecos = enderecos;
    }
}