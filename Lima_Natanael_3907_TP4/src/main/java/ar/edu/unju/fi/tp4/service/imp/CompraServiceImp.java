package ar.edu.unju.fi.tp4.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp4.model.Compra;
import ar.edu.unju.fi.tp4.service.ICompraService;
import ar.edu.unju.fi.tp4.util.TablaCompra;

@Service("tableCompra")
public class CompraServiceImp implements ICompraService{
    
	private List<Compra> compraList;
	
	@Override
	public void generarListaCompras() {
		compraList=TablaCompra.registros;
		
	}

	@Override
	public void agregarCompra(Compra compra) {
		
		if(compraList==null) 
			generarListaCompras();
		
		compraList.add(compra);
			
		
	}

	@Override
	public List<Compra> obtenerCompras() {
		
		return compraList;
	}
	

}
