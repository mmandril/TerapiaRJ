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
			Manter Usuário
			<a href="<c:url value='/admin/usuario'/>" class="btn btn-primary" title="Voltar">
				<span class="glyphicon glyphicon-arrow-left"></span>
			</a>
		</h1>
		<hr/>
		<div class="well">
			<form action="<c:url value='/admin/usuario/salvar'/>" method="post">
				<input type="hidden" name="usuario.id" value="${usuario.id}">
				<div class="form-group">
			    	<label for="perfil">Perfil</label>
			    	<select name="usuario.perfil.id" class="form-control">
			    		<option value="">Selecione</option>
			    		<c:forEach items="${perfils}" var="perf">
			    			<option value="${perf.id}" ${perf.id == usuario.perfil.id ? 'selected' : ''}>${perf.nome}</option>
			    		</c:forEach>
			    	</select>
			  	</div>
		  		<div class="form-group">
			    	<label for="nome">Nome</label>
			    	<input type="text" class="form-control" id="nome" name="usuario.email" placeholder="Email" value="${usuario.email}">
			  	</div>
			  	<div class="form-group">
			    	<label for="descricao">Senha</label>
			    	<input type="password" class="form-control" id="descricao" name="usuario.senha" placeholder="Senha">
			  	</div>
			 	<button type="submit" class="btn btn-success">Submit</button>
			</form>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>