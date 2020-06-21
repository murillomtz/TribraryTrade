package br.ucsal.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.EnderecoDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Endereco;
import br.ucsal.model.Usuario;

/**
 * Servlet implementation class UsuarioSalvarServlet
 */
@WebServlet("/UsuarioSalvar")
public class UsuarioSalvarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioSalvarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();	
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		
		Integer idUser =(Integer) request.getSession().getAttribute("idUsuario");
		
		String nome= request.getParameter("nome");
		String	nomeSocial= request.getParameter("nomeSocial");
		String	cpf= request.getParameter("cpf");
		String	email=request.getParameter("email");
		String	telefone=request.getParameter("telefone");
		String	senha=request.getParameter("senha");
		//String	pontosDeTroca=request.getParameter("pontosDeTroca");
		
		String	cep=request.getParameter("cep");
		String	cidade=request.getParameter("cidade");
		String	bairro=request.getParameter("bairro");
		String	rua=request.getParameter("rua");
		String	numero=request.getParameter("numero");
		
		if(idUser== null) {
			
			Endereco endereco = new Endereco(null, cep, cidade, bairro, rua, numero);
			Usuario usuario = new Usuario(null, nome, nomeSocial, cpf, email, telefone, senha, 0, endereco);
			usuarioDAO.inserir(usuario);
			response.sendRedirect("./index.jsp");	
		}else {
			Usuario usuario=usuarioDAO.buscarPorId(idUser);
			Endereco endereco = enderecoDAO.buscarPorID(usuario.getIdUsuario());
			
			usuario.setNome(nome);
			usuario.setNomeSocial(nomeSocial);
			usuario.setCpf(cpf);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setTelefone(telefone);
			
			endereco.setCep(cep);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			endereco.setNumero(numero);
			endereco.setRua(rua);
			usuario.setEndereco(endereco);
			usuarioDAO.editar(usuario);
			response.sendRedirect("./UsuarioPerfil");	
		}
		
	}

}
