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
<display:table uid="jugadoresListTable" keepStatus="true" name="jugadores"
	pagesize="5" class="table table-hover" requestURI="${requestURI}" id="row">

	<display:column property="name" titleKey="jugador.nombre" sortable="true" />
	<display:column property="surname" titleKey="jugador.apellido" sortable="true"/>
	<display:column property="email" titleKey="jugador.email" sortable="true" />
	<display:column class="success"> 
		<div align="center">
		<a  class="btn btn-sm btn-info" href="jugador/administrador/detalles.do?jugadorId=${row.id}"><spring:message code="jugador.detalles"/></a>
		</div>	
	</display:column>
</display:table>
</div>
</div>