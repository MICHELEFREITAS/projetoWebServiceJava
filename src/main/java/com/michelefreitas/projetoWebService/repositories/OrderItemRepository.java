package com.michelefreitas.projetoWebService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelefreitas.projetoWebService.entities.OrderItem;

//para salvar no BD preciso do repository
//é opcional colocar a notação @Repository pois está herdando do jPaRepository que está registrado como component Spring
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
