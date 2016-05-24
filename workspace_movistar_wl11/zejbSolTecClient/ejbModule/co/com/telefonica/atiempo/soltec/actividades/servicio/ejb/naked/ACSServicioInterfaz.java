/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.soltec.actividades.servicio.ejb.naked;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.soltec.dto.ModemSTDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface ACSServicioInterfaz extends ComunInterfaces {
	
	
//	REQ BA NAKED @Dcardenas
	//Version enviarAutoConfiguracion Cesar Remigio
	public void enviarAutoConfiguracion (ModemSTDTO modem,ActividadEJBDTO act, Long petiNumero, boolean esAutoinstalacion) throws ATiempoAppEx;
	//Version enviarAutoConfiguracion Juan Grisales
	public void enviarAutoConfiguracion (ModemSTDTO modem,String act, String idMensajePadre) throws ATiempoAppEx;
	public void recibirAutoConfiguracion (TR135S tr135s) throws ATiempoAppEx;
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx;
	
	public void enviarCambiodProducto (ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx;
	public void recibirCambioProducto (TR135S tr135s) throws ATiempoAppEx;	
//Fin req BA NAked
	
}
