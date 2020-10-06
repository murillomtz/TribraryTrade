package br.ucsal.persistence;


import br.ucsal.dao.GeneroDAO;
import br.ucsal.model.Genero;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void listarTest() {

        //Cria 2 Generos para inserir no banco
        Genero g1 = criarGeneroTest("Genero para listar 1");
        Genero g2 = criarGeneroTest("Genero para listar 2");

        //Inserir os generos do DB
        dao.inserir(g1);
        dao.inserir(g2);

        List<Genero> generoList = dao.listar();

        Genero nomeg1 = dao.buscarPorNome("Genero para listar 1");
        Genero nomeg2 = dao.buscarPorNome("Genero para listar 2");

        Assertions.assertEquals("Genero para listar 1", nomeg1.getNome());
        Assertions.assertEquals("Genero para listar 2", nomeg2.getNome());

        dao.deletar(nomeg1.getIdGenero());
        dao.deletar(nomeg2.getIdGenero());
    }


}
