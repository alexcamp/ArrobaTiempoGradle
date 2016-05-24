<%@ page import="com.tecnonautica.mvc.Vista,java.util.Calendar" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>				
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.MenuDTO" %>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig" %>
<%@page import="co.com.atiempo.dto.PeticionDTO" %>
<%@ page import="co.com.atiempo.dto.DominioTipoPcDTO" %>
<%
	int anno = Calendar.getInstance().get(Calendar.YEAR);	
	int randomInt = 1 + (int)(Math.random() * 50);
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
<jsp:useBean id="controladorDeAplicacion"
	class="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" scope="session" />
	
<%
	UsuarioWeb usuario = controladorDeAplicacion.getUsuario();
	ArrayList listMenuesUsuario = usuario.getMenues();
	String    urlSubidaArchivo  = (String) request.getAttribute("urlVPISTBBA");

%>
<script language="javascript">
<!--

var newWinIn;
function limpiar()
{
	var frm=document.formaBuscar;
	for (var i=0;i<frm.elements.length;i++)
	{
		var e = frm.elements[i];
		if(e.type==null || e.type!='text')
			continue;
		e.value="";
	}
	frm.submit();
}

function tomarFrame2(petinumero)
{
	//alert(petinumero);
	var url="<%=ApplicationConfig.getUrlBase(ApplicationConfig.APP_VPISTBBA)%>/"+"BuscadorPeticionesCo.acc?folio="+petinumero;
	if (newWinIn != null)
		newWinIn.close();
	//alert(url);
	var newWinIn = window.open("about:blank",'PantallaBuscador','width=900,height=660,resizable=yes,top=0,left=0,scrollbars=yes,location=no');
	newWinIn.location.href=url;

	newWinIn.focus();
}

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
                        <img src="/bandejaweb/img/t.gif" width="4">
                        <img src="/bandejaweb/img/icono_usuario.gif">
                        Usuario: <%= usuario.getUsername() %>
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
				<%
				if ( listMenuesUsuario != null ) {
				  for (Iterator iter = listMenuesUsuario.iterator(); iter.hasNext();) {
					MenuDTO menuPadre = (MenuDTO) iter.next();
					if ( menuPadre == null)
						continue;
				%>
					<SELECT class="menu-cabecera" onchange="javascript:cambiaPaginaMenuCabecera(this)">
						<OPTION value="<%=menuPadre.getMenuUrl()%>"><%=menuPadre.getMenuDescripcion()%></OPTION>
						<%
							String url="";
							if ( menuPadre.getMenuHijos() == null )
								continue;
							for (Iterator iterator = menuPadre.getMenuHijos().iterator();iterator.hasNext();) {
								MenuDTO menuHijo = (MenuDTO) iterator.next();
								if ( menuHijo == null )
									continue;

								int idMenu = menuHijo.getMenuId().intValue();
								url=menuHijo.getMenuUrl();	
							%>
								<OPTION value="<%=url%>" id="<%=idMenu%>">&nbsp;-&nbsp;<%=menuHijo.getMenuDescripcion()%></OPTION>
						<% } %>
					</SELECT>
				<%
				  }
				} 
				%>
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
		                <span class="nombre-app">@Tiempo &gt; Buscador Avanzado Instalacion Equipos</span></td>
		            <td align="right">
		                <img src="/bandejaweb/img/logo_atiempo.gif">
		            </td>
		        </tr>
		    </tbody>
		</table>
        
        
<!-- CUERPO -->


<br>

<%
	String folioAtis="";
	String rut="";
	String rutDv="";
	String folioPc="";
	if(request.getParameter("folioAtis")!=null)
		folioAtis=request.getParameter("folioAtis");
	if(request.getParameter("rut")!=null)
		rut=request.getParameter("rut");
	if(request.getParameter("rutDv")!=null)
		rutDv=request.getParameter("rutDv");
	if(request.getParameter("folioPc")!=null)
		folioPc=request.getParameter("folioPc");
	ArrayList comboTipoPc = new ArrayList();
	if((ArrayList)request.getAttribute("comboTipoPc") != null){
		comboTipoPc = (ArrayList)request.getAttribute("comboTipoPc");
	}			
%>
<SCRIPT>
	function buscarOut(forma)
	{
		var frm=forma;
		for (var i=0;i<frm.elements.length;i++)
		{
			var e = frm.elements[i];
			if(e.type==null || e.type!='text')
				continue;
			e.value=Trim(e.value);
		}
		if(forma.folioAtis.value=="" && forma.rut.value=="" && forma.rutDv.value=="" && forma.folioPc.value=="")
		{
			alert("Debe ingresar un parámetro de búsqueda");
			return;
		}
		else
		{
			var miInteger1 = forma.folioAtis.value;
			if ( isNaN(miInteger1) )
			{
				alert ("Ingrese sólo números para Número de Petición");
				return;
			}
			if(forma.folioPc.value!="" && forma.typePc.value==""){
				alert('Debe seleccionar un tipo de Pc');
				return;
			}			
		}
		forma.submit();
	}
	function enviarAM()
	{
		if(!confirm("Esta accion generará el Archivo Masivo Completo . ¿Desea Continuar?\nAdvertencia: Si ya generó el archivo CANCELE y busque en la Bandeja de Reportes"))
	 	{
		 	document.formaBuscar.action="verInstalarEquipos.acc";
		 	document.formaBuscar.target = "";
		 	return;
	 	}
		document.formaBuscar.action="<%=ApplicationConfig.getUrlBase(ApplicationConfig.APP_VPISTBBA) %>/GeneraAODSOutBound?userAccion=<%=usuario.getUsername()%>&iduserAccion=<%=usuario.getId() %>";
	 	document.formaBuscar.submit();
	
	 	document.formaBuscar.action="verInstalarEquipos.acc";
	 	document.formaBuscar.target = "";
	 	
	 	setTimeout("enviarBandejaMasiva();",30000);
	}
	function enviarBandejaMasiva()
	{
		var formaMasiva = document.formaMasiva;
		var actionOld = formaMasiva.action;
		var newWin = window.open("about:blank",'PantallaMasiva','width=800,height=600,resizable=yes,top=0,left=0,scrollbars=yes');
		formaMasiva.action = "/bandejaweb/ListadoMasivo.acc";
		formaMasiva.target = "PantallaMasiva";
		formaMasiva.submit();
		formaMasiva.action = actionOld;
		newWin.focus();
	}
</SCRIPT>
<form name="formaBuscar" action="verInstalarEquipos.acc" method="post">
<input type="hidden" name="ID_USUARIO" value="<%=usuario.getId() %>">
<table border="0" id="filtro-grande" style="width: 800px;">
	<tr class="elementos-filtro">
		<td align="center">N° de Petición (ATIS) </td>
		<td align="center">Identificador Cliente</td>
		<td align="center">Identificador PC</td>		
	</tr>
	<tr class="elementos-filtro">
		<td align="center">
		  <input type="text" name="folioAtis" size=14 maxlength="14" value="<%=folioAtis%>">
		</td>
		<td align="center">
		  <input type="text" name="rut" size=15 maxlength="20" value="<%=rut%>">-<input
			type="text" name="rutDv" size=2 maxlength="2" value="<%=rutDv%>">
		  <br>
		</td>
		<td align="center">
		  <input type="text" name="folioPc" size=14 maxlength="14" value="<%=folioPc%>">
  			  <SELECT name="typePc" id="typePc">
				<OPTION value="">Seleccione un PC</OPTION>
				<%
				for(Iterator iterator = comboTipoPc.iterator(); iterator.hasNext();){
					DominioTipoPcDTO domDTO = (DominioTipoPcDTO)iterator.next();
				%>
					<option value="<%=domDTO.getValue()%>"><%=domDTO.getLabel()%></option>
				<%}%>		
			  </SELECT>		  
		</td>		
	</tr>
	<TR class="elementos-filtro">
		<TD colspan="3" align="center">
			<input type="button" value="Buscar" class="botones-chicos" onclick="buscarOut(this.form)">
		</TD>
	</TR>
</table>

<%
	String url="about:blank";
	if(request.getAttribute("peticiones")!=null)
	{
		ArrayList peticiones=(ArrayList)request.getAttribute("peticiones");
		String msgError = (String)request.getAttribute("msgError");
		if ( msgError!=null && msgError.length()!=0 ) {
		%>
			<table border="0" width="60%" class="listado">
				<tr class="elementos-filtro">
					<td align="center"><b><%= msgError %></b></td>
				</tr>
			</table>
		<%
		}		
		else if(peticiones.size()>0)//Si es una igual tiene que pinchar el link
		{
			/*
			 *	CR 00027016 - 2009/07/01
			 *		Se muestran mensajes de sugerencias sobre el listado de peticiones encontradas
			 */
		 	String msgResultado	= (String)request.getAttribute("msgResultado");
	 		ArrayList listadoSugerencias=(ArrayList)request.getAttribute("listadoSugerencias"); %>
			<table border="0" id="filtro-grande" style="width: 800px;">	 		
				<tr class="encabezados-tabla">
					<td colspan="3"><b><%=msgResultado%></b></td>
				</tr>
			<%
			if (listadoSugerencias != null && listadoSugerencias.size() > 0){ %>	

					<tr class="elementos-filtro">
					<%
					for (int i=0; i<listadoSugerencias.size(); i++ ) {
						String mensSugerencia	= (String)listadoSugerencias.get(i); %>
							<tr class="elementos-filtro"><td>- <%=mensSugerencia%></td></tr>
					<%
					}
			}			
		%>
			</table>
			<TABLE>
			<TR>
				<TD>Peticion</TD>
				<TD></TD>
			</TR>
			<%
			for(Iterator iterator=peticiones.iterator();iterator.hasNext();)
			{
				PeticionDTO pDto = (PeticionDTO)iterator.next();
				
			%>
				<TR>
					<TD><%=pDto.getCodPetCd() %>-(<%=pDto.getAgrupaciones() %>)-<%=pDto.getPetiNumero() %></TD>
					<TD>
					<%
						if(pDto.isEstaEnBandeja() && pDto.isEstaEnActividadPermitida())
						{
						%>
						<a href="<%=ApplicationConfig.getUrlBase(ApplicationConfig.APP_VPISTBBA)%>/vpistbba<%=pDto.getUrlBandeja() %>&ID_USUARIO=<%=usuario.getId()%>&USUARIO=<%=usuario.getUsername() %>" target="BuscadorOutbounVPI">Ver</a>
						<%
						}
						else
						{
						%>
						<a href="javascript:tomarFrame2(<%=pDto.getPetiNumero()%>)">Ver</a>
						<%
						}
					%>
					</TD>
				</TR>
			
			<%
			}
			%>
			</TABLE>
			<%
		}

	}
%>
</form>
<!-- PIE DE PAGINA -->
<jsp:include page="/jsp/template/pie.jsp" flush="true" />
</body>
</html>
