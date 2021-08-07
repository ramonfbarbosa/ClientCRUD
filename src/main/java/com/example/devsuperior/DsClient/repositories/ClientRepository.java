package com.example.devsuperior.DsClient.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devsuperior.DsClient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
}
