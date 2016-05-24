package com.telefonica_chile.bandeja.mantenedores;

import java.util.HashMap;
import java.util.List;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.dto.DTO;

public interface ManagerAction {
	public DTO insert(DTO dto) throws ManagerException;
	public DTO update(DTO dto) throws ManagerException;
	public void delete(DTO dto) throws ManagerException;
	public List list(HashMap filtros) throws ManagerException;
	public Tabla list(HashMap filtros, int page, int largoPagina) throws ManagerException;
	public DTO retrieve(DTO dto) throws ManagerException;
}
