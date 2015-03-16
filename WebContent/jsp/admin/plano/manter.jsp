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
			Manter Plano 
			<a href="<c:url value='/admin/plano'/>" class="btn btn-primary" title="Voltar">
				<span class="glyphicon glyphicon-arrow-left"></span>
			</a>
		</h1>
		<hr/>
		<div class="well">
			<form action="<c:url value='/admin/plano/salvar'/>" method="post">
				<input type="hidden" name="plano.id" value="${plano.id}">
		  		<div class="form-group">
			    	<label for="nome">Nome</label>
			    	<input type="text" class="form-control" id="nome" name="plano.nome" placeholder="Nome" value="${plano.nome}">
			  	</div>
			 	<button type="submit" class="btn btn-success">Submit</button>
			</form>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>