package com.michelefreitas.projetoWebService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelefreitas.projetoWebService.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
