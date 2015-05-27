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


<div class="container-fluid">
		
<form:form action="administrador/saveAdministrador.do" modelAttribute="administradorForm">
		
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<div class="row">
	  <div class="col-md-6">
	  
<div class="container-fluid">
    
	<acme:textbox code="register.username" path="username" />
	<acme:password code="register.password" path="password" />
	<acme:password code="register.passwordRepeat" path="repeatPassword" />
	<acme:textbox code="register.name" path="name" />
	<acme:textbox code="register.surname" path="surname" />
	<acme:textbox code="register.email" path="email" />
	  </div>
	  </div>
	<div class="col-md-6">
	
<div class="container-fluid">
		<h3><spring:message code="register.condition" /></h3>
		<div class="form-group">
			<textarea class="form-control" rows="5" readonly="readonly"><spring:message code="register.condition.path" /></textarea>
		</div>
		<spring:message code="register.checkText" />
		<form:checkbox code="register.TOSAccepted" path="TOSAccepted"/>
		<br>
		<br />		
		
	
		<acme:submit code="register.save" name="save" />
	
		<a href="welcome/index.do"><input type="button" class="btn btn-sm btn-info"
			value="<spring:message code="register.cancel"/>"  class="btn btn-sm btn-info"id="cancelar"
			name="cancelar" onclick="self.location.href = welcome/index.do" /></a>
		<br />
		</div>
		</div>
    </div> 
</form:form>
		
</div>
