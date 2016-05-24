package com.telefonica_chile.bandeja.helpers;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;


public class SegmentoFactory {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	private SegmentoLocalHome home;
	public SegmentoLocalHome getSegmentoHome() throws FactoryException {
		if (home != null)
			return home;
		try {
			home = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando home de segmento: " + SegmentoLocalHome.JNDI_NAME, e);
			throw new FactoryException("Problemas manipulando segmentos", e);
		}
		return home;
	}
	
	public SegmentoLocal getSegmentoPorCodigo(String codigo) throws FactoryException, RegistroNoEncontradoException {
		SegmentoLocal segmentoLocal;
		try {
			segmentoLocal = getSegmentoHome().findByCodigo(codigo);
		} catch (FinderException e) {
//			log.warn("No se encontro SegmentoEntity " + codigo,e); 
			log.warn("No se encontro SegmentoEntity " + codigo); //Para que no asuste con la excepcion
			throw new RegistroNoEncontradoException("Segmento: " + codigo);
		}
		return segmentoLocal; 
	}
}

