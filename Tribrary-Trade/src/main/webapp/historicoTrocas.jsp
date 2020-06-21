<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Minhas Trocas</title>
<%@include file="./includes/head.html"%>
</head>
<body class="body">
<%@include file="./includes/header.jsp"%>
<h1 class="titulo">Misnhas Trocas</h1>
	
	<c:forEach items="${trocas}" var="troca">

<label>Data: ${troca.data}</label> <br>
<label>Local: ${troca.local}</label> <br>
<label>Status: ${troca.status}<br></label>
<br>
<h4>Livros Trocados</h4>
<c:forEach items="${troca.livros}" var="livro">
<label>${livro.titulo}</label><br>
</c:forEach>
<hr>
	</c:forEach>
	
<%@include file="./includes/footer.html"%>
</body>
</html>