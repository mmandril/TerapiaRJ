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
			Manter Cliente 
			<a href="<c:url value='/admin/cliente'/>" class="btn btn-primary" title="Voltar">
				<span class="glyphicon glyphicon-arrow-left"></span>
			</a>
		</h1>
		<hr/>
		<div class="well">
			<form action="<c:url value='/admin/cliente/salvar'/>" method="post">
				<input type="hidden" name="cliente.id" value="${cliente.id}">
		  		<div class="form-group">
			    	<label for="apelido">Apelido</label>
			    	<input type="text" class="form-control" id="apelido" name="cliente.apelido" placeholder="Apelido" value="${cliente.apelido}">
			  	</div>
			  	<div class="form-group">
			    	<label for="dtNasc">Descrição</label>
			    	<input type="text" class="form-control" id="dtNasc" name="cliente.dtNascimento" placeholder="Data de Nascimento" value="<fmt:formatDate value="${cliente.dtNascimento}" pattern="dd/MM/yyyy"/>">
			  	</div>
			 	<button type="submit" class="btn btn-success">Submit</button> 
			</form>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>