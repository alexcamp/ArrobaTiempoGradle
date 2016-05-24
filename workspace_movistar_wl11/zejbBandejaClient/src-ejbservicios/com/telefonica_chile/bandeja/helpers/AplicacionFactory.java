
package com.telefonica_chile.bandeja.helpers;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;


public class AplicacionFactory {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private AplicacionLocalHome homeAplicacion;
	public AplicacionLocalHome getAplicacionHome() throws FactoryException {
		if (homeAplicacion != null)
			return homeAplicacion;

		try {
			homeAplicacion = (AplicacionLocalHome) HomeFactory.getHome(AplicacionLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando home aplicacion: " + AplicacionLocalHome.JNDI_NAME, e);
			throw new FactoryException("No se pudo recuperar aplicacion", e);
		}
		return homeAplicacion;
	}
	
	public AplicacionLocal getAplicacionPorNombre(String nombre) throws FactoryException, RegistroNoEncontradoException {
		AplicacionLocalHome homeAplicacion = getAplicacionHome();
		AplicacionLocal aplicacionEntity;
		try {
			aplicacionEntity = homeAplicacion.findByNombre(nombre);
		} catch (FinderException e) {
			log.warn("No se encontro Aplicacion: " + nombre, e);
			throw new RegistroNoEncontradoException("Aplicacion " + nombre);
		}
		return aplicacionEntity;
	}
}
