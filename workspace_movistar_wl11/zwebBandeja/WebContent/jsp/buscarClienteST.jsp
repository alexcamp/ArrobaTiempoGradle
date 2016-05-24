<%@page import="java.util.*"%>
<%@page import="com.telefonica_chile.atiempo.utiles.*"%>

<%
//UrlAplicaciones url= new UrlAplicaciones();
//String urlATST=url.getUrl("ATST");
%>

<%
String urlATST = ApplicationConfig.getUrlBase(ApplicationConfig.APP_ATST);
%>
<script type="text/javascript">
<!--
//document.location='/SolTecWeb/BuscarCliente.acc';
document.location='<%=urlATST%>BuscarCliente.acc';
//-->
</script>
