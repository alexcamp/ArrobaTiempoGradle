/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ACSServicioLocal;
import co.com.telefonica.atiempo.ejb.eb.ACSServicioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author @dcardenas
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class ACSServicioDelegate implements ACSServicioInterfaz{

	
	private ACSServicioLocal servicios;
	//private ACSSelectorServicio selectorServicio;

	public ACSServicioDelegate() throws ATiempoAppEx {
		try {
			
			servicios = ((ACSServicioLocalHome) HomeFactory.getHome(ACSServicioLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#enviarAutoConfiguracion(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarAutoConfiguracion(ModemVpiDTO modem,ActividadEJBDTO act, Long petiNumero, boolean esAutoinstalacion) throws ATiempoAppEx {
		servicios.enviarAutoConfiguracion(modem, act, petiNumero, esAutoinstalacion);
	}
	
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#enviarAutoConfiguracion(co.com.atiempo.dto.ModemVpiDTO, co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.String)
	 */
	public void enviarAutoConfiguracion(ModemVpiDTO modem, String act, String idMensajePadre) throws ATiempoAppEx {
		servicios.enviarAutoConfiguracion(modem, act,idMensajePadre);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#recibirAutoConfiguracion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirAutoConfiguracion(TR135S tr135s) throws ATiempoAppEx {
		servicios.recibirAutoConfiguracion(tr135s);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#enviarCambiodProducto(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarCambiodProducto(ActividadEJBDTO act, Long petiNumero, String nombreActividad, Integer actiID) throws ATiempoAppEx {	
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioInterfaz#recibirCambioProducto(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirCambioProducto(TR135S tr135s) throws ATiempoAppEx {
	}

	/**
	 * @param modemDTO
	 * @param actDto
	 * @param long1
	 * @param string
	 * @param integer
	 */
	public void eliminarModemACS(ModemVpiDTO modemDTO, ActividadEJBDTO actDto, Long petiNumero, String nombreActividad, Integer actiID) {
		// TODO Apéndice de método generado automáticamente
		
	}
	/**
	 * @param tr135s
	 * @param esCierreActuacion
	 * @param esBaja
	 * @throws ATiempoAppEx
	 */
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esBaja, boolean esCierreActuacion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.recibirConfiguracionModemAutoinstalacion(tr135s, esBaja,esCierreActuacion);
	}
	

//	REQ BA NAKED @Dcardenas
	
}
