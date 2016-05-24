<%@page import="com.telefonica_chile.bandeja.datos.usuarios.ValorVariableDTO" %>
<%@page import="com.telefonica_chile.bandeja.dto.PeticionDTO" %>
<%@page import="com.tecnonautica.utiles.tablas.Tabla" %>
<%@page import="java.util.*"%>
<%@page import="com.tecnonautica.utiles.basicos.FechaUtil" %>
<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@page import="com.telefonica_chile.bandeja.web.acciones.UtilesBandeja" %>
<%@ page import="com.telefonica_chile.bandeja.datos.usuarios.RolDTO" %>
<%@ page import="com.telefonica_chile.bandeja.web.AtiempoControladorDeAplicacion" %>
<%@ page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.bandeja.datos.usuarios.SegmentoDTO" %>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@ page import="com.telefonica_chile.comun.datos_punto_venta.dto.PuntoVentaDTO"%>
<%@ page import="com.telefonica_chile.comun.vistaagencia.dto.vistaAgenciaDTO"%>
<%@ page import ="com.telefonica_chile.bandeja.datos.bandeja.ActividadDTO"%>
<%@ page import ="com.telefonica_chile.comun.usuario.dto.UsuarioDTO"%>
<%@page import="com.telefonica_chile.comun.parametro.session.ParametroDTO"%>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig" %>


<%
	ArrayList listaAct = (ArrayList)request.getAttribute("listaAct");
	UsuarioWeb usuario = (UsuarioWeb)request.getAttribute("usuario"); 
	
	//Parametros de segundos para el bloqueo del boton Accion Masiva... 
	ParametroDTO parameDto = (ParametroDTO) request.getAttribute("segundos");

	HashMap mapAplicaciones = (HashMap)request.getAttribute("mapAplicaciones");
	Long   ActRol = (Long) request.getAttribute("ActRol");	
	Long   idRol = (Long) request.getAttribute("idRol");	
	if ( idRol == null )
		idRol = new Long(0);


%>
<HTML>
<HEAD>
<script>
 function buscarUsuario(rol) {
    var url = "BuscarUsuarioTorreAcc.acc?idRol=" + rol;
 	var newWin = window.open(url,'BuscarUsuario','width=500,height=500,scrollbars=yes,resizable=yes,top=0,left=0');
	newWin.focus();
}
function cargaUsuario( id, nombre ) {
  document.forma.usuarioReasig.value = nombre;
  document.forma.cmbusuario.value = id;
}
 
   // restauro valores iniciales del formulario
	
 function accionMasiva(forma1, parametro, agen, actRol, act, TICA, PCOM, numPeticion, familia_ps, fecha, flujo){
   // restauro valores iniciales del formulario
   forma1.action = "verTorreControl.acc";
   forma1.target = "";
 
   var s = forma1.accion_masiva[document.forma.accion_masiva.selectedIndex].value;
   
   if (s=='imprimir') {
   	 blockButton();
	 imprimir(forma1,parametro);
	}
	if(s=='resumen'){
	blockButton()
   	 jsEjecutarResumenSSVA(tabla);
 	}
 	if(s=='torre'){
 		jsEjecutarArvhivo(forma1, parametro, agen, actRol, act, TICA, PCOM, numPeticion, familia_ps, fecha, flujo);
 	}
  }
	
 function jsEjecutarArvhivo(forma1, parametro, agen, actRol, act, TICA, PCOM, numPeticion, familia_ps, fecha, flujo){
	var opciones='width=650,height=300,resizable=yes,top=0,left=0';
	var newWin = window.open("about:blank", 'GenerarExcel', opciones);

	forma1.target = 'GenerarExcel';
	forma1.action = 'verGenerarTorreControl.acc';
	forma1.submit();
	
//		window.open("verGenerarTorreControl.acc?agencia="+agen+"&actividadId="+act+"&TICA="+TICA+"&PCOM="+PCOM+"&numPeticion="+numPeticion+"&familia_ps="+familia_ps+"&fecha="+fecha+"&flujo="+flujo,'GenerarExcel',opciones);
	newWin.focus();
 }
 
 function imprimir(forma1,parametro){
   forma1.action="AccionMasiva.acc?cantidad="+parametro;
   forma1.target="iprint";
   forma1.submit();
   forma1.action = "verTorreControl.acc";
   forma1.target = "";
 }
 
 function filtraPaginas(forma) {
	resetPagina();
    forma.submit();
}

	function jsAsignar(obj,valor) { 
		var var1 = obj.checked; 
		if (var1!=false) {
		   obj.value=valor; 
		} else {
		   obj.value=0; 
		}     
	} 
/*	
	function jsReasignarEstados(parametro){
	    document.forma.action = "ReasignarPeticion.acc?cantidad="+parametro;
	    var campo = document.forma.usu;
	    if ( campo.options[campo.selectedIndex].value == "0") {
	    	alert("Debe seleccionar un Usuario a quien Reasignar");
	    	return;
	    }
		document.forma.cmbusuario.value = campo.options[campo.selectedIndex].value;
	    document.forma.submit();
   }
*/
	function jsReasignarEstados(parametro){
	    document.forma.action = "ReasignarPeticion.acc?cantidad="+parametro;
	    if ( document.forma.usuarioReasig.value == "" ) {
	    	alert("Debe seleccionar un Usuario a quien Reasignar");
	    	return;
	    }
	    document.forma.submit();
   }
 	
 	function jsEjecutarResumenSSVA(table){
		var rolId  = document.forma.rol[document.forma.rol.selectedIndex].value;
    	document.forma.rolUsr.value=rolId;
 	
 		document.forma.submit();
    	document.forma.action="verTorreControl.acc";
 	}
 	
 	function jsEjecutarResumenSSVA(table){
		var rolId  = document.forma.rol[document.forma.rol.selectedIndex].value;
    	document.forma.rolUsr.value=rolId;
 	
 		document.forma.submit();
    	document.forma.action="verTorreControl.acc";
 	}

</script>
<SCRIPT>

function blockButton(){
    var boton = document.getElementById('btnimprimir');
    
    boton.value = '     Cargando...      ';
    boton.disabled=true;
	setTimeout ('activeButton()', <%if (parameDto.getValorParametro() == null) { %> 60000 <% }else%><%=new Integer(parameDto.getValorParametro())%>);  
	
}

function activeButton(){
    var boton = document.getElementById('btnimprimir');
    
    boton.value = 'Realizar Acción Masiva';
   	boton.disabled=false;

}
</SCRIPT>

<%@ page import = "com.telefonica_chile.bandeja.torreControl.DatosGlobalTorreControl" %>

<TITLE>Bandeja Torre de Control</TITLE>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</HEAD>
<BODY>

<%
  Tabla peticiones = (Tabla) request.getAttribute("peticiones");
  ArrayList listValoresVariables = (ArrayList) request.getAttribute("listValoresVariables");

  String agencia = UtilesBandeja.sinNull(request.getParameter("agenciaId"), "");
  String actividad = UtilesBandeja.sinNull(request.getParameter("actividadId"), "");
  String tipo_trabajo = UtilesBandeja.sinNull(request.getParameter("tipotrabajo"), "");
  String segmento_id = UtilesBandeja.sinNull(request.getParameter("segmento"), "");  
  String familia_ps = UtilesBandeja.sinNull(request.getParameter("familia"), "");
  String fecha_filtro = UtilesBandeja.sinNull(request.getParameter("fecha"), "");
  String flujo = UtilesBandeja.sinNull(request.getParameter("otrostipos"), "");


  String nombreAgencia = UtilesBandeja.sinNull((String) request.getAttribute("nombreAgencia"), "");
  String nombreActividad = UtilesBandeja.sinNull((String) request.getAttribute("nombreActividad"), "");

%>
<script language="JavaScript">
<!--
function validaFiltro(forma) {
  var miInteger = forma.numPeticion.value;
  if (isNaN(miInteger))
	alert ("Ingrese sólo números para Número de Petición")
  else {
	forma.action = "verBandejaTorreControl.acc";
	forma.target = "";
	resetPagina();
    forma.submit();
  }
}
//-->
</script>
<jsp:include page="../template/head.jsp" />

<div style="font-size: 2px">&nbsp;</div>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
 <tr>
  <td class="titulo-tabla">Listado de Trabajos en agencia <%=nombreAgencia%> para actividad <%=nombreActividad.toUpperCase()%>. [Total de <%=peticiones.getNroTotalElementos()%> Peticiones]</td>
 </tr>
</table>
<form name="forma" action="verBandejaTorreControl.acc" method="post">
<input type="hidden" name="agenciaId" value='<%=agencia%>'>
<input type="hidden" name="actividadId" value='<%=actividad%>'>
<input type="hidden" name="tipotrabajo" value='<%=tipo_trabajo%>'>
<input type="hidden" name="segmento" value='<%=segmento_id%>'>
<input type="hidden" name="familia" value='<%=familia_ps%>'>
<input type="hidden" name="fecha" value='<%=fecha_filtro%>'>
<input type="hidden" name="otrostipos" value='<%=flujo%>'>
<input type="hidden" name="torre" value='torre0'>
<input type="hidden" name="reasignarTC" value="TC">
<input type="hidden" name="largoPeticos" value="<%=peticiones.size()%>">

<table class="tabla-borde-delgado" width="100%">
	<tr class="elementos-filtro">
		<td  width="230">Folio o N° Petición : <INPUT
			type="text" name="numPeticion" class="input-form" size="15" maxlength="15"
			value='<%= UtilesBandeja.sinNull(request.getParameter("numPeticion"), "") %>'>
		&nbsp;&nbsp;&nbsp;</td>
	     <td valign="middle">TICA</td>
	    <td valign="middle">: 
	   		 <input type=text name="TICA" size=2 maxlength=2 
	   		 value="<%= UtilesBandeja.sinNull(request.getParameter("TICA"), "") %>" 
	   		 onchange="this.value=this.value.toUpperCase();" onBlur="this.value=this.value.toUpperCase();">
	    </td>
	    <td align="right" width="143">Planta Comercial:</td>
	    <td width="31">
	   		 <input type=text name="PCOM" size=6 maxlength=4 
	   		 value="<%= UtilesBandeja.sinNull(request.getParameter("PCOM"), "") %>"
	   		 onchange="this.value=this.value.toUpperCase();" onBlur="this.value=this.value.toUpperCase();">
	    </td>
	 </tr>   
	<tr class="elementos-filtro">
	    <td colspan="2">Actividad&nbsp;&nbsp;:&nbsp;&nbsp; 
		    <select name="actividadFiltro">
	        	<option value="">Todas las Actividades</option>
				<%
				if ( listaAct == null)
					listaAct = new ArrayList();
				for (Iterator it = listaAct.iterator(); it.hasNext(); ) {
				    ActividadDTO act = (ActividadDTO) it.next();
				%>
					<option  value="<%=act.getCodigo()%>"><%=act.getDescripcion() %></option>
				<%}%>
			 </select>&nbsp;&nbsp; &nbsp;&nbsp; 
	    <INPUT type="button" name="botonFiltro"
			onclick="return validaFiltro(this.form)" value=" Filtrar "
			class="botones-chicos"></td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
		<td>&nbsp;</td>
  </tr>

   	<tr>
		<td class="elementos-filtro">
		<select name="accion_masiva" class="botones-chicos">
			<option value="" selected>Seleccione una acción Masiva</option>
			<option value="imprimir">&nbsp;&nbsp;&nbsp;&nbsp;Imprimir los Trabajos Seleccionados</option>
			<option value="torre">Generar Archivo</option>
		</select>
		<input type="button" onclick="accionMasiva(this.form, '<%=peticiones.size()%>','<%=agencia%>','<%=idRol%>','<%=actividad%>','<%= UtilesBandeja.sinNull(request.getParameter("PCOM"), "") %>','<%= UtilesBandeja.sinNull(request.getParameter("TICA"), "") %>','<%= UtilesBandeja.sinNull(request.getParameter("numPeticion"), "") %>','<%=familia_ps%>','<%=fecha_filtro%>','<%=flujo%>');" name="btnimprimir" value="Realizar Acción Masiva" class="botones-chicos">
		
	    <!-- IFRAME DE IMPRESION MASIVA -->
	    <iframe src="/bandejaweb/blank.html" name="iprint" id="iprint" style="width:1px;height:1px;border:0px;"></iframe>
		</td>
    <td width="6" class="elementos-filtro"  valign="middle"> 
      <div align="center"><img src="/bandejaweb/img/separador_vertical.gif" height="10" width="2" align="middle"></div>
    </td>  
	<td class="elementos-filtro">&nbsp;</td>
    <td class="elementos-filtro">
		    Mostrar
		    <select name="dpp" onChange = "filtraPaginas(this.form)">
<%
	for (int i=10; i<=50; i+=10) {
%>
	    <option value="<%= i %>"  <%= String.valueOf(i).equals(request.getParameter("dpp")) ? "selected" : "" %> ><%= i %></option>
<%
	}
%>
		    </select>
		    &nbsp;d.&nbsp;p.&nbsp;pág.<br><br>

    </td>  
	    <td class="elementos-filtro" align="right" width="143"><jsp:include
			page="/jsp/paginador.jsp" flush="true" /></td>
		
	</tr>
	
</table>

<!-- Mostrar la tabla de filtro -->
<table class="tabla-borde-delgado" width="100%">
	<tr class="encabezados-tabla">
		<td></td>
		<td>Petici&oacute;n o Folio </td>
		<td>Actividad</td>
		<td>Cliente</td>
		<td>Usuario</td>
		<td>Grupo Segmento</td>
		<td>&Aacute;rea</td>
		<td>N&ordm; Servicio</td>
		<%
		if ( listValoresVariables == null )
			listValoresVariables = new ArrayList();
		for (Iterator it = listValoresVariables.iterator(); it.hasNext(); ) {
		    ValorVariableDTO valor = (ValorVariableDTO) it.next();%>
		    <td><%= valor.getNombre() != null ? valor.getNombre() : "" %></td>
		<%}%>		
		<td>Fecha Inicio</td>
		<td>Fecha Apertura</td>
		<td>Fecha Compromiso</td>
		<td>Estado Compromiso</td>
		<td>Estado Actividad</td>
	</tr>
<%
	int par = 0;
	int contPet = 0;
	String urlIni = "";
	String urlFin = "";
	
	String nameAct = "";
	for (Iterator it = peticiones.iterator(); it.hasNext(); par++) {
		PeticionDTO peticion = (PeticionDTO) it.next();
		AplicacionDTO aplicacion = (AplicacionDTO) mapAplicaciones.get(peticion.getAplicacionNombre());
		String urlBase= "";
		if (aplicacion!=null && aplicacion.getApUrlBase() !=null){
				urlBase=aplicacion.getApUrlBase();
		}
		nameAct = peticion.getActividadDescripcion();
		if ( nameAct.startsWith("Pendiente en Legacy") ) {
			urlIni = "";
			urlFin = "";
		} else { 
		 	String numPeticion = UtilesBandeja.sinNull((String) request.getParameter("numPeticion"),"");
		 	String aplicacionTC = UtilesBandeja.sinNull((String) request.getParameter("aplicacion"),"");
		 	String agenciaId = UtilesBandeja.sinNull((String) request.getParameter("agenciaId"),"");
		 	String actividadId = UtilesBandeja.sinNull((String) request.getParameter("actividadId"),"");
		 	String tipotrabajo = UtilesBandeja.sinNull((String) request.getParameter("tipotrabajo"),"");
		 	String familia = UtilesBandeja.sinNull((String) request.getParameter("familia"),"");
		 	String segmento = UtilesBandeja.sinNull((String) request.getParameter("segmento"),"");
		 	String fecha = UtilesBandeja.sinNull((String) request.getParameter("fecha"),"");
		 	String otrostipos = UtilesBandeja.sinNull((String) request.getParameter("otrostipos"),"");
		 	String actividadFiltro = UtilesBandeja.sinNull((String) request.getParameter("actividadFiltro"),"");
		 	String TICA = UtilesBandeja.sinNull((String) request.getParameter("TICA"),"");
		 	String PCOM = UtilesBandeja.sinNull((String) request.getParameter("PCOM"),"");
		 	String CCN = UtilesBandeja.sinNull((String) request.getParameter("CCN"),"");
		 	
			urlIni = "<a href=\"" + urlBase + peticion.getBiUrlDetalle() + "&vieneDe=TC" +
					 "&numPeticion="+numPeticion+ 
 					 "&aplicacion="+aplicacionTC+
 					 "&agenciaId="+agenciaId+
 					 "&actividadId="+actividadId+
 					 "&tipotrabajo="+tipotrabajo+
 					 "&familia="+familia+
 					 "&segmento="+segmento+
 					 "&fecha="+fecha+
 					 "&otrostipos="+otrostipos+
 					 "&actividadFiltro="+actividadFiltro+
 					 "&TICA="+TICA+
 					 "&PCOM="+PCOM+
 					 "&CCN="+CCN+
			         "&\">";
			         
			urlFin = "</a>";
		}
				
		int estPet = (peticion.getEstadoPeticion() == null) ? -1 : peticion.getEstadoPeticion().intValue();
%>
	<tr valign="top"
		class="fila-detalle-<%=par % 2 == 0 ? "par" : "impar"%>">
		<td>
		<input type="checkbox" name="checkbox<%=contPet%>" onclick="jsAsignar(this,1)">
		<input type="hidden" name="petiId_<%=contPet%>" value="<%= peticion.getBiNroPeticion()  %>">
		<input type="hidden" name="actividadId_<%=contPet%>" value="<%= peticion.getActividadId() %>">
		<input type="hidden" name="duenoPeticion_<%=contPet%>" value="<%= peticion.getUsuaId() %>">	
		<input type="hidden" name="foliotc<%=contPet%>" value="<%=peticion.getBiId()%>">
		<input type="hidden" name="bint<%=peticion.getBiId()%>" value="<%=peticion.getBiId()%>">
		</td>
		<td><%= urlIni %><%=peticion.getBiNroPeticion()%><%= urlFin %></td>
		<td><%
			if ( estPet==3 || estPet==7 || estPet==8 || estPet==9 ) {
			%><%= UtilesBandeja.sinNull(peticion.getActividadDescripcion(), "") %> (REV)
  	    	<%} else {
  	    			if(peticion.getTipoTrabajo().equals(new Integer(2))){
  	    		%> 		<%=peticion.getActividadNombreReversa()%> 
  	    			<%}else{%>
  	    				<%= UtilesBandeja.sinNull( peticion.getActividadDescripcion() , "") %>
  	    			<%}%>
  	    	 <%}%>
		</td>
		<td><%= urlIni %><%= UtilesBandeja.sinNull( peticion.getBiClienteNombre(), "")%><%= urlFin %></td>
		<td><%= urlIni %><%= UtilesBandeja.sinNull( peticion.getUsuaNombre(), "")%><%= urlFin %></td>
		<td><%= UtilesBandeja.sinNull( peticion.getNombreBloqueSegmento(), "") %></td>	
		<td><%= urlIni %><%= UtilesBandeja.sinNull( peticion.getBiClienteArea(), "") %><%= urlFin %></td>
		<td><%= urlIni %><%= UtilesBandeja.sinNull( peticion.getBiClienteServicio(), "") %><%= urlFin %></td>
	    <%
	    String fComp = UtilesBandeja.formatoFecha(peticion.getBiFechaCompromiso(), "dd/MM/yyyy", "");
	    if ( "01/01/0001".equals(fComp) )
	    	fComp = "";
	    else {
	    	if ( peticion.getIdTipoAgendamiento() != null && 
	    		peticion.getIdTipoAgendamiento().intValue()==1 ) {
	    		fComp += "<br>(Plazo Maximo)";
	    	} else {
		    	if ( peticion.getHoraDesde() != null )
			    	fComp += "<br>(" + peticion.getHoraDesde();
		    	if ( peticion.getHoraHasta() != null )
			    	fComp += "-" + peticion.getHoraHasta() + ")";
			}
	    }

		for (Iterator iV = listValoresVariables.iterator(); iV.hasNext(); ) {
		    ValorVariableDTO valor = (ValorVariableDTO) iV.next();
		    String v = valor.getValorPorPeticion(peticion.getBiId());
		%><td><%= UtilesBandeja.sinNull( v, "&nbsp;") %></td>
		<% }%>		
		<td><%=UtilesBandeja.formatoFecha(peticion.getBiFechaInicio(), "dd/MM/yyyy HH:mm", "")%></td>
		<td><%=UtilesBandeja.formatoFecha(peticion.getBiFechaApertura(), "dd/MM/yyyy HH:mm", "")%></td>
		<td><%=fComp%></td>
		<td><img height="12"
			src="img/<%=UtilesBandeja.decideColorSemaforo(peticion.getBiSemaforoCompromiso())%>"></td>
		<td><img height="12"
			src="img/<%=UtilesBandeja.decideColorSemaforo(peticion.getBiSemaforoActividad())%>"></td>
	</tr>
	
<%
	  contPet++;
	}
	if (peticiones.size() == 0) {
%>
	<tr class="fila-detalle-impar"><td colspan="8">No se encontraron datos</td></tr>
<%
	} else {
%>
	</table>
	<table class="tabla-borde-delgado">
	<tr>
		<td>Reasignar las peticiones seleccionadas al usuario:</td>
		<td>&nbsp;&nbsp;&nbsp;Usuario&nbsp;:&nbsp;&nbsp;</td>
		<td>
		<td><input type="text" name="usuarioReasig" onclick="buscarUsuario('<%=idRol%>')">
		<input type="hidden" name="cmbusuario" value="">

		</td>
		<td><input type="button" value="Reasignar" onclick="jsReasignarEstados('<%=peticiones.size()%>')">
	</tr>
	</table>
<%	}  %>	
</form>
    <!-- IFRAME DE IMPRESION MASIVA -->
    <iframe src="/bandejaweb/blank.html" name="iprint" id="iprint" style="width:1px;height:1px;border:0px;"></iframe>
</BODY>
</HTML>

