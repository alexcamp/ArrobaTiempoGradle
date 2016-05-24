package com.telefonica_chile.vpistbba.causa.session;

import java.util.Date;
import java.util.HashMap;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.CausaKey;
import co.com.telefonica.atiempo.ejb.eb.CausaLocal;
import co.com.telefonica.atiempo.ejb.eb.CausaLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.causa.dto.CausaDTO;

/**
 * Bean implementation class for Enterprise Bean: CausaComun
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class CausaComunBean implements javax.ejb.SessionBean {
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
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
	//CausaLocalHome cHome = null;
	public void ejbCreate() throws javax.ejb.CreateException {
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
//		try {
//			cHome = (CausaLocalHome) HomeFactory.getHome(CausaLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			log.error("NamingException. CausaHome");
//		}
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

	HashMap causaHash = new HashMap();
	Date dateCache = null;
	long maxDiff = 24*3600*1000;

	private void refreshCache() {
		if ( dateCache == null ) {
			dateCache = new Date();
			return;
		}
		long diffMS = new Date().getTime() - dateCache.getTime();
		if ( diffMS < maxDiff )
			return;
		dateCache = new Date();
		causaHash = new HashMap();
	}

	/** Método que me retorna datos de CAUSA COMUN **/
	public CausaDTO recuperarCausa (Long idCausa){
		refreshCache();
		
		if ( causaHash.containsKey("ID_" + idCausa) ) 
			return (CausaDTO) causaHash.get("ID_" + idCausa);
		
		CausaLocal causaLocal = null;
		try {
			CausaLocalHome cHome = (CausaLocalHome) HomeFactory.getHome(CausaLocalHome.JNDI_NAME);
			CausaKey cKey = new CausaKey(idCausa);
			causaLocal = cHome.findByPrimaryKey( cKey );
		} catch (FinderException e) {
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
		}

		CausaDTO causaDTO = new CausaDTO();
		if ( causaLocal == null )
			return causaDTO;
		causaDTO.setCausaNombre( causaLocal.getCaus_nombre() );
		causaDTO.setCausaId( idCausa );
		causaDTO.setCausaCodigo( causaLocal.getCaus_codigo() );
		causaHash.put("ID_" + idCausa, causaDTO);
		causaHash.put("CO_" + causaLocal.getCaus_codigo(), causaDTO);			 			
		return causaDTO;	
	}

	public CausaDTO getCausaByCodigo(String codCausa){
		refreshCache();
		
		if ( causaHash.containsKey("CO_" + codCausa) ) 
			return (CausaDTO) causaHash.get("CO_" + codCausa);
		
		CausaLocal causaLocal = null;
		try {
			CausaLocalHome cHome = (CausaLocalHome) HomeFactory.getHome(CausaLocalHome.JNDI_NAME);
			causaLocal = cHome.findByCodigo(codCausa);
		} catch (FinderException e) {
		} catch (NamingException e) {
			log.error(" Creacion de Local Home Nulls",e);
		}

		CausaDTO causaDTO = new CausaDTO();
		if ( causaLocal == null )
			return causaDTO;
		causaDTO.setCausaNombre( causaLocal.getCaus_nombre() );			 			

		Long idCausa = ( (CausaKey) causaLocal.getPrimaryKey()).caus_id;
		causaDTO.setCausaId( idCausa );
		causaDTO.setCausaCodigo( causaLocal.getCaus_codigo() );
		causaHash.put("ID_" + idCausa, causaDTO);
		causaHash.put("CO_" + causaLocal.getCaus_codigo(), causaDTO);			 			

		return causaDTO;
	}
}

