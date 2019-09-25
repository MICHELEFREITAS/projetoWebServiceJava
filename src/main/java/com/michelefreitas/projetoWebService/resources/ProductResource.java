package com.michelefreitas.projetoWebService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelefreitas.projetoWebService.entities.Product;
import com.michelefreitas.projetoWebService.services.ProductService;
//controlador rest
@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	//faz a injeção de dependencia
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//buscar produto por id. Requisição tipo Get. value id para requisição aceitar id dentro da url
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
