package ar.edu.unju.fi.tp4.service.imp;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;
import ar.edu.unju.fi.tp4.util.TablaCliente;
@Service("tableService")
public class ClienteServiceImp implements IClienteService{

	private static final Log LOGGER = LogFactory.getLog(ClienteServiceImp.class);
	
	private List<Cliente> clienteList;
	
	@Override
	public void generarList() {
		clienteList = TablaCliente.registros;	
	}
	
	@Override
	public void agregarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		LOGGER.info("SERVICE: IClienteService -> ClienteServiceImp");
		LOGGER.info("METHOD: agregarCliente()");
		LOGGER.info("RESULT:a object cliente: "+cliente.obtenerUltimaCompra());
		if(clienteList == null)
			generarList();

		clienteList.add(cliente);

	}

	@Override
	public List<Cliente> obtenerCliente() {
		// TODO Auto-generated method stub
		return clienteList;
	}

	
}
