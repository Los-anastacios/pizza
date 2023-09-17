package com.pizzaria.pizzaria.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @OneToOne(mappedBy = "usuario")
    @Column(name = "conta")
    private Conta conta;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

}
