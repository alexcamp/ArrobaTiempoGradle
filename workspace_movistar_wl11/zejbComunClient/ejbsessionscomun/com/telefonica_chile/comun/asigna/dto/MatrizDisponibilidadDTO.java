/*
 * Created on Mar 3, 2005
 * 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.asigna.dto;

import java.util.HashMap;
import java.util.Vector;


public class MatrizDisponibilidadDTO {
	
	
	HashMap matrizOcupado = null;
	HashMap matrizTotal = null;
	private Vector listaRango = new Vector();
	private Vector listaNombreRango = new Vector();
	private Vector listaFechas = new Vector();
	
	public MatrizDisponibilidadDTO() {
		matrizOcupado = new HashMap();	
		matrizTotal = new HashMap();
	}

	/**
	 * @return
	 */
	public String getValueOcupado(String rango, String fecha) {
			
		if (matrizOcupado.get(rango + "_" + fecha) == null)
			return "";
		
		Integer aux = (Integer) matrizOcupado.get(rango + "_" + fecha); 
		return aux.intValue()+"";
	}

	/**
	 * @return
	 */
	public String getValueTotal(String rango, String fecha) {
		if (matrizTotal.get(rango + "_" + fecha) == null)
			return "";
		Integer aux = (Integer) matrizTotal.get(rango + "_" + fecha); 
		return aux.intValue()+"";
	}

	/**
	 * @param integers
	 */
	public void setValueOcupado(Long rango, String fecha, Integer valueOcupado) {
		String key = rango + "_" + fecha;
		if(matrizOcupado.get(key) == null)
			matrizOcupado.put(key, valueOcupado);
		else{
			
			int valor = ((Integer) matrizOcupado.get(key)).intValue();
			int ocupado = valueOcupado == null ? 0 : valueOcupado.intValue(); 
			int nuevo = valor + ocupado;
			matrizOcupado.put(key, new Integer(nuevo));
		}
	}

	/**
	 * @param integers
	 */
	public void setValueTotal(Long rango, String fecha, Integer valueTotal) {
		String key = rango + "_" + fecha;
		if(matrizTotal.get(key) == null)
			matrizTotal.put(key, valueTotal);
		else{
			
			int valor = ((Integer) matrizTotal.get(key)).intValue();
			int ocupado = valueTotal == null ? 0 : valueTotal.intValue(); 
			int nuevo = valor + ocupado;
			matrizTotal.put(key, new Integer(nuevo));
		}
	
	}

	/**
	 * @param integers
	 */
	public int getDisponibilidad(String rango, String fecha) {
		String key = rango + "_" + fecha;

		Integer tt = (Integer) matrizTotal.get(key);
		Integer oc = (Integer) matrizOcupado.get(key);
		
		if (tt == null || tt.intValue()==0) {
			return -1;
		}

		if (oc == null) {
			return -1;
		}

		int m = tt.intValue();
		int n = oc.intValue();
		
		double aux = ( ((double)n * (double)100 )/ (double) m);
		
		int p = (int) (aux * 100);
		
		return p;
	}

	/**
	 * @return
	 */
	public void addRango(String str) {
		listaRango.add(str); 
	}

	/**
	 * @return
	 */
	public void addNombreRango(String str) {
		listaNombreRango.add(str); 
	}

	/**
	 * @return
	 */
	public void addFecha(String str) {
		listaFechas.add(str); 
	}

	/**
	 * @return
	 */
	public String getRango(int i) {
		if (i<0 || i>=listaRango.size())
			return "";
		return ( (String) listaRango.get(i) ); 
	}

	/**
	 * @return
	 */
	public String getNombreRango(int i) {
		if (i<0 || i>=listaNombreRango.size())
			return "";
		return ( (String) listaNombreRango.get(i) ); 
	}

	/**
	 * @return
	 */
	public String getFecha(int i) {
		if (i<0 || i>=listaFechas.size())
			return "";
		return ( (String) listaFechas.get(i) ); 
	}

	/**
	 * @return
	 */
	public int getSizeRango() {
		return ( listaRango.size() ); 
	}

	/**
	 * @return
	 */
	public int getSizeFechas() {
		return ( listaFechas.size() ); 
	}

}
