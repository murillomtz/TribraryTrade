package br.ucsal.controller;


import br.ucsal.builders.GeneroBuilderModeNeiva;
import br.ucsal.builders.UsuarioBuilderModeNeiva;
import br.ucsal.dao.GeneroDAO;
import br.ucsal.dao.LivroDAO;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LivroDAO.class, Livro.class})
public class LivroSalvarServletTest {
    private HttpServletRequest req;
    private HttpServletResponse res;
    private StringWriter sw;
    private UsuarioDAO usuarioDAO;
    private GeneroDAO generoDAO;
    private LivroDAO livroDAO;
    private LivroSalvarServlet servlet;

    @Before
    public void init() throws Exception {
        servlet = new LivroSalvarServlet();


        req = Mockito.mock(HttpServletRequest.class);
        res = Mockito.mock(HttpServletResponse.class);
        usuarioDAO = Mockito.mock(UsuarioDAO.class);
        generoDAO = Mockito.mock(GeneroDAO.class);
        livroDAO = Mockito.mock(LivroDAO.class);
        sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(pw);

    }

    @Test
    public void testDoPost() throws Exception {

        Genero genero = GeneroBuilderModeNeiva.umGenero().comId(100).build();
        Usuario user = UsuarioBuilderModeNeiva.umUsuario().comEmail("murillo@email.com").comId(100).build();


        when(req.getParameter("titulo")).thenReturn("Titulo Test");
        when(req.getParameter("autor")).thenReturn("Autor Test");
        when(req.getParameter("sinopse")).thenReturn("Snopse Test");
        when(req.getParameter("detalhes")).thenReturn("Detalhes Test");
        when(req.getParameter("fotoLivro")).thenReturn("FotoLivro Test");
        when(req.getParameter("genero")).thenReturn("Genero Test");


        when(generoDAO.buscarPorId(100)).thenReturn(genero);
        when(usuarioDAO.buscarPorId(100)).thenReturn(user);


        Livro livro = new Livro(null, "Titulo Test", "Autor Test", "Snopse Test", "Detalhes Test", genero, user);

        PowerMockito.whenNew(Livro.class)
                .withArguments(null, "Titulo Test", "Autor Test", "Snopse Test", "Detalhes Test", genero, user)
                .thenReturn(livro);


        PowerMockito.mockStatic(LivroDAO.class);

        servlet.doPost(req, res);

        verifyNoMoreInteractions(UsuarioDAO.class);

        PowerMockito.verifyPrivate(LivroDAO.class);
        livroDAO.inserir(livro);

        PowerMockito.verifyNoMoreInteractions(LivroDAO.class);


    }


}
