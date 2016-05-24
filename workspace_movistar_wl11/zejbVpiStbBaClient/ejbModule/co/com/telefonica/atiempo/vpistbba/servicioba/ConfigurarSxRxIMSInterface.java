/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba;

import com.telefonica_chile.comun.ComunInterfaces;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR606S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface ConfigurarSxRxIMSInterface extends ComunInterfaces {
	
//	REQ BA NAKED @Dcardenas
	public void configurarSxRxIMS(ActividadEJBDTO act) throws ATiempoAppEx;
		

	public void respuestaConfigurarSxRxIMS(TR606S tr606s) throws ATiempoAppEx;
	//Fin req BA NAked
	
}
