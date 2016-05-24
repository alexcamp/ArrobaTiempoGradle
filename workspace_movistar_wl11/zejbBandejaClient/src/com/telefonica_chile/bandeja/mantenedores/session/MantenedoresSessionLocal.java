package com.telefonica_chile.bandeja.mantenedores.session;
import java.util.HashMap;
import java.util.List;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;
/**
 * Local interface for Enterprise Bean: MantenedoresSession
 */
public interface MantenedoresSessionLocal extends javax.ejb.EJBLocalObject {
	public DTO insert(Class claseResponsable, DTO dto)
		throws ManagerException;
	public DTO update(Class claseResponsable, DTO dto)
		throws ManagerException;
	public void delete(Class claseResponsable, DTO dto)
		throws ManagerException;
	public List list(Class claseResponsable, HashMap filtros)
		throws ManagerException;
	public Tabla list(Class claseResponsable, HashMap filtros, int page)
		throws ManagerException;
	public DTO retrieve(Class claseResponsable, DTO dto)
		throws ManagerException;
	public Tabla list(
		Class claseResponsable,
		HashMap filtros,
		int page,
		int largoPagina)
		throws ManagerException;
}
