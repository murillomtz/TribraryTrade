package br.ucsal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Livro;
import br.ucsal.model.Notificacao;
import br.ucsal.util.Conexao;

public class LivrosDoUsuarioDAO {
	private static Connection conexao = Conexao.getConnection();
	
	public void inserir(Notificacao notificacao) {
		String sql = "INSERT INTO LIVRO_USUARIO (ID_LIVRO, ID_NOTIFICACAO) VALUES ( ?,? );";
		try {
			
			
			
			for (Livro livro : notificacao.getLivrosDoUsuario()) {
				PreparedStatement pstmt= conexao.prepareStatement(sql);
				pstmt.setInt(1, livro.getIdLivro());
				pstmt.setInt(2, notificacao.getIdNotificacao());
				pstmt.execute();
				pstmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Livro> buscarPeloIdNotificacao(Integer idNot) {
		LivroDAO livroDAO = new LivroDAO();
		List<Livro> livrosDoUsuario = new ArrayList<Livro>();
		String sql= "select * from livro_usuario where id_notificacao = ?;";
		
		try {
			PreparedStatement pstmt= conexao.prepareStatement(sql);
			pstmt.setInt(1, idNot);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//Livro l = new Livro();
				//l.setIdLivro(rs.getInt("id_livro"));
				livrosDoUsuario.add(livroDAO.buscarPorID(rs.getInt("id_livro")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livrosDoUsuario;
	}
}
