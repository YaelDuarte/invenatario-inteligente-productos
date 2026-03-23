package com.yael.inventario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yael.inventario.config.ConexionBD;
import com.yael.inventario.models.Categoria;

public class CategoriaDao implements ICategoriaDAO{

	@Override
	public void agregarCategoria(Categoria categoria) {
		String sqlQ = "INSERT INTO categorias (nombre,descripcion) VALUES (?,?)";
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ)){
			
			ps.setString(1, categoria.getNombre());
			ps.setString(2, categoria.getDescripcion());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Categoria> listarCategorias() {
		List<Categoria> categorias = new ArrayList<>();
		
		String sqlQ = "SELECT * FROM categorias";
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ);
				ResultSet rs = ps.executeQuery()){
			
			while(rs.next()) {
				categorias.add(new Categoria(
						rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getString("descripcion")
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return categorias;
	}

	@SuppressWarnings("unused")
	@Override
	public Optional<Categoria> obtenerCategoria(int id) {
		String sqlQ = "SELECT * FROM categorias WHERE id = ?";
		Optional<Categoria> categoria;
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ);
				){
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return categoria = Optional.of(new Categoria(
							rs.getInt("id"), 
							rs.getString("nombre"), 
							rs.getString("descripcion")));
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Optional.empty();
	}
	
	

}
