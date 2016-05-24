package com.telefonica_chile.comun.usuario_rol.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.HabilidadLocal;
import co.com.telefonica.atiempo.ejb.eb.HabilidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.asigna.dto.UsuarioRolDTO;
/**
 * Bean implementation class for Enterprise Bean: UsuarioRolSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class UsuarioRolSessionBean implements javax.ejb.SessionBean {
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
	
	public ArrayList rolesUsuarios(Long usuaRol){
		Collection roles=null;
		Usuario_rolLocalHome home;
		ArrayList arreglo=new ArrayList();
		UsuarioRolDTO usuaRolDto = new UsuarioRolDTO();
		try {
			home =	(Usuario_rolLocalHome) HomeFactory.getHome(	Usuario_rolLocalHome.JNDI_NAME );
			roles =home.findByRol(usuaRol);
			//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
			Usuario_rolLocal rolesEjb = null;
			UsuarioKey usuarioKey = null;
			for (Iterator iter = roles.iterator(); iter.hasNext();) {
				usuaRolDto = new UsuarioRolDTO();
				rolesEjb =	(Usuario_rolLocal) iter.next();
				usuarioKey=(UsuarioKey) rolesEjb.getFk_usuarol_usua().getPrimaryKey();
				usuaRolDto.setRolId(usuarioKey.usua_id);
				arreglo.add(usuaRolDto);
			}	
		} catch (FinderException e2) {
				log.warn("ArrayList rolesUsuarios FinderException = " + e2.toString());
		} catch (NamingException e1) {
				log.error("ArrayList rolesUsuarios NamingException = " + e1.toString());
		}
		return arreglo;
	}	

	/*
	 * Retorna Todos Los usuarios Que PErtenecen al ROL dado.
	 * OJO: Es lo mimso que el de arriba, pero se llenana mas datos del DTO
	 * y en el RolID se setea el id del ROL.
	 */
	public ArrayList usuariosByRol(Long idRol, String tipoHab) {
		ArrayList lista = new ArrayList();
		Long idHab = null;
		HashMap auxMap = null;

		// Cargamos las Habilidades, si es que las hay.
		if (tipoHab!=null && tipoHab.length()!=0)
			auxMap = buscarUsuariosHabilidad( tipoHab );

		try {
			Usuario_rolLocalHome urHome = (Usuario_rolLocalHome) HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
			Usuario_rolLocal urLocal = null;
			UsuarioLocal uLocal = null;
			UsuarioKey usuarioKey = null;
			RolKey rolKey = null;
			Collection c = urHome.findByRol( idRol );
			UsuarioRolDTO urDto = null;
			UsuarioLocal usuarioLocal = null;
			UsuarioLocal local = null;
			UsuarioKey key = null;
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				urLocal = (Usuario_rolLocal) it.next();
				uLocal = urLocal.getFk_usuarol_usua();
				urDto = new UsuarioRolDTO();
				urDto.setLogin( uLocal.getUsua_login() );
				urDto.setNombre( uLocal.getUsua_nombre() );
				usuarioLocal=urLocal.getFk_usuarol_usua();
				usuarioKey=(UsuarioKey) usuarioLocal.getPrimaryKey();
				rolKey=(RolKey)urLocal.getFk_usuarol_rol().getPrimaryKey();
				urDto.setUsuaId( usuarioKey.usua_id );
				urDto.setRolId( rolKey.rol_id );
				if(urLocal.getFk_supe_2_usro()!=null)
				urDto.setUsuaIdSup( (Long)urLocal.getFk_supe_2_usro().getPrimaryKey());
				int habInt = (urLocal.getUsro_habilitado() == null ) ? 0 : urLocal.getUsro_habilitado().intValue();
				urDto.setUsroHabilitado( new Long( habInt ) );
				
				if ( auxMap != null )
				{
					local =urLocal.getFk_usuarol_usua();
					key=(UsuarioKey) local.getPrimaryKey();
					urDto.setHabilidades( (HashMap) auxMap.get( key.usua_id ) );
				}

				lista.add( urDto );
			}	
		} catch (FinderException e) {
			log.warn("FinderException Buscando Usuarios de Rol [" + idRol + "]");
		} catch (NamingException e) {
			log.error("NamingException Buscando Usuarios de Rol [" + idRol + "]");
		}

		return lista;
	}

	private HashMap buscarUsuariosHabilidad( String tipoHabilidad ) {
		HashMap auxMap = null;

		try {
			HabilidadLocalHome habHome = (HabilidadLocalHome) HomeFactory.getHome(HabilidadLocalHome.JNDI_NAME);
			HabilidadLocal habLocal = habHome.findByNombre( tipoHabilidad );
			Collection c2 = habLocal.getHabilidad_usuario();
			if ( c2 != null) {
				Habilidad_usuarioLocal huLocal = null;
				UsuarioKey usuarioKey = null;
				HashMap aux = null;
				for (Iterator it=c2.iterator(); it.hasNext(); ) {
					 huLocal = (Habilidad_usuarioLocal) it.next();
					if ( huLocal == null )
						continue;
						
					if ( auxMap == null )
						auxMap = new HashMap();
					usuarioKey=(UsuarioKey) huLocal.getFk_usua_2_husa().getPrimaryKey();
					aux = (HashMap) auxMap.get( usuarioKey.usua_id );
					if ( aux == null )
						aux = new HashMap();
					aux.put( huLocal.getHusu_valor(), "1" );
					auxMap.put( usuarioKey.usua_id, aux );
				}
			}
		} catch (NamingException e) {
			log.error("NamingException. No pude Cargar Habilidades.");
		} catch (FinderException e) {
			log.warn("FinderException. No pude Cargar Habilidades.");
		} catch (Exception e) {
			log.error("Exception. No pude Cargar Habilidades.", e);
		}
		return auxMap;
	}
}
