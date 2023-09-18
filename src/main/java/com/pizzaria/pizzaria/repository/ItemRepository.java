package com.pizzaria.pizzaria.repository;

import com.pizzaria.pizzaria.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}