/*
 * Created on Jan 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.ejbutiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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
	
	public static void main(String[] args) {
		
		Date f = new Date();
		String fecha = UtilesWeb.convertirDate2String(f,UtilesWeb.formatStandard);
		System.out.println("Fecha "+fecha); 
		
	}

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
	   
	public static Date agregaHorasFecha(Date fecha,int horas) {
		   GregorianCalendar gc = new GregorianCalendar();
		   gc.setTime(fecha);
		   gc.add(GregorianCalendar.HOUR_OF_DAY,horas);		
		   return (gc.getTime());
	   }

	public static String double2String(Double valor){
		String str = "";
		try {
			str = valor.toString();
			str = str.substring(0, str.length()-2);
		} catch (Exception e) {
		}

		return str;
	}

	public static String[] split(String str, char c) {
		StringTokenizer st = new StringTokenizer(str, String.valueOf(c));
		int n = st.countTokens();
		if (n == 0)
			return null;

		String[] res = new String[n];

		for (int i = 0; st.hasMoreTokens(); i++)
			res[i] = st.nextToken();

		return res;
	}

	public static String getFechaYYMMDD() {
		// Obtengo la Fecha Actual. Formato (YYMMDD)
		String fecha = "";
		try {
			SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yy");
			java.util.Date fechaDate = new java.util.Date();
			fecha = UtilesWeb.convertirDate2String(fechaDate,SDF);
			String[] fecha_array = split(fecha,'-');
			fecha = fecha_array[2]+fecha_array[1]+fecha_array[0];
		} catch (Exception e) {
		}
		return fecha;
	}
}