package com.pizzaria.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Conta", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Conta extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_conta", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cliente_fk", referencedColumnName = "id")
    private Cliente cliente;


    public Conta(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }
}