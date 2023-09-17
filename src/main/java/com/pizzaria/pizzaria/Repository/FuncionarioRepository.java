package com.pizzaria.pizzaria.Repository;

import com.pizzaria.pizzaria.Entity.Cliente;
import com.pizzaria.pizzaria.Entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

}
