package com.tecnonautica.utiles.basicos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class FechaUtil {
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(FechaUtil.class);
	
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d);
		System.out.println(agregaMinutosRelativo(d, 10));
		System.out.println(agregaMinutosRelativo(d, -70));
		System.out.println(nuevaFecha(2003, 11, 2, 3, 12));
		System.out.println(agregaSegundosRelativo(d, 40));
	}
	public static Date agregaMinutosRelativo(int minutos) {
		return agregaMinutosRelativo(Calendar.getInstance().getTime(), minutos);
	}

	public static Date agregaMinutosRelativo(Date base, int minutos) {
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.add(Calendar.MINUTE, minutos);
		return c.getTime();
	}

	public static Date agregaSegundosRelativo(Date base, int segundos) {
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.add(Calendar.SECOND, segundos);
		return c.getTime();
	}
	
	public static Date nuevaFecha(int anno, int mes, int dia, int hora, int minutos) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, anno);
		c.set(Calendar.MONTH, mes - 1);
		c.set(Calendar.DATE, dia);
		c.set(Calendar.HOUR_OF_DAY, hora);
		c.set(Calendar.MINUTE, minutos);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND,00);
		return c.getTime();
	}

	public static Date nuevaFecha(String yy, String mm, String dd, String hora, String minutos) {
		return nuevaFecha(
			Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd),
			Integer.parseInt(hora), Integer.parseInt(minutos));
	}

	public static String formatoFecha(Date fecha) {
		return formatoFecha(fecha, "--");
	}
	public static String formatoFecha(Date fecha, String porDefecto) {
		if ( fecha == null )
			return porDefecto;

		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TimeZone tz = TimeZone.getTimeZone("America/Santiago");
		formatoFecha.setTimeZone(tz);

		String salida = porDefecto;
		try {
			salida = formatoFecha.format(fecha);
		} catch (Exception e) {
			log.warn("No se pudo parsear fecha " + fecha + ": " + e);
		}
		return salida;
	}

	public static Date stringADateTime(String dateTime) {
		if ( dateTime == null )
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.00");
		java.util.Date indate = null;
		try {
			indate = new java.util.Date(sdf.parse(dateTime).getTime());
		} catch (Exception e) {
			log.warn("No se pudo parsear fecha " + dateTime + ": " + e);
		}
		
		return indate;
	}
	
	public static Date stringAFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date indate = null;
		try {
			indate = new java.util.Date(sdf.parse(fecha).getTime());
		} catch (Exception e) {
			log.warn("No se pudo parsear fecha " + fecha + ": " + e);
		}
		
		return indate;
	}
	
	public static Date agregaMinutosFecha(Date base, int minutos) {
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.add(Calendar.MINUTE, minutos);
		return c.getTime();
	}
	
	public static Date agregaSegundosFecha(Date base, int segundos) {
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.add(Calendar.SECOND, segundos);
		return c.getTime();
	}
	
	public static String formatoParaFecha(Date fecha, String porDefecto) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat(porDefecto);

	
		String salida = "0000";
		try {
			salida = formatoFecha.format(fecha);
		} catch (Exception e) {
			log.warn("No se pudo parsear fecha " + fecha + ": " + e);
		}
		return salida;
	}
	
	public static String fechaSantiago(Date fecha,String formato) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
		TimeZone tz = TimeZone.getTimeZone("America/Santiago"); 
		formatoFecha.setTimeZone(tz);
	
		String salida = "0000";
		try {
			salida = formatoFecha.format(fecha);
		} catch (Exception e) {
			log.warn("No se pudo parsear fecha " + fecha + ": " + e);
		}
		return salida;
	}
	
	public static double convierteHHMMaMM(String hhmmss) {
		double segundos = 0;
		double resultado = 0;
		int minutos = 0;
		int timetmp = 0;
		int hora = 0;
		timetmp = Integer.parseInt(hhmmss);
		hora =((timetmp/10000)*60);
		minutos = (timetmp%10000)/100;
		segundos = ((timetmp%100)/60);
   		resultado = hora + minutos + segundos;
		return resultado;
	}
	
	/**
	 * método que resta la fechaInicial a la fechaFinal, y calcula la diferencia entre ellas en dias, horas, minutos, segundos
	 * @param fechaFinal
	 * @param fechaInicial
	 * @return un arreglo de longs, comenzando por los días (0) y terminando por los milisegundos (4)
	 */
	public static long[] restarFechas(Date fechaFinal, Date fechaInicial) {
		long dias = 0;
		long horas = 0;
		long minutos = 0;
		long segundos = 0;
		long milis = fechaFinal.getTime() - fechaInicial.getTime();
		dias = milis / (24*60*60*1000);
		milis -= dias * (24*60*60*1000);
		horas = milis / (60*60*1000);
		milis -= horas * (60*60*1000);
		minutos = milis / (60*1000);
		milis -= minutos * (60*1000);
		segundos = milis / (1000);
		milis -= segundos * (1000);
		
		long arre[] = new long[5];
		arre[0] = dias;
		arre[1] = horas;
		arre[2] = minutos;
		arre[3] = segundos;
		arre[4] = milis;
		
		return arre;
	}
}
