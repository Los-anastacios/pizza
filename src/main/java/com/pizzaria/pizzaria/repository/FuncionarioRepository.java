package com.pizzaria.pizzaria.repository;


import com.pizzaria.pizzaria.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

}
