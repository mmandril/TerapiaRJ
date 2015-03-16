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
		<div class="row">
			<ul class="nav nav-pills">
				<c:forEach items="${areaAtuacaos}" var="area">
					<li role="presentation"><a href="<c:url value='/profissionais/tipoArea/${tipoProfissional.id}/${area.id}'/>">${area.nome}</a></li>
				</c:forEach>
				<li role="presentation"><a href="<c:url value='/profissionais/tipo/${tipoProfissional.id}'/>">Todos</a>
			</ul>
		</div>
		<h3>${tipoProfissional.nome} - 
		<c:choose>
			<c:when test="${areaAtuacao != null}">
				${areaAtuacao.nome}
			</c:when>
			<c:otherwise>
				Todos
			</c:otherwise>
		</c:choose>
		</h3> 
		<div class="row">
			<c:forEach items="${profissionals}" var="prof">
				<div class="col-md-2">
	    			<a href="" class="thumbnail">
	      				<img src="<c:url value='/imagens/foto1.jpg'/>" alt="...">
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
	</tiles:putAttribute>
	
	
</tiles:insertTemplate>