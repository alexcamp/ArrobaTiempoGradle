<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>				
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>

<jsp:useBean id="controladorDeAplicacion"
	class="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" scope="session" />
	
<%
	UsuarioWeb usuario = controladorDeAplicacion.getUsuario();
	ArrayList listMenuesUsuario = usuario.getMenues();
	String    urlSubidaArchivo  = (String) request.getAttribute("urlVPISTBBA");

%>
<html>
<HEAD>
<TITLE>Grabacion Exitosa</TITLE>
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<LINK REL="SHORTCUT ICON" HREF="/bandejaweb/jsp/asigna/favicon.ico">
<SCRIPT>
	function inicio()
	{
		window.opener.limpiar();
	}
</SCRIPT>
</HEAD>
<BODY onload="inicio();">
    <table border="0" cellpadding="0" cellspacing="0" height="75" width="780">
	    <tbody>
            <tr>
                <td colspan="6" height="10"><img src="/bandejaweb/img/t.gif" height="10" width="1"></td>
            </tr>

            <tr>
                <td>
                    <img src="/bandejaweb/img/t.gif" height="19" width="10">
                </td>

                <td rowspan="5">
                    <img src="/bandejaweb/img/logo.gif" width="100">
                </td>

                <td height="19" valign="bottom" class="txt-usuario-rol">
                    <img src="/bandejaweb/img/t.gif" width="4">
                    <img src="/bandejaweb/img/icono_usuario.gif">
                    Usuario: <%= usuario.getUsername() %>
                </td>
                <td align="right" height="19" valign="bottom">
					
					
					
                </td>
            </tr>

            <!-- franja amarillo limon -->
            <tr>
                <td bgcolor="#def84c" height="19" width="18"><img src="/bandejaweb/img/t.gif" height="19" width="1"></td>
                <td colspan="2" bgcolor="#def84c" height="19" align="right" width="100%">
			
                </td>
            </tr>
            <!-- franja blanca delgada -->
            <tr>
                <td width="18"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
                <td colspan="2"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
            </tr>

            <!-- franja color verde oscuro -->
            <!-- verde oscuro tol: #288e93 -->
            <!-- azul oscuro @T: #3e5e8d -->
            <tr>
                <td bgcolor="#def84c" width="18"><img src="/bandejaweb/img/t.gif" height="8" width="1"></td>
                <td colspan="2" bgcolor="#def84c" width="370"><img src="/bandejaweb/img/t.gif" height="1" width="1"></td>
            </tr>

            <!-- franja blanca bajo franja verde -->
            <tr>
                <td width="18"><img src="/bandejaweb/img/t.gif" height="5" width="1"></td>
                <td colspan="2"></td>
            </tr>

        </tbody>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="780">
	    <tbody>
	        <tr>
	            <td>
	                <span class="nombre-app">@Tiempo &gt; Grabacion Exitosa</span>
	            </td>
	            <td align="right">
	                <img src="/bandejaweb/img/logo_atiempo.gif">
	            </td>
	        </tr>
	        <TR>
	        	<TD colspan="2" align="center">
	        		<input type="button" value="Cerrar" onclick="window.close();" class="botones-chicos">
	        	</TD>
	        </TR>
	    </tbody>
	</table>
</BODY>        
</html>
