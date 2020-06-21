<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./includes/head.html"%>
<title>Minhas Notificações</title>

</head>
<body class="body">

<%@include file="./includes/header.jsp"%>
	<c:forEach items="${notificacoes}" var="n">
	<c:if test="${n.status=='Esperando Resposta'}">
		<div class="conteiner">
			<div class="row">
				<div class= col-md-3>
					<h4>${n.livroDesejado.titulo}</h4> 
					<img alt="" src="/img/img_livros/11-06-2020/livro1.jpg">	
				</div>
				<div class="col-md-9">
					<label>O usuÃ¡rio ${n.usuarioSolicitante.nome} deseja o seu livro: ${n.livroDesejado.titulo}</label>
					<br><label>Lista de livros do usuario</label>
					<form action="RealizarTroca" method="post">
					<input type="hidden" name="livro" value="${n.livroDesejado.idLivro}">
					<input type="hidden" name="idNotificacao" value="${n.idNotificacao}">
					<div class="input-group">
					<c:forEach items="${n.livrosDoUsuario}" var="livroUser">
						 <br><input type="radio" value="${livroUser.idLivro}" name="livroEscolhido" aria-label="Radio button for following text input">
						 <label>${livroUser.titulo}</label><br>
					</c:forEach>
					
					</div>
					<button class="btn btn-danger">Recusar</button>
					<button type="submit" class="btn btn-success">Aceitar</button>
					</form>
				</div>
			</div>
		</div>
		</c:if>
	</c:forEach>

<%@include file="./includes/footer.html"%>

</body>
</html>
