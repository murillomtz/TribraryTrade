package br.ucsal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.NotificacaoDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Livro;
import br.ucsal.model.Notificacao;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/SolicitarTroca")
public class SolicitarTrocaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
		LivroDAO livroDAO = new LivroDAO();
		
		Integer idUser =(Integer) request.getSession().getAttribute("idUsuario"); 
		Livro livro = (Livro) request.getSession().getAttribute("livroSessao");
		
		
		Notificacao notificacao = new Notificacao(null, livro, usuarioDAO.buscarPorId(idUser), "Esperando Resposta");
		notificacao.setLivrosDoUsuario(livroDAO.listarPorUsuario(idUser));
		notificacaoDAO.inserir(notificacao);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
