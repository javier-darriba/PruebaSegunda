package com.curso.servlet;

import java.io.IOException;

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
 * Servlet que crea un producto indicado y redirige al inicio
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class CrearProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IGestionProducto gestionProducto = new GestionProductoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] categorias = Categoria.obtenerCategorias();
		request.setAttribute("categorias", categorias);

		RequestDispatcher dispatcher = request.getRequestDispatcher("crearProducto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String categoria = request.getParameter("categoria");
		String precioStr = request.getParameter("precio");
		String stockStr = request.getParameter("stock");

		if (nombre == null || nombre.isEmpty() || precioStr == null || precioStr.isEmpty() || stockStr == null
				|| stockStr.isEmpty()) {

			request.setAttribute("errorMessage", "Todos los campos son obligatorios.");

			String[] categorias = Categoria.obtenerCategorias();
			request.setAttribute("categorias", categorias);

			RequestDispatcher dispatcher = request.getRequestDispatcher("crearProducto.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Producto productoExistente = gestionProducto.buscarProducto(nombre);
		if (productoExistente != null) {
			request.setAttribute("errorMessage", "El producto con ese nombre ya existe.");

			String[] categorias = Categoria.obtenerCategorias();
			request.setAttribute("categorias", categorias);

			RequestDispatcher dispatcher = request.getRequestDispatcher("crearProducto.jsp");
			dispatcher.forward(request, response);
			return;
		}

		double precio = Double.parseDouble(precioStr);
		int stock = Integer.parseInt(stockStr);

		Producto producto = new Producto(nombre, Categoria.valueOf(categoria), precio, stock);
		gestionProducto.agregarProducto(producto);

		response.sendRedirect("index.jsp");
	}
}