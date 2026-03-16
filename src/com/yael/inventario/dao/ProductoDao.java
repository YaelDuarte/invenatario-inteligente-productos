package com.yael.inventario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yael.inventario.config.ConexionBD;
import com.yael.inventario.models.Producto;

public class ProductoDao implements IProductoDAO{

	@Override
	public void guardarProducto(Producto producto) {
		String sqlQ = "INSERT INTO productos" + " (nombre,precio,cantidad) " + "VALUES (?,?,?)";
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ)){
			
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setInt(3, producto.getCantidad());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void eliminarProducto(int id) {
		String sqlQ = "DELETE FROM productos WHERE id = ?";
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ)){
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Producto obtenerProducto(int id) {
		String sqlQ = "SELECT * FROM productos WHERE id = ?";
		Producto producto = null;
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ)){
			
			ResultSet rs = ps.executeQuery();
			ps.setInt(1, id);
			
			if(rs.next()) {
				producto = new Producto(rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getInt("cantidad"), 
						rs.getDouble("precio"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return producto;
	}

	@Override
	public List<Producto> obtenerProductos() {
		List<Producto> productos = new ArrayList<Producto>();
		String sqlQ = "SELECT * FROM productos";
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ)){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Producto producto = new Producto(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getInt("cantidad"),
						rs.getDouble("precio")
						);
				productos.add(producto);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return productos;
	}

	@Override
	public void actualizarStock(int id,int cantidad) {
		String sqlQ = "UPDATE productos SET" + " cantidad = ?" + " WHERE id = ?";
		
		try(Connection conx = ConexionBD.getConnection();
				PreparedStatement ps = conx.prepareStatement(sqlQ)){
			
			ps.setInt(1, cantidad);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
