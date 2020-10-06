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

/**
 * OS METODOS DESSA CLASSE SÃO BASEADOS NO BANCO DE DADOS ATUAIS
 * AO DELETAR DADOS DO BANCO PODE HAVER ERROS.
 * <p>
 * ####   CASO VOCE ALTERE ALGO **NÃO COMITA** OS LOGS DOS DB ####
 */

public class LivroDAOTest {

    private UsuarioDAO udao;

    private Genero genero;

    private Usuario usuario;

    private Endereco endereco;

    private LivroDAO dao;

    private GeneroDAO generoDAO;

    private UsuarioDAO usuarioDAO;

    private GeneroDAO gdao;

    @BeforeEach
    public void setup() throws SQLException {
        dao = new LivroDAO();
        confereConexao();
    }

    @AfterAll
    public static void teardown() throws SQLException {
        confereConexao();
    }

    @Test
    public void listarTest() throws Exception {


        /*
         * fazer assim quando arruamr o inserir
         *
         * */

        /* //Cria 2 Generos para inserir no banco
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
        dao.deletar(nomeg2.getIdGenero());*/

        List<Livro> livros = dao.listar();

        Assert.assertTrue(livros.size() >= 2);
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
        List<Livro> livros = dao.listarPorUsuario(1);

        Assert.assertTrue(livros.size() >= 2);

    }

    @Test
    public void inserirTest() throws SQLException {
        Usuario user = criarUsuarioTest("UserTest Inserir");
        Genero gen = criarGeneroTest("GeneroTest inserir");

        usuarioDAO.inserir(user);
        generoDAO.inserir(gen);

        Livro book = criarLivro(user, gen, "LivroTest inserir");
        dao.inserir(book);
        Livro livroEsperando = dao.buscarPorTitulo("LivroTest inserir");

        Assertions.assertEquals("LivroTest inserir", livroEsperando);

        usuarioDAO.deletar(usuarioDAO.buscarParaLogin("UserTest Inserir").getIdUsuario());
        dao.deletar(livroEsperando.getIdLivro());

        //USAR METODO DE APAGAR


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
                "Detalhes test", "main\\webapp\\img\\img_livros\\11-06-2020\\capaDefaut.jpg", genero, usuario);
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
