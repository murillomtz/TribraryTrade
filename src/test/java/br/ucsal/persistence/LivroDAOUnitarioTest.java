package br.ucsal.persistence;

import br.ucsal.builder.GeneroBuilderModeNeiva;
import br.ucsal.builder.LivroBuilderModeNeiva;
import br.ucsal.builder.UsuarioBuilderModeNeiva;
import br.ucsal.dao.GeneroDAO;
import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO *NÃO COMITA* OS LOGS DOS DB ####
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LivroDAO.class, GeneroDAO.class, UsuarioDAO.class})
public class LivroDAOUnitarioTest {


    private LivroDAO dao;

    @Mock
    private GeneroDAO generoDAO;
    @Mock
    private UsuarioDAO usuarioDAO;
    @Mock
    private ResultSet rs;
    @Mock
    private PreparedStatement preparedStatementMock;
    @Mock
    private Connection connectionMock;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        dao = new LivroDAO();
        generoDAO = Mockito.mock(GeneroDAO.class);
        usuarioDAO = Mockito.mock(UsuarioDAO.class);
        rs = Mockito.mock(ResultSet.class);
        preparedStatementMock = Mockito.mock(PreparedStatement.class);
        connectionMock = Mockito.mock(Connection.class);


    }

    @Test
    public void listandoPowerMockTest() throws Exception {

        PowerMockito.mockStatic(UsuarioDAO.class);
        Usuario usuario = new UsuarioBuilderModeNeiva().comNome("Usuario Builder").build();
        PowerMockito.when(UsuarioDAO.class, "buscarPorId", Mockito.any()).thenReturn(usuario);

        PowerMockito.mockStatic(GeneroDAO.class);
        Genero genero = new GeneroBuilderModeNeiva().comNome("Genero Builder").build();
        PowerMockito.when(GeneroDAO.class, "buscarPorId", Mockito.any()).thenReturn(genero);

        String sql = "SELECT * from livro where disponibilidade=true order by id;";
        dao = new LivroDAO();
        LivroDAO spy1 = PowerMockito.spy(dao);

        PowerMockito.when(connectionMock).thenReturn(Conexao.getConnection());
        //PowerMockito.whenNew(PreparedStatement.class).withNoArguments().thenReturn(connectionMock.prepareStatement(sql));
        PowerMockito.doReturn(preparedStatementMock).when(connectionMock).prepareStatement(sql);
        PowerMockito.doReturn(connectionMock).when(preparedStatementMock).executeQuery();

        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);

        Mockito.when(rs.getInt("id")).thenReturn(100);
        Mockito.when(rs.getString("titulo")).thenReturn("Livro Builder");
        Mockito.when(rs.getString("autor")).thenReturn("Authire builder");
        Mockito.when(rs.getString("sinopse")).thenReturn("Sinopse Builder");
        Mockito.when(rs.getString("detalhes")).thenReturn("Detalhes builder");
        Mockito.when(rs.getString("foto_livro")).thenReturn("foto.jpg");
        Mockito.when(rs.getBoolean("disponibilidade")).thenReturn(true);
        Mockito.when(rs.getInt("id_genero")).thenReturn(1);
        Mockito.when(rs.getInt("id_usuario")).thenReturn(1);

        Livro livro = new Livro(100, "Livro Builder", "Authire builder", "Sinopse Builder", "Detalhes builder"
                , "foto.jpg", genero, usuario);
        PowerMockito.whenNew(Livro.class).withArguments(100, "Livro Builder", "Authire builder", "Sinopse Builder", "Detalhes builder"
                , "foto.jpg", genero, usuario).thenReturn(livro);

        PowerMockito.mockStatic(LivroDAO.class);

        PowerMockito.verifyPrivate(LivroDAO.class);
        List<Livro> livros = spy1.listar();
        PowerMockito.verifyNoMoreInteractions(LivroDAO.class);

    }


    public void listarTest() throws Exception {


    }


    public void listarPorUsuarioTest() throws Exception {
        List<Livro> livros = dao.listarPorUsuario(1);

        Assert.assertTrue(livros.size() >= 1);

    }


    public void inserirTest() throws SQLException {

        Genero gen = generoDAO.buscarPorId(1);
        Usuario user = usuarioDAO.buscarPorId(1);

        Livro book = LivroBuilderModeNeiva.umLivro().comTitulo("LivroTest inserir")
                .comUsuario(user).comGenero(gen).build();
        dao.inserir(book);
        Livro livroEsperando = dao.buscarPorTitulo("LivroTest inserir");

        Assertions.assertEquals("LivroTest inserir", livroEsperando.getTitulo());


        dao.deletar(livroEsperando.getIdLivro());
    }


    public void buscarPorNomeOuAutorTest() {
        List<Livro> livros = dao.buscarPorNomeOuAutor("kiera cass");

        Assert.assertTrue(livros.size() >= 1);
        Assert.assertTrue(livros.get(0).getTitulo().equals("A seleção"));


    }


    // Verifica se a conexão é nula. Se não for nula, ele passa!

    public void getConexaoTest() {
        Connection con = Conexao.getConnection();
        Assertions.assertNotNull(con);
    }

    // Verifica o metodo BuscarPorID()

    public void buscarPorIDTest() throws Exception {
        Livro livro = dao.buscarPorID(1);

        Assertions.assertEquals("Kiera Cass", livro.getAutor());
        Assertions.assertEquals("A seleção", livro.getTitulo());

    }


    public void editarLivro() throws SQLException {
        Genero gen = generoDAO.buscarPorId(1);
        Usuario user = usuarioDAO.buscarPorId(1);


        Livro book = LivroBuilderModeNeiva.umLivro().comTitulo("LivroTest inserir")
                .comUsuario(user).comGenero(gen).build();

        dao.inserir(book);
        // dao.inserir(book);

        Livro livroEsperando = dao.buscarPorTitulo("LivroTest inserir");

        Assertions.assertEquals("LivroTest inserir", livroEsperando.getTitulo());

        livroEsperando.setTitulo("LivroTest EDITADO");

        dao.editar(livroEsperando);
        livroEsperando = dao.buscarPorTitulo("LivroTest EDITADO");

        Assertions.assertEquals("LivroTest EDITADO", livroEsperando.getTitulo());


        dao.deletar(livroEsperando.getIdLivro());

    }


    public void buscarPorIDErrorTest() throws Exception {


        Livro livro = dao.buscarPorID(1);

        Assertions.assertNotEquals("Kiera ", livro.getAutor());
        Assertions.assertNotEquals("A s", livro.getTitulo());

    }


    public static void confereConexao() throws SQLException {
        Assumptions.assumeTrue(Conexao.isConnectionValid()); // se a connection nao for validada abortar os testes
        // livros.delete(ID_LISTA,Conexao.getConnection(), TAB_NAME);
    }


}