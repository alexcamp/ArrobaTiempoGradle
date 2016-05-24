/*
 * Created on Feb 24, 2005
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.HabilidadKey;
import co.com.telefonica.atiempo.ejb.eb.HabilidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Rol_habilidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Rol_habilidadLocalHome;

import com.tecnonautica.utiles.excepciones.NestedRuntimeException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class rolHabilidad  implements Serializable{
	
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(rolHabilidad.class);

	private Long habilidadId;
	private String 	habilidadNombre;
	private String	habilidadValor;

	
	public Long getHabilidadId() {
		return habilidadId;
	}

	public String getHabilidadNombre() {
			return habilidadNombre;
		}
		
	public String getHabilidadValor() {
		return habilidadValor;
	}
	
		
	public void setHabilidadId(Long habilidadId) {
		this.habilidadId = habilidadId;
	}

	public void setHabilidadNombre(String habilidadNombre) {
		this.habilidadNombre = habilidadNombre;
	}
	
	public void setHabilidadValor(String habilidadValor) {
		this.habilidadValor = habilidadValor;
	}
	
	/**
	 * OBJETO QUE ALMACENA LA HABILIDAD DEL ROL DE LA ACTIVIDAD
	 * @param habilidadId
	 * @param habilidadNombre
	 * @param habilidadValor
	 */
	public rolHabilidad(Long habilidadId, String habilidadNombre,  String habilidadValor) {
		this.habilidadId = habilidadId;
		this.habilidadNombre = habilidadNombre;
		this.habilidadValor = habilidadValor;
	}
	
	/**
	 * RETORNA UNA LISTA DE LOS PARAMETROS ASOCIADOS A LA HABILIDAD DEL ROL
	 * @param Long idRol
	 * @return ArrayList habilidadesParaRol
	 */
	public static ArrayList recuperaRolHabilidad(Long idRol, parametrosParaFiltrar parametros) {
		ArrayList habilidadesParaRol = new ArrayList();
		Rol_habilidadLocalHome home;
		String valorHabilidad = null;
		try {
			home = (Rol_habilidadLocalHome)
					HomeFactory.getHome(Rol_habilidadLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + Rol_habilidadLocalHome.JNDI_NAME, e);
		}
		Collection rolHabilidadEjb = null;
		try {
			rolHabilidadEjb = home.findHabilidadesPorRol(idRol);
			if (log.isDebugEnabled())
				log.debug("Nro. de Habilidad Asociada al Rol: <" + rolHabilidadEjb.size() +">");
		} catch (FinderException e) {
		log.info("No se encontraron Habilidad Asociada al Rol: " + e.getMessage());
			return habilidadesParaRol;
		}
		
		for (Iterator it = rolHabilidadEjb.iterator(); it.hasNext(); ) {
			Rol_habilidadLocal rolhabiEjb = (Rol_habilidadLocal) it.next();
			HabilidadLocal 		habiEjb		=	rolhabiEjb.getF_fk_habi_2_roha();
			
			valorHabilidad = parametrosParaFiltrar.recuperaValorHabilidad(parametros, habiEjb.getHabi_nombre());
			HabilidadKey habilidadKey=(HabilidadKey) rolhabiEjb.getF_fk_habi_2_roha().getPrimaryKey();
			habilidadesParaRol.add(new rolHabilidad(
								habilidadKey.habi_id,
								habiEjb.getHabi_nombre(),
								valorHabilidad));
		}
		return habilidadesParaRol;
	}
	
}
