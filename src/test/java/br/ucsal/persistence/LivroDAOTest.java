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
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */

public class LivroDAOTest {

    private UsuarioDAO udao;

    private static Genero genero;

    private static Usuario usuario;

    private static Endereco endereco;

    private static LivroDAO ldao;



    private GeneroDAO gdao;


    @Mock
    private Livro livro = null;

    @Mock
    private static LivroDAO mockLivrodao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeEach
    public void setup() throws SQLException {
        ldao = new LivroDAO();
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

    @Test
    public void listarTest() throws Exception {
        List<Livro> livros = ldao.listar();

        Assert.assertTrue(livros.size() >= 3);
    }

  /*  @Test
    public void listarExeptionTest() throws Exception {
        List<Livro> livros = ldao.listar();

        when(mockLivrodao.listar()).thenThrow(new Exception("Lista nao localizado"));
        exception.expect(Exception.class);
        exception.expectMessage("Lista nao localizado");




        Assert.assertThat("Lista nao localizado", CoreMatchers.is("Lista nao localizado"));

    }*/


    @Test
    public void listarPorUsuarioTest() throws Exception {
        List<Livro> livros = ldao.listarPorUsuario(1);

        Assert.assertTrue(livros.size() >= 2);

    }

    @Test
    public void inserirTest() {
        Usuario user = udao.buscarPorId(1);
        Genero gen = gdao.buscarPorId(1);

        Livro book = criarLivro(user, gen);
        ldao.inserir(book);
        ldao.buscarPorNomeOuAutor("Autor test");

        Assertions.assertEquals("Livro para Test", book.getTitulo());

        //USAR METODO DE APAGAR


    }

    @Test
    public void buscarPorNomeOuAutorTest() {
        List<Livro> livros = ldao.buscarPorNomeOuAutor("kiera cass");

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
        Livro livro = ldao.buscarPorID(1);

        Assertions.assertEquals("Kiera Cass", livro.getAutor());
        Assertions.assertEquals("A seleção", livro.getTitulo());

    }

    @Test
    public void BuscarPorIDErrorTest() throws Exception {


        Livro livro = ldao.buscarPorID(1);

        Assertions.assertNotEquals("Kiera ", livro.getAutor());
        Assertions.assertNotEquals("A s", livro.getTitulo());

    }


    public static void confereConexao() throws SQLException {
        Assumptions.assumeTrue(Conexao.isConnectionValid()); // se a connection nao for validada abortar os testes
        // livros.delete(ID_LISTA,Conexao.getConnection(), TAB_NAME);
    }

    public Livro criarLivro(Usuario usuario, Genero genero) {
        Livro nLivro = new Livro(null, "Livro para Test", "Autor test", "Snopse test",
                "Detalhes test", "capaDefaut.jpg", genero, usuario);

        return nLivro;
    }

    public Genero criarGenero() {
        Genero nGenero = new Genero(null, "Genero test");
        return nGenero;
    }

    public Usuario criarUsuario() {
        Endereco endereco = criarEndereco();
        Usuario newUsuario = new Usuario(null, "Usuario test", "Tester user", "016.648.658-89", "test@email.com", "(71)98785-9628", "12345",
                50, endereco);

        return newUsuario;
    }

    public Endereco criarEndereco() {
        Endereco endereco = new Endereco(null, "46589 - 000", "Salvador", "Pituba", "Laranjeiras"
                , "205D");

        return endereco;
    }

}
