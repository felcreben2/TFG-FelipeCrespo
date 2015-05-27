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
			<form:form action="curso/administrador/edit.do" modelAttribute="curso">
			
				<!-- Poner todos los atributos, los no usados en oculto -->
			
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="numeroPreguntas" />
				<form:hidden path="administrador" />
				
	 
			<div class="container-fluid">
			 <div class="col-md-12">
				<acme:textbox code="curso.nombreCurso" path="nombre" />
				<acme:textbox code="curso.tematica" path="tematica" />
				<%-- <acme:select items="${galleries}" itemLabel="title" id="id" code="curso.gallery" path="gallery"/>
				 --%>
				 <br>
				
				<div align="center">
				<acme:submit name="save" code="curso.save"/>
				<jstl:if test="${curso.id!=0}">
					<acme:submitADelete code="curso.delete" codeConfirm="curso.confirm.delete"/>
				</jstl:if>	
				
				<a href = "curso/administrador/listaCursos.do"><input type="button" value="<spring:message code="curso.cancel"/>"  class="btn btn-sm btn-info" id="cancelar" name="cancelar" onclick= "self.location.href = curso/administrador/listaCursos.do" /></a>	
				</div>
				</div>
				</div>
				</form:form>
				
			</div>
</div>