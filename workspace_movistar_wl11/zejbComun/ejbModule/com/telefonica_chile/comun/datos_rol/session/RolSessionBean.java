package com.telefonica_chile.comun.datos_rol.session;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.rol.dto.RolDTO;

/**
 * Bean implementation class for Enterprise Bean: RolSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class RolSessionBean implements javax.ejb.SessionBean {
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
	
	/** Métooodo que me retorna datos desde ROL **/
	public RolDTO recuperarRol (Long idAct){
		
		ActividadLocal actividadEntity = getActividadEntity(idAct);
		//ArrayList listadoPS = new ArrayList();	
		
		RolDTO rolDTO = new RolDTO();
		
		//Nombre de Rol
		RolLocal rolEjb = actividadEntity.getFk_act_rol();
		rolDTO.setNombreRol(rolEjb.getRol_nombre());
		
		//Rold id
		rolDTO.setRolCod(rolEjb.getRol_codigo());
		
		//ISP id
		rolDTO.setIspId(rolEjb.getIsp_id());
		
		//ROL_VE_SABANA
		rolDTO.setSabana(rolEjb.getRol_ve_sabana());
		
		//log.debug(" Salio Recuperar ROL: =>" + rolEjb.getNombre());
		
		return rolDTO;	
	}

	
	/** ESTO ES EL ENTITY PARA ACTIVIDAD**/
	public ActividadLocal getActividadEntity (Long idAct){
		try{
			ActividadLocalHome ActHome = (ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal local = ActHome.findByPrimaryKey(new ActividadKey(idAct));
			return local;
		}catch (Exception e){
			log.warn("Exception",e);
			return null;
		}
		//turn nul;
		
	}
	
	public ActividadDTO retornaDatosAct(Long idAct){
		
		ActividadLocal actividadEntity = getActividadEntity(idAct);
		ActividadDTO actividadDTO = new ActividadDTO();
		
		if (actividadEntity.getAct_descripcion()!=null)
		{
			actividadDTO.setDescActividad(actividadEntity.getAct_descripcion());
			actividadDTO.setCodActividad(actividadEntity.getAct_codigo());
		}
		else
			actividadDTO.setDescActividad("");
			
		return actividadDTO;
		
	}
	
}
