/*
 * Creado el 22/05/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.soltec.servicios.TOA;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR803S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface ServicioTOASTInterfaz extends ComunInterfaces {

	public void crearActuacionTOA(Long idActuacion,ActividadEJBDTO act) throws ATiempoAppEx;
	
	public String actividadActual(Long idActuacion);
	
	public void recepcionActivarDecosTarjetasTOA(TR801S tr801s) throws ATiempoAppEx;
	
	public void recepcionActivarModemTOA(TR802S tr802s) throws ATiempoAppEx;
	
	public void enviaActivarDecosTarjetasTOA(String idActuacion, String idMensajePeticion) throws ATiempoAppEx;
	
	public void enviaActivarModemTOA(String idActuacion, String idMensajePeticion, TR135S tr135s) throws ATiempoAppEx;
	
	//REQ TOA FASE III dcardena
	public void recepcionCierreActuacionTOA(TR811S tr811s);
	
	public void refrescarRecursosSTB(TR803S tr803s)  throws ATiempoAppEx;
	
	public void recepcionActualizaInventarioBA(TR804S tr804s)throws ATiempoAppEx;
	
	public void recibeActualizacionInventarioTV (TR805E tr805e) throws ATiempoAppEx;
	
	public void enviaRefrescarRecursosTV(TR805S tr805s) throws ATiempoAppEx;
	
	public void enviaRefrescarRecursosSTB(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx;
	
	public void enviaRefrescarRecursosBA(Recursos_baLocal recursos_baLocal) throws ATiempoAppEx;
	//FIN REQ TOA III
	public boolean esLocalidadToa (Long idpeticion);
	// BA NAKED FASE II dacrdena
	public ActividadEJBDTO recuperaActividadDeBandejaIntegrada(Peticion_stLocal peticion_stLocal)throws ATiempoAppEx;
	
	public void setearDatosActividad(ActividadEJBDTO actDto)throws ATiempoAppEx;
}
