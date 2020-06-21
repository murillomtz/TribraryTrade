<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Editar Livro</title>
<%@include file="./includes/head.html"%>
</head>
<body class="bodyLogin">

	<%@include file="./includes/header.jsp"%>

	<h1 class="titulo">Cadastrar Livro</h1>

	<form action="LivroEditar" method="post">

		<input type="hidden" name="id" value="${livro.idLivro}">
		Titulo: <input class="form-control" type="text" name="titulo"
			value="${livro.titulo }" /><br> Autor: <input
			class="form-control" type="text" name="autor" value="${livro.autor }" /><br>
		Sinopse: <input class="form-control" type="text" name="sinopse"
			value="${livro.sinopse }" /><br> Detalhes: <input
			class="form-control " type="text" name="detalhes"
			value="${livro.detalhes}" /><br> Imagem do Livro:
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
			</div>
			<div class="custom-file">
				<input type="file" class="custom-file-input " name="fotoLivro"
					id="fotoLivro" aria-describedby="inputGroupFileAddon01"
					value="${livro.fotoLivro}"><label class="custom-file-label"
					for="inputGroupFile01">Choose file</label>
			</div>
		</div>
		<br> <label for="genero">Genero</label> <select
			class="form-control" name="genero" id="genero">
			<option value="">Selecione</option>
			<c:forEach var="lista" items="${lista}">
				<option value="${lista.idGenero}"
					${ lista.idGenero == lista.idGenero ? 'selected' : ''}>${lista.nome}</option>
			</c:forEach>
		</select>
		<br>
		<button class="btn btnPadrao" type="submit" class="btn">Cadastrar</button>
	</form>

	</div>
	<%@include file="./includes/footer.html"%>
</body>
</html>