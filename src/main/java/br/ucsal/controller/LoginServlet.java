package br.ucsal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private LoginBO service = new LoginBO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario user = usuarioDAO.buscarParaLogin(email, senha);
		
		if(user!=null) {
			request.getSession().setAttribute("usuario", user);
			
			request.getSession().setAttribute("idUsuario", user.getIdUsuario());
			
//			HttpSession sessaoUsuario = request.getSession();
//	        sessaoUsuario.setAttribute("usuario",user);
//	        sessaoUsuario.setMaxInactiveInterval(10);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else {
			request.setAttribute("erro", "E-mail ou senha inválido!");
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		}
		

} 
}
