package com.pizzaria.pizzaria.DTO;

import com.pizzaria.pizzaria.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;

    private String senha;

    private Usuario usuario;

    public UserDTO(){

    }

    public UserDTO(Long id,String email, String senha, Usuario usuario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
    }
}