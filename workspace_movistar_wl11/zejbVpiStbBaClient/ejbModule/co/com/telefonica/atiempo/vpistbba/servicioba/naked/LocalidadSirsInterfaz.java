/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import com.telefonica_chile.comun.ComunInterfaces;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface LocalidadSirsInterfaz extends ComunInterfaces {
	
//	REQ BA NAKED @Dcardenas
	public void enviarAsignacionRecursos(ActividadEJBDTO act,Long petiNumero,String nombreActividad,Integer actiID
			) throws ATiempoAppEx;
		

	public void recibirAsignacionRecursos(TR510S tr510s) throws ATiempoAppEx;
	
	public void recibirAsignacionRecursos(ActividadEJBDTO act,Long petiNumero, String nombreActividad,Integer actiID) throws ATiempoAppEx;
	
	public void recibirAsignacionRecursos(TR512S tr512s) throws ATiempoAppEx; 
	//Fin req BA NAked
	
	
	
}
