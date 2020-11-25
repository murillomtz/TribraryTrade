package br.ucsal.persistence;


import br.ucsal.builders.GeneroBuilderModeNeiva;
import br.ucsal.dao.GeneroDAO;
import br.ucsal.model.Genero;
import br.ucsal.util.Conexao;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */
public class GeneroDAOIntegradoTest {

    private GeneroDAO dao;

    @Before
    public void setup() throws SQLException {
        dao = new GeneroDAO();
        confereConexao();
    }

    @After
    public static void teardown() throws SQLException {
        confereConexao();
    }

    // Verifica se a conexão é nula. Se não for nula, ele passa!
    @Test
    public void GetConexaoTest() {
        Connection con = Conexao.getConnection();
        Assert.assertNotNull(con);
    }

    public static void confereConexao() throws SQLException {
        Assume.assumeTrue(Conexao.isConnectionValid()); // se a connection nao for validada abortar os testes
        // livros.delete(ID_LISTA,Conexao.getConnection(), TAB_NAME);
    }

    /***/
    @Test
    public void listarTest() {
        List<Genero> generos = dao.listar();

        Assert.assertTrue(generos.size() >= 2);

    }

    @Test
    public void inserirEditaDeletaTest() throws SQLException {
        Genero genero = GeneroBuilderModeNeiva.umGenero().comNome("Genero Builder").build();
        dao.inserir(genero);
        Genero generoEsperando = dao.buscarPorNome("Genero Builder");

        Assert.assertEquals("Genero Builder", generoEsperando.getNome());

        generoEsperando.setNome("Genero Builder EDITADO");

        dao.editar(generoEsperando);
        generoEsperando = dao.buscarPorNome("Genero Builder EDITADO");

        Assert.assertEquals("Genero Builder EDITADO", generoEsperando.getNome());

        dao.deletar(generoEsperando.getIdGenero());
    }

    @Test
    public void buscarPorIDTest() throws Exception {
        Genero genero = dao.buscarPorId(1);
        Assert.assertEquals("Romance", genero.getNome());

    }

}
