package br.ucsal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Usuario;


@WebServlet("/UsuarioPerfil")
public class UsuarioPerfilServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioPerfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	Integer id = (Integer) request.getSession().getAttribute("idUsuario");
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	Usuario usuario = usuarioDAO.buscarPorId(id);
    	
    	request.setAttribute("usuario", usuario);
    	request.getRequestDispatcher("usuarioPerfil.jsp").forward(request, response);
    }
    
    
}
