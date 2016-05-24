<%@page import="java.util.*"%>
<%@ page import="com.telefonica_chile.bandeja.dto.AgenciaDTO"%>
<%@page import="com.telefonica_chile.bandeja.torreControl.CuadroMandoDTO"%>
<SCRIPT language="JavaScript">
<!--
function verBandeja(agencia, actividad) {
 window.parent.verLista(agencia, actividad);
}

function getColor(i,j) {
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
<%! String generaCuadro(ArrayList listadoAg,ArrayList listaTit,int x,int y,int cuadro) {
		HashMap Totales = new HashMap();
		String jsTit="";
		int sum=0;
		for (int i=0; i<listadoAg.size(); i++) {
			AgenciaDTO agDto = (AgenciaDTO) listadoAg.get(i);
			if (agDto == null)
				continue;
				sum = 1;
			y++;
			x = 0;
			jsTit+= "cSub"+ cuadro +"["+y+"]["+ (x) + "]='"+agDto.getAgenDescripcion()+"';\n";
			x++;
				
		
			int totalAg = 0;
			for (int j=0; j<listaTit.size(); j++) {
				CuadroMandoDTO cDto = (CuadroMandoDTO) listaTit.get(j);
				int cant = agDto.getCantidad("" + cDto.getActividadTC()).intValue();
				totalAg += cant;
				
				String val = "";
				if ( cDto.getSubTotal() == 1 ) {
					val = "" + totalAg;
					cant = totalAg;
				}
				else if ( cant == 0 || cDto.getLink()==0)
					val = ""+ cant;
				else//CR19747 - pcawen
					val = "<A  href=\"javascript:window.parent.verLista(\\'"+ agDto.getAgenId() + "\\'," + cDto.getActividadTC() + ");\">" + cant + "</a>";
	
				Integer acTot = (Integer) Totales.get(""+cDto.getActividadTC());
				if ( acTot == null )
					Totales.put( ""+cDto.getActividadTC(), new Integer(cant) );
				else
					Totales.put( ""+cDto.getActividadTC(), new Integer( acTot.intValue() + cant) );
					
				jsTit+= "cSub"+ cuadro +"["+y+"]["+sum+"]='"+val+"';\n";
				sum++;
	
			}
			Integer acTot = (Integer) Totales.get("TOTAL");
			if ( acTot == null )
				Totales.put( "TOTAL", new Integer(totalAg) );
			else
				Totales.put( "TOTAL", new Integer( acTot.intValue() + totalAg) );
				
			jsTit+="cSub"+ cuadro +"["+y+"]["+sum+"]='"+totalAg+"';\n";
	
		}
		y++;
		jsTit+= "cSub"+ cuadro +"["+y+"][0]='TOTALES';\n";
		sum = 1;
		for (int j=0; j<listaTit.size(); j++) {
			String val = "";
			CuadroMandoDTO cDto = (CuadroMandoDTO) listaTit.get(j);
			Integer acTot = (Integer) Totales.get(""+cDto.getActividadTC());
			val = ( acTot == null ) ? "0" : ""+acTot;
			
			jsTit+= "cSub"+ cuadro +"["+y+"]["+sum+"]='"+val+"';\n";
			sum++;
		}
		Integer total = (Integer) Totales.get("TOTAL");
		if ( total == null )
			total = new Integer(0);
		
		jsTit+= "cSub"+ cuadro +"["+y+"]["+sum+"]='"+total+"';\n";
	return jsTit;
  }
%>
<HTML>
<HEAD>

<TITLE>Torre de Control</TITLE>
</HEAD>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<BODY TOPMARGIN=0 BOTTOMMARGIN=0 LEFTMARGIN=0 RIGHTMARGIN=0>
<%
String tipo= request.getParameter("tipo");

ArrayList listaAgencias = (ArrayList) request.getAttribute("listaAgencias");
ArrayList titulosCM = (ArrayList) request.getAttribute("titulosCuadroMando");
ArrayList titulosCM2 = (ArrayList) request.getAttribute("titulosCuadroMando2");
HashMap Totales = new HashMap();

if ( listaAgencias == null ) 
	listaAgencias = new ArrayList();
if ( titulosCM == null ) 
	titulosCM = new ArrayList();

if ( titulosCM2 == null ) 
	titulosCM2 = new ArrayList();

int nFilas = listaAgencias.size();
int nCols = titulosCM.size();
int nCols2 = titulosCM2.size();

String jsTit = "";

%>
<%=nCols%><br>
<%=nFilas%><br>
<%
	int x = 0;
	int y = 0;
 // if ( "CUADRO".equals( tipo ) ) {
	String clase_fila = "";
	int sum;
	
	jsTit+= "cSub1["+y+"][" + (x++) + "]='Departamentos';\n";
	
	for (int j=0; j<nCols; j++) {
		CuadroMandoDTO cDto = (CuadroMandoDTO) titulosCM.get(j);
		if (cDto == null)
			continue;
		jsTit+= "cSub1["+y+"]["+ (x++) +"]='"+cDto.getNombreActividad()+"';\n";
	}
	jsTit+= "cSub1["+y+"]["+ (x++) +"]='Total';\n";

// El Segundo cuadro.
	x = 0;
	jsTit+= "cSub2["+y+"][" + (x++) + "]='Departamentos';\n";
	
	for (int j=0; j<nCols2; j++) {
		CuadroMandoDTO cDto = (CuadroMandoDTO) titulosCM2.get(j);
		if (cDto == null)
			continue;
		jsTit+= "cSub2["+y+"]["+ (x++) +"]='"+cDto.getNombreActividad()+"';\n";
	}
	jsTit+= "cSub2["+y+"]["+ (x++) +"]='Total';\n";


	jsTit += generaCuadro(listaAgencias, titulosCM, 0, 0, 1);
	jsTit += generaCuadro(listaAgencias, titulosCM2, 0, 0, 2);
%>

	
		
<SCRIPT>
var cSub1 = new Array(<%=nFilas+2%>);
var cSub2 = new Array(<%=nFilas+2%>);

for(i=0;i<<%=nFilas+2%>;i++){
	cSub1[i] = new Array(<%=nCols+2%>);
}
for(i=0;i<<%=nFilas+2%>;i++){
	cSub2[i] = new Array(<%=nCols2+2%>);
}
<%=jsTit%>;

// Escribir en los otros Frames.
function escribe( nameFrame, cuadro ) {
	var pag = eval("window.parent." + nameFrame + ".document");
	
	if(nameFrame == "titulo1" || nameFrame == "titulo2"){
		pag.write(  "<HTML>"+
					"<HEAD>"+
					"<TITLE>Torre de Control</TITLE>"+
					"<LINK rel=\"stylesheet\" href=\"/bandejaweb/estilo.css\" type=\"text/css\">"+
					"</HEAD>"+
					"<BODY TOPMARGIN=0 BOTTOMMARGIN=0 LEFTMARGIN=0 RIGHTMARGIN=0>"+
					"<table class=\"tabla-borde-delgado\" width=\"100%\">"+
					"<tr valign=\"top\" class=\"encabezados-tabla\" align=\"center\">");
					for(i=0;i<cuadro[0].length;i++){
						pag.write("<td style=\"width: 2cm;\">"+cuadro[0][i]+"</td>");
					}
		pag.write("</tr>"+
				  "<tr valign=\"bottom\" class=\"fila-invisible\" align=\"center\">"+
    			  "<td style=\"width: 2cm;\">INDEPENDENCIA</td>");
					for(i=1;i<cuadro[0].length;i++){
						pag.write("<td style=\"width: 2cm;\">"+cuadro[0][i]+"</td>");
					}
  		pag.write("</tr>"+
				  "</table>"+
				  "</BODY>"+
				  "</HTML>");
	}
	if(nameFrame == "iframe1" || nameFrame == "iframe2"){
	
		pag.write(  "<HTML>"+
					"<HEAD>");
		pag.write(	"<TITLE>Torre de Control</TITLE>" +
					"<META http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"+
					"<LINK rel=\"stylesheet\" href=\"/bandejaweb/estilo.css\" type=\"text/css\">"+
					"</HEAD>"+
					"<BODY LEFTMARGIN=0 TOPMARGIN=0 BOTTOMMARGIN=0 RIGHTMARGIN=0>"+
					"<TABLE class=\"tabla-borde-delgado\" width=\"100%\">");
					
					for(x=1;x<cuadro.length;x++){
						if(x % 2 == 0)
							pag.write("<tr valign=\"top\" class=\"fila-detalle-par\">");
						else
							pag.write("<tr valign=\"top\" class=\"fila-detalle-impar\">");

						for(t=0;t<cuadro[0].length;t++){
							pag.write("<td style=\"width: 2cm;\">"+ cuadro[x][t] +"</td>");
						}
						pag.write("</tr>");
					}
					

					
  		pag.write(  "<tr valign=\"bottom\" class=\"fila-invisible\" align=\"center\">"+
    			    "<td style=\"width: 2cm;\">INDEPENDENCIA</td>");
    				for(i=1;i<cuadro[0].length;i++){
						pag.write("<td style=\"width: 2cm;\">"+cuadro[0][i]+"</td>");
					}
        pag.write(  "</tr>");
        
  		pag.write(  "</TABLE>"+
					"</BODY>"+
					"</HTML>");
	}
}


escribe( "titulo1", cSub1);
escribe( "iframe1", cSub1);
escribe( "titulo2", cSub2);
escribe( "iframe2", cSub2);

</SCRIPT>