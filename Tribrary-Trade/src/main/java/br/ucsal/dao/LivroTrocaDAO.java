package br.ucsal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Livro;
import br.ucsal.model.Troca;
import br.ucsal.util.Conexao;

public class LivroTrocaDAO {

	private static Connection conexao = Conexao.getConnection();
	private static LivroDAO livroDAO = new LivroDAO();
	private static TrocaDAO trocaDAO = new TrocaDAO();
	
	/**
	 * @author Renato
	 * @param idTroca
	 * @return Lista que contem dois livros. Esses dois livros são referentes ao id da troca na tabela livro_troca
	 */
	//esse método recebe o idtroca e retorna todos os dois livros daquela troca
	public List<Livro> buscarLivrosPorTroca(int idTroca) {
		List<Livro> livros = new ArrayList<>();
		try {
			String sql = "select * from livro_troca where id_troca=?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idTroca);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idLivro = Integer.parseInt(rs.getString(2));
				Livro livro = livroDAO.buscarPorID(idLivro);
				livros.add(livro);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livros;
	}
 
	public List<Troca> buscarHistorico(Integer idUsuario){
		List<Troca> trocas = new ArrayList<Troca>();
		try {
			String sql = "select L.id, LT.id_troca, U.nome\r\n" + 
					"from livro L, livro_troca LT, usuario U\r\n" + 
					"where L.id = LT.id_livro and L.id_usuario=U.id and U.id=?;";
			
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idUsuario);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idTroca = Integer.parseInt(rs.getString(2));
				Troca troca = trocaDAO.buscarPorId(idTroca);
				
				trocas.add(troca);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return trocas;
		
	}
	public void inserir(Troca troca) {
		try {
			String sql = "insert into livro_troca (id_livro, id_troca) values (?, ?)";
			for (Livro livro : troca.getLivros()) {
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, livro.getIdLivro());
				ps.setInt(2, troca.getIdTroca());
				ps.executeUpdate();
				ps.close();
			}
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Esse método deleta da tabela troca_livro todos os campos referentes ao id da troca
	 * @param idTroca
	 */
	public void deletarPorTroca(int idTroca) {
		try {
			String sql = "DELETE FROM livro_troca where id_troca=?;";
			
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idTroca);
			pstmt.executeUpdate();
			pstmt.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
