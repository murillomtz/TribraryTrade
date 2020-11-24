Como um usuario
desejo verificar o email e senha
de modo que possa saber se o mesmo foi aprovado

Cenário: Um usuario gostaria de fazer o login
Dado que estou em um site com o link http://localhost:8081/login.jsp
Quando informo o email murillo@email.com
E informo a senha 12345
Então o link esperado é http://localhost:8081/Login

Cenário: Um usuario gostaria de fazer o login
Dado que estou em um site com o link http://localhost:8081/
Quando informo o email murillo@email.com
E informo a senha 232323
Então o link esperado é http://localhost:8081/Login



