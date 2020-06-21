package br.ucsal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;
import br.ucsal.model.Livro;

@WebServlet("/MeusLivros")
public class LivroListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivroDAO livroDAO = new LivroDAO();
		Integer id = (Integer) req.getSession().getAttribute("idUsuario");
		System.out.println("User session for MEUSLIVROS " +id);
		List<Livro> livros = livroDAO.listarPorUsuario(id);
		req.setAttribute("livros", livros);
		req.getRequestDispatcher("livroListar.jsp").forward(req, resp);
	}

}
