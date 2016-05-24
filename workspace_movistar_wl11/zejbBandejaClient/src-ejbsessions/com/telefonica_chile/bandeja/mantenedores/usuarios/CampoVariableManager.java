package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Campo_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocalHome;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.CampoDTO;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerAction;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;

public class CampoVariableManager implements ManagerAction {

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
		try {
			Campo_variableLocalHome home = getHomeCampoVariable();
			List lista = new ArrayList();
			for (Iterator it = home.findAll().iterator(); it.hasNext(); ) {
				Campo_variableLocal campoEntity = (Campo_variableLocal) it.next();
				CampoDTO campo = new CampoDTO();
				Campo_variableKey pk = (Campo_variableKey)campoEntity.getPrimaryKey();
				campo.setId(pk.cv_id.shortValue());
				campo.setNombreInterno(campoEntity.getCv_nombre_int());
				campo.setNombreExterno(campoEntity.getCv_nombre_ext());
				lista.add(campo);
			}
			return lista;
		} catch (Exception e) {
			log.error("No se pudo recuperar campos variables", e);
			throw new ManagerException("No se pudo recuperar campos variables", e);
		}
	}


	private Campo_variableLocalHome home;
	private Campo_variableLocalHome getHomeCampoVariable() throws NamingException {
		if (home == null) {
			home = (Campo_variableLocalHome) HomeFactory.getHome(Campo_variableLocalHome.JNDI_NAME);
		}
		return home;
	}

	public Tabla list(HashMap filtros, int page, int largoPagina)
		throws ManagerException {
		return null;
	}

	public DTO retrieve(DTO dto) throws ManagerException {
		return null;
	}

}
