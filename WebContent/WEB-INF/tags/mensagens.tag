<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty errors}">
	<div class="alert alert-danger alert-dismissable">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
       	<c:forEach var="error" items="${errors}">
		    ${error.message}<br />
		</c:forEach>
    </div>
</c:if>
<c:if test="${not empty mensagem}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
	    ${mensagem}
	</div>
</c:if>
