package com.curso.servlet;

import java.io.IOException;
import java.util.List;

import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;
import com.curso.servicio.GestionProductoImpl;
import com.curso.servicio.IGestionProducto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que modifica lo atributos de un producto
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IGestionProducto gestionProducto = new GestionProductoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = gestionProducto.listarProductos();
		if (productos == null || productos.isEmpty()) {
			request.setAttribute("errorMessage", "No hay productos, primero cree uno.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.setAttribute("productos", productos);
			String[] categorias = Categoria.obtenerCategorias();
			request.setAttribute("categorias", categorias);
			RequestDispatcher dispatcher = request.getRequestDispatcher("modificarProducto.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String categoria = request.getParameter("categoria");
		String precioStr = request.getParameter("precio");
		String stockStr = request.getParameter("stock");

		if (nombre == null || nombre.isEmpty() ||

				precioStr == null || precioStr.isEmpty() || stockStr == null || stockStr.isEmpty()) {
			request.setAttribute("errorMessage", "Todos los campos son obligatorios.");

			List<Producto> productos = gestionProducto.listarProductos();
			request.setAttribute("productos", productos);
			String[] categorias = Categoria.obtenerCategorias();
			request.setAttribute("categorias", categorias);

			RequestDispatcher dispatcher = request.getRequestDispatcher("modificarProducto.jsp");
			dispatcher.forward(request, response);
			return;
		}

		double precio = Double.parseDouble(precioStr);
		int stock = Integer.parseInt(stockStr);

		Producto producto = new Producto(nombre, Categoria.valueOf(categoria), precio, stock);
		boolean modificado = gestionProducto.modificarProducto(producto);

		if (modificado) {
			request.setAttribute("successMessage", "El producto fue modificado con éxito.");
		} else {
			request.setAttribute("errorMessage", "Hubo un error y el producto no se modificó.");
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
