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


		<div class="container-fluid">
			<div class="col-md-12">
				<div align="center">
					<form action="curso/administrador/eleccion.do" name="rr">
						<select class="form-control" name="cursoeleccion" >
							<jstl:forEach var="curso" items="${cursos}"
								varStatus="nombreCurso">
								<option value="${curso.nombre}">${curso.nombre}</option>
							</jstl:forEach>
							</select>
					
			<%-- 		 <%! private int id2 = 0; %>
      
					   <%!id2= request.getParameter("select").id;%>
					   
					 --%>
					<br> <a
						href="pregunta/administrador/listaPreguntasPorCurso.do?cursoId=${id2}"><input
						type="button" value="<spring:message code="curso.verPreguntas"/>"
						class="btn btn-sm btn-success" id="cancelar" name="cancelar"
						onclick="self.location.href = curso/administrador/listaCursos.do" /></a>


					<a href="welcome/index.do"><input type="button"
						value="<spring:message code="curso.cancel"/>"
						class="btn btn-sm btn-info" id="cancelar" name="cancelar"
						onclick="self.location.href = welcome/index.do" /></a>
				
			</form>
		</div>
</div>
			</div>
	</div>
</div>