package com.michelefreitas.projetoWebService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelefreitas.projetoWebService.entities.Category;
import com.michelefreitas.projetoWebService.services.CategoryService;
//controlador rest
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	//faz a injeção de dependencia
	@Autowired
	private CategoryService service;
	
	//endpoint para buscar todas categorias
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//buscar categoria por id. Requisição tipo Get. value id para requisição aceitar id dentro da url
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
