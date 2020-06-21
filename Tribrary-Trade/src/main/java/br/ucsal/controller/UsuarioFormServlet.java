package br.ucsal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Usuario;

/**
 * Servlet implementation class CadastraUsuarioServlet
 */
@WebServlet("/UsuarioForm")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Integer idUser =(Integer) request.getSession().getAttribute("idUsuario"); 
		Usuario user = null;
		
		if (idUser != null) {
			user= usuarioDAO.buscarPorId(idUser);
		}
		
		request.setAttribute("usuario", user);
		request.getRequestDispatcher("usuarioForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	

}
