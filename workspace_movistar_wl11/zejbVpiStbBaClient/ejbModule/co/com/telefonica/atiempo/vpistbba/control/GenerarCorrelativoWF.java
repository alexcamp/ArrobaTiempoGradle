/*
 * Creado el 7/07/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.control;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarCorrelativoWF {

	/**
	 * @param args
	 */
private Random random =null;
	private char[] buf;
	private final static char[] symbols = new char[52];

static{
		for (int idx=0;idx < 26 ;idx++)
		{
			symbols[idx]=(char)('a'+idx);
		}
		for (int idx=26;idx < 52 ;idx++)
		{
			symbols[idx]=(char)('A'+idx-26);
		}
	}
			
	
	public String RandomString(int length){
	if (length < 1 )
	{
	throw new  IllegalArgumentException ("length < 1:" + length);
	}
	buf = new char[length];
	random=new Random (Calendar.getInstance().getTimeInMillis());
	return nextString();
	
	}
	
	public String nextString(){
		for (int idx=0;idx < buf.length;idx++)
		{
			buf[idx]= symbols[random.nextInt(symbols.length)];
		}
		return new String(buf); 	
	}
}
