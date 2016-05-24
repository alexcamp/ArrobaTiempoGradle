package com.telefonica_chile.comun.operacion_comercial.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.opearacion_comercial.dto.OperacionComercialDTO;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: OperacionComercialSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class OperacionComercialSessionBean implements javax.ejb.SessionBean {
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
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 19, 2009
//	Operacion_comercialLocalHome ocHome = null;
	public void ejbCreate() throws javax.ejb.CreateException {
//		try {
//			ocHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			log.error("NamingException. OperacionComercialLocalHome.");
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
	
	HashMap operComHash = new HashMap();
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
		operComHash = new HashMap();
		
	}

	public OperacionComercialDTO recuperarOP(Long idOC){
		refreshCache();
		if ( operComHash.containsKey( ""+idOC ) )
			return (OperacionComercialDTO) operComHash.get( ""+idOC ) ;
		
		Operacion_comercialLocal ocDatos = (Operacion_comercialLocal) getOCEntity(idOC);
		log.debug("Operacion Comercial [" + idOC + "," + ocDatos.getOpco_nombre() + "," + ocDatos.getOpco_tipo() + "]");
		OperacionComercialDTO obj = new OperacionComercialDTO();
		obj.setNombreOpco(ocDatos.getOpco_nombre());
		obj.setTipoOC(ocDatos.getOpco_tipo());
		Operacion_comercialKey key=(Operacion_comercialKey) ocDatos.getPrimaryKey();
		obj.setIdOpco( ""+key.opco_id  );
		obj.setIdTipoTrabajo( ocDatos.getTitr_id() );

		operComHash.put("" + idOC, obj);

		return obj;	
	}	
	
	/** Método que me retorna datos de Operación Comercial **/
	public ProductoDTO recuperarOC (Long idOC){

		OperacionComercialDTO ocDto = recuperarOP( idOC );
		ProductoDTO objOC = new ProductoDTO();
		
		log.debug("Operacion Comercial [" + idOC + "," + ocDto.getTipoOC() + "]");
		objOC.setTipoOperacionComercial( ocDto.getTipoOC() );		
		objOC.setOperacionComercial( ocDto.getNombreOpco() );
			 			
		return objOC;	
	}	
	
	
	/** ESTO ES EL ENTITY PARA Opercacion-Comercial **/
	public Operacion_comercialLocal getOCEntity (Long idOC){
		try{
			Operacion_comercialLocalHome PSHome = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			return (Operacion_comercialLocal) PSHome.findByPrimaryKey(new Operacion_comercialKey(idOC));
		}catch (Exception e){
			log.warn("Exception",e);
			return null;
		}
		//turn nul;
		
	}
	
//-------------------------------------------------------------------------------
  /** Método que me retorna datos de OPERACION COMERCIAL **/
	/*
	public ArrayList recuperarAllOc() {
	
		Collection allOcCollection = (Collection) getAllOcEntity();
		ArrayList listAllOc = new ArrayList();
		for (Iterator iter = allOcCollection.iterator(); iter.hasNext();) {
			OperacionComercialDTO allOcDto = new OperacionComercialDTO();
			Operacion_comercialLocal element = (Operacion_comercialLocal) iter.next();
			Operacion_comercialKey key=(Operacion_comercialKey) element.getPrimaryKey();
			allOcDto.setIdOpco("" + key.opco_id );
			allOcDto.setTipoOC(element.getOpco_tipo());
			allOcDto.setNombreOpco(element.getOpco_nombre());
			allOcDto.setIdTipoTrabajo( element.getTitr_id() );
			
			operComHash.put( "" + key.opco_id, allOcDto );
			listAllOc.add(allOcDto);
		}
		return listAllOc;
	}
*/
  /** ESTO ES EL ENTITY PARA PRODUCTO_SERVICIO **/
  /*
	public Collection getAllOcEntity (){
	  try{
		  Operacion_comercialLocalHome allOcHome = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
		  return allOcHome.findAll(); 
	  }catch (Exception e){
			log.warn("Exception",e);
		  return null;
	  }
	  //turn nul;
		
  }	
  */
//------------------------------------------------------------------------------
	public Integer getCodigoTipoTrabajo(Long idOC) {
		OperacionComercialDTO ocDto = recuperarOP( idOC );
		
		if ( ocDto == null )
			return null;

		return ocDto.getIdTipoTrabajo();
	}


  public Collection getOpCoAltas() {
	  //ArrayList lista=new ArrayList();
	  Collection lista=null;
	  ArrayList listaResultado=new ArrayList();
		try {
			Operacion_comercialLocalHome opCoHome=(Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			lista=(Collection)opCoHome.findTipoA();
			for (Iterator it=lista.iterator(); it.hasNext();) {
				Operacion_comercialLocal opCo=(Operacion_comercialLocal)it.next();
				Operacion_comercialKey key=(Operacion_comercialKey) opCo.getPrimaryKey();
				listaResultado.add(key.opco_id);			
			}
		} catch (FinderException e) {
			log.error(e.getMessage());
		} catch (NamingException e) {
			log.error(e.getMessage());
		}
		
		listaResultado.addAll(lista);	
	  return listaResultado;
  }

}
