<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<tiles:insertTemplate template="../../layout/template.jsp">
	<tiles:putAttribute name="menu">
		<tag:menuAdmin></tag:menuAdmin>
	</tiles:putAttribute>
	
	
	
	<tiles:putAttribute name="body">
		<h1>
			Perfil
			<a href="<c:url value='/admin/perfil/novo'/>" class="btn btn-success" title="Incluir">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</h1>
		<hr/>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Nome</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${perfils}" var="perfil">
					<tr>
						<td>${perfil.nome}</td>
						<td>
							<a href="<c:url value='/admin/perfil/editar/${perfil.id}'/>" class="btn btn-info btnTooltip">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertTemplate>