/*
 * Created on Jun 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.utiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;
import com.telefonica_chile.bandeja.dto.AplicacionDTO;

/**
 * @author luolave
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UrlAplicaciones {
	
	//
	// private fields
	//

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Obtiene la url base para una aplicacion.
	 * 
	 * @param appName nombre de la aplicacion.
	 * @return la url base.
	 */	
	public String getUrl(String appName)
	{
		String url = null;
		
		try {
			url = ApplicationConfig.getUrlBase(appName);
			//log.debug("URL RETORNADA ==> " + appName + ": " + url);
			return url;		
		} catch (Exception e) {
			// TODO: manejar la excepcion!
			log.fatal("**************************************************");
			log.fatal("**** OCURRIO UN ERROR Y NO SE ESTA MANEJANDO *****");
			log.fatal("**************************************************");
			return null;
		}
//		String url=null;
//		try {
//			BIntegradaSessionLocal bandejaSession = getBandejaSession();
//			/**Listado Aplicaciones (Ambitos)**/
//			ArrayList listAplicaciones = (ArrayList)bandejaSession.recuperarAplicaciones();
//					
//			HashMap mapAplicaciones=new HashMap();
//			for (Iterator it = listAplicaciones.iterator(); it.hasNext(); ) {
//				AplicacionDTO aplicacion = (AplicacionDTO) it.next();
//				log.debug("Aplicacion:"+aplicacion.getApNombre());
//				if (apli.equals(aplicacion.getApNombre())){
//							
//					url=aplicacion.getApUrlBase();
//					log.debug("URL RETORNADA ==> "+url);
//				}
//				mapAplicaciones.put(aplicacion.getApNombre(),aplicacion);
//			}
//			return url;
//		} catch (BandejaException e) {
//			e.printStackTrace();
//		} catch (NamingException e) {
//			log.debug("Problema para recueprar JNDI de BIntegradaSessionLocal");
//		} catch (CreateException e) {
//			e.printStackTrace();
//		}
//		return url;
	}
	
	private BIntegradaSessionLocal getBandejaSession() throws NamingException, CreateException {
		BIntegradaSessionLocalHome home =  
					(BIntegradaSessionLocalHome)HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		BIntegradaSessionLocal local = home.create();
		return local;
	}
	
	public HashMap mapAplicaciones()
	{
		HashMap mapAplicaciones = new HashMap();
		try {
			BIntegradaSessionLocal bandejaSession = getBandejaSession();
			/**Listado Aplicaciones (Ambitos)**/
			ArrayList listAplicaciones = (ArrayList)bandejaSession.recuperarAplicaciones();
					
			mapAplicaciones=new HashMap();
			for (Iterator it = listAplicaciones.iterator(); it.hasNext(); ) {
				AplicacionDTO aplicacion = (AplicacionDTO) it.next();
				mapAplicaciones.put(aplicacion.getApNombre(),aplicacion);
			}

		} catch (BandejaException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			log.debug("Problema para recueprar JNDI de BIntegradaSessionLocal");
		} catch (CreateException e) {
			e.printStackTrace();
		}
		return mapAplicaciones;
	}
}
