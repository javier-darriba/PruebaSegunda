package com.curso.servicio;

import java.util.ArrayList;
import java.util.List;

import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;

/**
 * Clase de servicio que implementa la interfaz GestionProductoInterfaz.
 * Encargada de agregar, elemininar, modificar, buscar y devolver los productos
 * de la aplicacion.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class GestionProductoImpl implements IGestionProducto {

	private static List<Producto> productos = new ArrayList<>();

	static {
		productos.add(new Producto("Llave inglesa", Categoria.HERRAMIENTAS, 9.99, 10));
		productos.add(new Producto("Destonillador", Categoria.HERRAMIENTAS, 4.99, 15));
	}

	@Override
	public void agregarProducto(Producto producto) {
		productos.add(producto);

	}

	@Override
	public boolean eliminarProducto(String nombre) {
		for (int i = 0; i < productos.size(); i++) {
			Producto producto = productos.get(i);
			if (producto.getNombre().equalsIgnoreCase(nombre)) {
				productos.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modificarProducto(Producto producto) {
		for (int i = 0; i < productos.size(); i++) {
			if (producto.getNombre().equalsIgnoreCase(producto.getNombre())) {
				productos.set(i, producto);
				return true;
			}
		}
		return false;
	}

	@Override
	public Producto buscarProducto(String nombre) {
		for (Producto producto : productos) {
			if (producto.getNombre().equalsIgnoreCase(nombre)) {
				return producto;
			}
		}
		return null;
	}

	@Override
	public List<Producto> listarProductos() {
		return productos;
	}

}
