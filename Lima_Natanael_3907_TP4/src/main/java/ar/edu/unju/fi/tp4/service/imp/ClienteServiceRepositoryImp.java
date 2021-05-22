package ar.edu.unju.fi.tp4.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.repository.IClienteRepository;
import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;

@Service("tableClienteRepository")
public class ClienteServiceRepositoryImp implements IClienteService{
    
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public void generarList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>)clienteRepository.findAll();
	}

	@Override
	public void eliminarCliente(int id) {
		clienteRepository.deleteById(id);
	
	}
	@Override
	public Optional<Cliente> buscarCliente(int id){
		return clienteRepository.findById(id);
	}

}
