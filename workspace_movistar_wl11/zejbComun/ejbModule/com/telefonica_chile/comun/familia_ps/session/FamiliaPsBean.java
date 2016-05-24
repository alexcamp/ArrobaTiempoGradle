package com.telefonica_chile.comun.familia_ps.session;

import java.util.Date;
import java.util.HashMap;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.familia_ps.dto.FamPsRemotoDTO;

/**
 * Bean implementation class for Enterprise Bean: FamiliaPs
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class FamiliaPsBean implements javax.ejb.SessionBean {
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
	Familia_producto_servicioLocalHome fapsHome = null;
	public void ejbCreate() throws javax.ejb.CreateException {
		try {
			fapsHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("NamingException. FamiliaProductoServicio");
		}
		
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

	HashMap familiaPsHash = new HashMap();
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
		familiaPsHash = new HashMap();
		
	}
	public FamPsRemotoDTO getFamiliaPS(Long idFaPS) {
		refreshCache();
		if ( familiaPsHash.containsKey( ""+idFaPS ) )
			return (FamPsRemotoDTO) familiaPsHash.get( ""+idFaPS );

		FamPsRemotoDTO fapsDto = null;
		try {
			Familia_producto_servicioLocal fapsLocal =  fapsHome.findByPrimaryKey( new Familia_producto_servicioKey(idFaPS) );
			fapsDto = new FamPsRemotoDTO();
			fapsDto.setFapsId( idFaPS );
			fapsDto.setFapsNom( fapsLocal.getFaps_nombre() );
			fapsDto.setFapsCod( fapsLocal.getFaps_codigo() );
			familiaPsHash.put( ""+idFaPS, fapsDto );
		} catch (FinderException e) {
			log.error("FinderException. FamiliaPS [" + idFaPS + "]: " + e.getMessage());
		} catch (Exception e) {
			log.error("Exception. FamiliaPS [" + idFaPS + "]: " + e.getMessage());
		}
		
		return fapsDto;
	}

	/** Método que me retorna datos de FAMILIA PRODUCTOS SERVICIOS **/
	
	// CR16429 FindAll - ana santos
	/*public ArrayList recuperarFamPs (){
		
		ArrayList listadoFam = new ArrayList();	
		//Declaro Entity FamiliaProductoServicio
		try {
			Collection famDatos = fapsHome.findAll();
			for (Iterator iter = famDatos.iterator(); iter.hasNext();) {
				FamPsRemotoDTO fapsDto = new FamPsRemotoDTO();
				Familia_producto_servicioLocal fapsLocal =(Familia_producto_servicioLocal) iter.next();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) fapsLocal.getPrimaryKey();
				fapsDto.setFapsId( familia_producto_servicioKey.faps_id  ); 
				fapsDto.setFapsNom( fapsLocal.getFaps_nombre() ); 
				fapsDto.setFapsCod( fapsLocal.getFaps_codigo() );
				familiaPsHash.put(""+fapsDto.getFapsId(), fapsDto);
				listadoFam.add( fapsDto );
			}
		} catch (EJBException e) {
			log.error("EJBException. FamiliaPS ALL: " + e.getMessage());
		} catch (FinderException e) {
			log.error("FinderException. FamiliaPS ALL: " + e.getMessage());
		} catch (Exception e) {
			log.error("Exception. FamiliaPS ALL: " + e.getMessage());
		}
	
		return listadoFam;
	}	*/	

}
