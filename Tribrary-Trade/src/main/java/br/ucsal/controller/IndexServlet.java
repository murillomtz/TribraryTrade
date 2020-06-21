package br.ucsal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/teste")
public class IndexServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Do Get Index");
		LivroDAO livroDAO = new LivroDAO();

		request.setAttribute("livros", livroDAO.listar());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("Do Post Index");
//		LivroDAO livroDAO = new LivroDAO();
//
//		request.setAttribute("livros", livroDAO.listar());
//		request.getRequestDispatcher("index.jsp").forward(request, response);
//	}

}
