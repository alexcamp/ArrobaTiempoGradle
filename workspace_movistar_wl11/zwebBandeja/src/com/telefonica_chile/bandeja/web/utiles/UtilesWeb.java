/*
 * Created on Jan 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.utiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author rbpizar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UtilesWeb {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(UtilesWeb.class);
	
	public static final SimpleDateFormat SDF =
		new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		
	public static final SimpleDateFormat formatStandard = 
		new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
	public static final SimpleDateFormat formatOnlyStandardDate = 
		new SimpleDateFormat("dd-MM-yyyy");
		
	public static final SimpleDateFormat formatOnlyStandardHour =
		new SimpleDateFormat("HH:mm");

	public static Date convertirFecha(String fechaString, SimpleDateFormat sdf) {
		log.debug("Vamos a parsear: " + fechaString);
		Date fechaDate = new Date();		
		try {
			fechaDate = sdf.parse(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.debug(fechaDate);
		return (fechaDate);
	}
	
	public static String convertirDate2String(Date d, SimpleDateFormat sdf) {
		try {
			return sdf.format(d);
			/*
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(d);
			
			return "" + gc.get(GregorianCalendar.DATE) + ""
				+ gc.get(GregorianCalendar.MONTH) + ""
				+ gc.get(GregorianCalendar.YEAR) + ""
				+ gc.get(GregorianCalendar.HOUR) + ""
				+ gc.get(GregorianCalendar.MINUTE);
			*/
				
			
		} catch (Exception e) {
			return "";		
		}
	}
	/*
	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.DAY_OF_YEAR,1);
		Date fecha = gc.getTime();
	}
	*/
	

	public static String Revisa_Valor_CheckBox(String valor) {
		String cadena = valor;
		if ((cadena == null) || (cadena.length() == 0) || (cadena == "0")) {
			return ("0");
		} else {
			return ("1");
		}
	}
	
	public static int getYear(Date fecha) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(fecha);
			return  gc.get(GregorianCalendar.YEAR);	
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getMonth(Date fecha) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(fecha);
			return  gc.get(GregorianCalendar.MONTH);	
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getHour(Date fecha) {
			try {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(fecha);
				return  gc.get(GregorianCalendar.HOUR_OF_DAY);	
			} catch (Exception e) {
				return 0;
			}
	}
	
	public static int getMinutes(Date fecha) {
			try {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(fecha);
				return  gc.get(GregorianCalendar.MINUTE);	
			} catch (Exception e) {
				return 0;
			}		
	}
	
	public static Date agregaDiasFecha(Date fecha,int dias) {
	   GregorianCalendar gc = new GregorianCalendar();
	   gc.setTime(fecha);
	   gc.add(GregorianCalendar.DAY_OF_YEAR,dias);		
	   return (gc.getTime());
   }

	public static String muestraFecha(String fecha, String formato, String formatoSalida) {
		SimpleDateFormat f1 = new SimpleDateFormat(formato);
		SimpleDateFormat f2 = new SimpleDateFormat(formatoSalida);
		
		if (fecha == null)
			return "";
		String aux = "";
		try {
			aux = f2.format( f1.parse(fecha) );
		} catch (ParseException e) {
			aux = "";
		}
		
		return aux;
   }

}