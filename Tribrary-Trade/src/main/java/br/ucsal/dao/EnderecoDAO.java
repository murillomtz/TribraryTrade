package br.ucsal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Endereco;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;


public class EnderecoDAO {
	private static Connection conexao = Conexao.getConnection();
	
	public List<Endereco> listar() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			String sql = "SELECT * from endereco ;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String cep = rs.getString(2);
				String cidade = rs.getString(3);
				String bairro = rs.getString(4);
				String rua = rs.getString(5);
				String numero = rs.getString(6);
				//int idUsuario = Integer.parseInt(rs.getString(7)); 
				//Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
				
				Endereco endereco = new Endereco(id, cep, cidade, bairro, rua, numero);
				enderecos.add(endereco);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return enderecos;
		}

	public void inserir(Usuario usuario) {
		try {

			PreparedStatement ps = conexao.prepareStatement("insert into endereco( cep, cidade, bairro, rua, numero, id_usuario) values\r\n"
							+ "( ?, ?, ?, ?,?,?);");
			ps.setString(1, usuario.getEndereco().getCep());
			ps.setString(2, usuario.getEndereco().getCidade());
			ps.setString(3, usuario.getEndereco().getBairro());
			ps.setString(4, usuario.getEndereco().getRua());
			ps.setString(5, usuario.getEndereco().getNumero());
			ps.setInt(6,usuario.getIdUsuario());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Endereco buscarPorID(int id) {
		Endereco endereco = null;
		try {
			String sql = "select * from endereco where id=?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idEndereco = Integer.parseInt(rs.getString(1));
				String cep = rs.getString(2);
				String cidade = rs.getString(3);
				String bairro = rs.getString(4);
				String rua = rs.getString(5);
				String numero = rs.getString(6);
				endereco = new Endereco(idEndereco, cep, cidade, bairro, rua, numero);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return endereco;
	}

	public Endereco buscarPorIDUsuario(int id) {
		Endereco endereco = null;
		try {
			String sql = "select * from endereco where id_usuario=?;";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idEndereco = Integer.parseInt(rs.getString(1));
				String cep = rs.getString(2);
				String cidade = rs.getString(3);
				String bairro = rs.getString(4);
				String rua = rs.getString(5);
				String numero = rs.getString(6);
				endereco = new Endereco(idEndereco, cep, cidade, bairro, rua, numero);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return endereco;
	}
	
	public void deletar(int id) {
		try {
			String sql = "DELETE FROM endereco where id=?;";
			
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editar(Endereco endereco) {
		try {
			String sql = "UPDATE endereco  set cep=?, cidade = ?, bairro = ?, rua = ?, numero = ? where id = ?;";

			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, endereco.getCep());
			pstmt.setString(2, endereco.getCidade());
			pstmt.setString(3, endereco.getBairro());
			pstmt.setString(4, endereco.getRua());
			pstmt.setString(5, endereco.getNumero());
			pstmt.setInt(6, endereco.getIdEndereco());
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
