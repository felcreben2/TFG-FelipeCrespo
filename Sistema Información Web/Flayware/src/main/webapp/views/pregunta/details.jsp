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
		<form:form action="curso/administrador/details.do"
			modelAttribute="curso">

			<!-- Poner todos los atributos, los no usados en oculto -->

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="administrador" />

			<acme:textbox code="curso.nombreCurso" path="nombre" readonly="true" />
			<acme:textbox code="curso.tematica" path="tematica" readonly="true" />
			<acme:textbox code="curso.administrador" path="administrador"
				readonly="true" />
			<acme:textbox code="curso.numeroPreguntas" path="numeroPreguntas"
				readonly="true" />


			<br>

			<h3>Preguntas</h3>
			<br>
			<div class="table-responsive">
				<display:table uid="preguntasListTable" keepStatus="true" name="preguntas"
					pagesize="5" class="table table-hover" requestURI="${requestURI}"
					id="row">

					<display:column property="numeroPregunta" titleKey="curso.pregunta.numero"
						sortable="true" />
					<display:column property="enunciado" titleKey="curso.pregunta.enunciado"
						sortable="true" />
					<display:column property="valorPregunta"
						titleKey="curso.pregunta.valor" sortable="true" />
					<display:column class="warning">
						<a class="btn btn-sm btn-info"
							href="curso/respuestas/list.do?preguntaId=${row.id}"><spring:message
								code="curso.pregunta.respuestas" /></a>
					</display:column>
				</display:table>
			</div>
			
			<br>
			
			<div class="container-fluid">
			<jstl:if test="${npreguntas<20 && npreguntas>=0 }">
				<a  class="btn btn-sm btn-success" href="pregunta/administrador/create.do"><spring:message code="curso.pregunta.crear"/></a>
			</jstl:if>	
			</div>

			<a href="curso/administrador/listaCursos.do"><input type="button"
				value="<spring:message code="curso.cancel"/>"
				class="btn btn-sm btn-info" id="cancelar" name="cancelar"
				onclick="self.location.href = curso/administrador/listaCursos.do" /></a>

		</form:form>

	</div>
</div>