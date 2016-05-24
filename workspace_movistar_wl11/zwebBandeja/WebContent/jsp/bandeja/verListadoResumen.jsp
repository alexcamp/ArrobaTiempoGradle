<%@page import="com.telefonica_chile.bandeja.dto.PeticionDTO" %>
<%@page import="com.tecnonautica.utiles.tablas.Tabla" %>
<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap"%>
<%@page import="com.telefonica_chile.bandeja.dto.AplicacionDTO" %>
<%@page import="com.telefonica_chile.comun.parametro.session.ParametroDTO"%>
<%
	ArrayList peticiones = (ArrayList) request.getAttribute("ListadoPeticiones");
	//	HashMap mapAplicaciones = (HashMap)request.getAttribute("mapAplicaciones");

	//Parametros de segundos para el bloqueo del boton Accion Masiva... 
	ParametroDTO parameDto = (ParametroDTO) request.getAttribute("subsegundos");
	
	String url = (String) request.getAttribute("urlVPISTBBA");
	String formato = request.getParameter("formato");
	HashMap mapActiv = (HashMap) request.getAttribute("mapaActividades");
	String descActividad = "Actividades";
	String totalActividad = "10";
	
	String checked1 = "", checked2 = "";
	if ( "1".equals(formato) )
		checked1 = " checked";
	else if ( "2".equals(formato) )
		checked2 = " checked";
%>

<HTML>
<HEAD>

<script>
<!--
	function generarArchivo(forma) {
		var formato = "0";
		
		blockButton();
		
		if ( forma.check1.checked )
			formato = "1";
		else if ( forma.check2.checked )
			formato = "0";
		else {
			alert("Debe Seleccionar el Tipo de Archivo a Generar");
			return false;
		}

		forma.formato.value = formato;
		forma.action = "<%=url%>/FormatoArchivo";
		forma.submit();
		
	}

	function cerrar() {
		window.close();
	}

	function CambiaCheck( campo, nameCampo) {
		var campo2 = eval("document.forma." + nameCampo);
		if ( campo2.checked )
			campo2.checked = false;
	}
//-->
</script>

<SCRIPT>

function blockButton(){
    var boton = document.getElementById('geneArchivo');
    
    boton.value = '     Cargando...     ';
    boton.disabled=true;
	setTimeout ('activeButton()', <%if (parameDto.getValorParametro() == null) { %> 60000 <% }else%><%=new Integer(parameDto.getValorParametro())%>); 
	
}

function activeButton(){
    var boton = document.getElementById('geneArchivo');
    
    boton.value = 'Realizar Acción Masiva';
   	boton.disabled=false;


}
   	
</SCRIPT>
<TITLE>Generar Archivos SSVA</TITLE>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</HEAD>
<BODY>
<br><br>
<form name="forma" method="post">
<input type="hidden" name="accionArchivo" value="NEW">
<input type="hidden" name="formato" value="">
<table id="table" class="tabla-borde-delgado" width="80%" align="center">
	<tr class="encabezados-tabla">
		<td>Nombre Actividad </td>
		<td>Cantidad de Peticiones</td>
	</tr>
<%
	if (mapActiv != null ) {
	
		for (Iterator it = mapActiv.keySet().iterator(); it.hasNext(); ) {
			String nameAct = (String) it.next();
%>
	<tr class="fila-detalle-impar">
	    <td><%= nameAct %></td>
	    <td><%= (Integer)mapActiv.get(nameAct) %></td>
    </tr>
<%
		}
	}
	int contPet = 0;
	for (Iterator it = peticiones.iterator(); it.hasNext(); ) {
		PeticionDTO peticion = (PeticionDTO) it.next();
%>
		<input type="hidden" name="petiId_<%=contPet%>" value="<%= peticion.getBiNroPeticion() %>">
		<input type="hidden" name="actividadId_<%=contPet%>" value="<%= peticion.getActividadId() %>">
<%
		contPet++;
	}
%>
<br>
<input type="hidden" name="totalListado" value="<%=peticiones.size()%>">
 <TR align="center">
  <TD colspan=2>
  	<div align="right">
  	 Archivo de Resumen. <input type="checkbox" name="check1" value="1" onClick="CambiaCheck(this, 'check2');"<%=checked1%>>
  	<br>
  	 Archivo de Comando. <input type="checkbox" name="check2" value="0" onClick="CambiaCheck(this, 'check1');"<%=checked2%>>
  	</div>
	<br>
	<INPUT type="button" value="Cerrar Ventana" onclick="cerrar()">
	&nbsp;&nbsp;&nbsp;
	<INPUT type="button" name="geneArchivo" value="Generar Archivo" onclick="generarArchivo(this.form);">
	</TD>
 </TR>
</TABLE>
</form>	
</BODY>
</HTML>
