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
		<div class="col-md-3">
			<div data-spy="affix" class="well" data-offset-top="60" data-offset-bottom="200">
    			<div class="thumbnail">
      				<img src="..." alt="...">
    			</div>
    			<div class="row">
    				${profissional.nome}
    			</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="row">
				${profissional.descricao}
			</div>
			<div class="row">
			
			</div>
		</div>
	</tiles:putAttribute>
	
	
</tiles:insertTemplate>