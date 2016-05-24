/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR606S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.ConfigurarSxRxIMSLocal;
import co.com.telefonica.atiempo.vpistbba.serviciosba.ejb.ConfigurarSxRxIMSLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfigurarSxRxIMSDelegate implements ConfigurarSxRxIMSInterface{
	
	
	private ConfigurarSxRxIMSLocal servicios;
	public ConfigurarSxRxIMSDelegate() throws ATiempoAppEx {
		
		try {
			
		try {
			servicios = ((ConfigurarSxRxIMSLocalHome) HomeFactory.getHome(ConfigurarSxRxIMSLocalHome.JNDI_NAME)).create();
		} catch (CreateException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		}
		
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} 
	}


	
	//REQ BA NAKED @Dcardenas
	public void configurarSxRxIMS(ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.configurarSxRxIMS(act);
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#respuestaDisponibilidadAgendaSC(co.com.telefonica.atiempo.interfaces.atiempo.TR703S)
	 */
	public void respuestaConfigurarSxRxIMS(TR606S tr606s) throws ATiempoAppEx {
		servicios.respuestaConfigurarSxRxIMS(tr606s);
		
	}
	//Fin req BA NAked
}
