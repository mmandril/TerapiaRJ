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
			Clinica 
			<a href="<c:url value='/admin/clinica/novo'/>" class="btn btn-success" title="Incluir">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</h1>
		<hr/>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Descrição</th>
					<th>Status</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clinicas}" var="cli">
					<tr>
						<td>${cli.nome}</td>
						<td>${cli.descricao}</td>
						<td>
							<c:choose>
								<c:when test="${cli.dtInat == null}">
									<a href="<c:url value='/admin/clinica/inativar/${cli.id}'/>" class="btnTooltip" title="Inativar">
										<span class="label label-success">Ativo</span>
									</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/clinica/ativar/${cli.id}'/>" class="btnTooltip" title="Ativar">
										<span class="label label-danger">Inativo</span>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="<c:url value='/admin/clinica/editar/${cli.id}'/>" class="btn btn-info btnTooltip">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertTemplate>