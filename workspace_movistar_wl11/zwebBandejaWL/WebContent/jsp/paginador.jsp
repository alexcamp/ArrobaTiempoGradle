<%@page import="com.tecnonautica.utiles.tablas.Tabla"%>
<%
	String nombreTabla = (String) request.getAttribute("nombreTabla");
	Tabla tabla = null;
	if (nombreTabla != null)
		tabla = (Tabla) request.getAttribute(nombreTabla);
%>
<!-- INICIO PAGINADOR -->
<script language="JavaScript">
<!--
function cambiaPagina(pag)
{
	var forma = document.forma;
	forma.PAGINA_ACTUAL.value = pag;
	forma.submit();
}
function resetPagina() {
	if(document.forma.PAGINA_ACTUAL)
	document.forma.PAGINA_ACTUAL.value = 1;
}
// -->
</script>
<%
	if (tabla != null && tabla.size() > 0) {
%>
<input type="hidden" name="PAGINA_ACTUAL" value="<%= tabla.getPaginaActual() %>">
<% if (tabla.hayPaginaPrevia()) { %>
       <a href="#" onClick="return cambiaPagina(<%= tabla.getPaginaActual() - 1%>)">
       <img src="/bandejaweb/img/pag_anterior.gif" width="15" height="13" border="0"></a>  
<% } else { %>
        <img src="/bandejaweb/img/pag_anterior_pas.gif" width="15" height="13"> 
<% } %>
       Página
       <select name="offsetPag" class="botones-chicos"  onChange="return cambiaPagina(this[this.selectedIndex].value)">
<%
	for (int i = 1; i <= tabla.getTotalPaginas(); i++) {
%>
	<option value="<%= i %>" <%= i == tabla.getPaginaActual() ? "selected" : ""%>><%= i %></option>
<%
	}
%>
       </select> de <%= tabla.getTotalPaginas() %>
<% if (tabla.hayPaginaSiguiente()) { %>
       <a href="#" onClick="return cambiaPagina(<%= tabla.getPaginaActual() + 1%>)">
       <img src="/bandejaweb/img/pag_siguiente.gif" width="15" height="13" border="0"></a>  
<% } else { %>
        <img src="/bandejaweb/img/pag_siguiente_pas.gif" width="15" height="13"> 
<% } %>
	Total:<%=tabla.getNroTotalElementos()%>
<%
	}
%>
<!-- FIN PAGINADOR -->
