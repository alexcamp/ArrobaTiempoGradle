/*
 * Created on Dec 22, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @author rbpizar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Utiles {
    private static Logger log=Logger.getLogger (Utiles.class);	
	
	public static String retornaTitulo(int i) {
		
		String titulo = "";
	
		switch (i) {

			case 1 :
			titulo = "Instalados";
			break;
	  
			case 2 :
			titulo = "Retirados";
			break;		

			case 3:
			titulo = "Fallados";
			break;

			default :
			break;
		}

		return (titulo);
	}

	/**
	 * Este método es utilizado en el GrabadorComun que implementa el comportamiento por defecto
	 * de los grabadores. La idea es que todos los parámetros que vengan en el request y comiencen
	 * por 'WF_', son parámetros que corresponden a un valor a poner en la estructura de datos (phi)
	 * de la actividad. Hay que estudiar si esta forma de hacer las cosas es adecuada, está aquí más
	 * que nada para hacer funcionar las pruebas...
	 * 
	 * @param request
	 * @return
	 */
	public static Map getMapDatosFromRequest(Map datos, HttpServletRequest request) {
		
		//ESTO LO HAGO PARA NO PERDER LOS DATOS QUE NO DEFINA EXPLICITAMENTE
		if(null == datos) {
			datos = new HashMap();
		} 
		
		Enumeration parameterNames =
			request.getParameterNames();
		
		while (parameterNames.hasMoreElements()) {
			String nombreParametro =
				(String) parameterNames.nextElement();
		
			// Si el parametro comienza con "WF_", entonces le saco el "WF_" y
			// lo llevo al contenedor
			if (nombreParametro.startsWith("WF_")) {
				String parametro = nombreParametro.substring(3);
				//TODO evaluar bien que pasa aquí... ¿cómo le paso un null? ¿se necesita?
				if(request.getParameter(nombreParametro) != null) {
					datos.put(parametro,request.getParameter(nombreParametro));
				}
			}
		}
		return datos;
	}
	
	public static String longSinNull(Long long1,String porDefecto)
	{
		if(long1==null)
			return porDefecto;
		return long1.toString();
	}
	
	public static long longSinNullValue(Long long1,Long porDefecto)
	{
		if(long1==null)
			return porDefecto.longValue();
		return long1.longValue();
	}
	
	public static String integerSinNull(Integer integer1,String porDefecto)
	{
		if(integer1==null)
			return porDefecto;
		return integer1.toString();
	}

	public static String sinNull(Object o, String porDefecto) {
		if ( o == null )
			return porDefecto;
		if ( o.toString().trim().equals(""))
			return porDefecto;

		return o.toString();
	}

	
	public static String fechaSinNull(Timestamp timestamp,String porDefecto)
	{
		if(timestamp==null)
			return porDefecto;
		Fecha fecha=new Fecha(timestamp);
		return fecha.getFechaconFormato();
	}
	
	public static String ConversorGuion(String data){
		
		if (data != null && !data.trim().equals("")){
			
			return data;
		}else{
			
			return "-";
		}

	}
	
	public static char ConversorGuion(char c) {
			char[] arrChar = new char[1];
			arrChar[0] = c;
			String strOut = new String(arrChar);
		
			if ( strOut == null || strOut.length()==0 )
				return '0';

			return strOut.charAt(0);
		}

	
	public static String ConversorCero(String data){
		
			if (data != null && !data.trim().equals("")){
			
				return data;
			}else{
			
				return "0";
			}

		}

	public static String[] split(String str, char c) {
		if ( str == null )
			return null;
		StringTokenizer st = new StringTokenizer(str, String.valueOf(c));
		int n = st.countTokens();
		if (n == 0)
			return null;

		String[] res = new String[n];

		for (int i = 0; st.hasMoreTokens(); i++)
			res[i] = st.nextToken();

		return res;
	}

	/**
	 * @param string
	 * @param object
	 * @return
	 */
	public static Long parseLong(String str, Long longDef) {
		
		try {
			Long nLong = new Long( str );
			return nLong;
		} catch (NumberFormatException e) {
		}

		return longDef;
	}
	
	public static HashMap getSacarLetraNumero(String data){
		
		HashMap arreglo = new HashMap();
		char[] arregoData = data.toCharArray();
		StringBuffer letra = new StringBuffer();
		StringBuffer numero = new StringBuffer();
		try {
			for(int i = 0; i < arregoData.length; i++){
				try{
					Integer.parseInt(String.valueOf(arregoData[i]));
					numero.append(arregoData[i]);
					
				}catch (Exception e) {
					letra.append(arregoData[i]);
				}

			}

			if(!letra.toString().equals("") && letra.toString() != null ){
				arreglo.put("letra", letra.toString().trim());
			}else{
				arreglo.put("letra", "-");
			}
			
			if(!numero.toString().equals("") && numero.toString() != null ){
				arreglo.put("numero", numero.toString().trim());
			}else{
				arreglo.put("numero", "-");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arreglo;
	}
	
	//TODO : - Inicio - agonzalez- 10/04/2008  
	public static String getClassName(String nombreClase){
	
			String nombre = nombreClase.substring(nombreClase.lastIndexOf(".")+1, nombreClase.length());
			return nombre;
	}
	//TODO :  - Fin - agonzalez- 10/04/2008 
	
//	-Inicio Pablo Cawen - 11/06/2008
	public static void closeConn(ResultSet rs, PreparedStatement pstmt, Connection conn){
		try
		{
			if(rs!=null) rs.close();					
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		catch(Exception e)
		{
//			log.error("Exception en consultas SQL",e);
			e.printStackTrace();
		}
	}
//	-Fin Pablo Cawen - 11/06/2008

	//Elimina blancos, tab y enter al ppio, adentro y al final del String
	public static String eliminaEspacios(String s1) {
		String[] arrString = s1.split("[ \t\n]+");
		String resultado = "";
		//log.debug(">>>>>>>>>>>Inicio eliminaEspacios");
		//log.debug(">>>>>>>>>>>String Original:" + s1 + "<<<<<<<<<<<<");		
		for (int i=0; i <arrString.length; i++) {
			//log.debug(">>>>>>>" + arrString[i] + "<<<<<<<<<<<<");
			resultado = resultado + arrString[i];
		}		
		//log.debug(">>>>>>>String Resulatante:" + resultado + "<<<<<<<<<<<<");
		//log.debug(">>>>>>>>>>>Fin eliminaEspacios");
		
		return resultado;
	}
	// Fin eliminaBlancos	
}
