/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author @dcardenas
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class LocalidadSirsDelegate implements LocalidadSirsInterfaz{
	//REQ BA NAKED @Dcardenas
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.LocalidadSirsInterfaz#enviarAsignacionRecursos(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarAsignacionRecursos(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {	
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.LocalidadSirsInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR510S)
	 */
	public void recibirAsignacionRecursos(TR510S tr510s) throws ATiempoAppEx {		
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void recibirAsignacionRecursos(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadSirsInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR512S)
	 */
	public void recibirAsignacionRecursos(TR512S tr512s) throws ATiempoAppEx {
	}
	
	//Fin req BA NAked
}
