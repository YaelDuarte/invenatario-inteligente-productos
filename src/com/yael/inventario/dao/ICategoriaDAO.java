package com.yael.inventario.dao;

import java.util.List;
import java.util.Optional;

import com.yael.inventario.models.Categoria;

public interface ICategoriaDAO {

	void agregarCategoria(Categoria categoria);
	List<Categoria> listarCategorias();
	Optional<Categoria> obtenerCategoria(int id);
}
