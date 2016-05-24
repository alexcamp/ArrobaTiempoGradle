package com.telefonica_chile.bandeja.usuario.sessions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Campo_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.MenuKey;
import co.com.telefonica.atiempo.ejb.eb.MenuLocal;
import co.com.telefonica.atiempo.ejb.eb.PerfilLocal;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.dto.CampoDTO;
import com.telefonica_chile.bandeja.dto.MenuDTO;
import com.telefonica_chile.bandeja.dto.RolDTO;
import com.telefonica_chile.bandeja.dto.UsuarioDTO;

public class UsuarioHelper {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public ArrayList recuperaMenues(Long usuaId) throws BandejaException {
		ArrayList lista = new ArrayList();
		
		UsuarioLocal usuarioEntity = getUsuarioEntity(usuaId);

		ArrayList hijos = new ArrayList();	// Almacenamiento temporal de los hijos.
		HashMap todos = new HashMap();		// Me sirve para acceso rapido y para unificar.		

		for (Iterator itPerfiles = usuarioEntity.getPerfil_usuario().iterator(); itPerfiles.hasNext(); ) {
			PerfilLocal perfilEntity = (PerfilLocal) itPerfiles.next();
			for (Iterator itMenues = perfilEntity.getMenu_perfil().iterator(); itMenues.hasNext(); ) {
				MenuLocal menuEntity = (MenuLocal) itMenues.next();
				MenuKey menuKey=(MenuKey) menuEntity.getPrimaryKey();
				if (todos.containsKey(menuKey.mn_id))
					continue;	// Para unificar menues... [ emulando un 'select distinct' ]

				MenuDTO menu = new MenuDTO();
				menu.setMenuId(menuKey.mn_id);
				menu.setMenuDescripcion(menuEntity.getMn_descripcion());
				menu.setMenuOrden(menuEntity.getMn_orden());
				menu.setMenuUrl(menuEntity.getMn_url());
				menu.setMenuIdPadre(menuEntity.getMn_id_padre());
				todos.put(menu.getMenuId(), menu);
				if (log.isDebugEnabled())
					log.debug(menu);
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
			padre.agregaHijo(hijo);
		}

		return lista;

	}

	public ArrayList recuperarRoles(Long usuaId) throws BandejaException {
	
		ArrayList listadoRoles = new ArrayList();
	
		Collection listadoRolesEjb = new ArrayList();
				
		UsuarioLocal usuarioEjb = getUsuarioEntity(usuaId);

		// Rescatar Roles del Usuario			
		listadoRolesEjb = usuarioEjb.getUsuario_rol();
		
		for (Iterator iter = listadoRolesEjb.iterator(); iter.hasNext();) {				
			RolLocal rolEjb = (RolLocal) iter.next();				
			RolDTO rol = new RolDTO();
			RolKey rolKey=(RolKey) rolEjb.getPrimaryKey();
			rol.setId(rolKey.rol_id);
			rol.setCodigo(rolEjb.getRol_codigo());
			rol.setNombre(rolEjb.getRol_nombre());				
			listadoRoles.add(rol);
		}					 			

		return listadoRoles;	
	}

	public UsuarioDTO recuperarUsuario(Long idUsuario) throws BandejaException {
		UsuarioLocal usuarioEjb = getUsuarioEntity(idUsuario);

		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setUsername(usuarioEjb.getUsua_login());			
		usuario.setRut(usuarioEjb.getUsua_rut());

		return usuario;
	}


	public ArrayList recuperarCamposVariables(Long usuaId) throws BandejaException {
		
		ArrayList listadoCampos = new ArrayList();
		
		UsuarioLocal usuarioEjb = getUsuarioEntity(usuaId);
		Collection campos = usuarioEjb.getCampos();
		for (Iterator iter = campos.iterator(); iter.hasNext();)
		{
			
			Campo_variableLocal campoEjb = (Campo_variableLocal) iter.next();

			CampoDTO campoDto = new CampoDTO();
			Campo_variableKey campoKey=(Campo_variableKey) campoEjb.getPrimaryKey();				
			campoDto.setId(campoKey.cv_id.shortValue());				
			campoDto.setNombreInterno(campoEjb.getCv_nombre_int());
			campoDto.setNombreExterno(campoEjb.getCv_nombre_ext());
			
			/***Aqui guardo los valores del campo de acuerdo a la peticion...*/
			HashMap valoresPorCampo = new HashMap();
			for (Iterator iterator = campoEjb.getValor_variable().iterator();iterator.hasNext();) {
				Valor_variableLocal valorEjb = (Valor_variableLocal) iterator.next();
				Valor_variableKey valorKey = (Valor_variableKey)valorEjb.getPrimaryKey();
				valoresPorCampo.put(valorKey.fk_valor_bi_bi_id,valorEjb.getValor());
			}

			/**encontrados los valores del campo se anexa a CampoDto*/
			campoDto.setValorVariables(valoresPorCampo);

			/**anexo a listado**/
			listadoCampos.add(campoDto);				
		}

		return listadoCampos;
	}

	public UsuarioLocal getUsuarioEntity(Long idUsuario) throws BandejaException {
		try {
			UsuarioLocalHome home = getHome();
			UsuarioLocal local = home.findByPrimaryKey(new UsuarioKey(idUsuario));
			return local;
		} catch (Exception e) {
			log.warn("Hubo problemas recuperando usuario " + idUsuario, e);
			throw new BandejaException("Hubo problemas recuperando usuario " + idUsuario, e);
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
}
