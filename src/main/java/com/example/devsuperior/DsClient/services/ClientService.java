package com.example.devsuperior.DsClient.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.devsuperior.DsClient.dto.ClientDTO;
import com.example.devsuperior.DsClient.entities.Client;
import com.example.devsuperior.DsClient.repositories.ClientRepository;
import com.example.devsuperior.DsClient.services.exceptions.DatabaseException;
import com.example.devsuperior.DsClient.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> page = repository.findAll(pageRequest);
		return page.map(x -> new ClientDTO(x));
	}

	@Transactional()
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Optional<Client> obj = repository.findById(id);
	        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
	        copyDtoToEntity(dto, entity);
	        entity = repository.save(entity);
	        return new ClientDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
