package br.ucsal.persistence;


import br.ucsal.dao.GeneroDAO;
import br.ucsal.model.Genero;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */
public class GeneroDAOTest {

    private GeneroDAO dao;

    @BeforeEach
    public void setup() throws SQLException {
        dao = new GeneroDAO();
        // genero = new Genero(null, "genero teste");
        // endereco = new Endereco(null, "46589-000", "Teste", "teste", "teste",
        // "291Test");
        // usuario = new Usuario(null, "Usuario teste", "teste", "016.648.658-89",
        // "teste@teste.com", "(71)98785-9628","1234", 50, endereco);
        // livros = new PersitenteLinkedList<>();
        confereConexao();
    }

    @AfterAll
    public static void teardown() throws SQLException {
        confereConexao();
    }

    // Verifica se a conexão é nula. Se não for nula, ele passa!
    @Test
    public void GetConexaoTest() {
        Connection con = Conexao.getConnection();
        Assertions.assertNotNull(con);
    }

    public static void confereConexao() throws SQLException {
        Assumptions.assumeTrue(Conexao.isConnectionValid()); // se a connection nao for validada abortar os testes
        // livros.delete(ID_LISTA,Conexao.getConnection(), TAB_NAME);
    }


    @Test
    public void inserirAndBuscarPorNomeAndEditarAndDeletarTest() {

        Genero newGenero = criarGeneroTest("Genero test");

        dao.inserir(newGenero);


        Assertions.assertEquals("Genero test", newGenero.getNome());

        //Executar testes para esse usuario

        editarTest();
        deletarAndBuscarParaLoginTest();


    }


    private Genero criarGeneroTest(String nome) {
        Genero newGenero = new Genero(null, nome);
        return newGenero;
    }


    public void deletarAndBuscarParaLoginTest() {

        Genero newGenero = dao.buscarPorNome("Genero test Já Editado!");
        Assertions.assertNotNull(newGenero);

        dao.deletar(newGenero.getIdGenero());
        Genero otherGenero = dao.buscarPorId(newGenero.getIdGenero());
        Assertions.assertNull(otherGenero);

    }


    public void editarTest() {
        Genero newGenero = dao.buscarPorNome("Genero test");
        Assertions.assertNotNull(newGenero);

        //newGenero.setNome("Romance");
        newGenero.setNome("Genero test Já Editado!");
        dao.editar(newGenero);
        String getNomeGenero = dao.buscarPorNome("Genero test Já Editado!").getNome();
        Assertions.assertEquals("Genero test Já Editado!", getNomeGenero);


    }


}
