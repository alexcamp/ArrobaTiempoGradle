var cuenta=0;velocidad=200;var texto=" **Bienvenido @Tiempo Colombia** "; function scrolltexto ()
{window.status=texto.substring (cuenta,texto.length)+  texto.substring(0,cuenta);if (cuenta <texto.length){ cuenta ++; }else{cuenta=0;}
setTimeout("scrolltexto()",velocidad);}scrolltexto ();