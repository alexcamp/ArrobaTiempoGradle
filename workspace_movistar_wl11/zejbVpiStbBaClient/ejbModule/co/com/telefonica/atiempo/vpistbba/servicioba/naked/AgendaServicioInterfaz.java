/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import java.sql.Timestamp;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface AgendaServicioInterfaz extends ComunInterfaces {
	
//	REQ BA NAKED @Dcardenas
	public void enviarCreacionActuacion (Long idPeticion, Timestamp fechaReagendamiento, String tipoOC, String codActividad, ActividadEJBDTO act) throws ATiempoAppEx;
	public void recibirAutoConfiguracion  (TR701S tr701s) throws ATiempoAppEx;
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701S) throws ATiempoAppEx;
	public void recepcionCierreActuacion(TR711S tr711s) throws ATiempoAppEx;
	public void setearDatosActividad(ActividadEJBDTO actDto) throws ATiempoAppEx;
//Fin req BA NAked
	
}
