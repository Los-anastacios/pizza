package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String email;

    private String senha;

    private Usuario usuario;

    public UserDTO(){

    }

    public UserDTO(String email, String senha, Usuario usuario) {
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
    }
}