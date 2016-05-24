<%@ page import="com.tecnonautica.utiles.web.MensajesWeb" %>
<%@ page import="java.util.Iterator" %>
<%
	MensajesWeb mensajes = MensajesWeb.getInstance(request);
	if (mensajes != null && mensajes.hayErrores()) {
%>		<span class="mensaje-error"><%

		for (Iterator it = mensajes.getErrores().iterator(); it.hasNext(); ) {
			%>-<%= it.next() %><br><%
		}

		%></span><%
	}
	if (mensajes != null && mensajes.hayOks()) {
		%><span class="mensaje-ok"><%

		for (Iterator it = mensajes.getOks().iterator(); it.hasNext(); ) {
			%>-<%= it.next() %><br><%
		}
		%></span>
<%
	}
%>