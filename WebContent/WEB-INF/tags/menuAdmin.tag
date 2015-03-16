<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <!-- /.navbar-header -->
    
    
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="<c:url value='/admin/home'/>">Home</a></li>
			<li><a href="<c:url value='/admin/areaAtuacao'/>">Área Atuação</a></li>
			<li><a href="<c:url value='/admin/cliente'/>">Cliente</a></li>
			<li><a href="<c:url value='/admin/clinica'/>">Clinica</a></li>
			<li><a href="<c:url value='/admin/perfil'/>">Perfil</a></li>
			<li><a href="<c:url value='/admin/plano'/>">Plano</a></li>
			<li><a href="<c:url value='/admin/profissional'/>">Profissional</a></li>
			<li><a href="<c:url value='/admin/tipoProfissional'/>">Tipo Profissional</a></li>
			<li><a href="<c:url value='/admin/usuario'/>">Usuário</a></li>
		</ul>
	    <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                <i class="fa fa-user fa-fw"></i> ${variaveisSessao.usuario.email} <i class="fa fa-caret-down"></i>
	            </a>
	            <ul class="dropdown-menu dropdown-user">
	                <li>
	                	<a href="#"><i class="fa fa-gear fa-fw"></i> <fmt:message key="menu.perfil"></fmt:message></a>
	                </li>
	                <li class="divider"></li>
	                <li>
	                	<a href="<c:url value='/admin/logout'/>"><i class="fa fa-sign-out fa-fw"></i> <fmt:message key="menu.sair"></fmt:message></a>
	                </li>
	            </ul>
	            <!-- /.dropdown-user -->
	        </li>
	        <!-- /.dropdown -->
	    </ul>
	</div>
</nav>