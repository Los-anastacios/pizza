package com.pizzaria.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "endereco", schema = "public")
public class Endereco extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "numero")
    private int numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "complemento")
    private String complemento;

    @JsonBackReference
    @JsonIgnoreProperties("enderecos")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    public Endereco(){

    }

    public Endereco(Long id, String rua, int numero, String bairro, String cep, String complemento, Cliente cliente) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.cliente = cliente;
    }
}