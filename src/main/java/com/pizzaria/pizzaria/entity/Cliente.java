package com.pizzaria.pizzaria.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente", schema = "public")
public class Cliente extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;


    @OneToOne
    @JoinColumn(name = "usuario")
    private Conta conta;

    @JsonManagedReference
    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", cascade =  CascadeType.ALL)
    private List<Endereco> enderecos;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, Conta conta, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.conta = conta;
        this.enderecos =enderecos;
    }
}
