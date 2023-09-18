package com.pizzaria.pizzaria.repository;

import com.pizzaria.pizzaria.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}