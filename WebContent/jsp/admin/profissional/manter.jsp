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
			Manter Profissional
			<a href="<c:url value='/admin/profissional'/>" class="btn btn-primary" title="Voltar">
				<span class="glyphicon glyphicon-arrow-left"></span>
			</a>
		</h1>
		<hr/>
		<div class="well">
			<form action="<c:url value='/admin/profissional/salvar'/>" method="post">
				<input type="hidden" name="profissional.id" value="${profissional.id}">
				<input type="hidden" name="usuario.id" value="${usuario.id}">
				<div class="form-group">
			    	<label for="nome">Plano</label>
			    	<select name="profissional.plano.id" class="form-control">
			    		<option value="">Selecione</option>
			    		<c:forEach items="${planos}" var="plano">
			    			<option value="${plano.id}" ${plano.id == profissional.plano.id ? 'selected' : ''} >${plano.nome}</option>
			    		</c:forEach>
			    	</select>
			  	</div>
			  	<div class="form-group">
			    	<label for="nome">Área Atuação</label>
			    	<select name="profissional.areaAtuacao.id" class="form-control">
			    		<option value="">Selecione</option>
			    		<c:forEach items="${areaAtuacaos}" var="area">
			    			<option value="${area.id}" ${area.id == profissional.areaAtuacao.id ? 'selected' : ''}>${area.nome}</option>
			    		</c:forEach>
			    	</select>
			  	</div>
			  	<div class="form-group">
			    	<label for="nome">Tipo Profissional</label>
			    	<select name="profissional.tipoProfissional.id" class="form-control">
			    		<option value="">Selecione</option>
			    		<c:forEach items="${tipoProfissionals}" var="tipo">
			    			<option value="${tipo.id}" ${tipo.id == profissional.tipoProfissional.id ? 'selected' : ''}>${tipo.nome}</option>
			    		</c:forEach>
			    	</select>
			  	</div>
			  	<div class="form-group">
			    	<label for="nome">Destaque</label>
			    	<select name="profissional.destaque" class="form-control">
			    		<option value="">Selecione</option>
		    			<option value="S" ${'S' == profissional.destaque ? 'selected' : ''}>Sim</option>
		    			<option value="N" ${'N' == profissional.destaque ? 'selected' : ''}>Não</option>
			    	</select>
			  	</div>
		  		<div class="form-group">
			    	<label for="nome">Nome</label>
			    	<input type="text" class="form-control" id="nome" name="profissional.nome" placeholder="Nome" value="${profissional.nome}">
			  	</div>
			  	<div class="form-group">
			    	<label for="descricao">Descrição</label>
			    	<textarea name="profissional.descricao" class="ckeditor">${profissional.descricao}</textarea>
			  	</div>
			 	<button type="submit" class="btn btn-success">Submit</button>
			</form>
		</div>
		
	</tiles:putAttribute>
</tiles:insertTemplate>