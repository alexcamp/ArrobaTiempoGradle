/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.instalacion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR803S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.instalacion.ejb.TOAServicioLocal;
import co.com.telefonica.atiempo.vpistbba.instalacion.ejb.TOAServicioLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class TOADelegate implements TOAInterfaces {

	private TOAServicioLocal servicios;

	public TOADelegate() throws ATiempoAppEx {
		try {
			servicios = ((TOAServicioLocalHome) HomeFactory
					.getHome(TOAServicioLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#crearActuacionTOA(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void crearActuacionTOA(ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.crearActuacionTOA(act);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionCrearActuacionTOA()
	 */
	public void recepcionCrearActuacionTOA(String respuesta, ActividadEJBDTO act, String idActuacion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionCrearActuacionTOA()
	 */
	public void recepcionActivarDecosTarjetasTOA(TR801S tr801s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.recepcionActivarDecosTarjetasTOA(tr801s);
	}
	
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.TOAInterfaces#recepcionCrearActuacionTOA()
	 */
	public void recepcionActivarModemTOA(TR802S tr802s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.recepcionActivarModemTOA(tr802s);
	}

	/**
	 * @param string
	 * @param string2
	 */
	public void enviaActivarDecosTarjetasTOA(String idActuacion, String idMensajePeticion)throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviaActivarDecosTarjetasTOA(idActuacion, idMensajePeticion);
	}
	
	/**
	 * @param string
	 * @param string2
	 */
	public void enviaActivarModemTOA(String idActuacion, String idMensajePeticion)throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviaActivarModemTOA(idActuacion, idMensajePeticion);
	}
	public void recepcionCierreActuacionTOA(TR811S tr811s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
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
	
	
}