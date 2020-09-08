package br.ucsal.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ucsal.dao.LivroDAO;
import br.ucsal.model.Endereco;
import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import br.ucsal.util.PersitenteLinkedList;
import br.ucsal.util.interfaces.PersistentList;

public class LivroPersistenceTest {

	private static final String TAB_NAME = "livro";

	private static Long ID_LISTA = 100L;

	private static PersistentList<Livro> livros;

	private static Genero genero;

	private static Usuario usuario;

	private static Endereco endereco;
	
	private static LivroDAO ldao;

	@BeforeEach
	public void setup() throws SQLException {
		ldao = new LivroDAO();
		genero = new Genero(null, "genero teste");
		endereco = new Endereco(null, "46589-000", "Teste", "teste", "teste", "291Test");
		usuario = new Usuario(null, "Usuario teste", "teste", "016.648.658-89", "teste@teste.com", "(71)98785-9628",
				"1234", 50, endereco);
		livros = new PersitenteLinkedList<>();
		limparLista();
	}

	@AfterAll
	public static void teardown() throws SQLException {
		limparLista();
	}

	// Verifica se a conexão é nula. Se não for nula, ele passa!
	@Test
	public void testGetConexao() {
		Connection con = Conexao.getConnection();
		Assertions.assertNotNull(con);
	}

	//Verifica o metodo BuscarPorID()
 	@Test
	public void testFindById() {
		Livro livro = ldao.buscarPorID(1);

		Assertions.assertEquals("Kiera Cass", livro.getAutor());
		Assertions.assertEquals("A seleção", livro.getTitulo());
		
		
	}
 	
 	@Test
	public void testFindByIdError() {
		Livro livro = ldao.buscarPorID(1);

		Assertions.assertNotEquals("Kiera ", livro.getAutor());
		Assertions.assertNotEquals("A s", livro.getTitulo());
		
		
	}

	/*
	 * @Test
	 * 
	 * @DisplayName("Teste de inclusão de 4 elementos") public void
	 * testarPersist4Elementos() throws InvalidElementException, SQLException,
	 * IOException, ClassNotFoundException {
	 * 
	 * // Cenario - Dados de entrada // execução do metodo a ser testado
	 * 
	 * livros.add(new Livro(null, "LIVRO1", "Livro 1", "livraria 1", "livro estado",
	 * "foto.jpeg", genero, usuario)); livros.add(new Livro(null, "LIVRO2",
	 * "Livro 2", "livraria 2", "livro estado", "foto.jpeg", genero, usuario));
	 * livros.add(new Livro(null, "LIVRO3", "Livro 3", "livraria 3", "livro estado",
	 * "foto.jpeg", genero, usuario)); livros.add(new Livro(null, "LIVRO4",
	 * "Livro 4", "livraria 4", "livro estado", "foto.jpeg", genero, usuario));
	 * livros.add(new Livro(null, "LIVRO5", "Livro 5", "livraria 5", "livro estado",
	 * "foto.jpeg", genero, usuario));
	 * 
	 * livros.persist(ID_LISTA, Conexao.getConnection(), TAB_NAME);
	 * 
	 * livros.clear();
	 * 
	 * livros.load(ID_LISTA, Conexao.getConnection(), TAB_NAME);
	 * 
	 * // Saida esperada // Verificação se a saida esperada é igual a saida atual
	 * Assertions.assertAll("Verificar add", () -> Assertions.assertEquals(5,
	 * livros.size()), () -> Assertions.assertEquals("LIVRO1", livros.get(0),
	 * "O nome na posição 0 deveria ser LIVRO1"), () ->
	 * Assertions.assertEquals("LIVRO2", livros.get(1),
	 * "O nome na posição 1 deveria ser LIVRO2"), () ->
	 * Assertions.assertEquals("LIVRO3", livros.get(2)), () ->
	 * Assertions.assertEquals("LIVRO4", livros.get(3)));
	 * 
	 * }*/
	  
	  public static void limparLista() throws SQLException {
	  Assumptions.assumeTrue(Conexao.isConnectionValid()); // se a connection nao  for validada abortar os testes 
	  // livros.delete(ID_LISTA,Conexao.getConnection(), TAB_NAME);
	  }
	 
}
