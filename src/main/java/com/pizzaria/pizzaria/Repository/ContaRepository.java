package com.pizzaria.pizzaria.Repository;

import com.pizzaria.pizzaria.Entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Conta, Long> {

}