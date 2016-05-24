package com.telefonica_chile.vpistbba.parametro.session;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.parametro.session.ParametroComunSessionLocal;
import com.telefonica_chile.comun.parametro.session.ParametroComunSessionLocalHome;
import com.telefonica_chile.comun.parametro.session.ParametroDTO;

/**
 * Bean implementation class for Enterprise Bean: ParametroSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ParametroSessionBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	public ParametroDTO buscarPorNombre(String nombre)
	{
		ParametroDTO dto = null;
		ParametroComunSessionLocalHome paraLocalHome = null;
		ParametroComunSessionLocal paraLocal = null;
		
		try {
			paraLocalHome = (ParametroComunSessionLocalHome)HomeFactory.getHome(ParametroComunSessionLocalHome.JNDI_NAME);
			paraLocal = paraLocalHome.create();
			dto = (ParametroDTO) paraLocal.obtenerPorNombre(nombre);	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}	
		
		return dto;	
	}
	
	public Integer obtenerFlujoVacio() {
		
		ParametroComunSessionLocalHome ejbLocalHome = null;
		ParametroComunSessionLocal datosLocal = null;
		
		try {
			ejbLocalHome = (ParametroComunSessionLocalHome) HomeFactory.getHome(ParametroComunSessionLocalHome.JNDI_NAME);
			datosLocal = ejbLocalHome.create();
			String respuesta = datosLocal.obtenerFlujoNulo();
			if(respuesta != null){
				return new Integer(respuesta);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
