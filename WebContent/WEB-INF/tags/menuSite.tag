<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
	 <a class="navbar-brand" href="<c:url value=''/>"> Terapia RJ </a>
</header>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0" id="nav">
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
			<li><a href="<c:url value='/'/>">Home</a></li>
			<li><a href="<c:url value='/profissionais/tipo/1'/>">Terapeuta</a></li>
			<li><a href="<c:url value='/profissionais/tipo/2'/>">Massagista</a></li>
			<li><a href="<c:url value='/contato/anuncie'/>">Anuncie</a></li>
		</ul>
	</div>
</nav>