package com.pizzaria.pizzaria.Repository;

import com.pizzaria.pizzaria.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query(value = "SELECT p FROM Usuario p where p.nome = :nome")
    List<Usuario> findPessoaByNome(@Param("nome")final String nome);
}