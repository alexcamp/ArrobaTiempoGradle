package com.telefonica_chile.comun.segmento.session;

import java.util.Date;
import java.util.HashMap;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.SegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.segmento.dto.SubSegmentoDto;
import com.telefonica_chile.comun.segmento.dto.segmentoDTO;

/**
 * Bean implementation class for Enterprise Bean: SegmentoSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class SegmentoSessionBean implements javax.ejb.SessionBean {
	
	private transient Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private javax.ejb.SessionContext mySessionCtx;
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
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
	//private SegmentoLocalHome sgHome = null;
	//private SubsegmentoLocalHome subHome=null; 
	public void ejbCreate() throws javax.ejb.CreateException {
//		try {
//			sgHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
//			subHome=(SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//
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

	HashMap segmentoHash = new HashMap();
	Date dateCache = null;
	long maxDiff = 48*3600*1000; // 48 Hrs.

	private void refreshCache() {
		if ( dateCache == null ) {
			dateCache = new Date();
			return;
		}
		long diffMS = new Date().getTime() - dateCache.getTime();
		if ( diffMS < maxDiff )
			return;
		dateCache = new Date();
		segmentoHash = new HashMap();
	}

	public segmentoDTO getSegmento(Long idSegmento) throws FinderException
	{
		segmentoDTO sgDto = new segmentoDTO();
		if ( idSegmento == null )
			throw new FinderException("Segmento no presente");
		
		//		refreshCache();
		//		if ( segmentoHash.containsKey("" + idSegmento) ) 
		//			return (segmentoDTO) segmentoHash.get("" + idSegmento);
		
		
		SegmentoKey sgKey = new SegmentoKey( idSegmento );
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
		try{
			SegmentoLocalHome sgHome = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
			SegmentoLocal sgLocal = sgHome.findByPrimaryKey( sgKey );
			sgDto.setDescripcion(sgLocal.getSegm_descripcion());
			sgDto.setCodigo(sgLocal.getSegm_codigo());
		}catch (NamingException e)
		{
			logger.error(" Creacion de Local Home Nulls",e);
		}
		sgDto.setIdSegmento( idSegmento );
		segmentoHash.put(""+idSegmento, sgDto);
		return sgDto;
		
	}
	
	public SubSegmentoDto getSubSegmento(Long idSubSegmento) throws FinderException
	{
		SubSegmentoDto subSegmentoDto=new SubSegmentoDto();
		SubsegmentoLocalHome subHome;
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
		try {
			subHome = (SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
			SubsegmentoLocal subsegmentoLocal=subHome.findByPrimaryKey(new SubsegmentoKey(idSubSegmento));
			subSegmentoDto.setSubSegmentoId(idSubSegmento);
			SegmentoLocal segmentoLocal=subsegmentoLocal.getSegmento();
			SegmentoKey segmentoKey=(SegmentoKey) segmentoLocal.getPrimaryKey();
			subSegmentoDto.setSegmentoId(segmentoKey.segm_id);
			subSegmentoDto.setDescripcion(subsegmentoLocal.getDescripcion());
		} catch (NamingException e) 
		{
			logger.error(" Creacion de Local Home Nulls",e);
		}
		return subSegmentoDto; 
	}

}
