package com.pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    @Column(name = "cadastro_Date")
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "edicao_Date")
    private LocalDateTime edicao;

    @Getter @Setter
    @Column(name = "ativo")
    private boolean ativo;

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }
}