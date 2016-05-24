/*
 * Created on Aug 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.singleton;

/**
 * @author mrcanal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author mrcanal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InicioSubflujo {   	

private static InicioSubflujo m_SingletonInstance = null;

private Map datos = new HashMap();

/**
 * Obtencion de logger.
 * @author amartoq
 */
protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	private InicioSubflujo() {
		ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getPackage().getName() + ".subflujos");
		for (Enumeration e = rb.getKeys(); e.hasMoreElements();) {
			String element = (String) e.nextElement();
			datos.put(element, rb.getString(element));
		}
	}
	
	public static InicioSubflujo getInstance(){
		if(m_SingletonInstance == null)
			m_SingletonInstance = new InicioSubflujo();
			
		return m_SingletonInstance;
	}
	
	public String getDato(String username){
		return (String) datos.get(username); 
	}
	
	public Map getDatos() {
		return datos;
	}

}
