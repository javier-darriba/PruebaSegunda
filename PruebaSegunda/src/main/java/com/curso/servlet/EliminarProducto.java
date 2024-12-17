package com.curso.servlet;

import java.io.IOException;
import java.util.List;

import com.curso.modelo.Producto;
import com.curso.servicio.GestionProductoImpl;
import com.curso.servicio.IGestionProducto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que elimina un producto indicado y redirige al inicio
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IGestionProducto gestionProducto = new GestionProductoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = gestionProducto.listarProductos();
		if (productos.isEmpty()) {
			request.setAttribute("errorMessage", "No hay productos que eliminar.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("productos", productos);
			request.getRequestDispatcher("eliminarProducto.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		boolean eliminado = gestionProducto.eliminarProducto(nombre);

		if (eliminado) {
			request.setAttribute("successMessage", "El producto fue eliminado con éxito.");
		} else {
			request.setAttribute("errorMessage", "Hubo un error y el producto no se eliminó.");
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
