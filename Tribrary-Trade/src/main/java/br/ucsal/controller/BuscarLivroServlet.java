package br.ucsal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;
import br.ucsal.model.Livro;

/**
 * Servlet implementation class LivroExibirServlet
 */
@WebServlet("/BuscarLivro")
public class BuscarLivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuscarLivroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busca = request.getParameter("titulo");
		busca = busca.toLowerCase();
		LivroDAO livroDAO = new LivroDAO();
		List<Livro> livros = livroDAO.buscarPorNomeOuAutor(busca);
		if (livros.isEmpty()) {
			request.setAttribute("erro", "Não foi encontrado registros para esse livro.");
		} else {
			for (Livro livro2 : livros) {				
				System.out.println(livro2.getIdLivro());
				System.out.println(livro2.getTitulo());
			}
			request.setAttribute("livros", livros);
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
