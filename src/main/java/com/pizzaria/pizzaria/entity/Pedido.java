package com.pizzaria.pizzaria.entity;

import com.pizzaria.pizzaria.entity.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "obs")
    private String obs;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario idFuncionario;

    @Column(name = "entrega")
    private Boolean entrega;

    @Enumerated(EnumType.STRING)
    @Column(name ="estado")
    private Estado estado;

    @OneToMany
    @Column(name = "id_Item")
    private List<Item> idItem;

}