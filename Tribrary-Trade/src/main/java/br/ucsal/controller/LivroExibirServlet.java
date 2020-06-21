package br.ucsal.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.NotificacaoDAO;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;

/**
 * Servlet implementation class LivroExibirServlet
 */
@WebServlet("/LivroExibir")
public class LivroExibirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivroExibirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivroDAO livroDAO = new LivroDAO();
		String idS= request.getParameter("id");
		Integer idLivro = Integer.parseInt(idS);
		Livro livro =  livroDAO.buscarPorID(idLivro);
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
		if (notificacaoDAO.permissaoSolicitarNovaTroca(usuario.getIdUsuario(), livro.getIdLivro())) {
			request.setAttribute("erro", "Troca já solicitada");
		}
		request.getSession().setAttribute("livroSessao",livro);			
		request.getRequestDispatcher("livroExibir.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
