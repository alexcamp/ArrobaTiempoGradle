/*
 * Created on 15-mar-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.telefonica.atiempo.interfaces.atiempo.TR003S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

/**
 * @author TCS
 * 
 * Mensaje: TR_003_S
 */
public class AsignacionRecursoManualSTBServicio extends ServicioBasico {

	private final String expRegMsg = "(.*)((<phone-number>)(.*)(</phone-number>))(.*)";
	private final String expRegPhoneTag = "<phone-number>.*</phone-number>*";
	private final String expRegNoDigitos = "[\\D]";
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {		
		TR003S tr003s = (TR003S) obj[0];		
		RecursosInterfaces recursos = new RecursosDelegate();
		recursos.asignarRecursoManualSTB(tr003s);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		
		mensaje = controlPhoneNumber(mensaje);
		
		Object[] obj = new Object[1];
		TR003S tr003s = (TR003S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr003s;
		return obj;
	}
	
	/*
	 * CR 00024071 - 2009/04/24 - 3
	 * 		Se hace control sobre el número de teléfono en el mensaje - German P. 
	 */
	private String controlPhoneNumber(String mensaje){
		if (mensaje != null){
			Pattern p = Pattern.compile(expRegMsg);
			Matcher m = p.matcher(mensaje);
			String mens1 = "";
			String mens2 = "";
			if (m.matches()){
				mens1 = m.group(1);
				mens2 = m.group(6);
				String tagOriginal = m.group(2);
				String iniTag = m.group(3);
				String phoneNumber = m.group(4).trim();
				String finTag = m.group(5);
				if (!phoneNumber.equals("-1")){
					phoneNumber = phoneNumber.replaceAll(expRegNoDigitos,"");
					if (phoneNumber == null || phoneNumber.equals("")){
						phoneNumber = "-1";
					}
				}
				String nuevoTag = iniTag + phoneNumber + finTag;
				mensaje = mens1 + nuevoTag + mens2;
			}
		} else {
			mensaje = "-1";
		}
			
		return mensaje;
	}

}
