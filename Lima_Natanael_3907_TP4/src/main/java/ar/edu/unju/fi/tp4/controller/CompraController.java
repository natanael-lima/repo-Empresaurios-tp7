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

import ar.edu.unju.fi.tp4.model.Compra;
import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.service.ICompraService;
import ar.edu.unju.fi.tp4.service.IProductoService;

@Controller
public class CompraController {
	@Autowired
	private Compra compra;
	
	@Autowired
	@Qualifier("tableCompraRepository")
	private ICompraService compraService;
	
	@Autowired
	@Qualifier("tableProductoRepository")
	private IProductoService productoService;
	
	//Formulario Compra
	@GetMapping("/index/compra")
	public String getFormCompra(Model model) {
	
		model.addAttribute(compra);
		model.addAttribute("productos",productoService.obtenerListaProducto());
		
		return "nuevacompra";
	}
	
	//Guardar Formulario Compra
	@PostMapping("/index/guardarCompra")
	public String getGuardarCompra(@ModelAttribute("compra") Compra compra,Model model) {
		String errorCompra="";
		Producto producto=productoService.buscarProductoID(compra.getProducto().getCodigo());
		compra.setProducto(producto); 
		if(compra.getCantidad()>producto.getStock()) {
			errorCompra="No hay suficiente stock";
			model.addAttribute(compra);
			model.addAttribute("productos",productoService.obtenerListaProducto());
			model.addAttribute("texto",errorCompra);
			return "nuevacompra";
		}else {
			
			compra.setTotal(producto.getPrecio()*compra.getCantidad());
			compraService.agregarCompra(compra);
			model.addAttribute("compras",compraService.obtenerCompras());
			
			return "mostrarcompra";
		}
	}
	
	
	//Mostra Tabla Compra
	@GetMapping("/index/listadoCompra")
	 public ModelAndView getCompraListado() {
		 ModelAndView model = new ModelAndView("mostrarcompra");
		
		model.addObject("compras",compraService.obtenerCompras());
		return model;
 	}
	
	//-----TP7----
	//Elimar Compra
	@GetMapping("/index/eliminarCompra/{id}")
	 public ModelAndView getEliminarCompra(@PathVariable(value="id") int param) {
		 ModelAndView model = new ModelAndView("mostrarcompra");
		 compraService.eliminarCompra(param);
		 model.addObject("compras",compraService.obtenerCompras());
		 return model;
 	}
	
	//Modificar Compra
	@GetMapping("/index/modificarCompra/{id}")
	 public String getModificarCompra(@PathVariable(value="id") int param,Model model) {
		 Optional<Compra> compras = compraService.buscarCompra(param);
		 model.addAttribute("compra",compras);
		 model.addAttribute("productos",productoService.obtenerListaProducto());
		 return ("nuevacompra");
	}
}
