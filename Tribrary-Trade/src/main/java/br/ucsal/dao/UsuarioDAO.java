package br.ucsal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Endereco;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;

public class UsuarioDAO {

	private static Connection conexao = Conexao.getConnection();
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			String sql = "select * from usuario;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setIdUsuario(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setNomeSocial(rs.getString("nome_social"));
				u.setCpf(rs.getString("cpf"));
				u.setEmail(rs.getString("email"));
				u.setTelefone(rs.getString("telefone"));
				u.setPontosDeTroca(rs.getInt("pontos_troca"));
				u.setSenha(rs.getString("senha"));

			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public void inserir(Usuario usuario) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {

			String sql = "insert into usuario( nome, nome_social, cpf, email, telefone, pontos_troca, senha, conta_ativa) values ( ?, ?, ?, ?, ?,?,?,?);";
			PreparedStatement pstmt= conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getNomeSocial());
			pstmt.setString(3, usuario.getCpf());
			pstmt.setString(4, usuario.getEmail());
			pstmt.setString(5, usuario.getTelefone());
			pstmt.setObject(6, usuario.getPontosDeTroca());
			pstmt.setString(7, usuario.getSenha());
			pstmt.setBoolean(8, usuario.isContaAtiva());
			pstmt.execute();
			//pra mandar pro insert de endereço
			ResultSet rs = pstmt.getGeneratedKeys();
			Integer id = -1;
			if(rs.next()) {
				id=rs.getInt("id");
			}
			usuario.setIdUsuario(id);
			
			enderecoDAO.inserir(usuario);
			
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public Usuario buscarParaLogin(String email, String senha) {
		Usuario usuario = null;
		try {
			String sql = "select * from usuario where email=? and senha=?";
			PreparedStatement pstmt= conexao.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, senha);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setNomeSocial(rs.getString("nome_social"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setPontosDeTroca(rs.getInt("pontos_troca"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setContaAtiva(rs.getBoolean("conta_ativa"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	

	public Usuario buscarPorId(int id) {
		Usuario usuario = null;
		try {
			String sql = "select * from usuario where id=?";
			PreparedStatement pstmt= conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setNomeSocial(rs.getString("nome_social"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setPontosDeTroca(rs.getInt("pontos_troca"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setContaAtiva(rs.getBoolean("conta_ativa"));
				EnderecoDAO enderecoDAO = new EnderecoDAO();
				Endereco endereco = enderecoDAO.buscarPorID(id);
				usuario.setEndereco(endereco);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public void deletar(Integer id) {
		String sql="delete from usuario where id=?;";
		try {
			PreparedStatement pstmt= conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editar(Usuario usuario) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		String sql ="update usuario set nome=?, nome_social=?, cpf=?, email=?, telefone=?, pontos_troca=?,conta_ativa=?, senha=? where id=?";
		try {
			PreparedStatement pstmt= conexao.prepareStatement(sql);
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getNomeSocial());
			pstmt.setString(3, usuario.getCpf());
			pstmt.setString(4, usuario.getEmail());
			pstmt.setString(5, usuario.getTelefone());
			pstmt.setInt(6, usuario.getPontosDeTroca());
			pstmt.setBoolean(7, usuario.isContaAtiva());
			pstmt.setString(8, usuario.getSenha());
			pstmt.setInt(9, usuario.getIdUsuario());
			pstmt.executeUpdate();
			
			enderecoDAO.editar(usuario.getEndereco());
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

//	public void close() {
//		conexao.closeConnection();
//	}

}
