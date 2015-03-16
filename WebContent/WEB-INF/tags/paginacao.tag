<%@ attribute name="size" required="true" type="java.lang.Integer"%>
<%@ attribute name="position" required="true" type="java.lang.Integer"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%
	int width = 100;
//	if(size > 10){
//		if(position > 4 && position < (size-5))
	//		width = 680;
		//else
			//width = 640;
	//}else if(size == 10){
//		width = 640;
	//}else if(size == 9){
//		width = 610;
	//}else if(size == 8){
		//width = 580;
	//}else if(size == 7){
//		width = 540;
//	}else if(size == 6){
		//width = 500;
	//}else if(size == 5){
//		width = 460;
//	}else if(size == 4){
		//width = 420;
	//}else if(size == 3){
//		width = 390;
//	}else if(size == 2){
		//width = 80;
	//}
%>

<% if(size > 1){ %>
<div class="rodape">
	<div class="paginacao" style="width: <%= width%>%;">
		<% 
			boolean maiorQue2 = (size > 2);
			String label;
			//int cursor = position-1;
			int inicio = position-4;
			
			if(inicio < 1)
				inicio = 1;
			int fim = position+4;
			
			if(fim > size || 10 > size)
				fim = size;
			else if(fim < 10)
				fim = 9;
			
			if(fim == size && inicio < size-8)
				inicio = size-8;
			if(maiorQue2){
				int previous = position-1;
				if(previous < 1)
					previous = 1;
				if(1 == position){
					out.print("<a href='#' class='botao-select'>primeira</a>");
					out.print("<a href='#' class='botao-select'>anterior</a>");
					
				}else{
					out.print("<a href='javascript:pageSelected(1);' class='botao'>primeira</a>");
					out.print("<a href='javascript:pageSelected("+previous+");' class='botao'>anterior</a>");
				}
		 	}
			
			if(size > 9 && position > 5)
				out.print("<a href='#' class='botao-select'>...</a>");
			
			for(int i = inicio; i <= fim; i++){
				label = ((i)<10 ? "0" : "");
				label +=(i);
				if(position == i)
					out.print("<a href='#' class='botao-select'>"+label+"</a>");
				else
					out.print("<a href='javascript:pageSelected("+i+");' class='botao'>"+label+"</a>");
		 	}
			
			if(size > 9 && position < size-4)
				out.print("<a href='#' class='botao-select'>...</a>");
			
			if(maiorQue2){
				int next = position+1;
				if(next > size)
					next = size;
				if(size.equals(position)){
					out.print("<a href='#' class='botao-select'>próxima</a>");
					out.print("<a href='#' class='botao-select'>última</a>");
				}else{
					out.print("<a href='javascript:pageSelected("+next+");' class='botao'>próxima</a>");
					out.print("<a href='javascript:pageSelected("+(size)+");' class='botao'>última</a>");
				}
			} %>
	</div>
</div>

<script type="text/javascript">
	function pageSelected(num){
		location.href = "${action}/"+num;
	}
</script>

<% } %>