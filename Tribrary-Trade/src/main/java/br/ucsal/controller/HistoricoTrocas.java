package br.ucsal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.LivroTrocaDAO;
import br.ucsal.model.Troca;

@WebServlet("/HistoricoTrocas")
public class HistoricoTrocas extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = (Integer) request.getSession().getAttribute("idUsuario");
		LivroTrocaDAO livroTrocaDAO = new LivroTrocaDAO();
		List<Troca> trocas = livroTrocaDAO.buscarHistorico(id);
		
		
		request.setAttribute("trocas", trocas);
		request.getRequestDispatcher("historicoTrocas.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}
