package br.ucsal.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.NotificacaoDAO;
import br.ucsal.dao.TrocaDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Livro;
import br.ucsal.model.Notificacao;
import br.ucsal.model.Troca;
import br.ucsal.model.Usuario;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/RealizarTroca")
public class RealizarTrocaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivroDAO livroDAO = new LivroDAO();
		TrocaDAO trocaDAO = new TrocaDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
		ArrayList<Livro> livrosDaTRoca = new ArrayList<Livro>();
		
		//Integer idUser = (Integer) request.getSession().getAttribute("idUsuario"); 
		String idLivroEscolhidoString = request.getParameter("livroEscolhido");

		Integer idLivro= Integer.parseInt(request.getParameter("livro"));
		Integer idNotificacao= Integer.parseInt(request.getParameter("idNotificacao"));
		
		Troca troca= new Troca();
		
		troca.setData(LocalDate.now());
		troca.setLocal("local");
		troca.setStatus("Em progresso");
		
		Notificacao notificacao = notificacaoDAO.getByID(idNotificacao);
		notificacao.setStatus("Aceita");
		notificacaoDAO.editarStatus(notificacao);
		
		
		Livro livroDoUsuario=livroDAO.buscarPorID(idLivro);
		livroDoUsuario.setDisponibilidade(false);
		livroDAO.editar(livroDoUsuario);
		livrosDaTRoca.add(livroDoUsuario);
		
		
		
		if(idLivroEscolhidoString != null) {
			Integer idLivroEscolhido= Integer.parseInt(idLivroEscolhidoString);
			Livro livroEscolhido= livroDAO.buscarPorID(idLivroEscolhido);
			livroEscolhido.setDisponibilidade(false);
			livroDAO.editar(livroEscolhido);
			livrosDaTRoca.add(livroEscolhido);
			
			troca.setLivros(livrosDaTRoca);	
			trocaDAO.inserir(troca);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else { //troca por ponto
			
			Usuario UserGanhaPonto = usuarioDAO.buscarPorId(livroDoUsuario.getUsuario().getIdUsuario()); 
			Usuario UserperdePonto=usuarioDAO.buscarPorId(notificacao.getUsuarioSolicitante().getIdUsuario()); 
			UserGanhaPonto.setPontosDeTroca(UserGanhaPonto.getPontosDeTroca()+1);
			UserperdePonto.setPontosDeTroca(UserperdePonto.getPontosDeTroca()-1);
			
			usuarioDAO.editar(UserperdePonto);
			usuarioDAO.editar(UserGanhaPonto);
			
			troca.setLivros(livrosDaTRoca);	
			trocaDAO.inserir(troca);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
			
		
		
		
		
		
	}

}
