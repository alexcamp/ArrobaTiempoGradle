<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>				
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.MenuDTO" %>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig" %>
<%
	/* Utilizado en la bandeja push */
	String redirectBandejaPushURL = (String) request.getAttribute("redirectBandejaPush");
	if (redirectBandejaPushURL != null) {
		out.println("<html><head><META HTTP-EQUIV=Refresh CONTENT=\"0; URL=" + redirectBandejaPushURL + "\"></head></html>");
		return;
	}
%>
<jsp:useBean id="controladorDeAplicacion"
	class="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" scope="session" />
	
<%
	UsuarioWeb usuario = controladorDeAplicacion.getUsuario();
	ArrayList listMenuesUsuario = usuario.getMenues();
	String    urlSubidaArchivo  = (String) request.getAttribute("urlVPISTBBA");

%>
<script language="javascript">
<!--
var newWin;
// -->
</script>
<SCRIPT src="/bandejaweb/js/cambiaPaginaMenuCabecera.js"></SCRIPT>

     <form method="post">   
     <input type="hidden" name="usuario2" value="<%=usuario.getId()%>">
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
                        
                    </td>
                    <td align="right" height="19" valign="bottom">
						<a href="cambiarClave.acc" title="Modificar su Clave">
							<img src="/bandejaweb/img/boton_cambioclave.gif" border="0" vspace="0" hspace="0"
								width="15" height="15" title="Modificar su Clave"></a>
						<a href="verBandeja.acc" title="Ir a su Bandeja Inicial">
							<img src="/bandejaweb/img/boton_home.gif" border="0" vspace="0" hspace="0"
								width="15" height="15" title="Ir a su Bandeja Inicial"></a>
						<a href="logoff.acc?exit=true" title="Desconectarse de @Tiempo">
							<img src="/bandejaweb/img/boton_salir.gif" width="15" height="15" border="0"
								title="Desconectarse de @Tiempo"></a>
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
	</form>	
		<table border="0" cellpadding="0" cellspacing="0" width="780">
		    <tbody>
		        <tr>
		            <td>
		                <span class="nombre-app">@Tiempo &gt; Recupera Clave</span>
		            </td>
		            <td align="right">
		                <img src="/bandejaweb/img/logo_atiempo.gif">
		            </td>
		        </tr>
		    </tbody>
		</table>
        