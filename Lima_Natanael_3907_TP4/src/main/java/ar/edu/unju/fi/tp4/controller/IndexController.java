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
import ar.edu.unju.fi.tp4.model.Compra;
import ar.edu.unju.fi.tp4.service.IClienteService;
import ar.edu.unju.fi.tp4.service.ICompraService;
import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.service.IProductoService;

@Controller
public class IndexController {
	
	
	@Autowired
    private Producto producto;
	
	@Autowired
    private Cliente cliente;
	
	@Autowired
	private Compra compra;
	 
	 @Autowired
	 @Qualifier("tableClienteRepository")
	 private IClienteService clienteService;
	 
	 @Autowired
	 @Qualifier("tableCompraRepository")
	 private ICompraService compraService;
	 
	 @Autowired
	 @Qualifier("tableProductoRepository")
	 private IProductoService productoService;
	
	/**
	 * Metodo que muestra la pagina de forma static
	 */
	@GetMapping("/index")
	public String getPage(Model model) {
		
		return "index";
	}
	
	@GetMapping("/index/producto")
	public String getPageProd(Model model) {
		model.addAttribute(producto);
		return "nuevoprod";
	}
	
	/**
	 * Metodo de proceso de guardar producto
	 */
	@PostMapping("/index/guardar")
	public String getProceso(@ModelAttribute("producto") Producto producto ) {
		productoService.agregarList(producto);
    	return "index";
	}
	
	/**
	 * Metodo de proceso de mostrar el ultimo producto cargado
	 */
	@GetMapping("/index/ultimo")
	public String getProcesoUltimo( Model model ) {
		model.addAttribute("prod",productoService.obtenerList());
    	return "mostrarprod";
	}
	
	 
	@GetMapping("/index/cliente")
	public String getPageMain(Model model) {
		model.addAttribute(cliente);
		return "nuevocliente";
	}
	 
	
	@PostMapping("/index/guardarcliente")
	public ModelAndView getProcesoGuardar(@ModelAttribute("cliente") Cliente cliente ) {
		ModelAndView model = new ModelAndView("mostrarclientes");
		clienteService.agregarCliente(cliente);
		model.addObject("clientes",clienteService.obtenerClientes());
		return model;
	}
	
	 @GetMapping("/index/listado")
	 public ModelAndView getProcesoListado() {
		ModelAndView model = new ModelAndView("mostrarclientes");
	
		model.addObject("clientes",clienteService.obtenerClientes());
		return model;
   	}
	 
	//Index del tp5 Compras
	
	@GetMapping("/index/compra")
	public String getFormCompra(Model model) {
		String text="";
		String textProd=productoService.mostrarUltimoProducto().getNombre();
		model.addAttribute(compra);
		model.addAttribute("textProd",textProd);
		if(productoService.obtenerListaProducto().size()==1) { // falta modificar
		   text="No ingreso un producto";
		   model.addAttribute("text",text);
		}
		
		return "nuevacompra";
	}
	 
	@PostMapping("/index/guardarCompra")
	public ModelAndView getGuardarCompra(@ModelAttribute("compra") Compra compra) {
		ModelAndView model = new ModelAndView("index");
		compra.setProducto(productoService.mostrarUltimoProducto()); // falta
		compraService.agregarCompra(compra);
		return model;
	}
	
	@GetMapping("/index/listadoCompra")
	 public ModelAndView getCompraListado() {
		 ModelAndView model = new ModelAndView("mostrarcompra");
		
		model.addObject("compras",compraService.obtenerCompras());
		return model;
  	}
	
	//--------------------- TP7 ---------------------
	
	// Controller Cliente
	@GetMapping("/index/eliminar/{id}")
	 public ModelAndView getEliminarCliente(@PathVariable(value="id") int param) {
		 ModelAndView model = new ModelAndView("mostrarclientes");
		 clienteService.eliminarCliente(param);
		 model.addObject("clientes",clienteService.obtenerClientes());
		 return model;
  	}
	 
	@GetMapping("/index/modificar/{id}")
	 public ModelAndView getModificarCliente(@PathVariable(value="id") int param) {
		 ModelAndView model = new ModelAndView("nuevocliente");
		 Optional<Cliente> clientes = clienteService.buscarCliente(param);
		 model.addObject("clientes",clientes);
		 return model;
 	}
	
	// Controller Compra
		@GetMapping("/index/eliminarCompra/{id}")
		 public ModelAndView getEliminarCompra(@PathVariable(value="id") int param) {
			 ModelAndView model = new ModelAndView("mostrarcompra");
			 compraService.eliminarCompra(param);
			 model.addObject("compras",compraService.obtenerCompras());
			 return model;
	  	}
		 
		@GetMapping("/index/modificarCompra/{id}")
		 public ModelAndView getModificarCompra(@PathVariable(value="id") int param) {
			 ModelAndView model = new ModelAndView("nuevacompra");
			 Optional<Compra> compras = compraService.buscarCompra(param);
			 model.addObject("compras",compras);
			 return model;
	 	}
	
		// Controller Producto
				@GetMapping("/index/eliminarProducto/{id}")
				 public ModelAndView getEliminarProducto(@PathVariable(value="id") int param) {
					 ModelAndView model = new ModelAndView("mostrarprod");
					 productoService.eliminarProducto(param);
					 model.addObject("prod",productoService.obtenerListaProducto());
					 return model;
			  	}
				 
				@GetMapping("/index/modificarProducto/{id}")
				 public ModelAndView getModificarProducto(@PathVariable(value="id") int param) {
					 ModelAndView model = new ModelAndView("nuevoprod");
					 Optional<Producto> prod = productoService.buscarProducto(param);
					 model.addObject("prod",prod);
					 return model;
			 	}
	
}
