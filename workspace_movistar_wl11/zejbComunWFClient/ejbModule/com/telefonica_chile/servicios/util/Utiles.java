/*
 * Created on 13-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.servicios.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;


//import com.tecnonautica.ondemand.ODParser;


public class Utiles {

	// String out = generaPassword()
	private static HashMap dateFormat = new HashMap();

	static {
		dateFormat.put("dd/MM/yyyy", new SimpleDateFormat("dd/MM/yyyy"));
		dateFormat.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String generaPassword() {
		int i;
		String out = String.valueOf((new java.util.Random()).nextInt(999999));

		while (out.length() < 6) {
			out += "7";
		}
		return out;
	}

	/*
	    public static String getCrypted(String plain) {
	
			// Por ahora no encripta
			if (true)
				return plain;
	
	        MD5 digest = new MD5();
	        digest.init();
	        digest.updateASCII(plain);
	        digest.finish();
	        return digest.toString();
	    }
	*/

	// Asume que la fecha viene de mbroker???...  ddmmaaaa
	public static String mbFormatFecha(String mbFecha) {
		if (mbFecha == null || mbFecha.length() != 8)
			return mbFecha;

		return mbFecha.substring(0, 2)
			+ "/"
			+ mbFecha.substring(2, 4)
			+ "/"
			+ mbFecha.substring(4, 8);
	}

	// Asume que la fecha viene de mbroker...  aaaammdd
	public static String mbFormatFechaY(String mbFecha) {
		if (mbFecha == null || mbFecha.length() != 8)
			return mbFecha;

		return mbFecha.substring(6, 8)
			+ "/"
			+ mbFecha.substring(4, 6)
			+ "/"
			+ mbFecha.substring(0, 4);
	}

	// Asume que la fecha viene de la base de datos...  aaaa-mm-dd    
	public static String bdFormatFecha(String bdFecha) {

		if (bdFecha == null || bdFecha.length() != 10)
			return bdFecha;

		return bdFecha.substring(8, 10)
			+ "/"
			+ bdFecha.substring(5, 7)
			+ "/"
			+ bdFecha.substring(0, 4);
	}

	public static String chopNull(String que, String cNull) {
		int i = 0;
		int largo = que.length();
		while (i < largo - 1 && que.substring(i, i + 1).equals(cNull))
			i++;
		return que.substring(i, largo);
	}

	public static String mbFormatValor(String mbValor) {
		if (mbValor == null || mbValor.length()<2)
			mbValor = "00";
		int largo = mbValor.length();
		String parteEntera = chopNull(mbValor.substring(0, largo-2), "0");
		if(parteEntera == null || parteEntera.equals(""))
			parteEntera = "0";
		return  parteEntera+ "," + mbValor.substring(largo-2, largo);
	}

	public static String rutFormatted(String rut, String dv) {
		String out = "";

		int len = rut.length();
		if (len < 7) {
			out = rut;
		} else {
			String tmp = "";
			tmp = rut.substring(len - 3, len);
			out = tmp;
			tmp = rut.substring(len - 6, len - 3);
			out = tmp + "." + out;
			tmp = rut.substring(0, len - 6);
			out = tmp + "." + out;
		}

		if (dv != null && dv.length() == 1)
			out += "-" + dv;

		return out;
	}

	public static String leftTrim(String str, char c) {
		int i = 0;
		int len = str.length();
		while (i < len && str.charAt(i) == c)
			i++;
		if (i >= len)
			return "";
			
		return str.substring(i, len);
	}

	public static String rightTrim(String str, char c) {
		int i = str.length() - 1;

		while (i >= 0 && str.charAt(i) == c)
			i--;

		if (i < 0)
			return "";

		return str.substring(0, i + 1);
	}

	public static String[] join(String[] data1, String[] data2) {
		return join(data1, data2, 0, data2.length);
	}

	public static String[] join(String[] data1,	String[] data2,	int begIdx,	int endIdx) {
		java.util.ArrayList arrays = new java.util.ArrayList(java.util.Arrays.asList(data1));
		arrays.addAll(new java.util.ArrayList(java.util.Arrays.asList(data2).subList(begIdx, endIdx)));
		return (String[]) arrays.toArray(new String[] { "" });
	}

	public static String[] subArray(String[] d, int beg, int end) {
		java.util.ArrayList al = new java.util.ArrayList(java.util.Arrays.asList(d).subList(beg, end));

		return (String[]) al.toArray(new String[] { "" });
	}

	public static String escapaBD(String dato) {
		String x = utilReplace(dato, "'", "''");
		return (x);
	}

	public static String utilReplace(String str, String que, String por) {
		StringTokenizer st = new StringTokenizer(str, que, true);
		String nuevo = "";
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			if (tok.equals(que))
				nuevo += por;
			else
				nuevo += tok;
		}
		if (nuevo.length() == 0)
			nuevo = str;

		
		return (nuevo);
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

	public static String filled(String str, int hasta, String c, int side) {
		int i;
		if (side == 1) { // Agregar 'c' a la derecha.
			for (i = str.length(); i < hasta; i++)
				str = str + c;
		} else { // Agregamos a la izquierda.
			for (i = str.length(); i < hasta; i++)
				str = c + str;
		}

		return (str);
	}
	
	public static void printDateTime() {
		//System.out.println("DATETIME: " + getDateTime());
	}

	public static String getDateTime() {
		Calendar cal = Calendar.getInstance();
		String time = ""+ cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+":"+cal.get(Calendar.MILLISECOND);

		return time;
	}

	public static String mbFormatHora(String hh, String mm, String ss) {
		if ( ss == null)
			return hh + ":" + mm;
		
		return hh + ":" + mm + ":" + ss;
	}

	public static String mbFormatSeg(String mm, String ss) {
		if ( ss == null)
			ss = "00";
		if ( mm == null)
			mm = "0";
		
		return mm + ":" + ss;
	}

	public static String formatNumber(String str, String c) {
		if ( str == null )
			return "0";
		
		String resto = "";
		int largo = str.length();
		String aux = str;
		while ( largo > 3 ) {
			resto =  c + aux.substring(largo-3, largo) + resto ;
			aux = aux.substring(0, largo-3);
			largo = aux.length();
		}
		
		resto = aux + resto;
		
		return (resto);
	}

	public static int sumaMontos(int suma, String str) {
		if ( str == null || str.length()==0 )
			str = "0";
		
		int valor = 0;
		try {
			valor = (int) Integer.parseInt( str );
		} catch (Exception e) {
			valor = 0;
		}
		
		return (suma + valor);
	}

	/**
	 * Crea un login de empresa a partir del rut de empresa y del rut del usuario
	 * 
	 * @param rutEmpresa El rut de la empresa (junto con el digito verificador)
	 * @param rutUsuario El rut del usuario (junto con el digito verificador)
	 * 
	 * @return El login de la empresa creado
	 */
	public static String getLoginEmpresa(String rutEmpresa, String rutUsuario) {
		if (rutEmpresa == null || rutUsuario == null)
			return null;
			
		String[] emp = split(rutEmpresa, '-');
		String[] usu = split(rutUsuario, '-');

		String login = rutEmpresa + "|" + rutUsuario;

		if (emp != null && usu != null)
			login = emp[0] + "|" + usu[0];
		
		return login;
	}

	public static String getFecha(String sep) {
		Calendar cal = Calendar.getInstance();
		String dd = filled( ""+cal.get(Calendar.DAY_OF_MONTH), 2, "0", 0);
		String mm = filled( ""+ (cal.get(Calendar.MONTH)+1) , 2, "0", 0);
		String yy = filled( ""+cal.get(Calendar.YEAR), 2, "0", 0);

		return (yy + sep + mm + sep + dd);
	}

	public static String getAnoMes(String sep) {
		Calendar cal = Calendar.getInstance();
		String mm = filled( ""+ (cal.get(Calendar.MONTH)+1) , 2, "0", 0);
		String yy = filled( ""+cal.get(Calendar.YEAR), 2, "0", 0);

		return (yy + sep + mm);
	}

	public static int compareFecha( String f1, String f2 ) {
		if (f1==null && f2==null)
			return 0;
		if (f1==null)
			return -1;
		if (f2==null)
			return 1;
		
		String[] fec1 = split(f1, '/');
		String[] fec2 = split(f2, '/');
		
		if ( fec1 != null && fec1.length==3) {
			int a = fec1[2].compareTo( fec2[2] );
			int m = fec1[1].compareTo( fec2[1] );
			int d = fec1[0].compareTo( fec2[0] );
			
			if (a != 0)
			  return a;
			if (m != 0)
			  return m;

			return d;
		}
		
		return -1;
	}

	public static String[] formatFono( String fono ) {
		if (fono == null || fono.length() < 2)
			return null;

		int j = 1;
		char c = fono.charAt(0);
		if (c != '2')
			j = 2;
		
		String[] aux = new String[2];
		aux[0] = fono.substring(0, j);
		aux[1] = fono.substring(j, fono.length());
		
		return (aux);

	}

	public static Hashtable leeConfig(String fName, String patron) {
		BufferedReader br;
		String line, key, value, cab;
		int pos;
		boolean flag_ok = false;

		Hashtable config = new Hashtable();
		try {
			br = new BufferedReader( new FileReader( fName ) );

			line = "x";
			while( (line=br.readLine()) != null ) {
				//line = br.readLine();
				
				if (line==null || line.length()<1)
					continue;

				if (line.charAt(0) == '[') { // La linea es encabezado.
					cab = line.substring(1, line.length()-1);
					
					if ("DEFAULT".equals(cab) || cab.equals(patron) )
						flag_ok = true;
					else
						flag_ok = false;
					continue;
				}
				if ( !flag_ok )
					continue;

				String[] aux1 = split(line, '#');
				if (aux1 == null)
					continue;
				
				for (int i=0; i<aux1.length; i++) {
					String[] aux = split(aux1[i], '=');
					if (aux == null || aux.length<2)
						continue;
				
					key = aux[0];
					value = aux[1];
				
					
					if (value==null)
						value = "";
					if (key != null && key.length()>0)
						config.put( key, value );
				}
			}
			br.close();
		}
		catch( Exception e ) {
			e.printStackTrace( System.err );
		}
		
		return (config);

	}

//	public static Hashtable leeFolders(String fName, String idServ) {
//		Hashtable folders = null;
//		if (idServ==null)
//			return folders;
//		ODParser p = new ODParser();
//		try {
//			p.process(fName);
//			folders = p.getSalida();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		
//		return (folders);
//	}
	
	public static int getValor(String val) {
		try {
			return (Integer.parseInt(val));
		} catch(Exception e) {
		}
		
		return 0;
	}
	
	public static String sacaNull( String val, String def) {
		if ( val == null )
			return def;
		return val;
	}

	public static String getFecha(String sep, int digitos) {
		Calendar cal = Calendar.getInstance();
		String dd = filled( ""+cal.get(Calendar.DAY_OF_MONTH), 2, "0", 0);
		String mm = filled( ""+ (cal.get(Calendar.MONTH)+1) , 2, "0", 0);
		String yy = filled( ""+cal.get(Calendar.YEAR), digitos, "0", 0);

		return (yy + sep + mm + sep + dd);
	}

	public static String formatDate(String fecha, String format1, String format2) {
		if (fecha==null || fecha.length()==0)
			return "";

		SimpleDateFormat sdfOrig = (SimpleDateFormat) dateFormat.get(format1);
		SimpleDateFormat sdfDest = (SimpleDateFormat) dateFormat.get(format2);

		if (sdfOrig==null || sdfDest==null)
			return fecha;

		try {
			fecha = sdfDest.format(sdfOrig.parse(fecha, new ParsePosition(0)));
		} catch (Exception e) {
			//fecha = fecha;// TODO: REVISAR SI ESTA LINEA UN ERROR
		}

		return (fecha);
	}

	/*
	 * Elimina los Caracteres Invalidos 
	 */
	public static String limpiaString(String entrada) {
		if (entrada == null || entrada.length()==0)
			return "";
		byte[] b = entrada.getBytes();
		byte[] bAux = new byte[ b.length ];
		
		byte[] bExt = new byte[5];
		bExt[0] = 0;
		bExt[1] = 10;
		bExt[2] = 13;
		bExt[3] = 9;
		bExt[4] = 38; // &

		int j = 0;
		boolean err = false;
		for (int i=0; i<b.length; i++ ) {
			err = false;
			for (int k=0; k<bExt.length; k++) {
				if (bExt[k] == b[i]) {
					err = true;
					break;
				}
			}
			if ( !err )
				bAux[j++] = b[i];
		}
		if ( j==0 )
			return "";

		String str = new String( bAux, 0, j );

		return str;
	}
}
