
<%@page import="br.ucsal.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="./includes/head.html"%>
<title>${livroSessao.titulo}</title>
</head>
<body class="body">

	<%@include file="./includes/header.jsp"%>

		<h1 >${livroSessao.titulo}</h1><br>
	<label>Este livro pertence a <a href="#">${livroSessao.usuario.nome}</a></label><br>
	<img alt="" src="/img/img_livros/11-06-2020/capaDefault.jpg">
	${erro}
	<hr>
	<p>${livroSessao.sinopse}</p>
	<p>${livroSessao.detalhes}</p>
	
	
		<c:if test="${sessionScope.usuario != null && livroSessao.usuario.idUsuario != usuario.idUsuario && erro==null}">
			<form action="SolicitarTroca" method="post" >
				<button type="submit" class="btn btn-success">Solicitar Troca</button>
			</form>
		</c:if>
		
		
		<!--<c:out value="${usuario}"></c:out>-->
	
	<%@include file="./includes/footer.html"%>
</body>
</html>