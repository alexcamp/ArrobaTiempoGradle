<HTML>
<HEAD>
<TITLE>status.jsp</TITLE>
</HEAD>
<BODY>
Version $Tag$
</BODY>
</HTML>
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
<TITLE>status.jsp</TITLE>
</HEAD>
<BODY>
<PRE>

STATUS $Id: status.jsp,v 1.1.2.1 2011/03/30 22:48:11 lfmartinez Exp $

Version $Name:  $

URLs:
	bandeja:  <%=urlBandeja%>
	vpistbba: <%=urlVpistbba%>
	atst:     <%=urlAtst%>

</PRE>
</BODY>
</HTML>
