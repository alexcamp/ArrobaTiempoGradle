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

import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ParametroLocal;
import co.com.telefonica.atiempo.ejb.eb.ParametroLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocalHome;

import com.tecnonautica.utiles.excepciones.NestedRuntimeException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class usuario  implements Serializable{
	
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(usuario.class);
	
	private Long 	usuarioId;
	private Long 	usuarioIdSupervisor;
	private String  usuarioIdca;
	private String	codigoContratista;
	


    public  usuario(){
    	
    }
	public Long getUsuarioId() {
		return usuarioId;
	}

	public Long getUsuarioIdSupervisor() {
		return usuarioIdSupervisor;
	}
	
	public String getUsuarioIdca() {
		return usuarioIdca;
	}
	
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public void setUsuarioIdSupervisor(Long usuarioIdSupervisor) {
		this.usuarioIdSupervisor = usuarioIdSupervisor;
	}
	
	public void setUsuarioIdca(String usuarioIdca) {
		this.usuarioIdca = usuarioIdca;
	}

	/**
	 * @return
	 */
	public String getCodigoContratista() {
		return codigoContratista;
	}

	/**
	 * @param string
	 */
	public void setCodigoContratista(String string) {
		codigoContratista = string;
	}	
	
	/**
	 * OBJETO TIPO USUARIO
	 * @param usuarioId
	 * @param usuarioIdSupervisor
	 * @param usuarioIdca
	 */
	public usuario(Long usuarioId, Long usuarioIdSupervisor, String usuarioIdca) {
		this.usuarioId = usuarioId;
		this.usuarioIdca = usuarioIdca;
		this.usuarioIdSupervisor = usuarioIdSupervisor;

	}
	/**
	 * RETORNA UN USUARIO PARA UN ID ESPECIFICO
	 * @param idUsuario
	 * @return usuario
	 */
	public static UsuarioLocal recuperaUsuario(Long idUsuario) {
		
		UsuarioLocalHome home;
		try {
			home = (UsuarioLocalHome)
					HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + UsuarioLocalHome.JNDI_NAME, e);
		}
		
		UsuarioLocal usuario = null;
		try {
			usuario = home.findByPrimaryKey(new UsuarioKey(idUsuario));
			if (log.isDebugEnabled())
				log.debug("USUARIOS Encontrados: " + usuario.getUsua_idca());
		} catch (FinderException e) {
			log.info("No se encontraron items usuario-habilidad : " + e.getMessage());
			
		}
		return usuario;
	}
	
	/**
	 * RECUPERA USUARIOS QUE CUMPLE CON EL CODIGO DE HABILIDAD Y VALOR, RETORNANDO UNA LISTA DE USUARIOS QUE CUMPLEN
	 * @param idHabilidad
	 * @param valorHabilidad
	 * @return usuariosCumpleHabilidad
	 */
	public static ArrayList recuperaUsuariosConHabilidad(Long idHabilidad, String valorHabilidad) {
		ArrayList usuariosCumpleHabilidad = new ArrayList();
		Habilidad_usuarioLocalHome home;
		
		try {
			home = (Habilidad_usuarioLocalHome)
					HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + Habilidad_usuarioLocalHome.JNDI_NAME, e);
		}
			Collection usuarioHabilidad = null;
		try {
			usuarioHabilidad = home.findUsuariosPorLaHabilidad(idHabilidad, valorHabilidad);
			if (log.isDebugEnabled())
					log.debug("Se encontraron : <" + usuarioHabilidad.size() +"> usuarios que cumplen con la habilidad ");
		} catch (FinderException e) {
			log.info("No se encontraron Habilidad Asociada al Rol: " + e.getMessage());
			return usuariosCumpleHabilidad;
		}
		
		int i=0;
		for (Iterator it = usuarioHabilidad.iterator(); it.hasNext(); ) {
			Habilidad_usuarioLocal usuarioHabiEjb = (Habilidad_usuarioLocal) it.next();
			UsuarioLocal usuarioEjb	= (UsuarioLocal) usuarioHabiEjb.getFk_usua_2_husa();
			UsuarioKey key=(UsuarioKey) usuarioHabiEjb.getFk_usua_2_husa().getPrimaryKey();
			usuariosCumpleHabilidad.add(new usuario(key.usua_id,null,usuarioEjb.getUsua_idca()));
				
			//log.debug("Método recuperaUsuariosConHabilidad(Retorna Arreglo): usuariosCumpleHabilidad= "+usuarioHabiEjb.getIdUsuario()+" , "+usuarioEjb.getIdca());
			i++;
		}
		return usuariosCumpleHabilidad;
	}

	/**
	 * VERIFICA SI EL USUARIO CUMPLE CON LA HABILIDAD Y EL VALOR DE LA HABILIDAD
	 * @param idUsuario
	 * @param idHabilidad
	 * @param valorHabilidad
	 * @return cumple
	 */
	public static boolean cumpleUsuariosConHabilidad(Long idUsuario, Long idHabilidad, String valorHabilidad) {
		boolean cumple = false;
		Habilidad_usuarioLocalHome home;
		
		try {
			home = (Habilidad_usuarioLocalHome)
					HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + Habilidad_usuarioLocalHome.JNDI_NAME, e);
		}
			Collection usuarioHabilidad = null;
		try {
			usuarioHabilidad = home.findPoseeHabilidadValor(idUsuario,idHabilidad, valorHabilidad);
			if (log.isDebugEnabled())
					log.debug("Se encontraron : <" + usuarioHabilidad.size() +"> usuarios que cumplen con la habilidad ");
		} catch (FinderException e) {
			log.info("No se encontraron Habilidad Asociada al Rol: " + e.getMessage());
			return cumple;
		}
		
		if (usuarioHabilidad.size()>0){
			if (log.isDebugEnabled())
				log.debug("EL USUARIO :["+idUsuario+"] CUMPLE CON HABILIDAD:" + idHabilidad +" Y VALOR: "+valorHabilidad);
			cumple = true;
		}
		return cumple;
	}

	/**
	 * RETORNA VERDAD SI EL USUARIO PERTENECE AL ROL
	 * @param idUsuario
	 * @param idRol
	 * @return cumple
	 */
	public static boolean cumpleUsuariosConRol(Long idUsuario, Long idRol) {
		boolean cumple = false;
		Usuario_rolLocalHome home;
		
		try {
			home = (Usuario_rolLocalHome)
					HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + Usuario_rolLocalHome.JNDI_NAME, e);
		}
			Collection usuarioRol = null;
		try {
			usuarioRol = home.findPoseeUsuarioRol(idUsuario,idRol);
			if (log.isDebugEnabled())
					log.debug("Se encontraron : <" + usuarioRol.size() +"> usuarios asociados al rol ");
		} catch (FinderException e) {
			log.info("No se encontraron usuarios asociados al Rol: " + e.getMessage());
			return cumple;
		}
		
		if (usuarioRol.size()>0){
			cumple = true;
		}
		return cumple;
	}


	/**
	 * RETORNA UNA LISTA DE USUARIOS ASOCIADOS A LAS HABILIDADES DEL ROL
	 * @param habilidadesDelRol
	 * @return usuariosDelRol
	 */
	public static ArrayList recuperaUsuariosHabilidadRol(ArrayList habilidadesDelRol) {
		ArrayList usuariosConHabilidadDelRolTemp = new ArrayList();
		ArrayList usuariosConHabilidadDelRol	 = new ArrayList();
		Habilidad_usuarioLocalHome home;
		int nroHabilidades = 0;
		int nroUsuarios = 0;
		boolean cumpleUsuario = false;	
		
		nroHabilidades = habilidadesDelRol.size();	/* cantidad de habilidades-valor a cumplir por el usuario */
		if (nroHabilidades>0) {						/* si hay habilidades para filtrar */
			rolHabilidad habilidad = (rolHabilidad) habilidadesDelRol.get(0);	/* recupera primera habilidad a filtrar */
																				/* recupera usuarios que cumplen con la primera habilidad-valor*/
			usuariosConHabilidadDelRolTemp = recuperaUsuariosConHabilidad(habilidad.getHabilidadId(),habilidad.getHabilidadValor());
														
			nroUsuarios = usuariosConHabilidadDelRolTemp.size();			/* nro. de usuarios que cumplen con la primera habilidad */
			
								/* si termina en false --> no cumple con todas las habilidades */
			for(int u=0;u <  nroUsuarios ; u++) {	/* PARA CADA USUARIO VERIFICA SI TIENE TODAS LAS HABILIDADES */
				cumpleUsuario = true;
				usuario usuarioPosible = (usuario) usuariosConHabilidadDelRolTemp.get(u);		/* para cada usuario de la lista */
				for(int h=1;h <  nroHabilidades ; h++) {										/* verifica si el usuario cumple con todas la habilidades */
					
					rolHabilidad otrasHabilidad = (rolHabilidad) habilidadesDelRol.get(h);
				
					if (!cumpleUsuariosConHabilidad(usuarioPosible.getUsuarioId(), 
					otrasHabilidad.getHabilidadId(), otrasHabilidad.getHabilidadValor())) {
								cumpleUsuario = false;		/* si no cumple con la habilidad */
													}
				}
				/* LUEGO DE REVIZAR TODAS LAS HABILIDADES PARA UN USUARIO */
				if (cumpleUsuario) {	/* Verifica si cumplio con todas */
					//log.debug("Entra a if.    cumpleUsuario = true ==> ");
					usuariosConHabilidadDelRol.add(new usuario(
						usuarioPosible.getUsuarioId(),
						null,
						usuarioPosible.getUsuarioIdca())); /* si cumple add a la lista de retorno */
						//log.debug("usuariosConHabilidadDelRol["+usuarioPosible.getUsuarioId()+",null,"+usuarioPosible.getUsuarioIdca()+"]");
				}
				
				
			}
		}
		return usuariosConHabilidadDelRol;
	}	
	
	/**
	 * RETORNA UNA LISTA DE USUARIOS QUE CUMPLEN CON EL ROL y SU SUPERVISOR
	 * @param usuariosHabilidad
	 * @param idRol
	 * @return usuariosAsociadosAlRol
	 */
	public static ArrayList recuperaUsuariosRol(ArrayList usuariosHabilidad,Long idRol) {
		ArrayList usuariosAsociadosAlRol	 = new ArrayList();
		
		int nroUsuariosHabilidad = 0;
		nroUsuariosHabilidad = usuariosHabilidad.size();
		for(int u=0;u <  nroUsuariosHabilidad ; u++) {	
			usuario usuarioRol = (usuario) usuariosHabilidad.get(u);		
			
			if (cumpleUsuariosConRol(usuarioRol.getUsuarioId(), idRol)) {	
				usuariosAsociadosAlRol.add(new usuario(
					usuarioRol.getUsuarioId(),
					retornaSupervisorDelUsuarioRol(usuarioRol.getUsuarioId(), idRol),
					usuarioRol.getUsuarioIdca())); 
				}
		}
		return usuariosAsociadosAlRol;
	}	
	
	
	
	/**
	 * RETORNA SUPERVISOR PARA UN USUARIO CON ROL Y HABILIDAD
	 * @param idUsuario
	 * @param idRol
	 * @return cumple
	 */
	public static Long retornaSupervisorDelUsuarioRol(Long idUsuario, Long idRol) {
		boolean cumple = false;
		Usuario_rolLocalHome home;
		Long idSupervisor = null;
		
		try {
			home = (Usuario_rolLocalHome)
					HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + Usuario_rolLocalHome.JNDI_NAME, e);
		}
		Usuario_rolLocal usuarioRol = null;
		try {
			usuarioRol = home.findSupervisorUsuarioRol(idUsuario,idRol);
			if (log.isDebugEnabled())
			{
					if(usuarioRol.getFk_supe_2_usro()!=null)
					log.debug("Id Supervisor encontrado : <" + usuarioRol.getFk_supe_2_usro().getPrimaryKey() +">  asociado al Usuarios-Rol ");
			}
		} catch (FinderException e) {
			log.info("No se encontraron usuarios asociados al Rol: " + e.getMessage());
			return idSupervisor;
		}
		
		if(usuarioRol.getFk_supe_2_usro()!=null)
			idSupervisor = (Long) usuarioRol.getFk_supe_2_usro().getPrimaryKey();
		return idSupervisor;
	}
	
	/**
	 * SUPERVISOR POR DEFECTO
	 * @param usuario
	 * @return
	 */
	public static Long retornaSupervisorPorDefecto(String usuario) {
		
		ParametroLocalHome home;
		Long idSupervisor = null;
		
		try {
			home = (ParametroLocalHome)
					HomeFactory.getHome(ParametroLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			throw new NestedRuntimeException("Problemas recuperando jndi " + ParametroLocalHome.JNDI_NAME, e);
		}
		
		ParametroLocal supervisor = null;
		try {
			supervisor = home.findBuscaValor(usuario);
			if (log.isDebugEnabled())
					log.debug("Id Supervisor encontrado : <" + supervisor.getPara_valor() +">  asociado al Usuarios-Rol ");
		} catch (FinderException e) {
			log.info("No se encontraron usuarios asociados al Rol: " + e.getMessage());
			return idSupervisor;
		}
		
		idSupervisor = Long.valueOf(supervisor.getPara_valor());
			
		return idSupervisor;
	}
	

}
