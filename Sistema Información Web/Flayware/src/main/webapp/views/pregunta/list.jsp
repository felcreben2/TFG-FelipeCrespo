<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">
<div class="table-responsive">
<display:table uid="preguntasListTable" keepStatus="true" name="preguntas"
	pagesize="5" class="table table-hover" requestURI="${requestURI}" id="row">

	<display:column property="numero" titleKey="pregunta.numero" sortable="true" />
	<display:column property="enunciado" titleKey="pregunta.enunciado" sortable="true"/>
	<display:column property="valorPregunta" titleKey="pregunta.valorPregunta" sortable="true" />
	<display:column property="curso.nombre" titleKey="pregunta.curso" sortable="true" />
	<display:column class="warning">
						<a class="btn btn-sm btn-info"
							href="pregunta/administrador/edit.do?preguntaId=${row.id}"><spring:message
								code="pregunta.edit" /></a>
					</display:column>
	<display:column class="warning">
		<a  class="btn btn-sm btn-info" href="respuesta/administrador/listaRespuestasPorPregunta.do?preguntaId=${row.id}"><spring:message code="pregunta.respuestas"/></a>
	</display:column>
</display:table>
</div>
</div>