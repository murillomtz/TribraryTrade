package br.ucsal.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
	
	public void inserirDados(Connection c) throws SQLException {
	Statement stmt = c.createStatement();
	
	stmt.executeUpdate("insert into usuario(id, nome, nome_social, cpf, email, telefone, pontos_troca, senha, conta_ativa) values\r\n" + 
			"(default, 'Renato Gomes', 'Renato', '016.648.658-89', 'renato@email.com', '(71)98785-9628', 50, '12345', true),\r\n" + 
			"(default, 'Yla Buri', 'Yla', '613.697.465-45', 'yla@email.com', '(71)98956-6122', 50, '12345', true);");
	
	stmt.executeUpdate("insert into endereco(id, cep, cidade, bairro, rua, numero, id_usuario) values\r\n" + 
			"(default, '46589-000', 'Camaçari', 'Monte Gordo', 'Rua da floresta', 'S/N', 1),\r\n" + 
			"(default, '42658-310', 'Simões Filho', 'Bairro', 'Rua da pedras', '0697', 2);");
	
	stmt.executeUpdate("insert into genero(id, nome) values\r\n" + 
			"(default, 'Romance'),\r\n" + 
			"(default, 'Ação');");
	
	stmt.executeUpdate("insert into livro(id, titulo, autor, sinopse, detalhes, foto_livro, id_genero, id_usuario, disponibilidade) values\r\n" + 
			"(default, 'A seleção', 'Kiera Cass', 'Muitas garotas sonham em ser princesas, mas este não é o caso de America Singer...', 'Livro usado em perfeito estado', 'foto.jpeg', 1, 1, true),\r\n" + 
			"(default, 'O ladrão de raios', 'Rick Riordan', 'A vida do adolescente Percy Jackson...', 'Livro em bom estado, com algumas marcas de uso', 'foto.jpeg', 2, 2, true);");
	

	
	stmt.close();
	c.close();
	}
}
