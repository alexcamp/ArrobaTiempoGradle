package com.telefonica_chile.comun.parametro.session;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ParametroKey;
import co.com.telefonica.atiempo.ejb.eb.ParametroLocal;
import co.com.telefonica.atiempo.ejb.eb.ParametroLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: ParametroComunSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ParametroComunSessionBean implements javax.ejb.SessionBean {
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
	
	
	public ParametroDTO obtenerPorNombre(String nombreParametro)
	{
		ParametroDTO paraDto = new ParametroDTO();
		
		ParametroLocalHome paraHome = null;
		ParametroLocal paraLocal = null;
		
		try {
			log.info("TRATANDO DE RECUPERAR EL PARAMETRO:"+nombreParametro);
			paraHome = (ParametroLocalHome)HomeFactory.getHome(ParametroLocalHome.JNDI_NAME);
			paraLocal = paraHome.findBuscaValor(nombreParametro);
			ParametroKey key=(ParametroKey) paraLocal.getPrimaryKey();
			paraDto.setIdParametro(key.para_id);
			paraDto.setNombreParametro(nombreParametro);
			paraDto.setValorParametro(paraLocal.getPara_valor());
		} catch (EJBException e) {
			log.warn("EJBException",e);
		} catch (NamingException e) {
			log.warn("NamingException",e);
		} catch (FinderException e) {
			log.warn("FinderException",e);
		}
		
		return paraDto;
	}
	
	public String obtenerFlujoNulo() {
		
		ParametroLocalHome paraHome = null;
		ParametroLocal paraLocal = null;
		
		try {
			paraHome = (ParametroLocalHome)HomeFactory.getHome(ParametroLocalHome.JNDI_NAME);
			paraLocal = paraHome.findBuscaValor("FLUJO_NULO");
			log.debug("Valor Nulo: " + paraLocal.getPara_valor());
			return paraLocal.getPara_valor();
			
		} catch (FinderException e) {
			log.warn("FinderException",e);
		} catch (NamingException e) {
			log.warn("FinderException",e);
		}
		
		return null;
	}
}
