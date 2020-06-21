package br.ucsal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.MetaData.Request;

import br.ucsal.dao.GeneroDAO;
import br.ucsal.dao.LivroDAO;
import br.ucsal.model.Livro;

/**
 * Servlet implementation class CadastraLivroServlet
 */
@WebServlet("/LivroForm")
public class LivroFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idSLivro = request.getParameter("id");
		Integer idUser = (Integer) request.getSession().getAttribute("idUsuario");
		GeneroDAO generoDAO = new GeneroDAO();
		LivroDAO livroDAO = new LivroDAO();


		
		if (idSLivro != null) {// Se houver um livro 
			Integer id= Integer.parseInt(idSLivro);
			Livro livro = livroDAO.buscarPorID(id);		
			
			if(livro.getUsuario().getIdUsuario()==idUser) { // Se o livro for do usuario da session
				
				request.setAttribute("lista", generoDAO.listar());
				request.setAttribute("livro", livro);
				request.getRequestDispatcher("livroEditar.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else { //Se não houver livro

			request.setAttribute("lista", generoDAO.listar());
			request.getRequestDispatcher("livroForm.jsp").forward(request, response);
		}

	}

	

}
