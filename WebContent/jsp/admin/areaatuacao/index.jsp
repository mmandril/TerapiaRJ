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
			Área de Atuação 
			<a href="<c:url value='/admin/areaAtuacao/novo'/>" class="btn btn-success" title="Incluir">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</h1>
		<hr/>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Descrição</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${areaAtuacaos}" var="area">
					<tr>
						<td>${area.nome}</td>
						<td>${area.descricao}</td>
						<td>
							<a href="<c:url value='/admin/areaAtuacao/editar/${area.id}'/>" class="btn btn-info btnTooltip">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertTemplate>