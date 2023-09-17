package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private Long id;

    private String rua;

    private int numero;

    private String bairro;

    private String cep;

    private String complemento;

    private Cliente cliente;

    public EnderecoDTO(){

    }

    public EnderecoDTO(Long id, String rua, int numero, String bairro, String cep, String complemento, Cliente cliente) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.cliente = cliente;
    }
}