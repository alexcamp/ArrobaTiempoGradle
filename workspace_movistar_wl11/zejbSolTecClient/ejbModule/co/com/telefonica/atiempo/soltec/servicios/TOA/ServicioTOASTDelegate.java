/*
 * Creado el 29/05/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.soltec.servicios.TOA;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR803S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.soltec.actividades.df.solucion.ASTServicioTOALocal;
import co.com.telefonica.atiempo.soltec.actividades.df.solucion.ASTServicioTOALocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ServicioTOASTDelegate implements ServicioTOASTInterfaz{

	private ASTServicioTOALocal servicios;

	public ServicioTOASTDelegate() throws ATiempoAppEx {
		try {
			servicios = ((ASTServicioTOALocalHome) HomeFactory.getHome(ASTServicioTOALocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioPuntosRedSTB(java.lang.String, java.lang.String)
	 */
	public void crearActuacionTOA(Long idActuacion,ActividadEJBDTO act) throws ATiempoAppEx {
	servicios.crearActuacionTOA(idActuacion,act);
	}
	
	public String actividadActual(Long idActuacion){
	return servicios.actividadActual(idActuacion);
	}
	
	public void recepcionActivarDecosTarjetasTOA(TR801S tr801s) throws ATiempoAppEx {
		servicios.recepcionActivarDecosTarjetasTOA(tr801s);
	}
	
	public void recepcionActivarModemTOA(TR802S tr802s) throws ATiempoAppEx {
		servicios.recepcionActivarModemTOA(tr802s);
	}
	
	public void enviaActivarDecosTarjetasTOA(String idActuacion, String idMensajePeticion) throws ATiempoAppEx{
		servicios.enviaActivarDecosTarjetasTOA(idActuacion, idMensajePeticion);
	}
	
	public void enviaActivarModemTOA(String idActuacion, String idMensajePeticion, TR135S tr135s) throws ATiempoAppEx{
		servicios.enviaActivarModemTOA(idActuacion, idMensajePeticion, tr135s);
	}
	//REQ TOA FASE III dcardena
	public void recepcionCierreActuacionTOA(TR811S tr811s) {
		servicios.recepcionCierreActuacionTOA(tr811s);
	}
	

	public void recibeActualizacionInventarioTV (TR805E tr805e) throws ATiempoAppEx {
		servicios.recibeActualizacionInventarioTV(tr805e);
	}
	
	public void enviaRefrescarRecursosTV(TR805S tr805s) throws ATiempoAppEx {
		servicios.enviaRefrescarRecursosTV(tr805s);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#refrescarRecursosSTB(co.com.telefonica.atiempo.interfaces.atiempo.TR803S)
	 */
	public void refrescarRecursosSTB(TR803S tr803s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.refrescarRecursosSTB(tr803s);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionActualizaInventarioBA(co.com.telefonica.atiempo.interfaces.atiempo.TR804S)
	 */
	public void recepcionActualizaInventarioBA(TR804S tr804s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.recepcionActualizaInventarioBA(tr804s);
	}
	
	public void enviaRefrescarRecursosSTB(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx{
		servicios.enviaRefrescarRecursosSTB(recursos_linea_basicaLocal);
	}
	
	public void enviaRefrescarRecursosBA(Recursos_baLocal recursos_baLocal) throws ATiempoAppEx{
		servicios.enviaRefrescarRecursosBA(recursos_baLocal);
	}
	//FIN REQ TOA III
	
	public boolean esLocalidadToa(Long idpeticion){
		return servicios.esLocalidadToa(idpeticion);
	}
	// BA NAKED FASE II dacrdena
	public ActividadEJBDTO recuperaActividadDeBandejaIntegrada(Peticion_stLocal peticion_stLocal)throws ATiempoAppEx{
		return servicios.recuperaActividadDeBandejaIntegrada(peticion_stLocal);
	}
	
	public void setearDatosActividad(ActividadEJBDTO actDto)throws ATiempoAppEx {
		servicios.setearDatosActividad(actDto);
	}
}