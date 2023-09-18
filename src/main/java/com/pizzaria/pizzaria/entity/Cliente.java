package com.pizzaria.pizzaria.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @OneToOne(mappedBy = "cliente")
    private Conta conta;

    @JsonIgnoreProperties("cliente")
    @JsonManagedReference("cliente")
    @OneToMany(mappedBy = "cliente", cascade =  CascadeType.ALL)
    private List<Endereco> enderecos;

}
