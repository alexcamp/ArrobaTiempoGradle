package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.PerfilKey;
import co.com.telefonica.atiempo.ejb.eb.PerfilLocal;
import co.com.telefonica.atiempo.ejb.eb.PerfilLocalHome;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.dto.PerfilDTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerAction;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;

public class PerfilManager implements ManagerAction {

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
			Collection c = getHomePerfil().findAll();
			for (Iterator it = c.iterator(); it.hasNext(); ) {
				PerfilLocal perfilEntity = (PerfilLocal) it.next();
				PerfilDTO perfil = new PerfilDTO();
				PerfilKey perfilKey=(PerfilKey) perfilEntity.getPrimaryKey();
				perfil.setId(perfilKey.perf_id );
				perfil.setDescripcion(perfilEntity.getPerf_descripcion());
				lista.add(perfil);
			}
		} catch (Exception e) {
			log.error(e);
			throw new ManagerException("Problemas recuperando perfiles", e);
		}
		return lista;
	}

	public Tabla list(HashMap filtros, int page, int largoPagina) throws ManagerException {
		return null;
	}

	public DTO retrieve(DTO dto) throws ManagerException {
		return null;
	}

	private PerfilLocalHome home;
	public PerfilLocalHome getHomePerfil() throws NamingException {
		if (home == null)
			home = (PerfilLocalHome) HomeFactory.getHome(PerfilLocalHome.JNDI_NAME);
		return home;
	}
}
