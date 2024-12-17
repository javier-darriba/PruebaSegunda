package com.curso.servlet;

import java.io.IOException;

import com.curso.modelo.Producto;
import com.curso.servicio.GestionProductoImpl;
import com.curso.servicio.IGestionProducto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que busca un producto en concreto
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 17/12/2024
 */
public class BuscarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IGestionProducto gestionProducto = new GestionProductoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		Producto producto = gestionProducto.buscarProducto(nombre);
		if (producto != null) {
			request.setAttribute("producto", producto);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/mostrarProducto.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Ese producto no existe, créalo o busque otro.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
