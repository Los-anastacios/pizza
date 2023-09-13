package com.pizzaria.pizzaria.Entity;

import com.pizzaria.pizzaria.Entity.Enums.Estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Pedido extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "obs")
    private String obs;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Usuario idUsuario;

    @Column(name = "entrega")
    private Boolean entrega;

    @Enumerated(EnumType.STRING)
    @Column(name ="estado")
    private Estado estado;

    @OneToMany
    @Column(name = "id_Item")
    private List<Item> idItem;

}