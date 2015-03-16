<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO8859-1" pageEncoding="ISO8859-1"%>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<html>  
    <head>
    	<!--[if lt IE 9]>
	      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	    <![endif]-->
    	<title><fmt:message key="titulo.geral"/></title>  
    	
    	<link href="<c:url value='/css/bootstrap.min.css'/>" type="text/css" rel="stylesheet" />
    	<link href="<c:url value='/css/style.css'/>" type="text/css" rel="stylesheet" />
    </head>  
    <body>  
    	<tiles:insertAttribute name="menu" ignore="true"/>
		<div class="container">		
	    	<div class="row">  
	    		<br/>
	    		<tag:mensagens></tag:mensagens>  		
				<tiles:insertAttribute name="body"/>
			</div>
		</div>
		<footer>
			<nav id="footer" class="navbar navbar-default footer-content navbar-fixed-bottom">
           		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
					<div class="pull-left footer">
					</div>
				</div>
				<h5>RjTerapia - Todos os Direitos Reservados</h5>
	  		</nav>
		</footer>
		
		
		<script type="text/javascript" src="<c:url value='/js/jquery-2.1.3.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.maskedinput.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ckeditor.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/utils.js'/>"></script>

		<tiles:insertAttribute name="script" ignore="true"/>
    </body>  
</html>
