package com.yael.inventario.service;

import java.util.List;
import java.util.stream.Collectors;

import com.yael.inventario.dao.ProductoDao;
import com.yael.inventario.excepciones.ExcepcionValorCero;
import com.yael.inventario.excepciones.ExcepcionValorVacio;
import com.yael.inventario.models.Producto;

public class ProductoService implements IProductoService{
	
	private final ProductoDao pd;
	
	public ProductoService(ProductoDao pd) {
		this.pd = pd;
	}

	@Override
	public void agregarProducto(Producto producto) throws ExcepcionValorVacio,ExcepcionValorCero {
		if(producto.getNombre() == null) {
			throw new ExcepcionValorVacio("El valor no puede estar vacio");
		}else if(producto.getCantidad() < 0 || producto.getPrecio() < 0) {
			throw new ExcepcionValorCero("No puede haber valores 0 o negativos");
		}
			pd.guardarProducto(producto);
	}

	@Override
	public void eliminarProducto(int id) throws ExcepcionValorCero{
		if(id <= 0) {
			throw new ExcepcionValorCero("No hay ID 0 o menor, verifica");
		}
		pd.eliminarProducto(id);
	}

	@Override
	public List<Producto> mostrarProductos() {
		return pd.obtenerProductos();
	}

	@Override
	public void actualizaStock(int id, int cantidad) throws ExcepcionValorCero {
		if(id <= 0 || cantidad <= 0) {
			throw new ExcepcionValorCero("No hay ID 0/No puedes agregar un stock en 0");
		}
		pd.actualizarStock(id, cantidad);
	}

	@Override
	public Producto mostrarProducto(int id) throws ExcepcionValorCero {
		if(id <= 0) {
			throw new ExcepcionValorCero("No existen ID 0 o negativo");
		}
		return pd.obtenerProducto(id);
	}

	/*
	 * El metodo hace uso del stream para poder filtrar, hace la comparacion buscando productos
	 * con stock en 0 para poder almacenarlos en una nueva lista temporal y poder mostrarla.
	 */
	@Override
	public List<Producto> productosSinStock() {
		return pd.obtenerProductos().stream().filter(
				prod -> prod.getCantidad() == 0).collect(Collectors.toList());		
	}

	/*
	 * El metodo es similar al anterior, solo que en este caso dentro de la lambda va a hacer 
	 * la comparacioón para obtener los productos que esten dentro de ese rango de precio que 
	 * fue indicado por el usuario/pruebas.
	 */
	@Override
	public List<Producto> obtenerProductosCaros(double precio) {
		return pd.obtenerProductos().stream().filter(
				prodPrecio -> prodPrecio.getPrecio() >= precio).collect(Collectors.toList());
	}
	
	/*
	 * Convertimos nuestra lista a doubles para que podamos realizar las operaciones, dentro del 
	 * map realizaremos la operacion con la lambda y posterior a esto realizaremos la suma de cada 
	 * producto que se ira guardando.
	 */

	@Override
	public double valorTotalInventario() {
		return pd.obtenerProductos().stream().mapToDouble(
				prod -> prod.getPrecio() * prod.getCantidad()).sum();
	}
	
	
	

	

}
