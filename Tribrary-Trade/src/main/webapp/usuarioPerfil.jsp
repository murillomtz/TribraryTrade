<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/head.html"%>

<title>Perfil</title>
</head>
<body class="body">
	<%@include file="./includes/header.jsp"%>
	<h1 class="titulo">Dados Pesoais</h1>
	<strong>Nome:</strong> ${usuario.nome}
	<br> <strong>Nome Especial:</strong> ${usuario.nomeSocial}
	<br> <strong>CPF:</strong> ${usuario.cpf}
	<br> <strong>Telefone:</strong> ${usuario.telefone}
	<br> <strong>E-mail:</strong> ${usuario.email}
	<br> <strong>Pontos de Troca:</strong> ${usuario.pontosDeTroca}
	<br>
	<hr>
	<h2>Endereço</h2>
	<strong>CEP:</strong> ${usuario.endereco.cep} <strong>Cidade:</strong> ${usuario.endereco.cidade} <strong>Bairro:</strong>
	${usuario.endereco.bairro}<strong> Rua:</strong> ${usuario.endereco.rua} <strong>Numero:</strong>
	${usuario.endereco.numero}

	<br>
	<a href="/UsuarioForm">Editar Perfil</a>

	<%@include file="./includes/footer.html"%>
</body>
</html>