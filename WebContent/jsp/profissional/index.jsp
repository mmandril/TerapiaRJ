<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<tiles:insertTemplate template="../layout/template.jsp">
	<tiles:putAttribute name="menu">
		<tag:menuSite></tag:menuSite>
	</tiles:putAttribute>
	
	
	
	<tiles:putAttribute name="body">
		<h3>${profissional.nome}</h3>
		<div role="tabpanel">

		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#basico" aria-controls="basico" role="tab" data-toggle="tab">Dados Básicos</a></li>
		    <li role="presentation"><a href="#fotos" aria-controls="fotos" role="tab" data-toggle="tab">Fotos</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <form action="<c:url value='/adminProfissional/alterar'/>" enctype="multipart/form-data" method="post">
			  <div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="basico">
				    	<div class="col-md-12" style="margin-left: 0%;">
							<input type="hidden" name="profissional.id" value="${profissional.id}">
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
						    	<label for="nome">Nome</label>
						    	<input type="text" class="form-control" id="nome" name="profissional.nome" placeholder="Nome" value="${profissional.nome}">
						  	</div>
						  	<div class="form-group">
						    	<label for="descricao">Descrição</label>
						    	<textarea name="profissional.descricao" class="ckeditor">${profissional.descricao}</textarea>
						  	</div>
						</div>
				    </div>
				    <div role="tabpanel" class="tab-pane" id="fotos">
				    	<br/>
				    	<div class="col-md-6" style="margin-left: 0%;">
							<dic class="row">
								<input type="file" multiple="multiple" name="fotos[]" />
							</div>	
						</div>
				    </div>
				  </div>
				  <button type="submit" title="Salvar" class="btn btn-success">Enviar</button>
			</div>
		</form>
	</tiles:putAttribute>
</tiles:insertTemplate>