package br.ucsal.persistence;


import br.ucsal.builders.UsuarioBuilderModeNeiva;
import br.ucsal.dao.EnderecoDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */
public class UsuarioDAOIntegradoTest {

    private UsuarioDAO dao;

    private EnderecoDAO endDao;

    @Before
    public void setup() throws SQLException {
        dao = new UsuarioDAO();
        endDao = new EnderecoDAO();
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
    public void listarTest() throws Exception {
        List<Usuario> usuarios = dao.listar();
        System.out.println("Lista " + usuarios);

        Assert.assertTrue(usuarios.size() >= 2);
    }

    @Test
    public void inserirEditarDeletarTest() {
        Usuario usuario = UsuarioBuilderModeNeiva.umUsuario().comNome("Usuario Builder Insert").comEndereco(endDao.buscarPorID(1)).build();

        dao.inserir(usuario);
        Usuario usuarioEsperando = dao.buscarPorNome("Usuario Builder Insert");

        Assert.assertEquals("Usuario Builder Insert", usuarioEsperando.getNome());
        usuarioEsperando.setNome("Usuario Builder EDITADO");
        dao.editar(usuarioEsperando);
        Assert.assertEquals("Usuario Builder EDITADO", usuarioEsperando.getNome());

        //TODO precisa deletar antes o endereço para depois excluir o usuario ALTERAR O COMANDO SQL
        dao.deletar(usuarioEsperando.getIdUsuario());

    }

    @Test
    public void buscarParaLoginTest() {
        Usuario usuario = dao.buscarParaLogin("murillo@email.com", "12345");
        Assert.assertNotNull(usuario);

    }

    @Test
    public void buscarPorIDTest() throws Exception {
        Usuario usuario = dao.buscarPorId(1);

        Assert.assertEquals("Murillo Caetano", usuario.getNome());

    }


}
