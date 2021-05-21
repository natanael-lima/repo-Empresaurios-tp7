package ar.edu.unju.fi.tp4.service;

import java.util.List;

import ar.edu.unju.fi.tp4.model.Cliente;

public interface IClienteService {
	
     /**
	 * Generar Tablar
	 */
	public void generarList();
    /**
     * Agregar nuevo cliente	
     */
    public void agregarCliente (Cliente cliente);
   
   /**
    * Muestra lista de clientes
    * @return
    */
    public List<Cliente> obtenerCliente();
   
  
   
}
