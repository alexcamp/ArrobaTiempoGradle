package com.telefonica_chile.comun.ps.session;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.familia_ps.dto.FamPsRemotoDTO;
import com.telefonica_chile.comun.familia_ps.session.FamiliaPsLocal;
import com.telefonica_chile.comun.familia_ps.session.FamiliaPsLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: ProductoServicioSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ProductoServicioSessionBean implements javax.ejb.SessionBean {
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
//	 AT-1780 - Crash on 21-Nov-2008 - guido
	//Producto_servicioLocalHome psHome = null;
	//FamiliaPsLocalHome fapsHome = null;
	//Operacion_comercialLocalHome ocHome = null; // Is not used!
	public void ejbCreate() throws javax.ejb.CreateException {
		// AT-1780 - Crash on 21-Nov-2008 - guido
//		try {
//			psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			log.error("NamingException. ProductoServicioLocalHome");
//		}
//		try {
//			fapsHome = (FamiliaPsLocalHome) HomeFactory.getHome(FamiliaPsLocalHome.JNDI_NAME);  
//		} catch (NamingException e) {
//			log.error("NamingException. FamiliaPSLocalHome");
//		}
//		try {
//			ocHome = (Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			log.error("NamingException. OperacionComercialLocalHome");
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
	
//	 AT-1780 - Crash on 21-Nov-2008 - guido
	//HashMap psHash = new HashMap();
	Date dateCache = null;
	long maxDiff = 24*3600*1000; // 24 Hrs.

	private void refreshCache() {
		if ( dateCache == null ) {
			dateCache = new Date();
			return;
		}
		long diffMS = new Date().getTime() - dateCache.getTime();
		if ( diffMS < maxDiff )
			return;
		dateCache = new Date();
//		 AT-1780 - Crash on 21-Nov-2008 - guido - is not used
		//psHash = new HashMap();
		
	}

	/** Método que me retorna datos de Producto y Servicios **/
	public ProductoDTO recuperarPS (Long idPS) {
		return recuperarPS(idPS, false);
	}

	public ProductoDTO recuperarPS (Long idPS, boolean pcoObligatorio)
	{
		//refreshCache();
		
//		if ( psHash.containsKey( ""+idPS ) ) {
//			ProductoDTO psAux = (ProductoDTO) psHash.get( ""+idPS );
//
//			ProductoDTO objPS = new ProductoDTO();
//			objPS.setId( psAux.getId() );
//			objPS.setIdProducto( psAux.getIdProducto() );
//			objPS.setNombreProducto( psAux.getNombreProducto() );
//			objPS.setObservacion( psAux.getObservacion() );
//			objPS.setPcoObligatorio( psAux.getPcoObligatorio() );
//			objPS.setIdEmpresa( psAux.getIdEmpresa() );
//			objPS.setIdAmbito( psAux.getIdAmbito() );
//			objPS.setIdGrupoProducto( psAux.getIdGrupoProducto() );
//			objPS.setIdFaps( psAux.getIdFaps() );
//			objPS.setCodigoFamiliaPS( psAux.getCodigoFamiliaPS() );
//			objPS.setNombreFamiliaPS( psAux.getNombreFamiliaPS() );
//
//			return objPS;
//		}
		// AT-1780 - Crash on 21-Nov-2008 - guido
		Producto_servicioLocalHome psHome = null;
		try {
			psHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("NamingException. ProductoServicioLocalHome", e);
		}
		Producto_servicioLocal psLocal = null;
		try {
			psLocal = psHome.findByPrimaryKey(new Producto_servicioKey(idPS));
		} catch (FinderException e) {
			log.error("FinderException. PS No encontrado. [" + idPS + "]");
			return null;
		} catch (Exception e) {
			log.error("Exception. PS No encontrado. [" + idPS + "]:" + e.getMessage());
			return null;
		}

		if ( pcoObligatorio ) {
			Byte pcoOb = new Byte(psLocal.getPs_pco_obligatorio().byteValue());
			if ( pcoOb==null || pcoOb.intValue() != 1)
				return null;
		}

		ProductoDTO objPS = new ProductoDTO();
		Producto_servicioKey key=(Producto_servicioKey) psLocal.getPrimaryKey();
		objPS.setId(key.ps_id);
		objPS.setIdProducto(key.ps_id);
		objPS.setNombreProducto(psLocal.getPs_nombre());
		objPS.setObservacion(psLocal.getPs_observacion());
		objPS.setPcoObligatorio(new Byte(psLocal.getPs_pco_obligatorio().byteValue()));
		objPS.setIdEmpresa(psLocal.getEmpr_id());
		objPS.setIdAmbito(psLocal.getAmbi_id());
		objPS.setIdGrupoProducto( psLocal.getGrps_id() );
		Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) psLocal.getFamilia_producto_servicio().getPrimaryKey();
		FamPsRemotoDTO faps = getFamiliaPS(familia_producto_servicioKey.faps_id );
		objPS.setNombreFamiliaPS( faps.getFapsNom() );
		objPS.setIdFaps( faps.getFapsId() );
		objPS.setCodigoFamiliaPS( faps.getFapsCod() );
//		psHash.put( ""+idPS, objPS);
		return objPS;	
		
	}
	

	private FamPsRemotoDTO getFamiliaPS(Long idFaPS) {
//		 AT-1780 - Crash on 21-Nov-2008 - guido
		FamiliaPsLocalHome fapsHome = null;
		try {
			fapsHome = (FamiliaPsLocalHome) HomeFactory.getHome(FamiliaPsLocalHome.JNDI_NAME);  
		} catch (NamingException e) {
			log.error("NamingException. FamiliaPSLocalHome", e);
		}
		FamPsRemotoDTO fapsDto = null;
		try {
			FamiliaPsLocal psLocal = fapsHome.create();
			fapsDto = psLocal.getFamiliaPS(idFaPS);
		} catch (CreateException e) {
			log.error("FinderException",e);
		}

		return fapsDto;
	}


	/** Método que me retorna datos de Operación Comercial **/
	public ProductoDTO recuperarOC (Long idOC){
		
		Operacion_comercialLocal ocLocal = getOCEntity(idOC);	
		
		ProductoDTO objOC = new ProductoDTO();
		String nomOc = ocLocal.getOpco_nombre();
		objOC.setOperacionComercial(nomOc);			 			
		return objOC;	
	}	
	/** ESTO ES EL ENTITY PARA Opercacion-Comercial **/
	public Operacion_comercialLocal getOCEntity (Long idOC){
		try{
			Operacion_comercialLocalHome home = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			Operacion_comercialLocal local =  home.findByPrimaryKey(new Operacion_comercialKey(idOC));
			return local;
		}catch (Exception e){
			log.warn("Exception",e);
			return null;
		}
		//turn nul;
	}

//********************************************************************************************

  /** Método que me retorna datos de FAMILIA PRODUCTOS SERVICIOS **/

  /*	
  public ArrayList recuperarAllPs (){
		
	  //Declaro Entity ProductoServicio
	  Collection allPsCollection = (Collection)getAllpsLocal();
	  ArrayList listAllPs = new ArrayList();	
	  Long idPs;
	  Long idFam;
	  String nomPs;
		Byte Obliga; 	  
	  for (Iterator iter = allPsCollection.iterator(); iter.hasNext();){
	  	
		  ProductoDTO allPsDto = new ProductoDTO();
		  Producto_servicioLocal element =(Producto_servicioLocal) iter.next();
				
		  idPs = (Long) element.getPrimaryKey(); //obtengo el id 
			
		  nomPs = element.getPs_nombre(); // obtengo el nombre
		  Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) element.getFamilia_producto_servicio().getPrimaryKey();
		  idFam = familia_producto_servicioKey.faps_id;
		  Obliga = new Byte(element.getPs_pco_obligatorio().byteValue());		
		  allPsDto.setIdProducto(idPs);
		  allPsDto.setNombreProducto(nomPs);
		  allPsDto.setIdFaps(idFam);
		  allPsDto.setObligatorio(Obliga);

		  psHash.put( ""+idPs, allPsDto );		

		  listAllPs.add(allPsDto);		
	  }	
	  return listAllPs;
  }	
  */	
  /** ESTO ES EL ENTITY PARA PRODUCTO_SERVICIO **/
  
  /*
  public Collection getAllpsLocal (){
	  try{
		  Producto_servicioLocalHome allPsHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
		  return allPsHome.findAll();
	  }catch (Exception e){
		  log.warn("Exception",e);
		  return null;
	  }
  }	
	*/
}
