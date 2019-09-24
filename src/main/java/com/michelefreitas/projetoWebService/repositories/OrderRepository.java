package com.michelefreitas.projetoWebService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelefreitas.projetoWebService.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
