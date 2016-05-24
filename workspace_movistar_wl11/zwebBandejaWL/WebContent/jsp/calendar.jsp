<%@ page import="java.util.*, java.text.*" %>
<%
    SimpleDateFormat formatter = new SimpleDateFormat("MMMM", new Locale("es", "CL"));
    SimpleDateFormat seeker = new SimpleDateFormat("EE", Locale.US);
    SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
    String annoPar=request.getParameter("year");
    String mesPar=request.getParameter("month");
    String diaPar=request.getParameter("day");
    Date hoy = new Date();
    String hoyString = parser.format(hoy);
    if (annoPar == null) {
        diaPar = hoyString.substring(0,2);
    }
    if (mesPar == null) {
        mesPar = hoyString.substring(3,5);
    }
    if (annoPar == null) {
        annoPar = hoyString.substring(6);
    }
    if (diaPar.length() < 2) {
        diaPar = "0" + diaPar;
    }
    if (mesPar.length() < 2) {
        mesPar = "0" + mesPar;
    }
    while (annoPar.length() < 4) {
        annoPar = "0" + annoPar;
    }

    String fieldPar = request.getParameter("field");
    String formPar = request.getParameter("form");
    String postPar = request.getParameter("post");

    int diaint=Integer.parseInt(new SimpleDateFormat("dd").format(hoy));
    int mes=Integer.parseInt(new SimpleDateFormat("MM").format(hoy));
    int anno=Integer.parseInt(new SimpleDateFormat("yyyy").format(hoy));
    try {
        diaint = Integer.parseInt(diaPar);
    } catch (NumberFormatException nfe) {}
    try {
        mes = Integer.parseInt(mesPar);
    } catch (NumberFormatException nfe) {}
    try {
        anno = Integer.parseInt(annoPar);
    } catch (NumberFormatException nfe) {}

    int annoPrevio = anno;
    int mesPrevio=mes - 1;
    if (mesPrevio < 1) {
        annoPrevio--;
        mesPrevio = 12;
    }
    int annoPost = anno;
    int mesPost = mes + 1;
    if (mesPost > 12) {
        annoPost++;
        mesPost = 1;
    }
    Date fecha = null;
    int pos = 0;
    fecha = parser.parse("01/" + mesPar + "/" + annoPar);

    while (!seeker.format(fecha).equals("Mon")) {
        pos++;
        fecha = parser.parse("0" + (pos+1) + "/" + mesPar + "/" + annoPar);
    }
    int start_pos = (7 - pos)%7;

    pos = 28;
    while (parser.format(parser.parse(pos+"/"+mesPar+"/"+annoPar)).substring(3,5).equals(mesPar))
        pos++;
    int fin = pos;

    String mesName = formatter.format(fecha);
    mesName = Character.toUpperCase(mesName.charAt(0)) + mesName.substring(1);

    int[][] dias = {{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0
, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};

    int dia = 1;
    for (int i = 0; dia < fin; ++i) {
        for (int j = start_pos; j < 7 && dia < fin; ++j)
            dias[i][j] = dia++;
        start_pos = 0;
    }

    String fijo = "";
    if (fieldPar != null)
        fijo += "field=" + fieldPar + "&";
    if (formPar != null)
        fijo += "form=" + formPar + "&";
    if (postPar != null)
        fijo += "post=" + postPar + "&";
%>


<%-- A Continuacion va el codigo HTML --%>

<html>
<head>
<title>Agendamiento @Tiempo - Calendario <%=mesName%> <%=anno%></title>
<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
<script language="javascript">
<!--
function validaFecha(valor)
{
	if(valor=="")
	{
		alert("Debe Seleccionar la Fecha de Cierre del Ps");
		return;
	}
//	alert(valor);
//valor es una fecha en el formato dd/MM/yyyy
	var diaSel=valor.substring(0,2);
	//alert(diaSel);
	var mesSel=valor.substring(3,5);
//	alert(mesSel);
	var anioSel=valor.substring(6);
//	alert(anioSel);
	var numeroFechaSel=anioSel+mesSel+diaSel;

	var mydate=new Date();
	var year=mydate.getYear();
	if (year < 1000)
	year+=1900;
	var day=mydate.getDate();
	var month=mydate.getMonth()+1;
	if (month<10)
	month="0"+month;
	if(day<10)
		day="0"+day;
	var numeroFechaHoy=year+month+day;
//	alert("numeroFechaHoy:"+numeroFechaHoy);
//	alert("numeroFechaSel:"+numeroFechaSel);
	if(numeroFechaSel>numeroFechaHoy)
		return false;
	return true;
}
	
function select(valor) {
    formulario = window.opener.<%=formPar%>;
    <%if(request.getParameter("limite")!=null){
    %>
	var nuevo=valor+ '/<%=mesPar%>/<%=annoPar%>';
    if(validaFecha(nuevo)==false)
	{
		alert("No puede Seleccionar una Fecha Superior al día de Hoy");
		return;
	}
    var Digital=new Date();
	var hours=Digital.getHours();
	if(hours<10)
		hours="0"+hours;
	var minutes=Digital.getMinutes();
	if(minutes<10)
		minutes="0"+minutes;
	var otro=nuevo+" "+hours+":"+minutes;
	formulario.<%=fieldPar%>.value=otro;
    <%}else{%>
    formulario.<%=fieldPar%>.value=valor + '/<%=mesPar%>/<%=annoPar%>';
    <%}%>
<%  if (postPar != null) { %>
    formulario.<%=postPar%>;
<%  } %>
    window.close();
}

function selectNull() {
    formulario = window.opener.<%=formPar%>;
    formulario.<%=fieldPar%>.value='';
<%  if (postPar != null) { %>
    formulario.<%=postPar%>;
<%  } %>
    window.close();
}
//-->
</script>
</head>
<body bgcolor="#FFFFFF" topmargin=0 leftmargin=0 rightmargin=0 bottommargin=0>
<center>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#000000">
<tr>
<td align="center">
<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#000000">
<tr>
<td class="titulo-tabla" colspan="3" align="center">Calendario</td>
</tr>

<%-- Link a mes anterior --%>
<tr>

<td width="36" class="elementos-filtro"><a 
href="/bandejaweb/jsp/calendar.jsp?<%=fijo%>day=<%=diaPar%>&month=<%=mesPrevio%>&year=<%=annoPrevio%><%if(request.getParameter("limite")!=null){%>&limite=1<%}%>"><img 
src="/bandejaweb/img/pag_anterior.gif" border="0"></a></td>

<%-- mes y año actual --%>
<td align="center" class="elementos-filtro"><b><%=mesName%> <%=annoPar%></b></td>

<%-- Link al mes siguiente --%>

<td width="36" class="elementos-filtro"><a 
href="/bandejaweb/jsp/calendar.jsp?<%=fijo%>day=<%=diaPar%>&month=<%=mesPost%>&year=<%=annoPost%><%if(request.getParameter("limite")!=null){%>&limite=1<%}%>"><img 
src="/bandejaweb/img/pag_siguiente.gif" border="0"></a></td>
</tr>
</table>
</td>
</tr>

<tr>
<td>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">

<%-- Fila de los nombres de los días --%>

<tr>
<td class="encabezados-tabla">Lu</td>
<td class="encabezados-tabla">Ma</td>
<td class="encabezados-tabla">Mi</td>
<td class="encabezados-tabla">Ju</td>
<td class="encabezados-tabla">Vi</td>
<td class="encabezados-tabla">S&aacute;</td>
<td class="encabezados-tabla">Do</td>
</tr>

<%-- A continuacion comienzan las filas de los dias del mes --%>

<%
for (int i = 0; i < dias.length; ++i)
{
    %>

<%-- HTML --%>
    <tr>
<%-- /HTML --%>

    <%
    for (int j = 0; j < dias[i].length; ++j)
    {
        String marca = "class=\"fila-detalle-par\"";
        if (diaint == dias[i][j])
            marca="class=\"fila-detalle-destacada\"";
        String val = String.valueOf(dias[i][j]);
        if (val.length() < 2)
            val = "0" + val;
        String clase = "";
        if (j == 6)
            clase = "class=\"elementos-filtro\"";
        %>

<%-- HTML --%>
        <td align="center" <%=marca%>>
<%-- /HTML --%>

        <%
            if (dias[i][j] != 0) {
                %>

<%-- HTML : Contenido de las celdas con dias, val es el dia que se muestra --%>
                <a href="javascript:select('<%=val%>')" <%=clase%>><%=val%></a>
<%-- /HTML --%>

                <%
            } else {
                %>

<%-- HTML : Contenido de las celdas vacias --%>
                &nbsp;
<%-- /HTML --%>

                <%
            }
        %>

<%-- HTML --%>
        </td>
<%-- /HTML --%>

        <%
    }
    %>

<%-- HTML --%>
    </tr>
<%-- /HTML --%>

    <%
}
%>

<form>
</table>
<center>
<input type="button" class="botones-chicos" value="Limpiar Fecha" onClick="selectNull()">
</center>
</td>
</tr>
</form>
</table>
</center>
<script language="javascript">
  setInterval("window.focus();", 1000);
</script>
</body>
</html>
