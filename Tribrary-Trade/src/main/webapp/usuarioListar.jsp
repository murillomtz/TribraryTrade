<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuarios</title>
<%@include file="./includes/head.html"%>
</head>
<body>
	<div class="">
		<div class="">
			<h1>Lista de Usuarios</h1>
		</div>
		<table class="table">
			<tr>
				<th>Identificação</th>
				<th>Nome</th>
				<th>NomeSocial</th>
				<th>Cpf</th>
				<th>Email</th>
				<th>Telefone</th>
				<th>Senha</th>
				<th>Pontos De Troca</th>
				<th>Lista de LIvros</th>
			</tr>

			<c:forEach var="e" items="${lista}">
				<tr>
					<td>${e.identificacao}</td>
					<td>${e.nome}</td>
					<td>${e.nomeSocial}</td>
					<td>${e.cpf}</td>
					<td>${e.email}</td>
					<td>${e.telefone}</td>
					<td>${e.senha}</td>
					<td>${e.pontosDeTroca}</td>

					<td>${e.livros.nome}</td>
				<tr>
			</c:forEach>
		</table>

	</div>


</body>
</html>