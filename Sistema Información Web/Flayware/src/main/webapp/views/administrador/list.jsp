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
<display:table uid="administradoresListTable" keepStatus="true" name="administradores"
	pagesize="5" class="table table-hover" requestURI="${requestURI}" id="row">

	<display:column property="name" titleKey="administrador.nombre" sortable="true" />
	<display:column property="surname" titleKey="administrador.apellido" sortable="true"/>
	<display:column property="email" titleKey="administrador.email" sortable="true" />
</display:table>
<a href = "welcome/index.do"><input type="button" value="<spring:message code="register.cancel"/>"  class="btn btn-sm btn-info" id="cancelar" name="cancelar" onclick= "self.location.href = welcome/index.do)" /></a>	
			
</div>


</div>