package com.pizzaria.pizzaria.repository;

import com.pizzaria.pizzaria.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query(value = "SELECT p FROM Cliente p where p.nome = :nome")
    List<Cliente> findPessoaByNome(@Param("nome")final String nome);
}