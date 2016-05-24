package com.telefonica_chile.bandeja.mantenedores.usuarios;

import java.util.HashMap;
import java.util.List;

import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerAction;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;


public class SupervisoresManager implements ManagerAction {
	public DTO insert(DTO dto) throws ManagerException {
		return null;
	}

	public DTO update(DTO dto) throws ManagerException {
		return null;
	}

	public void delete(DTO dto) throws ManagerException {
	}

	public List list(HashMap filtros) throws ManagerException {
		return null;
	}

	public Tabla list(HashMap filtros, int page, int largoPagina) throws ManagerException {
		Tabla tabla = new TablaBusquedaSupervisores(page);
		tabla.setLargoPagina(largoPagina);
		try {
			tabla.retrieve(filtros);
		} catch (TablaException e) {
			throw new ManagerException("TablaException listando supervisores", e);
		}
		return tabla;
	}

	public DTO retrieve(DTO dto) throws ManagerException {
		return null;
	}
}
