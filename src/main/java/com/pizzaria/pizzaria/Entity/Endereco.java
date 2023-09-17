package com.pizzaria.pizzaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "endereco", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private int numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "complemento")
    private String complemento;

}