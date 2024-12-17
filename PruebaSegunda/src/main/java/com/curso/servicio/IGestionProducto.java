package com.curso.servicio;

import java.util.List;

import com.curso.modelo.Producto;

/**
 * Interfaz contiene los metodos para agragar, eliminar, modificar, buscar y
 * devolver los productos de la aplicacion.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public interface IGestionProducto {

	void agregarProducto(Producto producto);

	boolean eliminarProducto(String nombre);

	boolean modificarProducto(Producto producto);

	Producto buscarProducto(String nombre);

	List<Producto> listarProductos();

}
