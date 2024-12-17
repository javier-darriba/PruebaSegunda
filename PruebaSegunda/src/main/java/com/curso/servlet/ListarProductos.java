package com.curso.servlet;

import java.io.IOException;
import java.util.List;

import com.curso.modelo.Producto;
import com.curso.servicio.GestionProductoImpl;
import com.curso.servicio.IGestionProducto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que busca los producto de la pagina y redirige a la vista que los
 * muestra
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class ListarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IGestionProducto gestionProducto = new GestionProductoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = gestionProducto.listarProductos();
		if (productos.isEmpty()) {
			request.setAttribute("errorMessage", "No hay productos que mostrar.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.setAttribute("productos", productos);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listadoProductos.jsp");
		dispatcher.forward(request, response);
	}
}
