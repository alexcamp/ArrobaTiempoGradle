
<%@page import="com.telefonica_chile.comun.asigna.dto.usuario" %> 
<%@page import="com.telefonica_chile.comun.asigna.dto.peticion" %> 
<%@page import="com.telefonica_chile.comun.asigna.dto.jornadaDelUsuario" %> 
<%@page import="com.tecnonautica.utiles.basicos.FechaUtil" %> 
<%@page import="com.telefonica_chile.comun.asigna.dto.rolHabilidad" %>
<%@page import="java.util.Date" %> 
<jsp:useBean id="usuariosDisponibles" 	class="java.util.ArrayList" scope="session" />
<jsp:useBean id="usuariosJornada" 		class="java.util.ArrayList" scope="session" />
<jsp:useBean id="habilidadesDelRol" 		class="java.util.ArrayList" scope="session" />
<%peticion resultPeticion =  (peticion)request.getAttribute("resultPeticion");%>
<% 	Date fechaTermino	= new Date();
	double segTemp 		= 0.0;
	int tmpMinutos 		= 0;
	double tmpSegundos 	= 0.0;
%>
<html>
<head>
<LINK REL="SHORTCUT ICON" HREF="favicon.ico">
<title>Lista Tecnicos por Habilidad</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">





<link rel="stylesheet" href="/bandejaweb/estilo.css" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000"><br>
<form name="frm"  >


  
  <table width="33%" border="0" class="tabla-borde-delgado" align="center" cellpadding="0" cellspacing="0">
    <tr> 
      <td height="18" width="35%" class="fila-detalle-par">Cod Peticion</td>
      <td height="18" width="2%" class="fila-detalle-par"> 
        <div align="center"><b>:</b></div>
      </td>
      <td height="18" width="63%" class="fila-detalle-impar"><%=resultPeticion.getPeticionId()%></td>
    </tr>
    <tr> 
      <td height="18" width="35%" class="fila-detalle-par"> 
        <div align="left">Fecha </div>
      </td>
      <td height="18" width="2%" class="fila-detalle-par"> 
        <div align="center"><b>:</b></div>
      </td>
      <td height="18" width="63%" class="fila-detalle-impar"><%=resultPeticion.getPeticionFecha()%></td>
    </tr>
    <tr> 
      <td height="18" colspan="3" class="fila-detalle-par"> 
        <div align="center"><font color="#FF0000">PARAMETROS A CUMPLIR - HABILIDAD ROL </font></div>
      </td>
    </tr>
    <tr> 
      <td width="35%" class="fila-detalle-par"> 
        <div align="left">Nombre</div>
      </td>
      <td width="2%" class="fila-detalle-par">:</td>
      <td width="63%" class="fila-detalle-par">Valor</td>
    </tr>
    <%if(habilidadesDelRol.size()!=0){%>
    <% for(int hr=0;hr<habilidadesDelRol.size();hr++) {
  rolHabilidad habilidadfiltro = (rolHabilidad) habilidadesDelRol.get(hr);%>
    <tr> 
      <td width="35%" class="fila-detalle-impar"> 
        <div align="left"><%=habilidadfiltro.getHabilidadNombre()%></div>
      </td>
      <td width="2%" class="fila-detalle-impar"><b>:</b></td>
      <td width="63%" class="fila-detalle-impar"><%=habilidadfiltro.getHabilidadValor()%></td>
    </tr>
        <%}%>
    <%}else{%>
    <TR> 
      <TD colspan="3" class="fila-detalle-impar"> No EXISTEN HABILIDADES PARA EL ROL</TD>
    </TR>
    <%}%>
  </table>
  <br>
  <br>
  <TABLE border="0" align="center" width="361" cellpadding="0" cellspacing="0" class="tabla-borde-delgado">
    <TR> 
      <TD class="fila-detalle-par" colspan="3">T&eacute;cnicos Encontrados para 
        la Actividad</TD>
    </TR>
    <TR> 
      <TD class="fila-detalle-par" valign="top" align="center" height="17" width="73"> 
        <div align="center">Id. T&eacute;cnico</div>
      </TD>
      <TD class="fila-detalle-par" valign="top" align="center" height="17" width="81">Idca 
        T&eacute;cnico</TD>
      <TD class="fila-detalle-par" valign="top" align="center" height="17" width="78">Id.Supervisor</TD>
    </TR>
    <%if(usuariosDisponibles.size()!=0){%>
    <% for(int i=0;i<usuariosDisponibles.size();i++) {
  usuario usuariosDispo = (usuario) usuariosDisponibles.get(i);

  %>
    <TR> 
      <TD class="fila-detalle-impar" width="73" height="2"><%=usuariosDispo.getUsuarioId()%></TD>
      <TD class="fila-detalle-impar" width="81" height="2"><%=usuariosDispo.getUsuarioIdca()%></TD>
      <TD class="fila-detalle-impar" width="78" height="2"><%=usuariosDispo.getUsuarioIdSupervisor()%></TD>
	  
    </TR>
    <%}%>
    <%}else{%>
    <TR> 
      <TD colspan="3" class="fila-detalle-impar"> No Encontrado</TD>
    </TR>
    <%}%>
  </table> 
<br>
 

  <table width="39%" border="0" cellpadding="4" cellspacing="0" align="center" class="tabla-borde-delgado">
    <tr> 
      <td class="fila-detalle-impar-sin-borde" colspan="5"><span class="fila-detalle-impar"  >Disponibilidad 
        de Técnicos de MAYOR a MENOR tiempo disponible.</span></td>
    </tr>
    <tr> 
      <td class="fila-detalle-imparSA" width="18%">T&eacute;cnico ID</td>
      <td class="fila-detalle-imparSA" width="24%">T&eacute;cnico IDCA</td>
      <td class="fila-detalle-imparSA" width="24%">Fecha Jornada</td>
      <td class="fila-detalle-imparSA" width="16%">Min.Dispo</td>
      <td class="fila-detalle-imparSA" width="18%">Pet. Asign.</td>
    </tr>
    <% if (usuariosJornada.size()>0) { %>
    <% for(int p=0;p<usuariosJornada.size();p++) {
	  jornadaDelUsuario usuarioDispo = (jornadaDelUsuario) usuariosJornada.get(p); %>
    <tr> 
      <td width="18%" class="fila-detalle-imparA"> 
        <div align="center"><%=usuarioDispo.getJornUsuId()%></div>
      </td>
      <td width="24%" class="fila-detalle-imparA"> 
        <div align="center"><%=usuarioDispo.getJornUsuIdca()%></div>
      </td>
      <td width="24%" class="fila-detalle-imparA"> 
        <div align="center"><%=usuarioDispo.getJornNombre()%></div>
      </td>
      <td width="16%" class="fila-detalle-imparA"><%=usuarioDispo.getJornMinDispo()%></td>
      <td width="18%" class="fila-detalle-imparA"><%=usuarioDispo.getJornUsuNroActuaciones()%></td>
    </tr>
    <%}%>
    <%} else {%>
    <tr> 
      <td colspan="5" class="fila-detalle-imparA"> 
        <div align="center">NO HAY DISPONIBILIDAD EN TIEMPO PARA LOS TECNICOS</div>
      </td>
    </tr>
    <%}%>
  </table>
  <br>
  <table width="31%" border="0" align="center" cellspacing="0" cellpadding="0" class="tabla-borde-delgado-lista">
    <tr> 
      <td class="fila-detalle-par" colspan="2"> 
        <div align="left"> </div>
        <font color="#FF0000">PETICION </font></td>
    </tr>
    <tr> 
      <td class="fila-detalle-par" width="43%"> 
        <div align="right">Id Bloque Asignado</div>
      </td>
      <td width="57%" class="fila-detalle-imparA"><%=resultPeticion.getPeticionIdBloque()%></td>
    </tr>
    <tr> 
      <td class="fila-detalle-par" width="43%"> 
        <div align="right">Tiempo Peticion</div>
      </td>
      <td width="57%" class="fila-detalle-imparA"><%=resultPeticion.getPeticionTimeActuacion()%></td>
    </tr>
    <tr> 
      <td class="fila-detalle-par" width="43%"> 
        <div align="right">Fecha y Hora Inicio</div>
      </td>
      <td width="57%" class="fila-detalle-imparA"><%=FechaUtil.formatoParaFecha(resultPeticion.getPeticionTimeAsignacion(),"dd/MM/yyyy H:mm:ss")%></td>
    </tr>
    <tr> 
      <td class="fila-detalle-par" width="43%"> 
        <div align="right">Fecha y Hora Termino</div>
      </td>
      <td width="57%" class="fila-detalle-imparA"><%if (resultPeticion.getPeticionTimeAsignacion()!=null) {
     			tmpMinutos 		= resultPeticion.getPeticionTimeActuacion().intValue();
				tmpSegundos 	= (resultPeticion.getPeticionTimeActuacion().doubleValue() - tmpMinutos )*60;
				fechaTermino 	= FechaUtil.agregaMinutosFecha(resultPeticion.getPeticionTimeAsignacion(),tmpMinutos);
				fechaTermino	= FechaUtil.agregaSegundosFecha(fechaTermino,(int)tmpSegundos); %>
       
       				<%=FechaUtil.formatoParaFecha(fechaTermino ,"dd/MM/yyyy H:mm:ss")%>
       												<%} %></td>
    </tr>
    <tr> 
      <td class="fila-detalle-par" width="43%"> 
        <div align="right">Id Rol Asignado</div>
      </td>
      <td width="57%" class="fila-detalle-imparA"><font color="#FF0000"><%=resultPeticion.getPeticionIdRolAsignado()%></font></td>
    </tr>
    <tr> 
      <td class="fila-detalle-par" width="43%"> 
        <div align="right">id Peticion Asignada</div>
      </td>
      <td width="57%" class="fila-detalle-imparA"><font color="#FF0000"><%=resultPeticion.getPeticionId()%></font></td>
    </tr>
  </table>
</form>
</body>

</html>
