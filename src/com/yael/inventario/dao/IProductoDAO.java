package com.yael.inventario.dao;

import java.util.List;

import com.yael.inventario.models.Producto;

public interface IProductoDAO {

	void guardarProducto(Producto producto);
	void eliminarProducto(int id);
	Producto obtenerProducto(int id);
	List<Producto> obtenerProductos();
	void actualizarStock(int id,int cantidad);
}
