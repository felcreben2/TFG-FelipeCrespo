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
<display:table uid="respuestasListTable" keepStatus="true" name="respuestas"
	pagesize="5" class="table table-hover" requestURI="${requestURI}" id="row">

	<display:column property="texto" titleKey="respuesta.texto" sortable="true" />
	<display:column property="puntuacion" titleKey="respuesta.puntuacion" sortable="true"/>
	<display:column titleKey="respuesta.solucion" sortable="true">
			<spring:message code="respuesta.solucion.${row.solucion}" />
	</display:column>
	<display:column titleKey="respuesta.image" sortable="true">
		<jstl:if test="${row.validImage}">
			<img src="image/show.do?respuestaId=${row.id}" style="height: 50px;" class="img-thumbnail" />
		</jstl:if>	
	</display:column>
	<display:column class="warning">
						<a class="btn btn-sm btn-info"
							href="respuesta/administrador/edit.do?respuestaId=${row.id}"><spring:message
								code="respuesta.edit" /></a>
					</display:column>
</display:table>
<br>

	<a  class="btn btn-sm btn-success" href="respuesta/administrador/create.do?preguntaId=${pregunta.id}"><spring:message code="curso.respuesta.crear"/></a>
		
	<a href = "curso/administrador/detalles.do?cursoId=${pregunta.curso.id}"><input type="button" value="<spring:message code="respuesta.cancel"/>"  class="btn btn-sm btn-info" id="cancelar" name="cancelar" onclick= "self.location.href = curso/administrador/detalles.do?cursoId=${pregunta.curso.id}" /></a>	
					
		
</div>
</div>