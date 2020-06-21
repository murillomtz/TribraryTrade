
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<header class="barraNavegacao2 navbar navbar-expand  flex-column flex-md-row bd-navbar" >
  <a class="navbar-brand " href="index.jsp" class="logoIndex">
  	<img  src="/img/logo2.png" alt="Logo do site" loading="lazy">
  </a>
  <form class="form-inline "action="BuscarLivro"  method="post">
      <!--  <input class="form-control" type="search" placeholder="Buscar">
      <button class="btn btnPadrao my-2 my-sm-0" type="submit">Search</button>-->
  	<div class="input-group mb-3">
  		<input type="text" name = "titulo" class="form-control inputBusca" placeholder="Buscar Livro" aria-label="Buscar Livro" aria-describedby="button-addon2">
  		<div class="input-group-append">
    		<button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
  		</div>
	</div>
  </form>

<c:if test="${usuario != null}">

  <ul class="nav navbar-nav justify-content-end navbar-nav ml-md-auto">
  	<li class="nav-item dropdown">
  	
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdowntest" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Livros
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdowntest">
          <a class="dropdown-item" href="/MeusLivros">Meus Livros</a>
          <a class="dropdown-item" href="/LivroForm">Cadastrar</a>
          <a class="dropdown-item" href="/HistoricoTrocas">Trocas</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         ${sessionScope.usuario.nome}
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="UsuarioPerfil">Perfil</a>
          <a class="dropdown-item" href="/MinhasNotificacoes">Notifiações</a>
          <a class="dropdown-item" href="#">Configurações</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="/Logout">Sair</a>
        </div>
      </li>
  </ul>
 </c:if>
 
 <c:if test="${usuario == null}">
 <ul class="nav justify-content-end navbar-nav ml-md-auto">
  	<li class="nav-item">
  		<a class="btn btnPadrao" href="/UsuarioForm">Cadastrar</a>
  	</li>
  	<li class="nav-item">
  		<a class="btn btnPadrao" href="/login.jsp">Entrar</a>
  	</li>
  </ul>
 
 </c:if>
  
</header>


	<div class="container body-content ">
		<div class="row">
			<div class="col-lg-12">
				<div class="container centered" id="conteudo">