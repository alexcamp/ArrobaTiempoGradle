<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.tecnonautica.utiles.ldap.SimpleLdap"%>	
<html>
<HEAD>
<TITLE>Error - Accion No Autorizada</TITLE>
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<LINK REL="SHORTCUT ICON" HREF="/bandejaweb/jsp/asigna/favicon.ico">
</HEAD>
<BODY >
<SCRIPT>
	function function1()
	{
		document.forma1.submit();
	}
	function function2()
	{
		document.forma2.submit();
	}
</SCRIPT>
<%
	String usuario_token=null;
	if(request.getAttribute("usuario_token")!=null)
		usuario_token=(String)request.getAttribute("usuario_token");
%>
<form name="forma1" action="/bandejaweb/logoff.free" method="get">
<input type="hidden" name="usuario_token" value="<%=usuario_token %>">
</form>
<form name="forma2" action="/bandejaweb/logoff.free" method="get">
<input type="hidden" name="usuario_token_2" value="NOBODY">
</form>
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
                    <!--<img src="/bandejaweb/img/t.gif" width="4">
                    <img src="/bandejaweb/img/icono_usuario.gif">
                    Usuario: -->
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
	<%
		String valorAccion="";
		if(request.getAttribute("accion")!=null)
			valorAccion=(String)request.getAttribute("accion");
	%>
	<table border="0" cellpadding="0" cellspacing="0" width="780">
	    <tbody>
	    	<%
	    	if(valorAccion.equals("usuarioDuplicado"))
	    	{
	    	%>
	        <tr>
	            <td align="left">
	                <span class="nombre-app">@Tiempo &gt; Ya existe un usuario Logeado con el mismo User</span>
	                <BR>
	                Si desea deslogear al usuario actual presione "Continuar".<BR>
	                Si desea reintentar con un usuario distinto presione "Regresar"
	            </td>
	            <td align="right">
	                <img src="/bandejaweb/img/logo_atiempo.gif">
	            </td>
	        </tr>
	        <TR>
	        	<TD colspan="2" align="center">
	        		<input type="button" value="Continuar" onclick="function1();" class="botones-chicos">
	        		<input type="button" value="Regresar" onclick="function2();" class="botones-chicos">
	        	</TD>
	        </TR>
	        <%
			}
	        else if(valorAccion.equals("usuarioNoExistente"))
	        {%>
	        <tr>
	            <td align="left">
	                <span class="nombre-app">@Tiempo &gt; El usuario ingresado No existe en la Base de Atiempo.</span>
	            </td>
	            <td align="right">
	                <img src="/bandejaweb/img/logo_atiempo.gif">
	            </td>
	        </tr>
	        <TR>
	        	<TD colspan="2" align="center">
	        		<input type="button" value="Regresar" onclick="function2();" class="botones-chicos">
	        	</TD>
	        </TR>
	        <%}
	        else
	        {%>
	        <tr>
	            <td align="left">
	                <span class="nombre-app">@Tiempo &gt; Acción No Autorizada.</span>
	            </td>
	            <td align="right">
	                <img src="/bandejaweb/img/logo_atiempo.gif">
	            </td>
	        </tr>
	        <TR>
	        	<TD colspan="2" align="center">
	        		<input type="button" value="Cerrar" onclick="window.close();" class="botones-chicos">
	        		<input type="button" value="Volver" onclick="history.back();" class="botones-chicos">
	        	</TD>
	        </TR>
	        <%}%>
	    </tbody>
	</table>
</BODY>        
</html>

