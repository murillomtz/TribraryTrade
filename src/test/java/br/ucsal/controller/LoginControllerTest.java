package br.ucsal.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    HttpServletRequest req;
    HttpServletResponse res;
    StringWriter sw;

    @Before
    public void init() {
        try {
            req = Mockito.mock(HttpServletRequest.class);
            res = Mockito.mock(HttpServletResponse.class);
            sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            when(res.getWriter()).thenReturn(pw);
        } catch (Exception e) {
        }
    }

    @Test
    public void correctUsernameInRequest() throws ServletException, IOException {


            when(req.getParameter("email")).thenReturn("murillo@email.com");
            when(req.getParameter("senha")).thenReturn("12345");
            new LoginServlet().doPost(req, res);
            assertEquals("IMC: 25.3\n", sw.toString());


    }


}

