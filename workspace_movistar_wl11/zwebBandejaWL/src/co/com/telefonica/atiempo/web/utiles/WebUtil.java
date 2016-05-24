/*
 * Created on 15-02-2007 
 */
package co.com.telefonica.atiempo.web.utiles;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TCS 
 */
public class WebUtil {

	public WebUtil() {
		super();
	}

	public static String getString(Object obj) {
		if (obj == null)
			return "";
		
		return obj.toString();
	}

	public static String formatFecha(Date fecha, SimpleDateFormat sdf) {
		if (fecha == null || sdf==null)
			return "";

		String aux = "";
		try {
			aux = sdf.format(fecha);
		} catch (Exception e) {
		}

		return aux;
	}

	public static String formatFecha(Timestamp tmstp, SimpleDateFormat sdf) {
		if (tmstp == null || sdf==null)
			return "";

		return formatFecha(new Date(tmstp.getTime()), sdf);

	}
	
	public static String elije(Object obj1,Object obj2)
	{
		if (obj1 == null && obj2==null)
			return "";
		if(obj1!=null)
			return obj1.toString();
		return obj2.toString();
	}

}
