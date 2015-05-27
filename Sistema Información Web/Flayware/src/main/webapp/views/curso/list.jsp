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
<display:table uid="cursosListTable" keepStatus="true" name="cursos"
	pagesize="5" class="table table-hover" requestURI="${requestURI}" id="row">

	<display:column property="nombre" titleKey="curso.nombreCurso" sortable="true" />
	<display:column property="numeroPreguntas" titleKey="curso.numeroPreguntas" sortable="true"/>
	<display:column property="tematica" titleKey="curso.tematica" sortable="true" />
	<display:column property="administrador.name" titleKey="curso.administrador" sortable="true" />
	<display:column class="success"> 
		<div align="center">
		<a  class="btn btn-sm btn-info" href="curso/administrador/edit.do?cursoId=${row.id}"><spring:message code="curso.edit"/></a>
		</div>	
	</display:column>
	<display:column class="success">
		<div align="center">
		<a   class="btn btn-sm btn-warning" href="curso/administrador/detalles.do?cursoId=${row.id}"><spring:message code="curso.details"/></a>
		</div>
	</display:column>
	<display:column class="danger">
		<div align="center">
		<a   class="btn btn-sm btn-warning" href="reto/administrador/create.do?cursoId=${row.id}"><spring:message code="curso.reto"/></a>
		</div>
	</display:column>
</display:table>
</div>
<div align="center">
<div class="container-fluid">
<a  class="btn btn-sm btn-success" href="curso/administrador/create.do"><spring:message code="curso.create"/></a>
</div>
</div>
</div>