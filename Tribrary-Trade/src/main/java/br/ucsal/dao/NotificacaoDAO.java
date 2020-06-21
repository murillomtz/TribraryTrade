package br.ucsal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Livro;
import br.ucsal.model.Notificacao;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;

public class NotificacaoDAO {

	private static Connection conexao = Conexao.getConnection();

	public List<Notificacao> listar() {
		LivroDAO livroDAO = new LivroDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Notificacao> notificacoes = new ArrayList<>();
		Livro livroDesejado;
		Usuario usuarioSolicitante;
		try {
			String sql= "select * from notificacao;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Notificacao not = new Notificacao();
				not.setIdNotificacao(rs.getInt("id"));
				not.setStatus(rs.getString("status"));
				
				livroDesejado= livroDAO.buscarPorID(rs.getInt("id_livro_desejado"));
				not.setLivroDesejado(livroDesejado);
				
				usuarioSolicitante = usuarioDAO.buscarPorId(rs.getInt("id_usuario_solicitante"));
				not.setUsuarioSolicitante(usuarioSolicitante);
				
				notificacoes.add(not);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notificacoes;
	}
	
	public List<Notificacao> listarPeloUsuario(Integer idUser) {
		LivroDAO livroDAO = new LivroDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		LivrosDoUsuarioDAO livrosDoUsuarioDAO = new LivrosDoUsuarioDAO();
		List<Notificacao> notificacoes = new ArrayList<>();	
		Livro livroDesejado=null;
		Usuario usuarioSolicitante=null;
		
		
		List<Livro> livrosUsuarioLogado =new ArrayList<Livro>();
		livrosUsuarioLogado.addAll(livroDAO.listarPorUsuario(idUser));
		try {
			for (Livro l : livrosUsuarioLogado) {
				String sql= "select * from notificacao where id_livro_desejado = ?;";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, l.getIdLivro());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Notificacao not = new Notificacao();
					not.setIdNotificacao(rs.getInt("id"));
					not.setStatus(rs.getString("status"));
					
					livroDesejado= livroDAO.buscarPorID(rs.getInt("id_livro_desejado"));
					not.setLivroDesejado(livroDesejado);
					
					usuarioSolicitante = usuarioDAO.buscarPorId(rs.getInt("id_usuario_solicitante"));
					not.setUsuarioSolicitante(usuarioSolicitante);
							
					not.setLivrosDoUsuario(livrosDoUsuarioDAO.buscarPeloIdNotificacao(not.getIdNotificacao()));
					
					notificacoes.add(not);
			}	
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notificacoes;
	}

	public boolean permissaoSolicitarNovaTroca(Integer idUsuario, Integer idLivro) {
		try {
			String sql= "select * from notificacao where id_livro_desejado = ? and id_usuario_solicitante = ?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idLivro);
			ps.setInt(2, idUsuario);
			System.out.println(idLivro);
			System.out.println(idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void inserir(Notificacao notificacao) {
		LivrosDoUsuarioDAO liDoUsuarioDAO = new LivrosDoUsuarioDAO();
		Usuario usuarioSolicitante= notificacao.getUsuarioSolicitante();
		try {

			String sql = "insert into notificacao(id_livro_desejado, id_usuario_solicitante, status) values (?, ?, ?);";
			PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, notificacao.getLivroDesejado().getIdLivro());
			ps.setInt(2, usuarioSolicitante.getIdUsuario());
			ps.setString(3, notificacao.getStatus());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			Integer id = -1;
			if(rs.next()) {
				id=rs.getInt("id");
			}
			notificacao.setIdNotificacao(id);	
			
			liDoUsuarioDAO.inserir(notificacao);
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Notificacao getByID(int id) {
		Notificacao not= null;
		LivroDAO livroDAO = new LivroDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		LivrosDoUsuarioDAO liDoUsuarioDAO = new LivrosDoUsuarioDAO();		
		Livro livroDesejado;
		Usuario usuarioSolicitante;
		try {
			String sql="select* from notificacao where id=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				not = new Notificacao();
				not.setIdNotificacao(rs.getInt("id"));
				not.setStatus(rs.getString("status"));
				
				livroDesejado= livroDAO.buscarPorID(rs.getInt("id_livro_desejado"));
				not.setLivroDesejado(livroDesejado);
				
				usuarioSolicitante = usuarioDAO.buscarPorId(rs.getInt("id_usuario_solicitante"));
				not.setUsuarioSolicitante(usuarioSolicitante);
				
				not.setLivrosDoUsuario(liDoUsuarioDAO.buscarPeloIdNotificacao(not.getIdNotificacao()));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return not;
	}
	
	public void editarStatus(Notificacao notificacao) {
		try {

			String sql = "update notificacao set status=? where id=?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, notificacao.getStatus());
			ps.setInt(2, notificacao.getIdNotificacao());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public void close() {
//		conexao.closeConnection();
//	}

}
