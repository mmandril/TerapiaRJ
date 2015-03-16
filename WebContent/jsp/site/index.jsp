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
		<h3>Destaques</h3>
		<hr>
		<div class="row">
			<c:forEach items="${destaques}" var="prof">
				<div class="col-md-2">
	    			<a href="<c:url value='/profissionais/view/${prof.id}'/>" class="thumbnail">
	    				<c:forEach items="${prof.fotos}" var="f">
	    					<c:if test="${f.capa == 'S' }">
	    						<img src="<c:url value='http://imagens.terapiarj.com.br/${f.foto }'/>" alt="${foto.legenda}">
	    					</c:if>
	    				</c:forEach>
	      				<div class="caption">
		        			<h5>${prof.nome}</h5>
	      				</div>
	      			</a>
	      			<c:if test="${prof.diasCadastrados <= 3}">
	    				<div class="ribbon-wrapper-green"><div class="ribbon-green">Nova</div></div>
	    			</c:if>
	  			</div>
			</c:forEach>
		</div>
		<h3>Últimas cadastradas</h3>
		<hr>
		<div class="row">
			<c:forEach items="${ultimasCadastradas}" var="prof">
				<div class="col-md-2">
	    			<a href="<c:url value='/profissionais/view/${prof.id}'/>" class="thumbnail">
	      				<c:forEach items="${prof.fotos}" var="f">
	    					<c:if test="${f.capa == 'S' }">
	    						<img src="<c:url value='http://imagens.terapiarj.com.br/${f.foto }'/>" alt="${foto.legenda}">
	    					</c:if>
	    				</c:forEach>
	      				<div class="caption">
		        			<h5>${prof.nome}</h5>
	      				</div>
	      			</a>
	  			</div>
			</c:forEach>
		</div>
		<h3>Mais visualizadas no mês</h3>
		<hr>
		<div class="row">
			<c:forEach items="${visualizadasMes}" var="prof">
				<div class="col-md-2">
	    			<a href="<c:url value='/profissionais/view/${prof.id}'/>" class="thumbnail">
	      				<c:forEach items="${prof.fotos}" var="f">
	    					<c:if test="${f.capa == 'S' }">
	    						<img src="<c:url value='http://imagens.terapiarj.com.br/${f.foto }'/>" alt="${foto.legenda}">
	    					</c:if>
	    				</c:forEach>
	      				<div class="caption">
		        			<h5>${prof.nome}</h5>
	      				</div>
	      			</a>
	  			</div>
			</c:forEach>
		</div>
		<h3>Mais votadas</h3>
		<hr>
		<div class="row">
			<c:forEach items="${votadas}" var="prof">
				<div class="col-md-2">
	    			<a href="<c:url value='/profissionais/view/${prof.id}'/>" class="thumbnail">
	      				<c:forEach items="${prof.fotos}" var="f">
	    					<c:if test="${f.capa == 'S' }">
	    						<img src="<c:url value='http://imagens.terapiarj.com.br/${f.foto }'/>" alt="${foto.legenda}">
	    					</c:if>
	    				</c:forEach>
	      				<div class="caption">
		        			<h5>${prof.nome}</h5>
	      				</div>
	      			</a>
	  			</div>
			</c:forEach>
		</div>
		<h3>Mais visualizadas no geral</h3>
		<hr>
		<div class="row">
			<c:forEach items="${visualizadasGeral}" var="prof">
				<div class="col-md-2">
      				<a href="<c:url value='/profissionais/view/${prof.id}'/>" class="thumbnail">
	      				<c:forEach items="${prof.fotos}" var="f">
	    					<c:if test="${f.capa == 'S' }">
	    						<img src="<c:url value='http://imagens.terapiarj.com.br/${f.foto }'/>" alt="${foto.legenda}">
	    					</c:if>
	    				</c:forEach>
	      				<div class="caption">
		        			<h5>${prof.nome}</h5>
	      				</div>
	      			</a>
	  			</div>
			</c:forEach>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>