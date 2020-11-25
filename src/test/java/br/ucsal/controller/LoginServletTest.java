package br.ucsal.controller;

import br.ucsal.builders.UsuarioBuilderModeNeiva;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class LoginServletTest {
    private HttpServletRequest req;
    private HttpServletResponse res;
    private StringWriter sw;
    private UsuarioDAO usuarioDAO;

    private LoginServlet servlet;


    @Before
    public void init() throws IOException {
        servlet = new LoginServlet();


        req = Mockito.mock(HttpServletRequest.class);
        res = Mockito.mock(HttpServletResponse.class);
        usuarioDAO = Mockito.mock(UsuarioDAO.class);
        sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(pw);


    }

    @Test
    public void testDoGet() throws Exception {
        String email = "murillo@email.com";
        String senha = "12345";
        Usuario user = UsuarioBuilderModeNeiva.umUsuario().comEmail(email).comSenha(senha).build();


        when(req.getParameter("email")).thenReturn(email);
        when(req.getParameter("senha")).thenReturn(senha);
        when(usuarioDAO.buscarParaLogin(email, senha)).thenReturn(user);
        servlet.doPost(req, res);
        verifyNoMoreInteractions(UsuarioDAO.class);
        verify(req, atLeast(10)).getParameter("email");
        //assertTrue(sw.toString().contains("My expected string"));


    }

    //@Test
    public void testDoGet2() throws Exception {
       /* String email = "murillo@email.com";
        String senha = "12345";
        Usuario user = UsuarioBuilderModeNeiva.umUsuario().comEmail(email).comSenha(senha).build();


        request.addParameter("email", "murillo@email.com");
        request.addParameter("senha", "12345");
        when(usuarioDAO.buscarParaLogin(email, senha)).thenReturn(user);
        servlet.doPost(request, response);

        verifyNoMoreInteractions(UsuarioDAO.class);
        verify(req, atLeast(10)).getParameter("email");
        //assertTrue(sw.toString().contains("My expected string"));*/


    }

}
