package com.michelefreitas.projetoWebService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelefreitas.projetoWebService.entities.User;
import com.michelefreitas.projetoWebService.repositories.UserRepository;

//@Component - registra classe como componente do Spring e vai poder se injetado com @Autowired.
//Existe o @Repository para registrar repositório e tem o @Service para registrar um serviço

@Service
public class UserService {
	
	//@Auto... para Spring faça injeção transparencia para o programador
	//UserService dependency para UserRepository(injeção dependencia do repository com @Autowired)
	@Autowired
	private UserRepository repository;
	
	
	//retornar todos usuários do BD
	public List<User> findAll(){
		return repository.findAll();
	}

	//recuperar usuário por id
	public User findById(Long id) {
		//retorna obj tipo Optional
		Optional<User> obj = repository.findById(id);
		//retorna obj do tipo User que está dentro do Optional
		return obj.get();
	}
}
