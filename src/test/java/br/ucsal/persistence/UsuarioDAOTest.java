package br.ucsal.persistence;


import br.ucsal.dao.EnderecoDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Endereco;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static br.ucsal.builder.EndereçoBuilder.umEndereco;
import static br.ucsal.builder.UsuarioBuilder.umUsuario;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */
public class UsuarioDAOTest {

    private UsuarioDAO dao;

    private EnderecoDAO endDao;

    @BeforeEach
    public void setup() throws SQLException {
        dao = new UsuarioDAO();
        endDao = new EnderecoDAO();
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

        Endereco endereco = umEndereco().agora();

        Usuario newUser = umUsuario().comNome("Usuario test").comEndereco(null).agora();
       // System.out.println(endereco);
        dao.inserir(newUser);
        newUser = dao.buscarPorNome("Usuario test");
        endDao.inserir(newUser);


        Usuario userEsperado = dao.buscarPorNome("Usuario test");
        endereco = endDao.buscarPorIDUsuario(userEsperado.getIdUsuario());

        Assertions.assertEquals("Usuario test", userEsperado.getNome());


        dao.deletar(userEsperado.getIdUsuario());
        endDao.deletar(endereco.getIdEndereco());

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
