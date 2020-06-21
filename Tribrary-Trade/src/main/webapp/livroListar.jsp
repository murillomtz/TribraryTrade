<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Lista de Livros</title>
<%@include file="./includes/head.html"%>
</head>
<body class="body">

	
		<%@include file="./includes/header.jsp"%>
	<div class="">
		<div class="">
			<h1 class="titulo">Lista de Livros</h1>
		</div>
		<label>
		 ${erro}
		</label>
		<table class="table">
			<tr>
				<th>Identificação</th>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Sinopse</th>
				<th>Detalhes</th>
				<th>Foto Livro</th>
				<th>Genero</th>
				<th>Usuario</th>
				<th>Opções</th>
			</tr>

			<c:forEach var="e" items="${livros}">
				<tr>
					<td>${e.idLivro}</td>
					<td>${e.titulo}</td>
					<td>${e.autor}</td>
					<td>${e.sinopse}</td>
					<td>${e.fotoLivro}</td>
					<td>${e.detalhes}</td>
					<td>${e.genero.nome}</td>
					<td>${e.usuario.nome}</td>
					<td><a href="/LivroForm?id=${e.idLivro}">Editar</a>|<a href="/LivroExcluir?id=${e.idLivro}">Excluir</td>
				<tr>
			</c:forEach>
		</table>

	</div>
<%@include file="./includes/footer.html"%>
</body>
</html>