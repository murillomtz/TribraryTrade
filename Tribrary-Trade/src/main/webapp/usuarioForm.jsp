<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Usuário</title>
<%@include file="./includes/head.html"%>
</head>
<body class="body">
<%@include file="./includes/header.html"%>
	<div>
		<div>
			<h1 class="titulo">Inserir Usuário</h1>
		</div>
		<form action="UsuarioSalvar" method="post">


			Nome: 
			<input value="${usuario.nome}" class= "form-control" type="text" name="nome" /><br> 
			Nome Social: 
			<input value="${usuario.nomeSocial}" class="form-control" type="text" name="nomeSocial" /><br> 
			CPF:
			<input value="${usuario.cpf}" class="form-control" type="text" name="cpf" /><br>
			 E-mail:
			 <input value="${usuario.email}"  class="form-control" type="email" name="email" /><br>
			Telefone:
			<input value="${usuario.telefone}"  class="form-control" type="text" name="telefone" /><br> 
			Senha:
			<input value="${usuario.senha}" class="form-control" type="text" name="senha" /><br> 
			
			<hr>
			Endereço:<br>
			cep:
			<input value="${usuario.endereco.cep}" class="form-control" type="text" name="cep" /><br> 
			cidade:
			<input value="${usuario.endereco.cidade}" class="form-control" type="text" name="cidade" /><br> 
			bairro:
			<input value="${usuario.endereco.bairro}" class="form-control" type="text" name="bairro" /><br> 
			rua:
			<input value="${usuario.endereco.rua}" class="form-control" type="text" name="rua" /><br> 
			numero:
			<input value="${usuario.endereco.numero}" class="form-control" type="text" name="numero" /><br> 
			
			<button class="btn btnLogin" type="submit">Salvar</button>
			
		</form>

	</div>
	<%@include file="./includes/footer.html"%>
</body>
</html>