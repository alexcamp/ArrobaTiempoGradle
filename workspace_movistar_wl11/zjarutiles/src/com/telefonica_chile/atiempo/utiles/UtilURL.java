/*
 * Created on Aug 18, 2005
 *
 */
package com.telefonica_chile.atiempo.utiles;

import java.util.StringTokenizer;

/**
 * @author rigarce
 *
 */
public class UtilURL {
	/**
	 * Método que pasado una url extrae el folio.
	 * @param url
	 * @return java.lang.String
	 * @author rigarce
	 */
	public static String getFolioReclamo(String url){
		String[] datos = getDatosURL(url);
						
		StringTokenizer tokenURL = new StringTokenizer( datos[0], "?" );
		tokenURL.nextToken();
		String datoFolio = tokenURL.nextToken();
	
		StringTokenizer tokenFolio = new StringTokenizer( datos[0], "=" );
		tokenFolio.nextToken();
	
		return  tokenFolio.nextToken();
	}
	/**
	 * Método que pasado una url devuelve 
	 * un arreglo de string con las partes
	 * de la url separadas.
	 * @param URL
	 * @return String[]
	 * @author rigarce
	 */
	public static String[] getDatosURL(String url){
		int i=0;
		StringTokenizer tokens = new StringTokenizer(url, "&");
		String[] elementos = new String[10];
		 
		while( tokens.hasMoreTokens() ){
			elementos[i] = tokens.nextToken();
			i++;
		}
		return (elementos);
	}



}
