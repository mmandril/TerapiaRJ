<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<tiles:insertTemplate template="../layout/template.jsp">
	<tiles:putAttribute name="menu">
	</tiles:putAttribute>
	
	
	
	<tiles:putAttribute name="body">
		<div class="modal fade" id="loginModal">
		  	<div class="modal-dialog">
		  		<form action="<c:url value="/admin/login"/>" name="loginForm" method="post">
			    	<div class="modal-content">
			      		<div class="modal-header">
			        		<h4 class="modal-title">Login</h4>
			      		</div>
			      		<div class="modal-body">
			      			<tag:mensagens></tag:mensagens>
		        			<div class="form-group">
	   							<label for="email">Email</label>
								<input type="text" class="form-control" name="usuario.email" id="email" />	
 							</div>
 							<div class="form-group">
   								<label for="password">Senha</label>
								<input type="password" class="form-control" name="usuario.senha" id="password"/>
 							</div>
		      			</div>
			      		<div class="modal-footer">
			        		<button type="submit" class="btn btn-primary" id="submit">Enviar</button>
			      		</div>
	    			</div><!-- /.modal-content -->
	    		</form>
		  	</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="script">
		<script type="text/javascript">
			$('#loginModal').modal({
				keyboard: false,
				backdrop: false
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertTemplate>