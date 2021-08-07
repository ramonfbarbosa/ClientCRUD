package com.example.devsuperior.DsClient.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devsuperior.DsClient.entities.Client;
import com.example.devsuperior.DsClient.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public 	List<Client> findAll(){
		return repository.findAll();
	}

}
