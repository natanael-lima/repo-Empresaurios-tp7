package ar.edu.unju.fi.tp4.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;
import ar.edu.unju.fi.tp4.service.ICuentaService;
@Controller
public class ClienteController {
	@Autowired
	private Cliente cliente;

	@Autowired
	@Qualifier("tableClienteRepository")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("tableCuentaRepository")
	private ICuentaService cuentaService;
	
	//Formulario Cliente
	@GetMapping("/index/cliente")
	public String getPageMain(Model model) {
		model.addAttribute(cliente);
		return "nuevocliente";
	}
	
	//Guardar FomrularioCliente
	@PostMapping("/index/guardarcliente")
	public ModelAndView getProcesoGuardar(@ModelAttribute("cliente") Cliente cliente ) {
		ModelAndView model = new ModelAndView("mostrarclientes");
		clienteService.agregarCliente(cliente);
		model.addObject("clientes",clienteService.obtenerClientes());
		model.addObject("cuentas", cuentaService.obtenerCuentas());
		return model;
	}
	
	//Tablas Clientes
	@GetMapping("/index/listado")
	public ModelAndView getProcesoListado() {
		ModelAndView model = new ModelAndView("mostrarclientes");
		model.addObject("clientes",clienteService.obtenerClientes());
		model.addObject("cuentas", cuentaService.obtenerCuentas());
		return model;
   	}
	
	//----TP7-----
	
	//Eliminar Compra
	@GetMapping("/index/eliminar/{id}")
	 public ModelAndView getEliminarCliente(@PathVariable(value="id") int param) {
		 ModelAndView model = new ModelAndView("mostrarclientes");
		 clienteService.eliminarCliente(param);
		 model.addObject("clientes",clienteService.obtenerClientes());
		 return model;
 	}
	//Editar Compra
	@GetMapping("/index/modificar/{id}")
	 public ModelAndView getModificarCliente(@PathVariable(value="id") int param) {
		 ModelAndView model = new ModelAndView("nuevocliente");
		 Optional<Cliente> cliente = clienteService.buscarCliente(param);
		 model.addObject("cliente",cliente);
		 return model;
	}

	
}
