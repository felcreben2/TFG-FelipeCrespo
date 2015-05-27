<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

	<div>
		 <img src="images/logo.png" class="img-responsive" alt="Aprende Fiware" />
	</div>


<div class="navbar navbar-default">
       
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          
         <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
		<security:authorize access="hasRole('ADMINISTRADOR')">
		<li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         <span class="glyphicon glyphicon-cog"></span>&nbsp;<spring:message code="master.page.administrador" /></a>
         <ul class="dropdown-menu">
				<li><a href="administrador/registrarAdministrador.do"><spring:message code="master.page.administrador.registrar" /></a></li>
				<li><a href="administrador/listaAdministradores.do"><spring:message code="master.page.administrador.lista" /></a></li>
				</ul>
			</li>
			
			<li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         <span class="glyphicon glyphicon-user"></span>&nbsp;<spring:message code="master.page.jugador" /></a>
         <ul class="dropdown-menu">
				<li><a href="jugador/administrador/listaJugadores.do"><spring:message code="master.page.jugador.listaJugadores" /></a></li>
				<li><a href="jugador/administrador/listaBanneados.do"><spring:message code="master.page.jugador.listaBanneados" /></a></li>
				</ul>
			</li>
			
			<li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         <span class="glyphicon glyphicon-folder-open"></span>&nbsp;<spring:message code="master.page.cursos" /></a>
         <ul class="dropdown-menu">
					<li><a href="curso/administrador/listaCursos.do"><spring:message code="master.page.cursos.listaCurso" /></a></li>
					<li><a href="curso/administrador/listaMisCursos.do"><spring:message code="master.page.cursos.listaMisCurso" /></a></li>
					<!--  <li><a href="curso/administrador/create.do"><spring:message code="master.page.cursos.crear" /></a></li>-->
				</ul>
			</li>
			
			
			<li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         <span class="glyphicon glyphicon-unchecked"></span>&nbsp;<spring:message code="master.page.reto" /></a>
         <ul class="dropdown-menu">
					<li><a href="reto/administrador/listaRetos.do"><spring:message code="master.page.reto.listaReto" /></a></li>
					<li><a href="reto/administrador/listaMisRetos.do"><spring:message code="master.page.reto.listaMisRetos" /></a></li>
					</ul>
			</li>
			
		<%-- 	<li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         <span class="glyphicon glyphicon-check"></span>&nbsp;<spring:message code="master.page.pregunta" /></a>
         <ul class="dropdown-menu">
				<li><a href="curso/administrador/eleccion.do"><spring:message code="master.page.pregunta.curso" /></a></li>
				</ul>
			</li> --%>
			
		</security:authorize>
		
		
		<security:authorize access="isAuthenticated() and !hasRole('ADMINISTRADOR')">
		<li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         <span class="glyphicon glyphicon-bullhorn"></span>&nbsp;<spring:message code="master.page.leeme" /></a>
         <ul class="dropdown-menu">
				<li><a href="jugadores/leeme.do"><spring:message code="master.page.leeme" /></a></li>
				</ul>
			</li>
		
		</security:authorize>
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><span class="glyphicon glyphicon-new-window"></span>&nbsp;<spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
				<li><a class="fNiv" href="j_spring_security_logout">
				<span class="glyphicon glyphicon-off"></span>&nbsp;<spring:message code="master.page.logout" /> </a></li>	
			</security:authorize>
			
			
	</ul>
	</div>
	</div>
</div>
