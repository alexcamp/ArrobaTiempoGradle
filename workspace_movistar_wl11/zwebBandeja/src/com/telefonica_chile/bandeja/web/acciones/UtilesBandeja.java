package com.telefonica_chile.bandeja.web.acciones;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilesBandeja {
	
	public static String decideColorSemaforo(String tipoSemaforo) {
		if (tipoSemaforo == null)
			tipoSemaforo = "N";
		tipoSemaforo = tipoSemaforo.toUpperCase();

		// Por Default Semaforo apagado...
		String strGif = "luz_negra.gif";

		if (tipoSemaforo.equals("R"))
			strGif = "luz_roja.gif";
		else if (tipoSemaforo.equals("A"))
			strGif = "luz_amarilla.gif";
		else if (tipoSemaforo.equals("V"))
			strGif = "luz_verde.gif";

		return strGif;
	}


	public static String selected(String v1, String v2) {
		if (v1 != null && v2 != null && v1.equals(v2))
			return "selected";
		return "";
	}

	public static String formatoFecha(Date fecha, String formato, String porDefecto) {
		String salida = porDefecto;
		if (fecha == null)
			return salida;

		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
			//TimeZone tz = TimeZone.getTimeZone("America/Santiago");
			//formatoFecha.setTimeZone(tz);
			salida = formatoFecha.format(fecha);
		} catch (Exception e) {
			//logger.debug("No se pudo parsear fecha " + fecha + ": " + e);
		}
		return salida;
	}

	public static String sinNull(String str, String porDefecto) {
		if ( str == null)
			return porDefecto;
		return str;
	}

	public static String filled(String str, int hasta, String c, int side) {
		if ( str == null)
			return str;
		int i = 0;
		if (side == 1) { // Agregar 'c' a la derecha.
			for (i = str.length(); i < hasta; i++)
				str = str + c;
		} else { // Agregamos a la izquierda.
			for (i = str.length(); i < hasta; i++)
				str = c + str;
		}

		return (str);
	}
}
