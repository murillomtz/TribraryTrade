package br.ucsal.persistence;

import br.ucsal.dao.GeneroDAO;
import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Endereco;
import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;
import br.ucsal.util.Conexao;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static br.ucsal.builder.LivroBuilder.umLivroDisponivel;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */

public class LivroDAOIntegradoTest {


    private LivroDAO dao;

    private GeneroDAO generoDAO;

    private UsuarioDAO usuarioDAO;

    private GeneroDAO gdao;

    @BeforeEach
    public void setup() throws SQLException {
        dao = new LivroDAO();
        generoDAO = new GeneroDAO();
        usuarioDAO = new UsuarioDAO();
        confereConexao();
    }

    @AfterAll
    public static void teardown() throws SQLException {
        confereConexao();
    }

    @Test
    public void listarTest() throws Exception {
        List<Livro> livros = dao.listar();

        Assert.assertTrue(livros.size() >= 2);
    }

    @Test
    public void listarPorUsuarioTest() throws Exception {
        List<Livro> livros = dao.listarPorUsuario(1);

        Assert.assertTrue(livros.size() >= 1);

    }

    @Test
    public void inserirTest() throws SQLException {

        Genero gen = generoDAO.buscarPorId(1);
        Usuario user = usuarioDAO.buscarPorId(1);

        Livro book = umLivroDisponivel().comTitulo("LivroTest inserir").comUsuario(user).comGenero(gen).agora();

        dao.inserir(book);
        Livro livroEsperando = dao.buscarPorTitulo("LivroTest inserir");

        Assertions.assertEquals("LivroTest inserir", livroEsperando.getTitulo());


        dao.deletar(livroEsperando.getIdLivro());
    }

    @Test
    public void buscarPorNomeOuAutorTest() {
        List<Livro> livros = dao.buscarPorNomeOuAutor("kiera cass");

        Assert.assertTrue(livros.size() >= 1);
        Assert.assertTrue(livros.get(0).getTitulo().equals("A seleção"));


    }


    // Verifica se a conexão é nula. Se não for nula, ele passa!
    @Test
    public void GetConexaoTest() {
        Connection con = Conexao.getConnection();
        Assertions.assertNotNull(con);
    }

    // Verifica o metodo BuscarPorID()
    @Test
    public void BuscarPorIDTest() throws Exception {
        Livro livro = dao.buscarPorID(1);

        Assertions.assertEquals("Kiera Cass", livro.getAutor());
        Assertions.assertEquals("A seleção", livro.getTitulo());

    }

    @Test
    public void BuscarPorIDErrorTest() throws Exception {


        Livro livro = dao.buscarPorID(1);

        Assertions.assertNotEquals("Kiera ", livro.getAutor());
        Assertions.assertNotEquals("A s", livro.getTitulo());

    }


    public static void confereConexao() throws SQLException {
        Assumptions.assumeTrue(Conexao.isConnectionValid()); // se a connection nao for validada abortar os testes
        // livros.delete(ID_LISTA,Conexao.getConnection(), TAB_NAME);
    }

    public Livro criarLivro(Usuario usuario, Genero genero, String titulo) {
        Livro nLivro = new Livro(null, titulo, "Autor test", "Snopse test",
                "Detalhes test", "foto.jpeg", genero, usuario);
//C:\Dev\WorkSpaceMTZ\Tribary\src\main\webapp\img\img_livros\11-06-2020
        return nLivro;
    }

    private Genero criarGeneroTest(String nome) {
        Genero newGenero = new Genero(null, nome);
        return newGenero;
    }

    private Usuario criarUsuarioTest(String nome) {
        Endereco endereco = new Endereco(null, "46589 - 000", "Salvador", "Pituba", "Laranjeiras"
                , "205D");
        Usuario newUsuario = new Usuario(null, nome, "Tester user", "016.648.658-89", "test@email.com", "(71)98785-9628", "12345",
                50, endereco);
        return newUsuario;
    }

}
