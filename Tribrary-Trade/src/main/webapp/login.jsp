<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
-8">
	<title>Login</title>

	<%@include file="./includes/head.html"%>
	
</head>
<body class="bodyLogin">
<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6 ColLogin">
				<form method="post" action="./Login">
				<div class="row centered">
					<div class="col-lg-1"></div>
					<div class="col-lg-10">
						<br>
						<div class="d-flex justify-content-center">
						<img class="logoLogin " alt="Logo do site" src="img/logoCerto.png" class="logoLogin" >
						</div>
						
						<br>
						<hr>
						
							<div class="form-group centered">
								<label for="email">Login</label><br>
								<input type="text" class="form-control" name="email" id="email">
							</div>
							<div class="form-group centered">
								<label for="senha">Senha</label><br>
								<input type="password" class="form-control" name="senha" id="senha">
							</div>
							${erro}<br>
							<a href="">Esqueceu a senha?</a>

						
					</div>
					<div class="col-lg-1"></div>
				</div>
				<br>
				<div class="row">
					<div class="col-lg-3"></div>
					<div class="col-lg-3">
						<button type="submit" class="btn btnLogin" >Entrar</button>	
					</div>	
					<div class="col-lg-3">
						<a class="btn btnPadrao" href="/UsuarioForm">Cadastrar</a>
						<!--<a href="/UsuarioForm"><button class="btn btnLogin">Cadastrar</button></a>
						<input type="button" class="btn btnLogin" name="cadastrar" value="Cadastrar">-->
					</div>
					<div class="col-lg-3"></div>
				</div>
				</form>
				<br>
				<hr>
				<br>
				<div class="row centered">
					<div class="col-lg-3"></div>
					<div class="col-lg-6 centered">
						<p class="texto">2020 © - Tribrary</p>
					</div>
					<div class="col-lg-3"></div>			
				</div>	
			</div>
			<div class="col-lg-3"></div>
		</div>
		
	</div>
</body>
</html>