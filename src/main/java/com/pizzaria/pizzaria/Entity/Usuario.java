package com.pizzaria.pizzaria.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pizzaria.pizzaria.Entity.Enums.Cargo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "usuario")
    private Conta conta;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name ="cargo")
    private Cargo cargo;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

}
