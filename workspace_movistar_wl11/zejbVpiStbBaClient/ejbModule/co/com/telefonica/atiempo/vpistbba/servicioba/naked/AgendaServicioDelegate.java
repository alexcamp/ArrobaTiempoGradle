/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import java.sql.Timestamp;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.AgendaServicioLocal;
import co.com.telefonica.atiempo.ejb.eb.AgendaServicioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author @dcardenas
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class AgendaServicioDelegate implements AgendaServicioInterfaz{
//	REQ BA NAKED @Dcardenas
	
	private AgendaServicioLocal servicios;

	public AgendaServicioDelegate() throws ATiempoAppEx {
		try {
			
			servicios = ((AgendaServicioLocalHome) HomeFactory.getHome(AgendaServicioLocalHome.JNDI_NAME)).create();
			
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioInterfaz#enviarCreacionActuacion(co.com.telefonica.atiempo.actividades.ActividadEJBDTO, java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	public void enviarCreacionActuacion(Long idPeticion, Timestamp fechaReagendamiento, String tipoOC, String codActividad, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviarCreacionActuacion(idPeticion, fechaReagendamiento, tipoOC,  codActividad,  act);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioInterfaz#recibirAutoConfiguracion(co.com.telefonica.atiempo.interfaces.atiempo.TR701S)
	 */
	public void recibirAutoConfiguracion(TR701S tr701s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		
	}

	/**
	 * @param tr701S
	 */
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701S) throws ATiempoAppEx{
		// TODO Apéndice de método generado automáticamente
		servicios.recepcionCreacionActuacionAgendaSC(tr701S);
	}
	
	public void recepcionCierreActuacion(TR711S tr711s) throws ATiempoAppEx {
		servicios.recepcionCierreActuacion(tr711s);
	}
	
	public void setearDatosActividad(ActividadEJBDTO actDto) throws ATiempoAppEx{
		servicios.setearDatosActividad(actDto);
	}
		
}
