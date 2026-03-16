package com.yael.inventario.service;

import java.util.List;
import java.util.Map;

import com.yael.inventario.excepciones.ExcepcionValorCero;
import com.yael.inventario.excepciones.ExcepcionValorVacio;
import com.yael.inventario.models.Producto;

public interface IProductoService {
	void agregarProducto(Producto producto) throws ExcepcionValorVacio, ExcepcionValorCero;
	void eliminarProducto(int id) throws ExcepcionValorCero;
	List<Producto> mostrarProductos();
	void actualizaStock(int id, int cantidad) throws ExcepcionValorCero;
	Producto mostrarProducto(int id) throws ExcepcionValorCero;
	List<Producto> productosSinStock();
	List<Producto> obtenerProductosCaros(double precio);
	double valorTotalInventario();
	Producto productoMasCaro();
	double promedioDePrecios();
	Map<Boolean, List<Producto>> productosConYSinStock();
}
