package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.RemoveException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Campo_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PerfilKey;
import co.com.telefonica.atiempo.ejb.eb.PerfilLocal;
import co.com.telefonica.atiempo.ejb.eb.PerfilLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.RolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;

import com.tecnonautica.utiles.ldap.SimpleLdap;
import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.CampoDTO;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.dto.PerfilDTO;
import com.telefonica_chile.bandeja.dto.RolDTO;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerAction;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;


public class UsuarioManager implements ManagerAction {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public DTO insert(DTO dto) throws ManagerException {
		UsuarioDTO u = (UsuarioDTO) dto;
		try {
			UsuarioLocal usuarioEntity = getHomeUsuario().create(u.getUsername(), u.getNombre());
			u.setId((Long) usuarioEntity.getPrimaryKey()); // Seteo el id para devolverlo
			usuarioEntity.setUsua_nombre(u.getNombre());
			usuarioEntity.setUsua_email(u.getEmail());
			usuarioEntity.setUsua_rut(u.getRut());
			usuarioEntity.setUsua_habilitado("S");
			usuarioEntity.setUsua_direccion(u.getDireccion());
			usuarioEntity.setUsua_telefono(u.getFono());
			usuarioEntity.setUsua_cargo(u.getCargo());
//
			SimpleLdap simpleLdap = SimpleLdap.getInstance();
			
			// TODO Verificar regla de negocio. Que pasa si el usuario ya existia en LDAP?
			if (! simpleLdap.existe(u.getUsername()))
				simpleLdap.insertaUsuario(u.getUsername(), u.getPassword());
			else
				simpleLdap.cambiaClave(u.getUsername(), u.getPassword());
		} catch (Exception e) {
			log.error("Problemas eliminando usuario", e);
			throw new ManagerException("Problemas creando usuario " + u.getUsername(), e);
		}
		
		return u;
	}

	public DTO update(DTO dto) throws ManagerException {
		UsuarioDTO u = (UsuarioDTO) dto;
		UsuarioLocal usuarioEntity;
		try {
			UsuarioKey key=new UsuarioKey(u.getId());
			usuarioEntity = getHomeUsuario().findByPrimaryKey(key);
		} catch (Exception e) {
			log.error("No se pudo encontrar usuario " + u.getId(), e);
			throw new ManagerException("No se pudo encontrar usuario " + u.getId(), e);
		}
		usuarioEntity.setUsua_nombre(u.getNombre());
		usuarioEntity.setUsua_email(u.getEmail());
		usuarioEntity.setUsua_rut(u.getRut());
		usuarioEntity.setUsua_habilitado(u.getHabilitado());
		usuarioEntity.setUsua_direccion(u.getDireccion());
		usuarioEntity.setUsua_telefono(u.getFono());
		usuarioEntity.setUsua_cargo(u.getCargo());

		usuarioEntity.setUsua_habilitado("S".equals(u.getHabilitado()) ? "S" : "N");
//		TODO: LCA: preguntar a jorge
//		if (u.getRolPorAgregar() != null) {
//			RolLocal rolEntity = getRolEntity(u.getRolPorAgregar());
//			usuarioEntity.agregaRol(rolEntity);
//		}
//		
//		if (u.getPerfilPorAgregar() != null) {
//			PerfilLocal perfilEntity = getPerfilEntity(u.getPerfilPorAgregar());
//			usuarioEntity.agregaPerfil(perfilEntity);
//		}
//
//		if (u.getRolPorEliminar() != null) {
//			RolLocal rolEntity = getRolEntity(u.getRolPorEliminar());
//			eliminarSupervisionesEnRol(usuarioEntity, (Long) rolEntity.getPrimaryKey());
//
//			usuarioEntity.eliminaRol(rolEntity);		
//		}
//		
//		if (u.getPerfilPorEliminar() != null) {
//			PerfilLocal perfilEntity = getPerfilEntity(u.getPerfilPorEliminar());
//			usuarioEntity.eliminaPerfil(perfilEntity);
//		}
//		
//		if (u.getParSupervisorPorAgregar() != null) {
//			ParUsuarioRol p = u.getParSupervisorPorAgregar();
//			crearJerarquia(p.getUsuario().getId(), u.getId(), p.getRol().getId());
//		}
//		
//		if (u.getParSupervisorPorEliminar() != null) {
//			ParUsuarioRol p = u.getParSupervisorPorEliminar();
//			eliminarJerarquia(p.getUsuario().getId(), u.getId(), p.getRol().getId());
//		}
//		
//		if (u.getCampoPorAgregar() != 0) {
//			Campo_variableLocal campoEntity = getCampoEntity(u.getCampoPorAgregar());
//			usuarioEntity.agregarCampo(campoEntity);
//		}
//		
//		if (u.getCampoPorEliminar() != 0) {
//			Campo_variableLocal campoEntity = getCampoEntity(u.getCampoPorEliminar());
//			usuarioEntity.eliminarCampo(campoEntity);
//		}
		
		return u;
	}

	private void eliminarSupervisionesEnRol(UsuarioLocal usuarioEntity, Long idRol) throws ManagerException {
		// Elimino a mis supervisores en el rol
		for (Iterator it = usuarioEntity.getJer_usuario_rol().iterator(); it.hasNext(); ) {
			Jer_usuario_rolLocal jer = (Jer_usuario_rolLocal) it.next();
			RolKey rolKey=(RolKey) jer.getFk_jerrol_rol().getPrimaryKey();
			if (rolKey.rol_id.equals(idRol))
				try {
					jer.remove();
				} catch (Exception e) {
					log.error("Problemas eliminando jerarquia " + jer, e);
					throw new ManagerException("Problemas eliminando jerarquia " + jer, e);
				}
		}

		// Elimino a mis SUPEVISADOS en el rol
		for (Iterator it = usuarioEntity.getJer_usuario_rol_1().iterator(); it.hasNext(); ) {
			Jer_usuario_rolLocal jer = (Jer_usuario_rolLocal) it.next();
			RolKey rolKey=(RolKey) jer.getFk_jerrol_rol().getPrimaryKey();
			if (rolKey.rol_id.equals(idRol))
				try {
					jer.remove();
				} catch (Exception e) {
					log.error("Problemas eliminando jerarquia " + jer, e);
					throw new ManagerException("Problemas eliminando jerarquia " + jer, e);
				}
		}
	}

	public void delete(DTO dto) throws ManagerException {
		UsuarioDTO usuario = (UsuarioDTO) dto;
		UsuarioLocal usuarioEntity = getUsuarioEntity(usuario.getId());
		try {
			usuarioEntity.remove();
		} catch (RemoveException e) {
			log.error("Problemas eliminando usuario " + usuario.getId(), e);
			throw new ManagerException("Problemas eliminando usuario " + usuario.getId(), e);
		}
	}

	public List list(HashMap filtros) throws ManagerException {
		return null;
	}

	public Tabla list(HashMap filtros, int page, int largoPagina) throws ManagerException {
		Tabla tabla = new TablaUsuario(page);
		tabla.setLargoPagina(largoPagina);
		try {
			tabla.retrieve(filtros);
		} catch (TablaException e) {
			throw new ManagerException("Problemas recuperando usuarios", e);
		}
		return tabla;
	}

	public DTO retrieve(DTO dto) throws ManagerException {
		UsuarioDTO usuario = (UsuarioDTO) dto;
		UsuarioLocal usuarioEntity = getUsuarioEntity(usuario.getId());
		usuario.setUsername(usuarioEntity.getUsua_login());
		usuario.setNombre(usuarioEntity.getUsua_nombre());
		usuario.setEmail(usuarioEntity.getUsua_email());
		usuario.setRut(usuarioEntity.getUsua_rut());
		usuario.setDireccion(usuarioEntity.getUsua_direccion());
		usuario.setFono(usuarioEntity.getUsua_telefono());
		usuario.setCargo(usuarioEntity.getUsua_cargo());
		usuario.setHabilitado(usuarioEntity.getUsua_habilitado());
	
		for (Iterator it = usuarioEntity.getUsuario_rol().iterator(); it.hasNext(); ) {
			RolLocal rolEntity = (RolLocal) it.next();
			RolDTO rol = new RolDTO();
			RolKey rolKey=(RolKey) rolEntity.getPrimaryKey();
			rol.setId(rolKey.rol_id);
			rol.setCodigo(rolEntity.getRol_codigo());
			usuario.addRol(rol);
		}

		for (Iterator it = usuarioEntity.getPerfil_usuario().iterator(); it.hasNext(); ) {
			PerfilLocal perfilEntity = (PerfilLocal) it.next();
			PerfilDTO perfil = new PerfilDTO();
			perfil.setId((Long) perfilEntity.getPrimaryKey());
			perfil.setDescripcion(perfilEntity.getPerf_descripcion());
			usuario.addPerfil(perfil);
		}

		// Saco los supervisores, con los roles en los cuales ocurre la supervision
		for (Iterator it = usuarioEntity.getJer_usuario_rol().iterator(); it.hasNext(); ) {
			Jer_usuario_rolLocal jerEntity = (Jer_usuario_rolLocal) it.next();

			UsuarioLocal usuarioSupervisorEntity = jerEntity.getFk_jerusuarol_usua();
			UsuarioDTO u = new UsuarioDTO();
			UsuarioKey key=(UsuarioKey) usuarioSupervisorEntity.getPrimaryKey();
			u.setId(key.usua_id );
			u.setUsername(usuarioSupervisorEntity.getUsua_login());

			RolLocal rolSupervisionEntity = jerEntity.getFk_jerrol_rol();
			RolDTO r = new RolDTO();
			RolKey key2=(RolKey) rolSupervisionEntity.getPrimaryKey();
			r.setId(key2.rol_id);
			r.setCodigo(rolSupervisionEntity.getRol_codigo());
			usuario.addParSupervisor(u, r);
		}

		// Campos Variables
		
		for (Iterator it = usuarioEntity.getCampos().iterator(); it.hasNext(); ) {
			Campo_variableLocal campoEntity = (Campo_variableLocal) it.next();
			Campo_variableKey pk = (Campo_variableKey)campoEntity.getPrimaryKey();
			CampoDTO campo = new CampoDTO();
			campo.setId(pk.cv_id.shortValue());
			campo.setNombreInterno(campoEntity.getCv_nombre_int());
			campo.setNombreExterno(campoEntity.getCv_nombre_ext());
			usuario.addCampoVariable(campo);
		}

		return usuario;
	}

	private UsuarioLocal getUsuarioEntity(Long idUsuario)throws ManagerException {
		UsuarioLocal usuarioEntity;
		try {
			UsuarioKey key=new UsuarioKey(idUsuario);
			usuarioEntity = getHomeUsuario().findByPrimaryKey(key);
		} catch (Exception e) {
			log.error("No se pudo recuperar usuario " + idUsuario);
			throw new ManagerException("No se pudo recuperar usuario " + idUsuario);
		}
		return usuarioEntity;
	}

	private PerfilLocal getPerfilEntity(Long idPerfil)
		throws ManagerException {
		PerfilLocal perfilEntity;
		try {
			PerfilKey perfilKey=new PerfilKey(idPerfil);
			perfilEntity = getHomePerfil().findByPrimaryKey(perfilKey);
		} catch (Exception e) {
			log.error("No se pudo encontrar perfil " + idPerfil, e);
			throw new ManagerException("No se pudo encontrar perfil " + idPerfil, e);
		}
		return perfilEntity;
	}

	private RolLocal getRolEntity(Long idRol) throws ManagerException {
		RolLocal rolEntity;
		try {
			RolKey key=new RolKey(idRol);
			rolEntity = getHomeRol().findByPrimaryKey(key);
		} catch (Exception e) {
			log.error("Problemas recuperando rol " + idRol, e);
			throw new ManagerException("No se pudo encontrar rol " + idRol, e);
		}
		return rolEntity;
	}

	private Campo_variableLocal getCampoEntity(short idCampo) throws ManagerException {
		try {
			Campo_variableLocalHome home = (Campo_variableLocalHome) getHomeCampoVariable();
			Campo_variableKey pk = new Campo_variableKey(new Short(idCampo));
			Campo_variableLocal local = home.findByPrimaryKey(pk);
			return local;
		} catch (Exception e) {
			log.error("Problemas recuperando campo " + idCampo, e);
			throw new ManagerException("No se pudo encontrar campo " + idCampo, e);
		}
	}

	private void crearJerarquia(Long idUsuarioSup, Long idUsuarioSub, Long idRol) throws ManagerException {
		try {
			Jer_usuario_rolLocalHome jHome = getHomeJerarquia();
			jHome.create(idUsuarioSup, idUsuarioSub, idRol);
		} catch (Exception e) {
			log.error("No se pudo crear jerarquia (usup, usub, idrol) = ("+idUsuarioSup+"," +idUsuarioSub+ "," +idRol+")", e);
			throw new ManagerException("No se pudo crear jerarquia (usup, usub, idrol) = ("+idUsuarioSup+"," +idUsuarioSub+ "," +idRol+")", e);
		}
	}

	private void eliminarJerarquia(Long idUsuarioSup, Long idUsuarioSub, Long idRol) throws ManagerException {
		try {
			Jer_usuario_rolLocalHome jHome = getHomeJerarquia();
			RolKey rolKey=new RolKey(idRol);
			UsuarioKey idUsuarioSupK=new UsuarioKey(idUsuarioSup);
			UsuarioKey idUsuarioSubK=new UsuarioKey(idUsuarioSub);
			Jer_usuario_rolKey pk = new Jer_usuario_rolKey(rolKey,idUsuarioSupK,idUsuarioSubK);
			Jer_usuario_rolLocal jerarquia = jHome.findByPrimaryKey(pk);
			jerarquia.remove();
		} catch (Exception e) {
			log.error("No se pudo eliminar jerarquia (usup, usub, idrol) = ("+idUsuarioSup+"," +idUsuarioSub+ "," +idRol+")", e);
			throw new ManagerException("No se pudo eliminar jerarquia (usup, usub, idrol) = ("+idUsuarioSup+"," +idUsuarioSub+ "," +idRol+")", e);
		}
	}

	private Jer_usuario_rolLocalHome getHomeJerarquia() throws NamingException {
		return (Jer_usuario_rolLocalHome) HomeFactory.getHome(Jer_usuario_rolLocalHome.JNDI_NAME);
	}

	private UsuarioLocalHome getHomeUsuario() throws NamingException {
		return (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
	}

	private RolLocalHome getHomeRol() throws NamingException {
		return (RolLocalHome) HomeFactory.getHome(RolLocalHome.JNDI_NAME);
	}

	private PerfilLocalHome getHomePerfil() throws NamingException {
		return (PerfilLocalHome) HomeFactory.getHome(PerfilLocalHome.JNDI_NAME);
	}

	private Campo_variableLocalHome getHomeCampoVariable() throws NamingException {
		return (Campo_variableLocalHome) HomeFactory.getHome(Campo_variableLocalHome.JNDI_NAME);
	}

}

