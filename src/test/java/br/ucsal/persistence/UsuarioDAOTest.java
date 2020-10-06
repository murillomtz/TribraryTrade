package br.ucsal.persistence;


import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Endereco;
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
public class UsuarioDAOTest {

    private UsuarioDAO dao;

    @BeforeEach
    public void setup() throws SQLException {
        dao = new UsuarioDAO();
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
    public void inserirAndBuscarParaLoginAndDeletarTest() {

        Usuario newUsuario = criarUsuarioTest("Usuario test");

        dao.inserir(newUsuario);


        Assertions.assertEquals("Usuario test", newUsuario.getNome());

        //Executar testes para esse usuario
        buscarParaLoginTest();
        editarTest();
        deletarAndBuscarParaLoginTest();


    }


    private Usuario criarUsuarioTest(String nome) {
        Endereco endereco = new Endereco(null, "46589 - 000", "Salvador", "Pituba", "Laranjeiras"
                , "205D");
        Usuario newUsuario = new Usuario(null, nome, "Tester user", "016.648.658-89", "test@email.com", "(71)98785-9628", "12345",
                50, endereco);
        return newUsuario;
    }

    @Test
    public void deletarAndBuscarParaLoginTest() {

        Usuario newUsuario = dao.buscarParaLogin("test@email.com", "12345");
        Assertions.assertNotNull(newUsuario);

        dao.deletar(newUsuario.getIdUsuario());
        Usuario otherUsuario = dao.buscarPorId(newUsuario.getIdUsuario());
        Assertions.assertNull(otherUsuario);

    }

    @Test
    public void buscarParaLoginTest() {
        Usuario newUsuario = dao.buscarParaLogin("test@email.com", "12345");
        Assertions.assertNotNull(newUsuario);
    }

    @Test
    public void editarTest() {
        Usuario newUsuario = dao.buscarParaLogin("test@email.com", "12345");
        Assertions.assertNotNull(newUsuario);

        newUsuario.setNomeSocial("Test Já Editado");

        dao.editar(newUsuario);

        String getNomeSocialUsuario = dao.buscarParaLogin("test@email.com", "12345").getNomeSocial();
        Assertions.assertEquals("Test Já Editado", getNomeSocialUsuario);

    }


}
