package br.ucsal.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.NotificacaoDAO;

/**
 * Servlet implementation class LivroExibirServlet
 */
@WebServlet("/MinhasNotificacoes")
public class NotificacaoListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificacaoListarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
		Integer id = (Integer) request.getSession().getAttribute("idUsuario");
		
		
		request.getSession().setAttribute("notificacoes", notificacaoDAO.listarPeloUsuario(id));
		request.getRequestDispatcher("notificacaoListar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
