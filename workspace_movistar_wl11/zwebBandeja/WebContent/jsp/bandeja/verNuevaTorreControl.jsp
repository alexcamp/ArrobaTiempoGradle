<%@page import="java.util.*"%>
<%@page import="com.telefonica_chile.bandeja.web.acciones.UtilesBandeja" %>
<%@ page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO" %>
<%@page import="com.telefonica_chile.bandeja.torreControl.CuadroMandoDTO"%>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.MenuDTO" %>
<%@page import="co.com.atiempo.dto.DepartamentoDTO"%>
<%@page import="co.com.atiempo.dto.MunicipioDTO"%>
<%@page import="co.com.atiempo.dto.LocalidadDTO"%>



<jsp:useBean id="controladorDeAplicacion"
	class="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" scope="session" />
	
<%
	UsuarioWeb usuario = controladorDeAplicacion.getUsuario();
// Los Combos para los Filtros.
ArrayList listSegmentos = (ArrayList) request.getAttribute("fTC_Segmento");
ArrayList listLocalidad=(ArrayList)request.getAttribute("listLocalidad");
ArrayList listaMunicipio=(ArrayList)request.getAttribute("listaMunicipio");
ArrayList listDepartamento=(ArrayList)request.getAttribute("listaDepartamento");
TreeMap listaFlujoL = (TreeMap) request.getAttribute("listaFlujo");

%>

<HTML>
<HEAD>
<script language="javascript" src="/bandejaweb/js/jslib.js"></script>
<SCRIPT language="JavaSCript">
<!--

 function busquedaInicial(){
// 	var inicializa()
// 	tomarForm(document.forma);
 }
 
 function tomarForm(forma){  

 // limpio los IFRAME
  window.titulo1.document.location = "about:blank";
  window.iframe1.document.location = "about:blank";
  window.iframe2.document.location = "about:blank";
  window.titulo2.document.location = "about:blank";

  forma.action="verMarcoTorreControl.acc";
  forma.fTC_Cuadro.value="2";
  forma.fTC_Tipo.value="CUADRO";
  forma.target = "iframe3";
  forma.submit();

  forma.action = "verBandejaTorreControl.acc";
  forma.target = "";
  
 }
 
function filtraPaginas(forma) {
	resetPagina();
    forma.submit();
}

function buscarLocalidad(forma)
{
  resetLocalidad();

  forma.action = "BuscarLocalidad.acc";
  forma.target = "iframeOculto";
  forma.submit();
  forma.action = "verBandejaTorreControl.acc";
  forma.target = "";
}
function cambioDepto(forma)
{
	forma.municipio.value="00000";
	forma.localidad.value="00000000";
	forma.action = "verNuevaTorreControl.acc";
  	//forma.target = "iframeOculto";
  	forma.submit();
	//forma.action = "verBandejaTorreControl.acc";
  	//forma.target = "";
}
function cambioMunicipio(forma)
{
	forma.localidad.value="00000000";
	forma.action = "verNuevaTorreControl.acc";
  	//forma.target = "iframeOculto";
	forma.submit();
	//forma.action = "verBandejaTorreControl.acc";
  	//forma.target = "";
}
function cambioLocalidad(forma)
{
	//document.forma.submit();
}



function getValorSelected( campo ) {

	if (campo.options.selectedIndex > 0)
		return campo.options[campo.options.selectedIndex].value;
	
	return "";

}

function verLista(depto, actividad)
{
	var forma = document.form1;
	
	forma.departamento.value = depto;
	var elmunicipio=document.getElementById("elmunicipio");
	
	forma.municipio.value=elmunicipio.value;
	var lalocalidad=document.getElementById("lalocalidad");
	
	forma.localidad.value=lalocalidad.value;
	
	forma.fTC_Actividad.value = actividad;
	
	var lafamilia=document.getElementById("lafamilia");
	forma.familiaPetiAtis.value=lafamilia.value;
	
	var elsegmento=document.getElementById("elsegmento");
	forma.segmento.value=elsegmento.value;
	
	var elflujo=document.getElementById("elflujo");
	forma.otrostipos.value=elflujo.value;
	
	var laopcocat=document.getElementById("laopcocat");
	forma.opcocat.value=laopcocat.value;
	
	forma.submit();
}

function resetLocalidad( ) {
	var forma = document.forma;
	forma.localidad.selectedIndex = 0;
	forma.localidad.options.length = 1;
}

function addLocalidad( codigo, valor ) {
	var forma = document.forma;
	var j = forma.localidad.options.length;
	forma.localidad.options.length++;
	forma.localidad.options[ j ].value = codigo;
	forma.localidad.options[ j ].text = valor;
}

//-->
</script>
<TITLE>Torre de Control</TITLE>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</HEAD>
<BODY onload="javascript:busquedaInicial();">

<%
	/* Utilizado en la bandeja push */
	String redirectBandejaPushURL = (String) request.getAttribute("redirectBandejaPush");
	if (redirectBandejaPushURL != null) {
		out.println("<html><head><META HTTP-EQUIV=Refresh CONTENT=\"0; URL=" + redirectBandejaPushURL + "\"></head></html>");
		return;
	}
%>
	
<%
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
		                <span class="nombre-app">@Tiempo &gt; Torre de Control</span>
		            </td>
		            <td align="right">
		                <img src="/bandejaweb/img/logo_atiempo.gif">
		            </td>
		        </tr>
		    </tbody>
		</table>

<!-- Mostrar la tabla de filtro, en caso de que el usuario desea ver la lista de cruzadas con parametros existentes -->
<form name="form1" action="verNuevaBandejaTorreControl.acc" method="post">
 <input type="hidden" name="departamento">
 <input type="hidden" name="municipio">
 <input type="hidden" name="localidad">
 <input type="hidden" name="segmento">
 <input type="hidden" name="otrostipos">
 <input type="hidden" name="opcocat">
 <input	type="hidden" name="fTC_Contratista">
 <input type="hidden" name="fTC_Segmento">
 <input type="hidden" name="fTC_Flujo">
 <input type="hidden" name="familiaPetiAtis">
 <input type="hidden" name="fTC_fechaCompromiso">
 <input type="hidden" name="fTC_Actividad">
 
</form>


<form name="forma" action="verNuevaTorreControl.acc">
<input type="hidden" name="fTC_Cuadro" value="">
<input type="hidden" name="fTC_IdDepartamento" value="">
<input type="hidden" name="fTC_IdActividad" value="">
<input type="hidden" name="fTC_Tipo" value="">

<table id="filtro-chico" style="display: none" width="100%">
 <tr class="titulo-tabla">
  <td class="titulo-tabla">Filtro de Torre de Control</td>
  <td align="right" valign="bottom"><a
	href="javascript:muestra_filtro();" class="link-simple-filtro"
	title="Mostrar Filtro Completo"> <img
	src="/bandejaweb/img/restaurar.gif" height="14" border="0"></a></td>
 </tr>
</table>
<table id="filtro-grande">
	<tr class="titulo-tabla">
		<td colspan="7" class="titulo-tabla">Filtro de Torre de Control</td>
		<td align="right" valign="bottom"><a
			href="javascript:oculta_filtro();" class="link-simple-filtro"
			title="Ocultar Filtro"> <img src="/bandejaweb/img/minimizar.gif"
			height="14" border="0"> </a></td>
	</tr>
	<tr class="elementos-filtro">
		<td align="right">Departamento</td>
		<td>: <select name="departamento" onChange="cambioDepto(this.form);" id="eldepartamento">
			<OPTION value="00" >Seleccione Departamento</OPTION>
			<%
			if(listDepartamento!=null)
			{
				String valorDep="";
				if(request.getParameter("departamento")!=null)
					valorDep=request.getParameter("departamento");
				for(Iterator iterator=listDepartamento.iterator();iterator.hasNext();)
				{
					DepartamentoDTO departamentoDTO=(DepartamentoDTO)iterator.next();
					%>
					<option <%=UtilesBandeja.selected(departamentoDTO.getCodDpt(), valorDep)%>
					value="<%=departamentoDTO.getCodDpt()%>"><%=departamentoDTO.getNombreDepartamento()%></option>
					<%
				}
			}
			%>
		</select></td>

		<td align="right">Municipio</td>
		<td>:<select name="municipio" onchange="cambioMunicipio(this.form);" id="elmunicipio">
				<OPTION value="00000">Seleccione Municipio</OPTION>
				<%
				if(listaMunicipio!=null)
				{
					String valorMun="";
					if(request.getParameter("municipio")!=null)
						valorMun=request.getParameter("municipio");
					for(Iterator iterator=listaMunicipio.iterator();iterator.hasNext();)
					{
						MunicipioDTO municipioDTO=(MunicipioDTO)iterator.next();
						%>
						<option <%=UtilesBandeja.selected(municipioDTO.getCod_mun(), valorMun)%>
						value="<%=municipioDTO.getCod_mun()%>" ><%=municipioDTO.getNombre_municipio()%></option>
						<%
					}
				}
				%>
			</select></td>
		<td align="right">Localidad</td>
		<td>:<SELECT name="localidad" id="lalocalidad">
			<option value="00000000">Seleccione Localidad</option>
			<%
			String valorLoc="";
			if(request.getParameter("localidad")!=null)
				valorLoc=request.getParameter("localidad");
			if(listLocalidad!=null)
			{
				for(Iterator iterator=listLocalidad.iterator();iterator.hasNext();)
				{
					LocalidadDTO localidadDTO=(LocalidadDTO)iterator.next();
					%>
					<OPTION <%=UtilesBandeja.selected(localidadDTO.getCodLoc(), valorLoc)%>
					value="<%=localidadDTO.getCodLoc()%>"><%=localidadDTO.getNombreLocalidad()%></OPTION>
					<%
				}
			}
			%>
		</SELECT></td>
		<td align="right">Segmento</td>
		<td>: <select name="segmento" id="elsegmento">
			<option value="">Todos los segmentos</option>
			<%
			if(listSegmentos!=null)
			{
				for (Iterator it = listSegmentos.iterator(); it.hasNext();) {
					SegmentoDTO segmentos = (SegmentoDTO) it.next();
					String valorSegmento = request.getParameter("segmento");
				%>
				<option
					<%=UtilesBandeja.selected(segmentos.getSegmId().toString(), valorSegmento)%>
					value="<%=segmentos.getSegmId()%>"><%=segmentos.getSegmDescripcion()%></option>
				<%}
			}
			%>
		</select></td>
	</tr>
	<tr class="elementos-filtro">
		<td align="right">Familia</td>
		<td>:
		<%
			String familiaAntes="00";
			if(request.getParameter("familiaPetiAtis")!=null)
				familiaAntes=request.getParameter("familiaPetiAtis");
		%>
		<select name="familiaPetiAtis" id="lafamilia">
				<OPTION value="00" <% if(familiaAntes.equals("00")){ %> selected="selected" <%}%>>Seleccione Familia</OPTION>
				<OPTION value="LB" <% if(familiaAntes.equals("LB")){ %> selected="selected" <%}%>>Solo LB</OPTION>
				<OPTION value="BA" <% if(familiaAntes.equals("BA")){ %> selected="selected" <%}%>>Solo BA</OPTION>
				<OPTION value="TV" <% if(familiaAntes.equals("TV")){ %> selected="selected" <%}%>>Solo TV</OPTION>
				<OPTION value="IC" <% if(familiaAntes.equals("IC")){ %> selected="selected" <%}%>>Solo IC</OPTION>
				<OPTION value="TVIC" <% if(familiaAntes.equals("TVIC")){ %> selected="selected" <%}%>>TV e IC</OPTION>
				<OPTION value="BAIC" <% if(familiaAntes.equals("BAIC")){ %> selected="selected" <%}%>>BA e IC</OPTION>
				<OPTION value="BATV" <% if(familiaAntes.equals("BATV")){ %> selected="selected" <%}%>>BA y TV</OPTION>
				<OPTION value="BATVIC" <% if(familiaAntes.equals("BATVIC")){ %> selected="selected" <%}%>>BA,TV e IC</OPTION>
				<OPTION value="LBIC" <% if(familiaAntes.equals("LBIC")){ %> selected="selected" <%}%>>LB e IC</OPTION>
				<OPTION value="LBTV" <% if(familiaAntes.equals("LBTV")){ %> selected="selected" <%}%>>LB y TV</OPTION>
				<OPTION value="LBBA" <% if(familiaAntes.equals("LBBA")){ %> selected="selected" <%}%>>LB y BA</OPTION>
				<OPTION value="LBTVIC" <% if(familiaAntes.equals("LBTVIC")){ %> selected="selected" <%}%>>LB,TV e IC</OPTION>
				<OPTION value="LBBAIC" <% if(familiaAntes.equals("LBBAIC")){ %> selected="selected" <%}%>>LB,BA e IC</OPTION>
				<OPTION value="LBBATV" <% if(familiaAntes.equals("LBBATV")){ %> selected="selected" <%}%>>LB,BA y TV</OPTION>
				<OPTION value="LBBATVIC" <% if(familiaAntes.equals("LBBATVIC")){ %> selected="selected" <%}%>>LB,BA,TV e IC</OPTION>
		</select></td>
		<td>Op Com</td>
		<td> :
			<%
			String opcocat="0";
			if(request.getParameter("opcocat")!=null)
				opcocat=request.getParameter("opcocat");
			%>
			<select name="opcocat" id="laopcocat">
				<OPTION value="0" <% if(opcocat.equals("0")){ %> selected="selected" <%}%>>Seleccione Tipo Op</OPTION>
				<OPTION value="A" <% if(opcocat.equals("A")){ %> selected="selected" <%}%>>Alta</OPTION>
				<OPTION value="T" <% if(opcocat.equals("T")){ %> selected="selected" <%}%>>Transferencia</OPTION>
				<OPTION value="R" <% if(opcocat.equals("R")){ %> selected="selected" <%}%>>Reconexión</OPTION>
				<OPTION value="S" <% if(opcocat.equals("S")){ %> selected="selected" <%}%>>Suspensión</OPTION>
				<OPTION value="P" <% if(opcocat.equals("P")){ %> selected="selected" <%}%>>PostVenta</OPTION>
			</select></td>
		<td align="right">Fe. Compromiso</td>
		<td>: <select name="fTC_FechaCompromiso">
			<option value="">Todas</option>
			<option value="<0">Fecha No Cumplida</option>
			<option value="=0">Se Cumple Fecha Hoy</option>
			<option value=" in (1,2,3,4,5)  ">Se Cumple Fecha 5 Proximos Dias</option>
		</select>
		</td>
		<td align="right">Flujo</td>
		<td>:<select name="otrostipos" id="elflujo" >
			<option value="">Todas</option>
			<%
			if(listaFlujoL!=null)
			{
				Iterator e = listaFlujoL.keySet().iterator();
					while (e.hasNext()) {
						String nombre = (String) e.next();
						String valorTipo = request.getParameter("otrostipos");%>
								<option
									<%=UtilesBandeja.selected(listaFlujoL.get(nombre).toString(), valorTipo)%>
									value="<%=listaFlujoL.get(nombre).toString()%>"><%=nombre%></option>
	
				<%}
			}
			%>
		</select><INPUT type="button" onClick="tomarForm(this.form)"
			value="Filtro" />
		</td>
		
</TABLE>
</FORM>
<!-- Cuadros de Mando -->

<%
	String aboutBlank = "about:blank";
%>
<iframe name="titulo1" style="height: 40px; width: 784px" id="titulo1" scrolling="no"
	src="<%=aboutBlank%>"
	frameborder="0"></iframe>
<iframe name="iframe1" style="height: 172px; width: 800px" id="iframe1"
	src="<%=aboutBlank%>"
	frameborder="0"></iframe>
<HR>
<iframe name="titulo2" style="height: 40px; width: 784px" id="titulo2" scrolling="no"
	src="<%=aboutBlank%>"
	frameborder="0"></iframe>
<iframe name="iframe2" style="height: 172px; width: 800px" id="iframe2"
	src="<%=aboutBlank%>"
	frameborder="0"></iframe>
<HR>
<%
String iFrame3 = "iframe3";
String iFrameOculto = "iframeOculto";
%>
<iframe name="iframe3" style="width:0px; height:0px; border: 0px id="<%=iFrame3%>"  src="<%=aboutBlank%>" frameborder="0"></iframe>
<iframe name="iframeOculto" style="width:0px; height:0px; border: 0px id="<%=iFrameOculto%>"  src="<%=aboutBlank%>" frameborder="0"></iframe>
</BODY>
</HTML>