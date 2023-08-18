package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private Long id;

    private String rua;

    private int numero;

    private Usuario usuario;

    public EnderecoDTO(){

    }

    public EnderecoDTO(String rua, int numero, Usuario usuarioDTO){
        this.rua=rua;
        this.numero=numero;
        this.usuario=usuarioDTO;
    }

}