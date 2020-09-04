package br.ucsal.app.todo.controller;

import br.ucsal.app.todo.dao.UsuarioDAO;
import br.ucsal.app.todo.model.Usuario;
import org.junit.jupiter.api.Test;


public class LoginControllerTest {

    @Test
    public void testarLoginUser() {


        LoginController loginController = new LoginController();
        UsuarioDAO dao = new UsuarioDAO();
        Usuario userTest = dao.findById(18L);//lOCALIZA O USER TEST5
        //18	test5	202cb962ac59075b964b07152d234b70	test5	test5@gmail.com	ATIVO=true	ADM=false

    }
}
