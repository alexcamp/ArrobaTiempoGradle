<%@page import="java.util.*"%>
<%
 String urlBandejaBase = (String) request.getAttribute("URL_BANDEJA");
 String urlBandeja = urlBandejaBase + "/verBandeja.acc";
 if(request.getParameter("busAvSt")!=null)
 	urlBandeja = urlBandejaBase+"/verResultadoGrabacion.acc";
%>
<HTML>
<META HTTP-EQUIV=Refresh CONTENT="0; URL=<%=urlBandeja%>">

</HTML>