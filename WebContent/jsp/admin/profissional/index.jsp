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
			Profissional
			<a href="<c:url value='/admin/profissional/novo'/>" class="btn btn-success" title="Incluir">
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
				<c:forEach items="${profissionals}" var="prof">
					<tr>
						<td>${prof.nome}</td>
						<td>${prof.descricao}</td>
						<td>
							<c:choose>
								<c:when test="${prof.dtInat == null}">
									<span class="label label-success">Ativo</span>
								</c:when>
								<c:otherwise>
									<span class="label label-danger">Inativo</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="<c:url value='/admin/profissional/editar/${prof.id}'/>" class="btn btn-info btnTooltip">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
							
							<c:choose>
								<c:when test="${prof.dtInat == null}">
									<a href="<c:url value='/admin/profissional/inativar/${prof.id}'/>" class="btn btn-danger btnTooltip" title="Inativar">
										<span class="glyphicon glyphicon-remove"></span>
									</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/profissional/ativar/${prof.id}'/>" class="btn btn-success btnTooltip" title="Ativar">
										<span class="glyphicon glyphicon-check"></span>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertTemplate>