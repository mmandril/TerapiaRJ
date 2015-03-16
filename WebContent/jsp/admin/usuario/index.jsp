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
			Usuário 
			<a href="<c:url value='/admin/usuario/novo'/>" class="btn btn-success" title="Incluir">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</h1>
		<hr/>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Perfil</th>
					<th>Email</th>
					<th>Status</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usu">
					<tr>
						<td>${usu.perfil.nome}</td>
						<td>${usu.email}</td>
						<td>
							<c:choose>
								<c:when test="${usu.dtInat == null}">
									<span class="label label-success">Ativo</span>
								</c:when>
								<c:otherwise>
									<span class="label label-danger">Inativo</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${usu.dtInat == null}">
									<a href="<c:url value='/admin/usuario/inativar/${usu.id}'/>" class="btn btn-danger btnTooltip" title="Inativar">
										<span class="glyphicon glyphicon-remove"></span>
									</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/usuario/ativar/${usu.id}'/>" class="btn btn-success btnTooltip" title="Ativar">
										<span class="glyphicon glyphicon-check"></span>
									</a>
								</c:otherwise>
							</c:choose>
							<a href="<c:url value='/admin/usuario/editar/${usu.id}'/>" class="btn btn-info btnTooltip" title="Editar">
								<span class="glyphicon glyphicon-edit"></span>
							</a>
							
							<c:if test="${usu.perfil.id == 3}">
								<a href="<c:url value='/admin/profissional/novo/${usu.id}'/>" class="btn btn-warning btnTooltip" title="Profissional">
									<span class="glyphicon glyphicon-user"></span>
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertTemplate>