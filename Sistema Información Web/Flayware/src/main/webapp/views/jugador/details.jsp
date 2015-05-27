<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="container">
	<div class="row">
		<form:form action="jugador/administrador/detalles.do"
			modelAttribute="jugador">

			<!-- Poner todos los atributos, los no usados en oculto -->

			<form:hidden path="id" />
			<form:hidden path="version" />

			<acme:textbox code="jugador.nombre" path="name" readonly="true" />
			<acme:textbox code="jugador.apellido" path="surname" readonly="true" />
			<acme:textbox code="jugador.email" path="email"
				readonly="true" />


			<br>
			<jstl:if test="${isEnable}">
							<a href="jugador/administrador/bloquear.do?jugadorId=${jugador.id}" class="btn btn-sm btn-danger">
							<spring:message code="jugador.bloquear"/></a>
						</jstl:if>
						<jstl:if test="${!isEnable}">
							<a href="jugador/administrador/desbloquear.do?jugadorId=${jugador.id}" class="btn btn-sm btn-success">
							<spring:message code="jugador.desbloquear"/></a>
						</jstl:if>
	<a href = "jugador/administrador/listaJugadores.do"><input type="button" value="<spring:message code="jugador.cancel"/>"  class="btn btn-sm btn-info" id="cancelar" name="cancelar" onclick= "self.location.href = jugador/administrador/listaJugadores.do" /></a>	
		
		</form:form>

	</div>
</div>