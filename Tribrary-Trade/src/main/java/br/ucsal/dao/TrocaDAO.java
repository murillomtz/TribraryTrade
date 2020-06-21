package br.ucsal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Troca;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;

public class TrocaDAO {

	private static Connection conexao = Conexao.getConnection();
	private static LivroTrocaDAO livroTrocaDAO = new LivroTrocaDAO();
	
	public List<Troca> listar() {
		List<Troca> trocas = new ArrayList<>();
		try {
			String sql = "SELECT * from troca ;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				LocalDate data= LocalDate.parse(rs.getString(2));
				String local = rs.getString(3);
				String status = rs.getString(4);
				List<Livro> livros = livroTrocaDAO.buscarLivrosPorTroca(id);
				
				Troca troca = new Troca(id, data, local, status, livros);
				trocas.add(troca);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trocas;
	}
	
	//esse método chama o metodo inserir da classe LivroTrocaDAO
	public void inserir(Troca troca) {
		try {
			String sql = "insert into troca (data, localidade, status) values (?, ?, ?);";
			PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Date.valueOf(troca.getData()));
			ps.setString(2, troca.getLocal());
			ps.setString(3, troca.getStatus());
			ps.executeUpdate();
			
			
			ResultSet rs = ps.getGeneratedKeys();
			Integer id = -1;
			if(rs.next()) {
				id=rs.getInt("id");
			}
			troca.setIdTroca(id);
			livroTrocaDAO.inserir(troca);
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Troca buscarPorId(int id) {
		Troca troca = null;
		try {
			String sql = "select * from troca where id=?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idTroca = Integer.parseInt(rs.getString(1));
				LocalDate data= LocalDate.parse(rs.getString(2));
				String local = rs.getString(3);
				String status = rs.getString(4);
				List<Livro> livros = livroTrocaDAO.buscarLivrosPorTroca(id);
				troca = new Troca(idTroca, data, local, status, livros);
						
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return troca;

	}

	public void editar(Troca troca) {
		try {
			String sql = "UPDATE troca " + "set data=?, local = ?, status = ?" + "where id = ?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			
			pstmt.setDate(1, Date.valueOf(troca.getData()));
			pstmt.setString(2, troca.getLocal());
			pstmt.setString(3, troca.getStatus());
			pstmt.setInt(4, troca.getIdTroca());
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//daqui pra baixo ainda n olhei
	public void deletar(int id) {
		livroTrocaDAO.deletarPorTroca(id);
	
		try {
			String sql = "DELETE FROM troca where id=?;";
			
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*public void close() {
		conexao.closeConnection();
	}*/

}

