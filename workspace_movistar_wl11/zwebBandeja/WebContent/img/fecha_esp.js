 meses = new Array("enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre");
 var curdate=new Date();
 var mes=curdate.getMonth();
 var dia_num=curdate.getDate();
 //var dia_sem=curdate.getDay();
 var anio=curdate.getFullYear();
 document.write( dia_num+" de "+meses[mes]+" de "+anio);
