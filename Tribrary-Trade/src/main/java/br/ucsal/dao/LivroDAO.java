package br.ucsal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hsqldb.types.Collation;

import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;

public class LivroDAO {
	private static Connection conexao = Conexao.getConnection();
	private static GeneroDAO generoDAO = new GeneroDAO();
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public List<Livro> listar() {
		List<Livro> livros = new ArrayList<Livro>();
		try {
			String sql = "SELECT * from livro where disponibilidade=true order by id;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String sinopse = rs.getString("sinopse");
				String detalhes = rs.getString("detalhes");
				String fotoLivro = rs.getString("foto_livro");
				boolean disponibilidade = rs.getBoolean("disponibilidade");
				int idGenero = Integer.parseInt(rs.getString("id_genero"));
				int idUsuario = Integer.parseInt(rs.getString("id_usuario")); 
				
				Genero genero = generoDAO.buscarPorId(idGenero);
				Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
				
				Livro livro = new Livro(id, titulo, autor, sinopse, detalhes, fotoLivro, genero, usuario);
				livro.setDisponibilidade(disponibilidade);
				
				livros.add(livro);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.reverse(livros);
		return livros;
		}
	public List<Livro> listarPorUsuario(Integer idU) {
		List<Livro> livros = new ArrayList<Livro>();
		try {
			String sql = "SELECT * from livro where id_usuario = ?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idU);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String sinopse = rs.getString("sinopse");
				String detalhes = rs.getString("detalhes");
				String fotoLivro = rs.getString("foto_livro");
				boolean disponibilidade = rs.getBoolean("disponibilidade");
				int idGenero = Integer.parseInt(rs.getString("id_genero"));
				int idUsuario = Integer.parseInt(rs.getString("id_usuario")); 
				
				Genero genero = generoDAO.buscarPorId(idGenero);
				Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
				
				Livro livro = new Livro(id, titulo, autor, sinopse, detalhes, fotoLivro, genero, usuario);
				livro.setDisponibilidade(disponibilidade);
				livros.add(livro);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return livros;
		}

	public void inserir(Livro livro) {
		try {

			PreparedStatement ps = conexao.prepareStatement("insert into livro( titulo, autor, sinopse, detalhes, foto_livro, disponibilidade, id_genero, id_usuario) values\r\n"
							+ "( ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getSinopse());
			ps.setString(4, livro.getDetalhes());
			ps.setString(5, livro.getFotoLivro());
			ps.setBoolean(6, livro.isDisponibilidade());
			ps.setInt(7, livro.getGenero().getIdGenero());
			ps.setInt(8, livro.getUsuario().getIdUsuario());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Livro buscarPorID(int id) {
		Livro livro = null;
		try {
			String sql = "select * from livro where id=? and disponibilidade=true;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idLivro = Integer.parseInt(rs.getString("id"));
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String sinopse = rs.getString("sinopse");
				String detalhes = rs.getString("detalhes");
				String fotoLivro = rs.getString("foto_livro");
				boolean disponibilidade = rs.getBoolean("disponibilidade");
				
				int idGenero = Integer.parseInt(rs.getString("id_genero"));
				int idUsuario = Integer.parseInt(rs.getString("id_usuario")); 
				
				Genero genero = generoDAO.buscarPorId(idGenero);
				Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
				livro = new Livro(idLivro, titulo, autor, sinopse, detalhes, fotoLivro, genero, usuario);
				livro.setDisponibilidade(disponibilidade);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return livro;
	}
	
	
	public List<Livro> buscarPorNomeOuAutor(String busca) {
		List<Livro> livros = new ArrayList<Livro>();
		try {
			String sql = "select * from livro where disponibilidade=true and lower(titulo) like ? or lower(autor) like ?";
			//String sql = "select * from livro where titulo=?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, '%'+busca+'%');
			pstmt.setString(2, '%'+busca+'%');
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idLivro = Integer.parseInt(rs.getString("id"));
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String sinopse = rs.getString("sinopse");
				String detalhes = rs.getString("detalhes");
				String fotoLivro = rs.getString("foto_livro");
				boolean disponibilidade = rs.getBoolean("disponibilidade");
				
				int idGenero = Integer.parseInt(rs.getString("id_genero"));
				int idUsuario = Integer.parseInt(rs.getString("id_usuario")); 
				
				Genero genero = generoDAO.buscarPorId(idGenero);
				Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
				
				Livro livro = new Livro(idLivro, titulo, autor, sinopse, detalhes, fotoLivro, genero, usuario);
				livro.setDisponibilidade(disponibilidade);
				livros.add(livro);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return livros;
	}
	
	
	public void deletar(int id) throws SQLException{
		
			String sql = "DELETE FROM livro where id=?;";
			
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();

		
	}
	
	public void editar(Livro livro) {
		//System.out.println("Entrando no Editar: " + livro);
		try {
			String sql = "UPDATE livro set titulo=?, autor = ?, sinopse = ?, detalhes = ?, foto_livro = ?, disponibilidade=?, id_usuario = ?, id_genero = ? where id = ?;";
//			titulo, autor, sinopse, detalhes, foto_livro, id_genero, id_usuario
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, livro.getTitulo());
			pstmt.setString(2, livro.getAutor());
			pstmt.setString(3, livro.getSinopse());
			pstmt.setString(4, livro.getDetalhes());
			pstmt.setString(5, livro.getFotoLivro());
			pstmt.setBoolean(6, livro.isDisponibilidade());
			
			pstmt.setInt(7, livro.getGenero().getIdGenero());
			pstmt.setInt(8, livro.getUsuario().getIdUsuario());
			pstmt.setInt(9, livro.getIdLivro());
			
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*public void close() {
		conexao.closeConnection();
	}*/

}
