

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
		<form:form action="reto/administrador/edit.do"
			modelAttribute="reto">

			<!-- Poner todos los atributos, los no usados en oculto -->

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="administrador" />
			<form:hidden path="curso" />
			
			<jstl:if test="${detalles }">
			<acme:textbox code="reto.nombre" path="nombre" readonly="true" />
			<acme:textbox code="reto.tiempoMax" path="tiempoMax" readonly="true" />
			<acme:textbox code="reto.fechaValida" path="fechaValida"
				readonly="true" />
		<spring:message code="reto.visible" />
		<form:checkbox code="reto.visible" path="visible"/>
			</jstl:if>
			<jstl:if test="${!detalles }">
			<acme:textbox code="reto.nombre" path="nombre"  />
			<acme:textbox code="reto.tiempoMax" path="tiempoMax"  />
			<acme:textbox code="reto.fechaValida" path="fechaValida" />
			
				<%-- <form:label path="fechaValida">
				<spring:message code="reto.fechaValida" />
				</form:label>
				<form:input class="form-control" path="fechaValida" <fmt:parseDate value="${dateString}" var="dateObject"
                                      pattern="MM/dd/yyyy" />/>
				<form:errors cssClass="error" path="fechaValida" />
				<br /> --%>
				
		<spring:message code="reto.visible" />
		<form:checkbox code="reto.visible" path="visible"/>
			</jstl:if>
	<br>
			<acme:submit name="save" code="reto.save"/>
			
				<jstl:if test="${reto.id!=0}">
					<acme:submitADelete code="reto.delete" codeConfirm="reto.confirm.delete"/>
				</jstl:if>	
			<a href="reto/administrador/listaRetos.do"><input type="button"
				value="<spring:message code="reto.cancel"/>"
				class="btn btn-sm btn-info" id="cancelar" name="cancelar"
				onclick="self.location.href = reto/administrador/listaRetos.do" /></a>

		</form:form>

	</div>
</div>