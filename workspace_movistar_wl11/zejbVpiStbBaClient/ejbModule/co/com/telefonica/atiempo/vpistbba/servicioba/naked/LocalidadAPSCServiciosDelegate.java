/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author @dcardenas
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class LocalidadAPSCServiciosDelegate implements LocalidadAPSCServiciosInterfaz{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadAPSCServiciosInterfaz#enviarAsignacionRecursos(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarAsignacionRecursos(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadAPSCServiciosInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR010S)
	 */
	public void recibirAsignacionRecursos(TR010S tr5010s) throws ATiempoAppEx {
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadAPSCServiciosInterfaz#enviarCambioNumero(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarCambioNumero(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.LocalidadAPSCServiciosInterfaz#recibirAsignacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR012S)
	 */
	public void recibirAsignacionRecursos(TR012S tr012s) throws ATiempoAppEx {	
	}


//	REQ BA NAKED @Dcardenas
	
}
