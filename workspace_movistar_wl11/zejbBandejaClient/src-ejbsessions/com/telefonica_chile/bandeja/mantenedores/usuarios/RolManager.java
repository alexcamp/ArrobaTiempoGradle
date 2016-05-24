package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.RolLocalHome;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.dto.RolDTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerAction;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;

public class RolManager implements ManagerAction {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public DTO insert(DTO dto) throws ManagerException {
		return null;
	}

	public DTO update(DTO dto) throws ManagerException {
		return null;
	}

	public void delete(DTO dto) throws ManagerException {
	}

	public List list(HashMap filtros) throws ManagerException {
		List lista = new ArrayList();

		try {
			RolLocalHome home = getHomeRol();
			Collection c = home.findAll();
			for (Iterator it = c.iterator(); it.hasNext(); ) {
				RolLocal rolEntity = (RolLocal) it.next();
				RolDTO rol = new RolDTO();
				RolKey rolKey=(RolKey) rolEntity.getPrimaryKey();
				rol.setId(rolKey.rol_id );
				rol.setCodigo(rolEntity.getRol_codigo());
				lista.add(rol);
			}
		} catch (Exception e) {
			log.error("Problemas recuperando roles", e);
			throw new ManagerException("Problemas recuperando roles", e);
		}
		return lista;
	}

	private RolLocalHome home;
	private RolLocalHome getHomeRol() throws NamingException {
		if (home == null)
			home = (RolLocalHome) HomeFactory.getHome(RolLocalHome.JNDI_NAME);
		return home;
	}

	public Tabla list(HashMap filtros, int page, int largoPagina) throws ManagerException {
		return null;
	}

	public DTO retrieve(DTO dto) throws ManagerException {
		return null;
	}

}
