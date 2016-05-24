<%@ page import="com.tecnonautica.mvc.Vista,java.util.Calendar" %>
<%
	Vista vista=(Vista)request.getSession().getAttribute("vista");

	

	String urlHead = "/jsp/template/head.jsp", urlMain = "/jsp/bandeja/verBandeja.jsp", urlPie = "/jsp/bandeja/pie.jsp";
	int anno = Calendar.getInstance().get(Calendar.YEAR);
	
	if (vista != null) {
		urlHead = vista.getUrlArea("head");
		urlMain = vista.getUrlArea("main");
		urlPie = vista.getUrlArea("pie");
	} else {
		
	}	
	
	System.out.print("templateBandeja.jsp ::: valor de la vista "+vista);
	System.out.print("templateBandeja.jsp ::: urlHead "+urlHead);
%>


<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
    <head>
        <title>@Tiempo <%= anno %></title>
        <link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
        <LINK REL="SHORTCUT ICON" HREF="/bandejaweb/jsp/asigna/favicon.ico">
        <script src="js/jslib.js">
        </script>
        <script src="js/scroll.js">
        </script>
        
        <LINK REL="SHORTCUT ICON" HREF="/bandejaweb/img/favicon.ico">   
       
    </head>
    <body topmargin="0" leftmargin="0" bgcolor="#ffffff" marginheight="0" >
<!-- CABECERA -->
<jsp:include page="<%= urlHead %>" flush="true" />
<!-- CUERPO -->
<jsp:include page="<%= urlMain %>" flush="true" />
<!-- PIE DE PAGINA -->
<jsp:include page="<%= urlPie %>" flush="true" />
<br>
	</body>
</html>
