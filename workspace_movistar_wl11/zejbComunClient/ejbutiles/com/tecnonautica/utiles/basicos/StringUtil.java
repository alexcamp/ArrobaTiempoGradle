package com.tecnonautica.utiles.basicos;


import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtil {
	
	public static String[] split(String string, char delim) {
		StringTokenizer st = new StringTokenizer(string, String.valueOf(delim));
		int n = st.countTokens();
		if (n == 0)
			return null;
		
		String[] arr = new String[n];
		for (int i = 0; st.hasMoreTokens(); ) {
			arr[i++] = st.nextToken();
		}
		return arr;
	}
	
	public static String join(String delim, String[] lista) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; lista != null && i < lista.length; i++) {
			sb.append(lista[i]);
			if (i < lista.length - 1)
				sb.append(delim);
		}
		return sb.toString();
	}
	
	public static String escapaBD(String str) {
		if (str == null || str.indexOf("'") < 0)
			return str;
		return join("'||''''||'", split(str, '\''));
	}

	public static int parseInt( String value ) {
		int valor = 0;
		try {
			valor = Integer.parseInt(value);
		} catch (Exception e) {
			valor = 0;
		}

		return valor;
	}

	// Pasa de String a Int.
	public static int[] parseInt( String[] values ) {
		if ( values == null )
			return null;

		int[] intVal = new int[ values.length ];
		
		for (int i=0; i<values.length; i++) {
			try {
				intVal[i] = Integer.parseInt( values[i] );
			} catch (Exception e) {
				intVal[i] = 0;
			}
		}
		
		return (intVal);
	}

	public static String toBDSublista(ArrayList lista, boolean esVarchar) {
		return toBDSublista((String[]) lista.toArray(new String[] {""}), esVarchar);
	}
	
	public static String toBDSublista(Set lista) {
		return toBDSublista(lista, false);
	}

	public static String toBDSublista(Set lista, boolean esVarchar) {
		return toBDSublista((String[]) lista.toArray(new String[] {""}), esVarchar);
	}

	public static String toBDSublista(Vector vLista) {
		return toBDSublista(vLista, false);
	}
	public static String toBDSublista(Vector vLista, boolean esVarchar) {
		ArrayList lista = new ArrayList();
		for (int i = 0; vLista != null && i < vLista.size(); i++)
			lista.add(vLista.get(i));

		return toBDSublista((String[]) lista.toArray(new String[] {""}), esVarchar);
	}


	public static String toBDSublista(String[] lista) {
		return toBDSublista(lista, false);
	}
	/**
	 * [1][2][4] ==> "1, 2, 4"
	 * 
	 * si 'esVarchar': 
	 *  [1][2][4] ==> "'1', '2', '4'"
	 */
	public static String toBDSublista(String[] lista, boolean esVarchar) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lista.length; i++) {

			if (esVarchar) sb.append("'");
			sb.append(lista[i]);
			if (esVarchar) sb.append("'");

			if (i < lista.length - 1)
				sb.append(",");
		}

		return sb.toString();
	}
	
	public static String sinNull(String s){
		return (s == null) ? "" : s;
	}
	
	/**
	 * 
	 * @author M.Alarcon
	 *
	 * Método que reemplaza a "replaceAll()" de la clase String, por asuntos de compatibilidad (entre java 1.3 y 1.4)
	 * @param s String de entrada al cual se va a aplicar ReplaceAll
	 * @param antiguo cadena antigua a reemplazar dentro de "s"
	 * @param nuevo cadena nueva que reemplazará todas las ocurrencias de "antiguo" dentro de "s"
	 * @return una nueva cadena con todas las ocurrencias de "antiguo" reemplazadas por "nuevo"
	 */
	public static String replaceAll(String s, String antiguo, String nuevo) {
		StringBuffer st = new StringBuffer();
		int ini = 0;
		if (s.indexOf(antiguo, ini) == -1) return s;
		while (s.indexOf(antiguo, ini) != -1) {
			st.append(s.substring(ini, s.indexOf(antiguo, ini)));
			ini = s.indexOf(antiguo, ini) + antiguo.length();
			st.append(nuevo);
		}
		if (ini < s.length()) {
			st.append(s.substring(ini));
		}
		return st.toString();
	}
	
	/**
	 * @author M.Alarcon
	 * 
	 * Método que trunca una cadena
	 * @param s cadena a truncar
	 * @param largo largo máximo que puede tener la cadena s
	 * @return una nueva cadena que no supera el largo estipulado
	 */
	public static String truncate(String s, int largo) {
		if (s != null) if (s.length() > largo) s = s.substring(0, largo);
		return s;
	}
	
	/**
	 * @author M.Alarcon
	 * 
	 * Método que trunca una cadena a partir del final, trunca la parte inicial
	 * @param s cadena a truncar
	 * @param largo largo máximo que puede tener la cadena s
	 * @return una nueva cadena que no supera el largo estipulado
	 */
	public static String truncateBack(String s, int largo) {
		if (s.length() > largo) s = s.substring(s.length() - largo, s.length());
		return s;
	}
	
	public static String toString(Object obj) {
		if (obj != null) return obj.toString();
		return "";
	}
	
	public static String desplegarArreglos(String[] str) {
		String cadena = null;
		for(int i=0;i<str.length;i++)
			cadena = cadena + str[i] + ", ";
		return cadena.substring(0, cadena.length() - 2);
	}
}
