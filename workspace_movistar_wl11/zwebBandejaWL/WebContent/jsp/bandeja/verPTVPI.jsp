<%@page import="java.util.*"%>
<%@page import="com.telefonica_chile.bandeja.dto.AuxBandejaDTO"%>
<%@page import="com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb" %>
<%
	UsuarioWeb usuario = (UsuarioWeb) request.getAttribute("usuario");
	int randomInt = 1 + (int)(Math.random() * 50);
	String URLVpi = (String) request.getAttribute("URL_VPI");
		AuxBandejaDTO aux=(AuxBandejaDTO)request.getAttribute("auxBandeja");
	URLVpi+="/DespliegaPantallaCo.acc?template="+aux.getTemplate();
	URLVpi+="&folio="+aux.getFolio();
	URLVpi+="&actividad="+aux.getActividad();
	URLVpi+="&instanciaActividad="+java.net.URLEncoder.encode(aux.getInstanciaActividad());
	URLVpi+="&familiaPs="+aux.getFamiliaPs();
	URLVpi+="&estadoPet="+aux.getEstadoPet();
	URLVpi+="&gestorOS="+randomInt;
	URLVpi+="&ID_USUARIO="+usuario.getId();
%>
<HTML>
<META HTTP-EQUIV=Refresh CONTENT="0; URL=<%=URLVpi%>">
</HTML>