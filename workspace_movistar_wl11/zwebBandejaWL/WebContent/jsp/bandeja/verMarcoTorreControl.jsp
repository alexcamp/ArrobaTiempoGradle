<%@page import="java.util.*"%>
<%@ page import="com.telefonica_chile.bandeja.dto.AgenciaDTO"%>
<%@page import="com.telefonica_chile.bandeja.torreControl.CuadroMandoDTO"%>
<HTML>
<HEAD>
<SCRIPT language="JavaScript">
<!--
function verBandeja(agencia, actividad) {
 window.parent.verLista(agencia, actividad);
}

function getColor(i, j) {
	if ( i%2 == 0 )
		document.write("fila-detalle-par");
	else
		document.write("fila-detalle-impar");
}

function muestra(i, j, matriz) {
	var aux = window.parent.verContenido(i, j, matriz);
	document.write(aux);
}

//-->
</SCRIPT>
<TITLE>Torre de Control</TITLE>
</HEAD>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<BODY TOPMARGIN=0 BOTTOMMARGIN=0 LEFTMARGIN=0 RIGHTMARGIN=0>
<%
String tipo= request.getParameter("tipo");
ArrayList listaAgencias = (ArrayList) request.getAttribute("listaAgencias");
ArrayList titulosCM = (ArrayList) request.getAttribute("titulosCuadroMando");
HashMap Totales = new HashMap();

if ( listaAgencias == null ) 
	listaAgencias = new ArrayList();
if ( titulosCM == null ) 
	titulosCM = new ArrayList();

int nFilas = listaAgencias.size();
int nCols = titulosCM.size();

String jsTit = "";

%>
<table class="tabla-borde-delgado" width="100%">
<%
  if ( "CUADRO".equals( tipo ) ) {
	String clase_fila = "";
	for (int i=0; i<nFilas; i++) {
		AgenciaDTO agDto = (AgenciaDTO) listaAgencias.get(i);
		if (agDto == null)
			continue;
		clase_fila = (i%2==0) ? "fila-detalle-par" : "fila-detalle-impar";
%>
  <tr valign="top" class="<%=clase_fila%>">
    <td style="width: 2cm;""><%=agDto.getAgenDescripcion()%></td>
<%
		int totalAg = 0;
		for (int j=0; j<nCols; j++) {
			CuadroMandoDTO cDto = (CuadroMandoDTO) titulosCM.get(j);
			int cant = agDto.getCantidad("" + cDto.getActividadTC()).intValue();

			totalAg += cant;
			String val = "";
			if ( cDto.getSubTotal() == 1 ) {
				val = "" + totalAg;
				cant = totalAg;
			}
			else if ( cant == 0 || cDto.getLink()==0)
				val = ""+ cant;
			else
				val = "<A  href=\"javascript:verBandeja("+ agDto.getAgenId() + "," + cDto.getActividadTC() + ");\">" + cant + "</a>";

			Integer acTot = (Integer) Totales.get(""+cDto.getActividadTC());
			if ( acTot == null )
				Totales.put( ""+cDto.getActividadTC(), new Integer(cant) );
			else
				Totales.put( ""+cDto.getActividadTC(), new Integer( acTot.intValue() + cant) );

%>
    <td style="width: 2cm;"><%=val%></td>
<%
		}
		Integer acTot = (Integer) Totales.get("TOTAL");
		if ( acTot == null )
			Totales.put( "TOTAL", new Integer(totalAg) );
		else
			Totales.put( "TOTAL", new Integer( acTot.intValue() + totalAg) );
%>
    <td style="width: 2cm;"><%=totalAg%></td>
  </tr>
<%
	}
%>
  <tr valign="top" class="encabezados-tabla">
    <td style="width: 2cm;"">TOTALES</td>
<%
	for (int j=0; j<nCols; j++) {
		String val = "";
		CuadroMandoDTO cDto = (CuadroMandoDTO) titulosCM.get(j);
		Integer acTot = (Integer) Totales.get(""+cDto.getActividadTC());
		val = ( acTot == null ) ? "0" : ""+acTot;
%>
    <td style="width: 2cm;"><%=val%></td>
<%
	}
	Integer total = (Integer) Totales.get("TOTAL");
	if ( total == null )
		total = new Integer(0);
%>
    <td style="width: 2cm;"><%=total%></td>
  </tr>
<%

  }
  String fila = "encabezados-tabla";
  if ( "CUADRO".equals( tipo ) )
  	fila = "fila-invisible";

%>
  <tr valign="top" class="<%=fila%>" align="center">
    <td style="width: 2cm;">Agencias</td>
<%
  // Titulo.
	for (int j=0; j<nCols; j++) {
		CuadroMandoDTO cDto = (CuadroMandoDTO) titulosCM.get(j);
%>
    <td style="width: 2cm;"><%=cDto.getNombreActividad()%></td>
<%
	}
%>
    <td style="width: 2cm;">Total</td>
  </tr>
<%

//  if ( !"CUADRO".equals( tipo ) ) {
%>
  <tr valign="bottom" class="fila-invisible" align="center">
    <td style="width: 2cm;">INDEPENDENCIA</td>
<%
//	}
%>

  </tr>
</table>
</body>


</HTML>
