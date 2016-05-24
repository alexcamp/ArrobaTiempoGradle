/*
 * Created on Feb 24, 2005
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;

import com.tecnonautica.utiles.excepciones.NestedRuntimeException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class rolActividad  implements Serializable{
	
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(rolActividad.class);

	private Long 		rolId;
	private Long 		rolIdIsp;
	private String		rolNombre;
	private String	 	rolCodigo;
	private Double	 	rolOla;
		
	public Long getRolId() {
		return rolId;
	}

	public Long getRolIdIsp() {
			return rolIdIsp;
		}
		
	public String getRolNombre() {
		return rolNombre;
	}
	
	public String getRolCodigo() {
		return rolCodigo;
	}
		
	public Double getRolOla() {
		return rolOla;
	}
	
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public void setRolIdIsp(Long rolIdIsp) {
		this.rolIdIsp = rolIdIsp;
	}
	
	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public void setRolCodigo(String rolCodigo) {
		this.rolCodigo = rolCodigo;
	}

	public void setRolOla(Double rolOla) {
		this.rolOla = rolOla;
	}
	/**
	 * OBJETO ALMACENA EL ROL ASOCIADO A LA ACTIVIDAD
	 * @param rolId
	 * @param rolIdIsp
	 * @param rolNombre
	 * @param rolCodigo
	 */
	public rolActividad(Long rolId, Long rolIdIsp,  String rolNombre, String rolCodigo, Double rolOla) {
		this.rolId 		= rolId;
		this.rolIdIsp 	= rolIdIsp;
		this.rolNombre 	= rolNombre;
		this.rolCodigo 	= rolCodigo;
		this.rolOla 	= rolOla;
	}
	
	/**
	 * RETORNA UN OBJETO ROLACTIVIDAD A PARTIR DEL CODIGO DE LA ACTIVIDAD
	 * @param 	String codActividad
	 * @return 	rolActividad rol
	 */
	public static rolActividad recuperaRolActividad(String codActividad, Long idAplicacion) {
		rolActividad rol = null;
		ActividadLocalHome home;
		try {
			home = (ActividadLocalHome)
					HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + ActividadLocalHome.JNDI_NAME, e);
		}
		ActividadLocal ActEjb = null;
		try {
			ActEjb = home.findByCodigoActividadIdAplicacion(codActividad,idAplicacion);
//			if (log.isDebugEnabled())
//				log.debug("Rol asociado a la actividad encontrado: <" + ActEjb.getIdRol() +">");
		} catch (FinderException e) {
		log.info("No se encontraron items Segmento : " + e.getMessage());
			return rol;
		}
			RolLocal rolEjb = ActEjb.getFk_act_rol();
				
		if (log.isDebugEnabled())
			log.debug("ACTIVIDAD: ACTcod: <" + ActEjb.getAct_codigo() + "> ROLcod: <" + rolEjb.getRol_codigo()+ ">");
		ActividadKey actividadKey=(ActividadKey) ActEjb.getPrimaryKey();
		RolKey rolKey=(RolKey) rolEjb.getPrimaryKey();
		rol = new rolActividad(
				rolKey.rol_id, 
				rolEjb.getIsp_id(),
				rolEjb.getRol_nombre(), 
				rolEjb.getRol_codigo(),
				new Double(ActEjb.getAct_ola().doubleValue()) );
		return rol;
	}

	
}
