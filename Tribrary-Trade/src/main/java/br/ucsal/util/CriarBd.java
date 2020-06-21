package br.ucsal.util;

import java.sql.Connection;

public class CriarBd {

	public static void main(String[] args) {
		Connection c = Conexao.getConnection();
		
		try {
			new LoadTables().creatScherma(c);
		}catch (Exception e) {
			System.out.println("erro ao criar as tabelas: "+e);
		}
		
		try {
			new Insert().inserirDados(c);
		}catch (Exception e) {
			System.out.println("erro ao inserir os dados: "+e);
		}

	}

}
