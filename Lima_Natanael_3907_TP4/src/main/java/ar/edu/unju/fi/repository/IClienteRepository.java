package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp4.model.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long>{
	
}
