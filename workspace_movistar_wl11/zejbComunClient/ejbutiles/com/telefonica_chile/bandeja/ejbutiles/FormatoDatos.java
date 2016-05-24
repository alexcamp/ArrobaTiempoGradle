/*
 * Created on Jan 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.ejbutiles;

import com.tecnonautica.utiles.basicos.StringUtil;


/**
 * @author Alejandro O. (Chantaman)
 * 
 */
public class FormatoDatos {

	/**
	 * Formatea un rut sin puntos ni guíones a un formato con puntos separadores de miles y guión
	 * @author Marco Alarcón "Dono"
	 * @param rut String del rut sin formato
	 * @param dv Char del dígito verificador
	 * @return un único String formateado
	 */
	public static String formatoRut(String rut, Character dv){
		// Nota: se cambió la versión ultrachanta desarrollada por chantaman (Alejandro Ordoñez) a una versión que funciona con cualkier largo
		String subRutFinal = "";
		rut = StringUtil.sinNull(rut);
		if (rut.length() > 3) {
			int counter = 0;
			while ((counter + 3) < rut.length()) {
				subRutFinal = "." + rut.substring(rut.length() - (counter + 3), rut.length() - counter) + subRutFinal;
				counter += 3;
			}
			
			if (counter < rut.length()) {
				subRutFinal = rut.substring(0, rut.length() - counter) + subRutFinal;
			} else if (counter == rut.length()) {
				subRutFinal = subRutFinal.substring(1, subRutFinal.length());
			}
			
		} else {
			subRutFinal = rut;
		}
		
		return subRutFinal + "-" + dv;
	}

	public static String desplegarArreglos(String[] str){
		String cadena = null;
		for (int i=0;i<str.length;i++)
			cadena= cadena + str[i]+", ";
		return cadena.substring(0,cadena.length()-2);
	}

	public static Object notNull(Object objeto){  
		if(objeto == null)
			if(objeto instanceof Character)
				return new Character(' ');  
			else if(objeto instanceof java.sql.Date)
				return "Sin fecha";
			else if(objeto instanceof String)
				return "";
			else
				return " ";
			else
				return objeto;
	}
 
}