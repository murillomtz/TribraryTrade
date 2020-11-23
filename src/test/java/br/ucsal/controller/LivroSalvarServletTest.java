package br.ucsal.controller;


import br.ucsal.builders.UsuarioBuilderModeNeiva;
import br.ucsal.dao.UsuarioDAO;
import br.ucsal.model.Usuario;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;

import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.verify;

public class LivroSalvarServletTest extends TestCase {
    private HttpServletRequest req;
    private HttpServletResponse res;
    private StringWriter sw;
    private UsuarioDAO usuarioDAO;

    private LoginServlet servlet;


    @BeforeEach
    public void init() throws Exception {

        servlet = new LoginServlet();

        HttpServletRequest requestMock = requestMock();
        servlet.doPost(requestMock, responseMock());
        verify(requestMock);

    }

    private RequestDispatcher requestDispatcher() {
        RequestDispatcher dispatcherMock = createNiceMock(RequestDispatcher.class);
        replay(dispatcherMock);
        return dispatcherMock;
    }

    private HttpServletResponse responseMock() throws Exception {
        HttpServletResponse responseMock = createMock(HttpServletResponse.class);
        replay(responseMock);
        return responseMock;
    }

    private HttpServletRequest requestMock() {
        String email = "murillo@email.com";
        String senha = "12345";
        Usuario user = UsuarioBuilderModeNeiva.umUsuario().comEmail(email).comSenha(senha).build();
        HttpServletRequest requestMock = createMock(HttpServletRequest.class);
        expect(requestMock.getParameter("email")).andReturn("murillo@email.com");
        expect(requestMock.getParameter("senha")).andReturn("12345");
        expect(usuarioDAO.buscarParaLogin(email, senha)).andReturn(user);
        replay(requestMock);
        return requestMock;
    }


}
