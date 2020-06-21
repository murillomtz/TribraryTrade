package br.ucsal.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.dao.GeneroDAO;
import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.util.Upload;


/**
 * Servlet implementation class LivroSalvarServlet
 */
@WebServlet("/LivroSalvar")
public class LivroSalvarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LivroSalvarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String sinopse = request.getParameter("sinopse");
		String detalhes = request.getParameter("detalhes");
		String fotoLivro = request.getParameter("fotoLivro");
		String generoID =request.getParameter("genero");
		Integer idUser =(Integer) request.getSession().getAttribute("idUsuario"); 

//		try {
//			fotoLivro = new Upload().anexos(request, response);
//			if (fotoLivro!= null) {
//
//				System.out.print("Ficheiro enviado!");
//			} else {
//				System.out.print("Ficheiro não enviado!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("PATH: "+fotoLivro);
				

		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setSinopse(sinopse);
		livro.setDetalhes(detalhes);
		livro.setFotoLivro(fotoLivro);

		GeneroDAO generoDAO = new GeneroDAO();
		int id = Integer.parseInt(generoID);
		Genero genero = generoDAO.buscarPorId(id);
		livro.setGenero(genero);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		livro.setUsuario(usuarioDAO.buscarPorId(idUser));

		LivroDAO livroDAO = new LivroDAO();
		livroDAO.inserir(livro);

		request.getRequestDispatcher("index.jsp").forward(request, response);}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String sinopse = request.getParameter("sinopse");
		String detalhes = request.getParameter("detalhes");
		String fotoLivro = request.getParameter("fotoLivro");
		String generoID =request.getParameter("genero");
//		Integer idUser = (Integer) request.getSession().getAttribute("idUsuario"); 
		
		try {
			fotoLivro = new Upload().anexos(request, response);
			if (fotoLivro!= null) {

				System.out.print("Ficheiro enviado!");
			} else {
				System.out.print("Ficheiro não enviado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PATH: "+fotoLivro);
		
//		Livro livro = new Livro();
//		livro.setTitulo(titulo);
//		livro.setAutor(autor);
//		livro.setSinopse(sinopse);
//		livro.setDetalhes(detalhes);
//		livro.setFotoLivro(fotoLivro);
//
//		GeneroDAO generoDAO = new GeneroDAO();
//		int id = Integer.parseInt(generoID);
//		Genero genero = generoDAO.buscarPorId(id);
//		livro.setGenero(genero);
//
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		livro.setUsuario(usuarioDAO.buscarPorId(idUser));
//
//		LivroDAO livroDAO = new LivroDAO();
//		livroDAO.inserir(livro);
//
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		request.setAttribute("pathFotoLivro", fotoLivro);
		doPost(request, response);
	}
	

	
}
