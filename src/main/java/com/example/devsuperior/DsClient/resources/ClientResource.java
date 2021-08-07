package com.example.devsuperior.DsClient.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devsuperior.DsClient.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = new ArrayList<>();
		list.add(new Client(null, "Maria", "12345678901", 10000.0, null, 3));
		list.add(new Client(null, "João", "98732138752", 15000.0, null, 0));
		list.add(new Client(null, "Miguel", "148295016452", 4000.0, null, 1));
		return ResponseEntity.ok().body(list);
	}
}

