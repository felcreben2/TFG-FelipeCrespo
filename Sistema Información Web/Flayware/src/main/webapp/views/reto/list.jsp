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
<display:table uid="retosListTable" keepStatus="true" name="retos"
	pagesize="5" class="table table-hover" requestURI="${requestURI}" id="row">

	<display:column property="nombre" titleKey="reto.nombre" sortable="true" />
	<display:column property="tiempoMax" titleKey="reto.tiempoMax" sortable="true"/>
	<display:column titleKey="reto.visible" sortable="true">
			<spring:message code="reto.visible.${row.visible}" />
	</display:column>
	<display:column property="fechaValida" titleKey="reto.fechaValida" sortable="true" format="{0,date,dd/MM/yyyy}" />
	<display:column property="curso.nombre" titleKey="curso.nombre" sortable="true" />
	<display:column class="success"> 
		<div align="center">
		<a  class="btn btn-sm btn-info" href="reto/administrador/edit.do?retoId=${row.id}"><spring:message code="reto.edit"/></a>
		</div>	
	</display:column>
	<display:column class="success">
		<div align="center">
		<a   class="btn btn-sm btn-warning" href="reto/administrador/detalles.do?retoId=${row.id}"><spring:message code="reto.details"/></a>
		</div>
	</display:column>
</display:table>
</div>

</div>