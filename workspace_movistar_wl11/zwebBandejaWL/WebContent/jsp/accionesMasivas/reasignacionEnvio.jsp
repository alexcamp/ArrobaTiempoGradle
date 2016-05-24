<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<SCRIPT>
<!--
var mensaje = '<%=request.getAttribute("mensaje")%>';
var codigoError = <%=request.getAttribute("codigoError")%>;
if (codigoError == 1) {
   mensaje = mensaje + '\n\nOperacion exitosa';
   alert(mensaje);
   window.parent.validaFiltro(window.parent.document.forma);   
} else {
   mensaje = mensaje + '\n\nOperacion abortada';
   alert(mensaje);
}
//-->
</SCRIPT>