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
			<form:form action="respuesta/administrador/edit.do" modelAttribute="respuesta" method="post" enctype="multipart/form-data">
			
				<!-- Poner todos los atributos, los no usados en oculto -->
			
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="pregunta" />
						
				
				<acme:textbox code="respuesta.texto" path="texto" />
				<acme:textbox code="respuesta.puntuacion" path="puntuacion" />
				<br>
	<spring:message code="respuesta.solucion" />
		<form:checkbox code="respuesta.solucion" path="solucion"/>
				<br>
				<form:label path="image">
					<spring:message code="respuesta.image" />
			</form:label>	
			<form:input path="image" id="image" type="file" />
			<form:errors path="image" cssClass="error" />
				
			<br />
			<jstl:if test="${respuesta.validImage }">
				<img src="image/show.do?respuestaId=${respuesta.id}" style="height: 100px;" class="img-thumbnail">
			</jstl:if>	
				 <br>
				
				<acme:submit name="save" code="respuesta.save"/>
				<jstl:if test="${respuesta.id!=0}">
					<acme:submitADelete code="respuesta.delete" codeConfirm="respuesta.confirm.delete"/>
				</jstl:if>	
				
				<a href = "respuesta/administrador/listaRespuestasPorPregunta.do?preguntaId=${respuesta.pregunta.id}"><input type="button" value="<spring:message code="respuesta.cancel"/>"  class="btn btn-sm btn-info" id="cancelar" name="cancelar" onclick= "self.location.href = respuesta/administrador/listaRespuestasPorPregunta.do?preguntaId=${respuesta.pregunta.id}" /></a>	
				
				</form:form>
				
			</div>
</div>