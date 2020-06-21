<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>Início</title>
<%@include file="./includes/head.html"%>
</head>
<body class="body">

	
		<%@include file="./includes/header.jsp"%>
	<label>
		${erro}
	</label>	
	
	<c:forEach items="${livros}" var="livro">
		<div class="livro conteiner">
			<div class="row">
				<a href="LivroExibir?id=${livro.idLivro}"><h4 class="titulo">${livro.titulo}</h3></a> 
			</div>
			<div class="row">
				<div class= col-md-2>
					
					<a href="LivroExibir?id=${livro.idLivro}"><img alt="" src="/img/img_livros/11-06-2020/capaDefault.jpg"></a>
				</div>
				<div class="col-md-10">
					<p>${livro.sinopse}</p>
					<p>${livro.detalhes}</p>
				</div>
			</div>
		</div>
		<hr>
	</c:forEach>
	<%@include file="./includes/footer.html"%>
	

</body>
</html>