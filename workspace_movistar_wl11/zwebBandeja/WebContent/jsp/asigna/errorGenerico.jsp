<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page isErrorPage="true" import="java.io.*" %>

<html>
<head>
<LINK REL="SHORTCUT ICON" HREF="favicon.ico">
<title>Error en Asignaci&oacute;n</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</head>
<body>

<font color="red"><%= exception != null ? exception.toString() : "" %><br></font>

<!--
<%
	if (exception != null) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		out.print(sw);
		sw.close();
		pw.close();
	}
%>
-->
</body>

</html>
