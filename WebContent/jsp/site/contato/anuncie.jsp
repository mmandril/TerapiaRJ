<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<tiles:insertTemplate template="../../layout/template.jsp">
	<tiles:putAttribute name="menu">
		<tag:menuSite></tag:menuSite>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form action="<c:url value='/contato/anuncio/enviar'/>" method="post">
			<div class="form-group">
		    	<label for="nome">Nome</label>
		    	<input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" required="required">
		  	</div>
	  		<div class="form-group">
		    	<label for="email">E-mail</label>
		    	<input type="email" class="form-control" id="email" name="email" placeholder="E-mail" required="required">
		  	</div>
		  	<div class="form-group">
		    	<label for="clinica">Clínica</label>
		    	<input type="text" class="form-control" id="clinica" name="clinica" placeholder="Clínica" required="required">
		  	</div>
		  	<div class="form-group">
		    	<label for="tipo">Tipo de Anuncio</label>
		    	<select name="tipo" class="form-control" required="required">
		    		<option value="">Selecione</option>
		    		<option value="T">Terapeuta</option>
		    		<option value="M">Massagista</option>
		    	</select>
		  	</div>
		  	<div class="form-group">
		    	<label for="mensagem">Mensagem</label>
		    	<textarea id="mensagem" name="mensagem" class="ckeditor" placeholder="Digite aqui sua mensagem"></textarea>
		  	</div>
		 	<button type="submit" class="btn btn-success">Enviar</button> 
		</form>
	</tiles:putAttribute>
</tiles:insertTemplate>