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
			Cliente 
			<a href="<c:url value='/admin/cliente/novo'/>" class="btn btn-success" title="Incluir">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</h1>
		<hr/>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Apelido</th>
					<th>Data Nascimento</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clientes}" var="cli">
					<tr>
						<td>${cli.apelido}</td>
						<td> <fmt:formatDate value="${cli.dtNascimento}"/> </td>
						<td>
							<a href="<c:url value='/admin/cliente/editar/${cli.id}'/>" class="btn btn-info btnTooltip">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertTemplate>