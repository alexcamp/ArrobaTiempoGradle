package com.telefonica_chile.bandeja.datos.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.MenuKey;
import co.com.telefonica.atiempo.ejb.eb.MenuLocal;
import co.com.telefonica.atiempo.ejb.eb.Menu_perfilLocal;
import co.com.telefonica.atiempo.ejb.eb.PerfilLocal;
import co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class UsuarioHelper {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public ArrayList recuperaMenues(Long usuaId) throws UsuariosException {
		ArrayList lista = new ArrayList();
		
		UsuarioLocal usuarioEntity = getUsuarioEntity(usuaId);

		ArrayList hijos = new ArrayList();	// Almacenamiento temporal de los hijos.
		HashMap todos = new HashMap();		// Me sirve para acceso rapido y para unificar.		

		for (Iterator itPerfilesUsuario = usuarioEntity.getPerfil_usuario().iterator(); itPerfilesUsuario.hasNext(); ) {
			Perfil_usuarioLocal perfilUsuarioEntity = (Perfil_usuarioLocal) itPerfilesUsuario.next();
			
			PerfilLocal perfilEntity = perfilUsuarioEntity.getFk_perfusu_perf();
//			PerfilLocal perfilEntity = (PerfilLocal) itPerfiles.next();
			for (Iterator itMenues = perfilEntity.getMenu_perfil().iterator(); itMenues.hasNext(); ) {
				Menu_perfilLocal menuPerfilEntity = (Menu_perfilLocal) itMenues.next();
				
				MenuLocal menuEntity = menuPerfilEntity.getFk_menperf_men(); 

				MenuKey menuKey=(MenuKey)menuEntity.getPrimaryKey();
				if (todos.containsKey(menuKey.mn_id))
					continue;	// Para unificar menues... [ emulando un 'select distinct' ]

				MenuDTO menu = new MenuDTO();
				menu.setMenuId(menuKey.mn_id);
				menu.setMenuDescripcion(menuEntity.getMn_descripcion());
				menu.setMenuOrden(menuEntity.getMn_orden());
				menu.setMenuUrl(menuEntity.getMn_url());
				menu.setMenuIdPadre(menuEntity.getMn_id_padre());
				todos.put(menu.getMenuId(), menu);
//				if (log.isDebugEnabled())
//					log.debug(menu.toString());
				if (menuEntity.getMn_id_padre() == null) {
					lista.add(menu);
				} else {
					hijos.add(menu);
				}
			}
		}
		
		lista = ordenarMenues(lista);
		hijos = ordenarMenues(hijos);
		
		// Reduzco los hijos
		for (Iterator it = hijos.iterator(); it.hasNext(); ) {
			MenuDTO hijo = (MenuDTO) it.next();
			MenuDTO padre = (MenuDTO)todos.get(hijo.getMenuIdPadre());
			if ( padre == null ) // No se encotrno el padre
				continue;
			padre.agregaHijo(hijo);
		}

		return lista;

	}

	public ArrayList recuperarRoles(Long usuaId) throws UsuariosException {
		//log.info("Buscando roles de usuario " + usuaId);
	
		ArrayList listadoRoles = new ArrayList();
	
		Collection listadoRolesEjb = new ArrayList();
				
		UsuarioLocal usuarioEjb = getUsuarioEntity(usuaId);

		if (usuarioEjb != null) {
			// Rescatar Roles del Usuario			
			listadoRolesEjb = usuarioEjb.getUsuario_rol_1();
			
			for (Iterator iter = listadoRolesEjb.iterator(); iter.hasNext();) {				
				Usuario_rolLocal uRolEjb = (Usuario_rolLocal) iter.next();

				RolLocal rolEjb = uRolEjb.getFk_usuarol_rol();
				RolDTO rol = new RolDTO();
				RolKey rolKey=(RolKey) rolEjb.getPrimaryKey();
				rol.setId(rolKey.rol_id);
				rol.setCodigo(rolEjb.getRol_codigo());
				rol.setNombre(rolEjb.getRol_nombre());				
				AplicacionLocal aplicacionEntityLocal = rolEjb.getFk_ap_2_rol();
				if (aplicacionEntityLocal != null) {
					rol.setPushApp(aplicacionEntityLocal.getAp_nombre());
				}
				listadoRoles.add(rol);
				//log.debug("Usuario tiene rol: [" + rol + "]");
			}					 			
		}
		//log.debug("Listado Roles size:"+listadoRoles.size());
		if(listadoRoles.size()>0)
			Collections.sort(listadoRoles);
		return listadoRoles;	
	}

	public UsuarioDTO recuperarUsuario(Long idUsuario) throws UsuariosException {
		UsuarioLocal usuarioEjb = getUsuarioEntity(idUsuario);

		UsuarioDTO usuario = new UsuarioDTO(idUsuario);
		usuario.setUsername(usuarioEjb.getUsua_login());			
		usuario.setRut(usuarioEjb.getUsua_rut());
		usuario.setEmail(usuarioEjb.getUsua_email());
		usuario.setCargo(usuarioEjb.getUsua_cargo());
		usuario.setNombre(usuarioEjb.getUsua_nombre());

		return usuario;
	}
	
	/**
	 * Recupera la lista de supervisores de un rol dado.
	 * 
	 * @param rolId codigo del rol
	 * @return una lista de UsuarioDTO, o una lista vacia si no se encontraron supervisores para el rol.
	 * @throws UsuariosException en caso de algun error.
	 * @author amartoq
	 */
	public List recuperarSupervisoresRol(Long idRol) throws UsuariosException {
		ArrayList list = new ArrayList();
		try {
			Jer_usuario_rolLocalHome home = (Jer_usuario_rolLocalHome) HomeFactory.getHome(Jer_usuario_rolLocalHome.JNDI_NAME);
			Collection col = home.findSupervisoresByRol(idRol);
			//log.debug("Buscando supervisores para rol: " + idRol);
			for (Iterator it = col.iterator(); it.hasNext();) {
				Jer_usuario_rolLocal jer = (Jer_usuario_rolLocal)it.next();
				UsuarioLocal user = jer.getFk_jerusuarol_usua();

				UsuarioDTO usuario = new UsuarioDTO((Long) user.getPrimaryKey());
				usuario.setUsername(user.getUsua_login());			
				usuario.setRut(user.getUsua_rut());
				usuario.setEmail(user.getUsua_email());
				usuario.setCargo(user.getUsua_cargo());
				usuario.setNombre(user.getUsua_nombre());
				
				list.add(usuario);
				//log.debug("agregando a lista de supervisores rol=[" + idRol + "]" + " usuario=[" + usuario + "]");
			}
		} catch (Exception e) {
			throw new UsuariosException("Error al obtener supervisores de un rol: " + e, e);
		}
		
		return list;
	}


	public ArrayList recuperarValoresVariables(Long usuaId) throws UsuariosException {
		
		ArrayList listadoCampos = new ArrayList();
		
		UsuarioLocal usuarioEjb = getUsuarioEntity(usuaId);
		Collection campos = usuarioEjb.getCampos();
		for (Iterator iter = campos.iterator(); iter.hasNext();) {
			Campo_variableLocal campoEjb = (Campo_variableLocal) iter.next();
			ValorVariableDTO vv = new ValorVariableDTO();
			vv.setNombre(campoEjb.getCv_nombre_ext());

			for (Iterator iterator = campoEjb.getValor_variable().iterator();iterator.hasNext();) {
				Valor_variableLocal valorEjb = (Valor_variableLocal) iterator.next();
				Valor_variableKey valorKey = (Valor_variableKey)valorEjb.getPrimaryKey();
				vv.setValorPorPeticion(valorKey.fk_valor_bi_bi_id,valorEjb.getValor());
			}

			listadoCampos.add(vv);				
		}

		return listadoCampos;
	}

	private UsuarioLocal getUsuarioEntity(Long idUsuario) throws UsuariosException {
		//log.info("Buscano entity de usuario " + idUsuario);
		try {
			UsuarioLocalHome usuarioHome = getHome();
			return usuarioHome.findByPrimaryKey(new UsuarioKey(idUsuario));
		} catch (Exception e) {
			throw new UsuariosException("Hubo problemas recuperando usuario " + idUsuario, e);
		}
	}
	private Usuario_rolLocal getUsuarioRolEntity(Long idUsuario, Long idRol) throws UsuariosException {
		//log.info("Buscando entity usuario rol para usuario " + idUsuario + " y rol " + idRol);
		try {
			Usuario_rolLocalHome usuarioHome = (Usuario_rolLocalHome)HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
			Usuario_rolKey id = new Usuario_rolKey(new RolKey(idRol),new UsuarioKey(idUsuario));
			
			return usuarioHome.findByPrimaryKey(id);
		} catch (Exception e) {
			throw new UsuariosException("Hubo problemas recuperando usuario ROL " + idUsuario, e);
		}
	}	

	private UsuarioLocalHome homeUsuario;
	private UsuarioLocalHome getHome() throws NamingException {
		if (homeUsuario == null)
			homeUsuario = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			
		return homeUsuario;
	}
	

	private ArrayList ordenarMenues(ArrayList lista) {
		Set set = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2) {
				MenuDTO m1 = (MenuDTO) o1;
				MenuDTO m2 = (MenuDTO) o2;
				return m1.getMenuId().compareTo(m2.getMenuId());
			}
		});
		set.addAll(lista);
		return new ArrayList(set);
	}

	/*
	public Collection getSegmentosEntity() throws UsuariosException {
		try {
			SegmentoLocalHome home =
					(SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
			return home.findAll();
		} catch (Exception e) {
			throw new UsuariosException("Problemas recuperando segmentos", e);
		}
	}
	*/
	
	public boolean updateEstadoUsuario(Long idUsuario , String habilitado) {
			
		try {
			//Load Usuario
			UsuarioLocal usuarioEntityLocal = getUsuarioEntity(idUsuario);
			//Seteo Estado de Usuario
			usuarioEntityLocal.setUsua_habilitado(habilitado);
			return true;
		} catch (Exception e) {
			log.warn("Hubo problemas recuperando usuario " + idUsuario, e);
			return false;			
		}			
	}
	public boolean updateEstadoRolUsuario(Long idUsuario ,Long rol, String habilitado) {
		Byte hab = null;
		byte hab1 = 0;
		byte hab2 = 1;
		try {
			//Load Usuario
			Usuario_rolLocalHome usRolHome = (Usuario_rolLocalHome)HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
			Usuario_rolKey id = new Usuario_rolKey(new RolKey(rol),new UsuarioKey(idUsuario));
			Usuario_rolLocal usRolLocal = usRolHome.findByPrimaryKey(id);
			//Seteo Estado de Usuario
			if (habilitado.equals("S"))
				hab = new Byte(hab2);
			if (habilitado.equals("N"))
				hab = new Byte(hab1);
			usRolLocal.setUsro_habilitado(new Short(hab.byteValue()));
			return true;
		} catch (Exception e) {
			log.warn("Hubo problemas recuperando usuario " + idUsuario, e);
			return false;			
		}			
	}	
}
