package com.pizzaria.pizzaria.Entity;

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
    @Column(name = "id_conta")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

}