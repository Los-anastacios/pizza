package com.pizzaria.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ContaFUNCIONARIO", schema = "public")
@AllArgsConstructor
public class ContaFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta_funcionario")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToOne
    @JoinColumn(name = "funcionario_fk", referencedColumnName = "id")
    private Funcionario funcionario;

    public ContaFuncionario() {
    }

    public ContaFuncionario(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }
}
