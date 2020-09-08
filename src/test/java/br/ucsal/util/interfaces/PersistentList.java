package br.ucsal.util.interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.ucsal.util.exception.InvalidElementException;

public interface PersistentList<T> extends List<T> {

	/*
	 * Exemplo de script para criação de tabela:
	 * 
	 * create table lista1 (
	 * 
	 * seq serial not null ,
	 *
	 * id integer not null ,
	 * 
	 * element bytea not null,
	 * 
	 * constraint pk_lista1 primary key(seq));
	 * 
	 */

	/**
	 * 
	 * Persiste a coleção no banco de dados.
	 * 
	 * @param id         identificador da lista no banco de dados.
	 * @param connection objeto de conexão com o banco de dados.
	 * @param tabName    tabela na qual a lista será persistida.
	 * @throws SQLException
	 * @throws IOException
	 */
	void persist(Long id, Connection connection, String tabName) throws SQLException, IOException;

	/**
	 * 
	 * Limpa a lista atual e carrega a lista do banco de dados.
	 * 
	 * @param id         identificador da lista no banco de dados.
	 * @param connection objeto de conexão com o banco de dados.
	 * @param tabName    tabela na qual a lista será persistida.
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InvalidElementException 
	 */
	void load(Long id, Connection connection, String tabName) throws SQLException, ClassNotFoundException, IOException, InvalidElementException;

	/**
	 * 
	 * Remove a lista no banco de dados.
	 * 
	 * @param id         identificador da lista no banco de dados.
	 * @param connection objeto de conexão com o banco de dados.
	 * @param tabName    tabela na qual a lista será persistida.
	 * @throws SQLException
	 */
	void delete(Long id, Connection connection, String tabName) throws SQLException;

}
