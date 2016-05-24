package com.telefonica_chile.bandeja.datos.bandeja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Accion_masivaKey;
import co.com.telefonica.atiempo.ejb.eb.Accion_masivaLocal;
import co.com.telefonica.atiempo.ejb.eb.Accion_masivaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.AplicacionKey;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.datos.DatosBandejaRuntimeException;
import com.telefonica_chile.bandeja.datos.usuarios.RolDTO;
import com.telefonica_chile.bandeja.dto.AccionMasivaDTO;
import com.telefonica_chile.bandeja.ejbutiles.Boletero;

/**
 * Bean implementation class for Enterprise Bean: BandejaSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class BandejaSessionBean implements javax.ejb.SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6529572935255841274L;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

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

	/**
	 * Retorna null si no encuentra agencia.
	 */
	
	
	/**
	 * Retorna la actividad del workflow dado su identificador PK
	 * @param idActividad Primary key
	 * @return Datos de actividad WF
	 */
	public ActividadDTO recuperaActividadPorId(Long idActividad) {
		ActividadDTO actividad = null;
		
		try {
			ActividadLocalHome actividadHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadKey actividadKey=new ActividadKey(idActividad);
			ActividadLocal actividadEjb = actividadHome.findByPrimaryKey(actividadKey);
			
			actividad = new ActividadDTO();
			actividad.setId(idActividad);
			actividad.setCodigo(actividadEjb.getAct_codigo());
			actividad.setDescripcion(actividadEjb.getAct_descripcion());
			actividad.setNombreFlujo(actividadEjb.getAct_nombre_flujo());
		} catch (Exception e) { }
		
		return actividad;
	}

	public ActividadDTO recuperaActividadPorCodigoActividadCodigoAplicacion(String codigoActividad, String codigoAplicacion) {
		ActividadLocalHome actividadHome;
		AplicacionLocalHome aplicacionHome;
		
		try {
			
			actividadHome =
				(ActividadLocalHome) HomeFactory.getHome(
					ActividadLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new DatosBandejaRuntimeException(
				"Problemas recuperando jndi "
					+ ActividadLocalHome.JNDI_NAME,
				e);
		}

		try {
			aplicacionHome =
				(AplicacionLocalHome) HomeFactory.getHome(
			AplicacionLocalHome.JNDI_NAME );
		} catch (NamingException e) {
			throw new DatosBandejaRuntimeException(
				"Problemas recuperando jndi "
					+ AplicacionLocalHome.JNDI_NAME,
				e);
		}

		ActividadDTO actividad = null;
		try {
			
			AplicacionLocal aplicacionEjb = aplicacionHome.findByNombre( codigoAplicacion );
			AplicacionKey aplicacionKey=(AplicacionKey) aplicacionEjb.getPrimaryKey();
			Long idAplicacion = aplicacionKey.ap_id ;		
			ActividadLocal actividadEjb = actividadHome.findByCodigoActividadIdAplicacion(codigoActividad, idAplicacion);
			actividad = new ActividadDTO();
			ActividadKey actividadKey=(ActividadKey) actividadEjb.getPrimaryKey();
			actividad.setId(actividadKey.act_id);
			actividad.setCodigo(actividadEjb.getAct_codigo());
			actividad.setDescripcion(actividadEjb.getAct_descripcion());
			actividad.setNombreFlujo(actividadEjb.getAct_nombre_flujo());
		} catch (FinderException e) {
			log.warn("No se encontro' Actividad " + codigoActividad + ": " + e);
			throw new DatosBandejaRuntimeException(
							"Problemas, no se encontro la actividad  "
								+ codigoActividad,
							e);
		}
		return actividad;
	}

	/**Retornamos todas las Agencias*/
	public ArrayList recuperarAcciones(Long idRol)
	{
   	
		Collection listado = getAccionesEntity(idRol);
		ArrayList listadoAcciones = new ArrayList();
		for (Iterator iter = listado.iterator(); iter.hasNext();) {			
			Accion_masivaLocal objAcciones = (Accion_masivaLocal) iter.next();				
			Accion_masivaKey accion_masivaKey=(Accion_masivaKey) objAcciones.getPrimaryKey();
			AccionMasivaDTO obj = new AccionMasivaDTO();
			RolKey rolKey=(RolKey) objAcciones.getF_fk_rol_2_acma().getPrimaryKey();
			
			obj.setAcmaId(accion_masivaKey.acma_id);
			obj.setAcamValor(objAcciones.getAcma_valor());
			
			if(listadoAcciones.indexOf(obj)>0)
				continue;
			
			obj.setAcmaRol(rolKey.rol_id);
			obj.setAcmaDescripcion(objAcciones.getAcma_descripcion());
			
			obj.setAcamCierre(objAcciones.getAcma_cierre());
			listadoAcciones.add(obj);					
		}			 			
		return listadoAcciones;		
	}
	  
	public ArrayList recuperarAcciones(Collection roles)
	{
	   	
		ArrayList listadoAcciones = new ArrayList();
		try{
	   	for(Iterator iterRol = roles.iterator(); iterRol.hasNext();){
			 
			RolDTO rolesObj = (RolDTO) iterRol.next();
			Collection listado = getAccionesEntity(rolesObj.getId());
			//log.debug("--- recuperarAcciones ---" + listado.size() );
	  
			  for (Iterator iter = listado.iterator(); iter.hasNext();) {			
				  Accion_masivaLocal objAcciones = (Accion_masivaLocal) iter.next();				
	
				  AccionMasivaDTO obj = new AccionMasivaDTO();
				  RolKey rolKey=(RolKey) objAcciones.getF_fk_rol_2_acma().getPrimaryKey();
				  obj.setAcmaRol(rolKey.rol_id);
				  obj.setAcmaDescripcion(objAcciones.getAcma_descripcion());
				  obj.setAcamValor(objAcciones.getAcma_valor());
				  obj.setAcamCierre(objAcciones.getAcma_cierre());
				  listadoAcciones.add(obj);					
			  }			 
	   	}
	}catch (Exception e) {
		log.fatal("Error Exception ");	
		log.fatal("Error  Accion Masiva  Collection getAccionesEntity ==> ", e);
	}
	  			
		return listadoAcciones;		
	}
	  
	private Collection getAccionesEntity(Long idRol){
		  try {
		   	
		   Accion_masivaLocalHome accionHome =  (Accion_masivaLocalHome) HomeFactory.getHome(Accion_masivaLocalHome.JNDI_NAME);
		   //log.debug("--- Rol de Usuario ---" + idRol);
		   Collection accion=accionHome.findByRol(idRol);
		   //log.debug("--- Collection getAccionesEntity ---" + accion.size());
		   return accion;
		  } catch (NamingException e) {
			   log.fatal("Error NamingException ");	
			   log.fatal("Error  Accion Masiva  Collection getAccionesEntity ==> ", e);
		  } catch(FinderException e){
			   log.fatal("Error FinderException ");
			   log.error("Error  Accion Masiva  Collection getAccionesEntity ==> ", e);
		  }
	   
	   return null;
	   }
	   
	 /**
	  * Obtiene un folio de reclamo usando el boletero
	  * @return Folio de reclamo
	  */
	 public Long obtieneFolioUsandoBoletero() {
		return Boletero.getInstance().obtenerTicket("RECLAMO");
	 }
	 
	/**
	 * Obtiene un folio de reclamo usando el boletero
	 * @param secuencia Codigo de secuencia de tabla SECUENCIA
	 * @return Id generado
	 */
	public Long obtieneFolioUsandoBoletero(String secuencia) {
	   return Boletero.getInstance().obtenerTicket(secuencia);
	}

}
