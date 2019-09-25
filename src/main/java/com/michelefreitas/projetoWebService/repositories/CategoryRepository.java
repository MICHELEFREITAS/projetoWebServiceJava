package com.michelefreitas.projetoWebService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelefreitas.projetoWebService.entities.Category;

//é opcional colocar a notação @Repository pois está herdando do jPaRepository que está registrado como component Spring
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
