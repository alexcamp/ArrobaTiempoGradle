package com.telefonica_chile.bandeja.helpers;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.AplicacionKey;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.datos.DatosBandejaRuntimeException;

public class ActividadFactory {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private ActividadLocalHome homeActividad;
	public ActividadLocalHome getActividadHome() throws FactoryException {
		if (homeActividad != null)
			return homeActividad;
		try {
			homeActividad = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando home " + ActividadLocalHome.JNDI_NAME, e);
			throw new FactoryException("No se pudo recuperar actividad", e);
		}
		return homeActividad;
	}
	
	public ActividadLocal getActividadPorCodigoActividadCodigoAplicacion(String codigoActividad, String codigoAplicacion) throws FactoryException, RegistroNoEncontradoException {
		ActividadLocal actividadEntity;
		AplicacionLocalHome aplicacionHome;

		try {
			try {
				aplicacionHome =
					(AplicacionLocalHome) HomeFactory.getHome(
						AplicacionLocalHome.JNDI_NAME);
			} catch (NamingException e) {
				throw new DatosBandejaRuntimeException(
					"Problemas recuperando jndi "
						+ AplicacionLocalHome.JNDI_NAME,
					e);
			}
			
			AplicacionLocal aplicacionEjb = aplicacionHome.findByNombre( codigoAplicacion );
			AplicacionKey aplicacionKey=(AplicacionKey) aplicacionEjb.getPrimaryKey();
			Long idAplicacion = aplicacionKey.ap_id;
			
			actividadEntity = getActividadHome().findByCodigoActividadIdAplicacion(codigoActividad, idAplicacion);
		} catch (FinderException e) {
			log.warn("No se encontro ActividadEntity " + codigoActividad, e);
			throw new RegistroNoEncontradoException("Actividad: " + codigoActividad);
		}
		return actividadEntity; 
	}
}

