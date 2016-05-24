package com.telefonica_chile.bandeja.mantenedores.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.tablas.Tabla;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.dto.DTO;
import com.telefonica_chile.bandeja.mantenedores.ManagerAction;
import com.telefonica_chile.bandeja.mantenedores.ManagerException;

/**
 * Bean implementation class for Enterprise Bean: MantenedoresSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class MantenedoresSessionBean implements javax.ejb.SessionBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 578673100580054109L;

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

	public DTO insert(Class claseResponsable, DTO dto) throws ManagerException {
		try {
			return getAccion(claseResponsable).insert(dto);
		} catch (ManagerException e) {
			getSessionContext().setRollbackOnly();
			throw e;
		} catch (Exception e) {
			getSessionContext().setRollbackOnly();
			log.error("Problemas insertando", e);
			throw new ManagerException(e);
		}
	}

	public DTO update(Class claseResponsable, DTO dto) throws ManagerException {
		try {
			return getAccion(claseResponsable).update(dto);
		} catch (ManagerException e) {
			getSessionContext().setRollbackOnly();
			throw e;
		} catch (Exception e) {
			getSessionContext().setRollbackOnly();
			log.error("Problemas actualizando", e);
			throw new ManagerException(e);
		}
	}

	public void delete(Class claseResponsable, DTO dto) throws ManagerException {
		try {
			getAccion(claseResponsable).delete(dto);
		} catch (ManagerException e) {
			getSessionContext().setRollbackOnly();
			throw e;
		} catch (Exception e) {
			getSessionContext().setRollbackOnly();
			log.error("Problemas eliminando", e);
			throw new ManagerException(e);
		}
	}

	public List list(Class claseResponsable, HashMap filtros) throws ManagerException {
		try {
			return getAccion(claseResponsable).list(filtros);
		} catch (ManagerException e) {
			throw e;
		} catch (Exception e) {
			log.error("Problemas listando", e);
			throw new ManagerException(e);
		}
	}

	public Tabla list(Class claseResponsable, HashMap filtros, int page) throws ManagerException {
		int largoPagina = 10;
		return list(claseResponsable, filtros, page, largoPagina);
	}

	public Tabla list(Class claseResponsable, HashMap filtros, int page, int largoPagina) throws ManagerException {
		try {
			return getAccion(claseResponsable).list(filtros, page, largoPagina);
		} catch (ManagerException e) {
			throw e;
		} catch (Exception e) {
			log.error("Problemas en listado paginado", e);
			throw new ManagerException(e);
		}
	}
	
	public DTO retrieve(Class claseResponsable, DTO dto) throws ManagerException {
		try {
			return getAccion(claseResponsable).retrieve(dto);
		} catch (ManagerException e) {
			throw e;
		} catch (Exception e) {
			log.error("Problemas recuperando dto", e);
			throw new ManagerException(e);
		}
	}

	private ManagerAction getAccion(Class claseResponsable) throws ManagerException {
		ManagerAction manager;
		try {
			manager = (ManagerAction) claseResponsable.newInstance();
		} catch (Exception e) {
			throw new ManagerException("No se conoce claseResponsable: " + claseResponsable);
		}
		return manager;
	}
}
