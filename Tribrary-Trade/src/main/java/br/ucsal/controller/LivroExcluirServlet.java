package br.ucsal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;
import br.ucsal.model.Livro;

@WebServlet("/LivroExcluir")
public class LivroExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LivroExcluirServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idSLivro = request.getParameter("id");
		Integer idUser = (Integer) request.getSession().getAttribute("idUsuario");
		LivroDAO livroDAO = new LivroDAO();
		Integer id = Integer.parseInt(idSLivro);
		Livro livro = livroDAO.buscarPorID(id);
		if (idSLivro != null && livro != null && livro.getUsuario().getIdUsuario() == idUser) {
			try {
			livroDAO.deletar(id);
			}catch (Exception e) {
			request.setAttribute("erro", "Não é possível excluir o livro pois ele está em uma troca!");		
			}
			request.getRequestDispatcher("MeusLivros").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
