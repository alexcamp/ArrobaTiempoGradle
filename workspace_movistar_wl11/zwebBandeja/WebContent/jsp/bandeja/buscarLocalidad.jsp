<%@page import="java.util.*"%>
<%
	HashMap hashLocalidad =  (HashMap) request.getAttribute("hashLocalidad");

%>
<HTML>
<HEAD>
<SCRIPT language="JavaScript">
<!--

function addLocalidad( codigo, valor ) {
	window.parent.addLocalidad( codigo, valor );
}
//-->
</SCRIPT>
</HEAD>
<BODY>
<SCRIPT language="JavaScript">
<!--
<%
	for (Iterator it=hashLocalidad.keySet().iterator(); it.hasNext(); ) {
		String cod = (String) it.next();
		String valor = (String) hashLocalidad.get(cod);
		if ( valor==null || cod==null )
			continue;
%>
	addLocalidad ('<%= cod %>', '<%= valor%>');
<%
	}
%>
//-->
</SCRIPT>
<BODY>
</HTML>
