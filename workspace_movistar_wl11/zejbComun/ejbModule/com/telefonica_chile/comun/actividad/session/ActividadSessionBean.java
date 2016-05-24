package com.telefonica_chile.comun.actividad.session;

import java.util.Date;
import java.util.HashMap;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.ejb.eb.RolKey;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;

/**
 * Bean implementation class for Enterprise Bean: ActividadSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ActividadSessionBean implements javax.ejb.SessionBean {
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
	ActividadLocalHome actHome = null;
	public void ejbCreate() throws javax.ejb.CreateException {
		try {
			actHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("NamingException. ActividadLocalHome");
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
	

	HashMap actividadHash = new HashMap();
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
		actividadHash = new HashMap();
		
	}

	/** Métoodo que retorna CIERRE de acuerdo a la Actividad **/
	public ActividadDTO recuperaActividad (String codActividad, Long idAplicacion){
//		log.debug ("ejecuto recuperaActividad:" + codActividad );
		refreshCache();
		
		if ( actividadHash.containsKey( codActividad ) )
			return (ActividadDTO) actividadHash.get( codActividad );

		ActividadDTO dto = new ActividadDTO();
		try{
			ActividadLocal actividadLocal = getIdActEntity(codActividad,idAplicacion);
//			log.debug ("ejecuto recuperaActividad 2:" + codActividad );
			dto.setActManual(new Byte(actividadLocal.getAct_manual().byteValue()));
			dto.setActNombreReversa(actividadLocal.getAct_nombre_reversa());
			dto.setActOrdenTc(actividadLocal.getAct_orden_tc());
			dto.setCodActividad(actividadLocal.getAct_codigo());
			dto.setDescActividad(actividadLocal.getAct_descripcion());
			ActividadKey actividadKey=(ActividadKey) actividadLocal.getPrimaryKey();
			RolKey rolKey=(RolKey) actividadLocal.getFk_act_rol().getPrimaryKey();
			dto.setIdActividad(actividadKey.act_id );
			dto.setIdRol(rolKey.rol_id);
			dto.setDescRol(actividadLocal.getFk_act_rol().getRol_nombre());
			dto.setNombreFlujo(actividadLocal.getAct_nombre_flujo());
			Actividad_flujoKey afK =null;
			if (actividadLocal.getActividad_flujo() != null){
//				log.debug ("ejecuto recuperaActividad 3:" + codActividad );
				afK = (Actividad_flujoKey) actividadLocal.getActividad_flujo().getPrimaryKey();
				dto.setIdActFlujo(afK.acti_id);	
			}
			else{
				dto.setIdActFlujo(null);	
			}
			if(actividadLocal.getAct_quiebre()!=null)
			{
				if(actividadLocal.getAct_quiebre().intValue()==1)
					dto.setQuiebre(true);
				else
					dto.setQuiebre(false);
			}
			else
				dto.setQuiebre(false);
			actividadHash.put( codActividad, dto );
			actividadHash.put( ""+dto.getIdActividad(), dto );
//			log.debug ("ejecuto recuperaActividad 4:" + codActividad );
		} catch (Exception e) {
			log.error("No se pudo encontrar Actividad [" + codActividad + "] : " + e, e);
		}	

		return dto;		
	}		
	
	
	public ActividadDTO getActividad(Long idActividad) {
		refreshCache();
		
		if ( actividadHash.containsKey( ""+idActividad ) )
			return (ActividadDTO) actividadHash.get( ""+idActividad );

		ActividadDTO dto = new ActividadDTO();
		ActividadLocal actividadLocal = IdAct(idActividad);
		if ( actividadLocal == null )
			return dto;

		try {
			dto.setActManual(new Byte(actividadLocal.getAct_manual().byteValue()));
			dto.setActNombreReversa(actividadLocal.getAct_nombre_reversa());
			dto.setActOrdenTc(actividadLocal.getAct_orden_tc());
			dto.setCodActividad(actividadLocal.getAct_codigo());
			dto.setDescActividad(actividadLocal.getAct_descripcion());
			ActividadKey actividadKey=(ActividadKey) actividadLocal.getPrimaryKey();
			dto.setIdActividad(actividadKey.act_id);
			dto.setIdRol(((RolKey)actividadLocal.getFk_act_rol().getPrimaryKey()).rol_id);
			dto.setDescRol(actividadLocal.getFk_act_rol().getRol_nombre());
			dto.setNombreFlujo(actividadLocal.getAct_nombre_flujo());

			actividadHash.put( actividadLocal.getAct_codigo(), dto );
			actividadHash.put( ""+idActividad, dto );
		} catch (Exception e) {
			log.error("Excepcion. Buscando Actividad [ " + idActividad + "]",e);
		}
		return dto;
	}
	
	public ActividadLocal IdAct(Long idCodigo){
		try{
			ActividadKey actividadKey=new ActividadKey(idCodigo);
			ActividadLocal actLocal = actHome.findByPrimaryKey( actividadKey ); 
			return actLocal;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("Excepcion. No se encontró Actividad [ " + idCodigo + "]");
			return null;
		}
	}
	
	/** ESTO ES EL ENTITY PARA ACTIVIDAD: obtengo por Código de Actividad **/
	public ActividadLocal getIdActEntity (String codigo,Long idAplicacion) {
		try {			
			ActividadLocal actLocal = actHome.findByCodigoActividadIdAplicacion(codigo,idAplicacion);
			return actLocal;
		} catch (FinderException e) {
			log.error("No se encontro Codigo Actividad [" + codigo + "]", e);
		} catch (Exception e) {
			log.error("Error buscando Codigo Actividad [" + codigo + "]", e);
		}

		return null;
	}
	
	
	public boolean ActividadesNoGrabables(String codAct, Long idAplicacion){

		ActividadDTO actDto = recuperaActividad( codAct, idAplicacion );
		if (actDto == null)
			return false;
		
		if ( actDto.getActManual()==null || actDto.getActManual().intValue()!=1 )
			return false;
			
		return true;
	}
	
	public Long getIdActividad (String codAct, Long idAplicacion) {
		ActividadDTO actDto = recuperaActividad( codAct, idAplicacion );
		if (actDto == null)
			return null;
		
		return actDto.getIdActividad();
	}
}
