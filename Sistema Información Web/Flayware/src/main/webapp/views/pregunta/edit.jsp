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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="container">
	<div class="row">
			<form:form action="pregunta/administrador/edit.do" modelAttribute="pregunta">
			
				<!-- Poner todos los atributos, los no usados en oculto -->
			
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="curso" />
				
				
				
				<acme:textbox code="pregunta.numero" path="numero" />
				<acme:textbox code="pregunta.enunciado" path="enunciado" />
				<acme:textbox code="pregunta.valorPregunta" path="valorPregunta" />
				
				 <br>
				
				<acme:submit name="save" code="pregunta.save"/>
				<jstl:if test="${pregunta.id!=0}">
					<acme:submitADelete code="pregunta.delete" codeConfirm="pregunta.confirm.delete"/>
				</jstl:if>	
				
				<a href = "curso/administrador/detalles.do?cursoId=${pregunta.curso.id}"><input type="button" value="<spring:message code="pregunta.cancel"/>"  class="btn btn-sm btn-info" id="cancelar" name="cancelar" onclick= "self.location.href = curso/administrador/detalles.do?cursoId=${pregunta.curso.id}" /></a>	
				
				</form:form>
				
			</div>
</div>