<%@ page import="java.util.ArrayList,java.util.Iterator,java.util.HashMap"%>
<%@ page import="com.telefonica_chile.comun.datos_punto_venta.dto.PuntoVentaDTO"%>
<%@ page import="com.telefonica_chile.comun.datos_agencia.dto.AgenciaDTO"%>
<%@page import="com.telefonica_chile.comun.asigna.dto.UsuarioRolDTO" %>
<%

	ArrayList listaUsuarios = (ArrayList) request.getAttribute("listaUsuarios");
	if ( listaUsuarios == null )
		listaUsuarios = new ArrayList();

	ArrayList listaFiltro = (ArrayList) request.getAttribute("listaFiltro");
	String tipoFiltro = (String) request.getAttribute("tipoCombo");

	String idRol = request.getParameter("idRol");
%>

<HTML>
<HEAD>
<script>
<!--
function cerrarVentana( forma ) {
	window.close();
}

function elegirUsuario( id, nombreUsuario ) {
	if ( confirm("Esta seguro de Elegir: [" + id + "," + nombreUsuario + "]") ) {
		window.opener.cargaUsuario( id, nombreUsuario);
		cerrarVentana();
	}
}

function crearCelda( id, nombre ) {

  var txtName = document.createTextNode(nombre);
  var datoCelda = txtName;
  if ( nombre != "" ) {
    var aux = document.createElement("A");
    aux.href="javascript:elegirUsuario(\""+ id + "\",\"" + nombre + "\");";
    aux.appendChild( txtName );
    datoCelda = aux;
  }

  var celda = document.createElement("td");
  celda.colspan = "1";
  celda.appendChild( datoCelda );
  
  return celda;
	
}

function limpiaTable( table ) {

  table.getElementsByTagName("tbody")[0];
  var listTR = document.getElementsByTagName("TR");

  for ( i=listTR.length-2; i>1;i--) {
  //   alert( listTR.item(i).firstChild.firstChild.nodeValue );
     table.getElementsByTagName("tbody")[0].removeChild( listTR.item(i) );
  }
  
}

function crearFila( table, tipo, tipoClase, id1, name1, id2, name2 ) {
  var col1 = crearCelda( id1, name1 );
  var col2 = crearCelda( "", "" );
  var col3 = crearCelda( id2, name2 );

  var newRow = document.createElement("TR");
  newRow.valign = "top";
  newRow.className = tipoClase;
  newRow.appendChild( col1 );
  newRow.appendChild( col2 );
  newRow.appendChild( col3 );

  table.getElementsByTagName("tbody")[0].appendChild(newRow);
}

function buscar( param, ind, str ) {
	if ( str == null )
		return "";
	var ij = str.indexOf(param);
	if ( ij >=0 ) {
		if ( ind == 0 )
			return str.substring(0, ij);
		else
			return buscar( param, --ind, str.substring(ij+2, str.length) );
	} else
		return str;
}
function actualizaTabla( tipo, nameTabla ) {

  var tabla = document.getElementById( nameTabla );


  limpiaTable( tabla );
//  alert("Creando TAbla : " + tipo  );
  cargarUsuarios( tipo, tabla );
}

function sirveHab( idUsuario, tipo ) {

	if ( tipo == "TODO" )
		return true;
    var k = 0;
    for (k=0; k<Hab.length; k++) {
	    var aux = Hab[k];
    	var idUsr = buscar("##", 0, aux);
    	if ( idUsr != idUsuario )
    		continue;

    	var ij = aux.indexOf("##" + tipo + "##");
    	if ( ij >= 0 )
    		return true;

    	return false;
    }
   	return false;

}

function cargarUsuarios( tipo, tabla ) {
  var j = 0;
  var k = 0;
  for ( i=0; i<TodoUser.length; i++) {
    var tipoClase = "fila-detalle-impar";
    j++;
    if ( j % 2 == 0 )
    	tipoClase = "fila-detalle-par";

    var aux = TodoUser[i];
    var idUser1 = buscar("##", 0, aux);
    var nomUser1 = buscar("##", 1, aux);
    var logUser1 = buscar("##", 2, aux);

    if ( !sirveHab( idUser1, tipo ) )
      continue;

    // Busco el siguiente usuario.
	var idUser2 = "";
	var nomUser2 = "";
	var logUser2 = "";
	for ( k=i+1; k<TodoUser.length; k++) {
//		alert( "i==" + i + "; k==" + k );
		var aux2 = TodoUser[k];
		idUser2 = buscar("##", 0, aux2);
		nomUser2 = buscar("##", 1, aux2);
		logUser2 = buscar("##", 2, aux2);
	    if ( !sirveHab( idUser2, tipo ) ) {
	    	idUSer = "";
	    	nomUser = "";
	    	logUSer = "";
	    	continue;
	    }
	    break;

	}
//	alert( "i==" + i + "; k==" + k );
	i = k;

	crearFila( tabla, tipo, tipoClase, idUser1, nomUser1, idUser2, nomUser2);

   }


}

//-->
</script>
<TITLE>Buscar Usuarios para Asignar</TITLE>
<LINK rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</HEAD>
<BODY>
<br><br>
<%
  String js = "";
  String jsHab = "";
  HashMap auxMap = null;
  for (int i=0; listaUsuarios!=null && i<listaUsuarios.size(); i++) {
    UsuarioRolDTO urDTO = (UsuarioRolDTO) listaUsuarios.get(i);
    if ( urDTO == null )
    	continue;
     Long habilitado = urDTO.getUsroHabilitado();
     String strHab = (habilitado == null || habilitado.intValue()==0) ? "No Habilitado" : "Habilitado";
     js += "TodoUser[TodoUser.length++] = '" + urDTO.getUsuaId() + "##" + urDTO.getNombre() + "##" + urDTO.getLogin() + "##" +  strHab + "##';\n";
     auxMap = urDTO.getHabilidades();
     String listaKey = "";
     if (auxMap != null ) {
       	if ( auxMap.keySet()!=null) {
			for (Iterator it = auxMap.keySet().iterator(); it.hasNext(); ) {
				String key = (String) it.next();
				listaKey += key + "##";
			}
		}
		jsHab += "Hab[Hab.length++] = '" + urDTO.getUsuaId() + "##" +  listaKey + "';\n";
	 }
  }
%>
<SCRIPT language="JavaSCript">
<!--
var TodoUser = new Array();
var Hab = new Array();
<%= js %>
<%= jsHab %>
//-->
</SCRIPT>
<form name="forma" action="BuscarUsuarioTorre.acc" method="post">
<input type="hidden" name="idRol" value="<%=idRol%>">

<table width="80%" align="center">
 <tr align="right">
  <td>
<% if ( "AGENCIA".equals( tipoFiltro ) ) { %>
Filtrar por Agencia: 
<% } else if( "PTO_VTA".equals( tipoFiltro ) ) { %>
Filtrar por Punto de Venta: 
<% } %>
<select name="comboFiltro" onChange="actualizaTabla(this.options[this.selectedIndex].value, 'tablaUsuarios');">
<option value="TODO">Mostrar todos los Usuarios
<%
  for (int i=0; listaFiltro!=null && i<listaFiltro.size(); i++) {
    if ( "AGENCIA".equals( tipoFiltro ) ) {
    	AgenciaDTO dto = (AgenciaDTO) listaFiltro.get(i);
    	if ( dto == null )
    		continue;
%>
	<option value="<%=dto.getAgenId()%>"><%=dto.getAgenDescripcion()%></option>
<%
	} else if ( "PTO_VTA".equals( tipoFiltro ) ) {
    	PuntoVentaDTO dto = (PuntoVentaDTO) listaFiltro.get(i);
    	if ( dto == null )
    		continue;
%>
	<option value="<%=dto.getIdPtoVenta()%>"><%=dto.getNombre()%></option>
<%
	}
  }
%>
</select>
  </td>
 </tr>
</table>
<hr>
<input type="hidden" name="idRol" value="<%=idRol%>">
<table id="tablaUsuarios" name="tablaUsuarios" class="tabla-borde-delgado" width="80%" align="center">
 <tr class="encabezados-tabla">
  <td>Nombre Usuario</td>
  <td>&nbsp;&nbsp;&nbsp;</td>
  <td>Nombre Usuario</td>
 </tr>
</table>
<br>
<table width="80%" align="center">
 <tr align="center">
  <td>
	<input type="button" value="Cerrar Ventana" onclick="cerrarVentana()">
  </td>
 </tr>
</table>
</form>	
<SCRIPT language="JavaSCript">
<!--
  actualizaTabla( "TODO", "tablaUsuarios" );
//  setInterval("window.focus();", 10000);
//-->
</SCRIPT>

</BODY>
</HTML>
