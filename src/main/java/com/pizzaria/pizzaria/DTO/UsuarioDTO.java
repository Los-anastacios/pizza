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

    private String Cpf;

    private List<Endereco> enderecos;

    public UsuarioDTO(){

    }

    public UsuarioDTO(Long id, String nome, String telefone, String Cpf, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.Cpf = Cpf;
        this.enderecos = enderecos;
    }
}