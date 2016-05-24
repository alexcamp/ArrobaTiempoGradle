/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import com.telefonica_chile.comun.ComunInterfaces;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface LocalidadAPSCServiciosInterfaz extends ComunInterfaces {
	
//	REQ BA NAKED @Dcardenas
	public void enviarAsignacionRecursos (ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx;
	public void recibirAsignacionRecursos(TR010S tr010s) throws ATiempoAppEx;
	
	public void enviarCambioNumero (ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx;
	public void recibirAsignacionRecursos (TR012S tr012s) throws ATiempoAppEx;
//Fin req BA NAked
	
}
