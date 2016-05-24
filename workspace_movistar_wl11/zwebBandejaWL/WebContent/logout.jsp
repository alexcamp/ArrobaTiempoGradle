<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<%@page import="com.telefonica_chile.atiempo.utiles.ApplicationConfig"%>
<%
String urlBandeja = ApplicationConfig.getUrlBase(ApplicationConfig.APP_BANDEJA);
String urlVpistbba = ApplicationConfig.getUrlBase(ApplicationConfig.APP_VPISTBBA);
String urlAtst = ApplicationConfig.getUrlBase(ApplicationConfig.APP_ATST);
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META HTTP-EQUIV=Refresh CONTENT="2; URL=<%=urlBandeja%>/">
<TITLE>logoff.jsp</TITLE>
</HEAD>
<BODY>

Desconectandose...

<!-- Realizamos desconexion de cada uno de los proyectos -->
<iframe id="atst"
  name="atst"
  style="width:0px; height:0px; border: 0px"
  src="<%=urlAtst%>/LogOut.acc"></iframe>
<iframe id="vpistbba"
  name="vpistbba"
  style="width:0px; height:0px; border: 0px"
  src="<%=urlVpistbba%>/ibm_security_logout"></iframe>
<iframe id="bandeja"
  name="bandeja"
  style="width:0px; height:0px; border: 0px"
  src="<%=urlBandeja%>/ibm_security_logout"></iframe>

</BODY>
</HTML>
