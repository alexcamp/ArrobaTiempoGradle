/*
 * Created on 28-may-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.tecnonautica.mvc;

/**
 * @author jguridi
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
class Mapa {
	
	static final int ACCION = 1;
	static final int VISTA = 2;
	
	protected String resultado;
	protected String accion;
	protected String vista;
	
	
	String getDestino(){
		if (accion!=null && accion.length()>0){ 
			return accion;
		}else{ 
			return vista;
		}
	} 
	
	int getTipoDestino(){
			if (accion!=null && accion.length()>0){ 
				return ACCION;
			}else{ 
				return VISTA;
			}
		}
	
	
	public String getResultado() {
		return resultado;
	}

	
	/**
	 * @param string
	 */
	public void setAccion(String string) {
		accion = string;
	}

	/**
	 * @param string
	 */
	public void setResultado(String string) {
		resultado = string;
	}

	/**
	 * @param string
	 */
	public void setVista(String string) {
		vista = string;
	}

}
