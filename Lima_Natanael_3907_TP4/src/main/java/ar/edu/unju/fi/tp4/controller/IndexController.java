package ar.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private IProductoService productoService;
	
	@Autowired
    private Producto producto;
	
	@Autowired
    private Cliente cliente;
	
	@Autowired
	private Compra compra;
	 
	 @Autowired
	 @Qualifier("tableService")
	 private IClienteService clienteService;
	 
	 @Autowired
	 @Qualifier("tableCompra")
	 private ICompraService compraService;
	 
	
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
		ModelAndView model = new ModelAndView("index");
		clienteService.agregarCliente(cliente);
		model.addObject("clientes",clienteService.obtenerCliente());
		return model;
	}
	
	 @GetMapping("/index/listado")
	 public ModelAndView getProcesoListado() {
		 ModelAndView model = new ModelAndView("mostrarclientes");
		
		if(clienteService.obtenerCliente() == null) {
			 clienteService.generarList();
		}
		
		model.addObject("clientes",clienteService.obtenerCliente());
		return model;
   	}
	 
	//Index del tp5 Compras
	
	@GetMapping("/index/compra")
	public String getFormCompra(Model model) {
		String text="";
		String textProd=productoService.mostrarUltimoProducto().getNombre();
		model.addAttribute(compra);
		model.addAttribute("textProd",textProd);
		if(productoService.obtenerListaProducto().size()==1) {
		   text="No ingreso un producto";
		   model.addAttribute("text",text);
		}
		
		return "nuevacompra";
	}
	 
	@PostMapping("/index/guardarCompra")
	public ModelAndView getGuardarCompra(@ModelAttribute("compra") Compra compra) {
		ModelAndView model = new ModelAndView("index");
		compra.setProducto(productoService.mostrarUltimoProducto());
		compraService.agregarCompra(compra);
		//model.addObject("compras",compraService.obtenerCompras());
		return model;
	}
	
	@GetMapping("/index/listadoCompra")
	 public ModelAndView getCompraListado() {
		 ModelAndView model = new ModelAndView("mostrarcompra");
		
		if(compraService.obtenerCompras() == null) {
			 compraService.generarListaCompras();
		}
		
		model.addObject("compras",compraService.obtenerCompras());
		return model;
  	}
	
	 
	 
	 
	 
}
