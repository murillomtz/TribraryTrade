package br.ucsal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Genero;
import br.ucsal.util.Conexao;

public class GeneroDAO {
		private static Connection conexao = Conexao.getConnection();

		public List<Genero> listar() {
			List<Genero> generos = new ArrayList<>();
			try {
				String sql = "SELECT * from genero ;";
				PreparedStatement pstmt = conexao.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = Integer.parseInt(rs.getString(1));
					String nome = rs.getString(2);
					Genero genero = new Genero(id, nome);
					generos.add(genero);
				}
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return generos;
		}

		public void inserir(Genero genero) {
			try {
				String sql = "insert into cursos (nome) values (?);";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, genero.getNome());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		public Genero buscarPorId(int id) {
			Genero genero = null;
			try {
				String sql = "select * from genero where id=?;";
				PreparedStatement pstmt = conexao.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					int idCurso = Integer.parseInt(rs.getString(1));
					String nome = rs.getString(2);
					genero = new Genero(idCurso, nome);
				}
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return genero;
		}

		public void editar(Genero genero) {
			try {
				String sql = "UPDATE genero " + "set nome=?" + "where id = ?;";

				PreparedStatement pstmt = conexao.prepareStatement(sql);
				pstmt.setString(1, genero.getNome());
				pstmt.setInt(2, genero.getIdGenero());
				pstmt.executeUpdate();	
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void deletar(int id) {
			try {
				String sql = "DELETE FROM genero where id=?;";
				
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

