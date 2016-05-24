package com.telefonica_chile.bandeja.servicios.publicador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.helpers.BandejaFactory;
import com.telefonica_chile.bandeja.helpers.CriterioBusquedaAmbiguoException;
import com.telefonica_chile.bandeja.helpers.FactoryException;
import com.telefonica_chile.bandeja.helpers.ParametrosIncorrectosException;
import com.telefonica_chile.bandeja.helpers.RegistroDuplicadoException;

/**
 * Bean implementation class for Enterprise Bean: PublicadorBandeja
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class PublicadorBandejaBean implements javax.ejb.SessionBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1238403397990207026L;

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

	public int nuevoRegistro(DatosPeticion dp)
		throws
			PublicadorException,
			ParametrosIncorrectosException,
			RegistroDuplicadoException {
		try {
			BandejaFactory bandejaFactory = new BandejaFactory();
			bandejaFactory.agregaPeticion(dp);
		} catch (FactoryException e) {
			log.error("FactoryException en Publicador:",e);
			getSessionContext().setRollbackOnly();
			throw new PublicadorException("No se pudo crear nuevo registro: " + e);
		} catch (ParametrosIncorrectosException e) {
			log.error("ParametrosIncorrectosException en Publicador:",e);
			throw new PublicadorException("No se pudo crear nuevo registro: " + e);
		} catch (RegistroDuplicadoException e) {
			log.error("RegistroDuplicadoException en Publicador:",e);
			throw new PublicadorException("No se pudo crear nuevo registro: " + e);
		} catch (NamingException e) {
			log.error("NamingException en Publicador:",e);
			throw new PublicadorException("No se pudo crear nuevo registro: " + e);
		} catch (FinderException e) {
			log.error("FinderException en Publicador:",e);
			throw new PublicadorException("No se pudo crear nuevo registro: " + e);
		}

		return 1;
	}

	public boolean actualizaRegistro(DatosPeticion dp)
		throws
			ParametrosIncorrectosException,
			CriterioBusquedaAmbiguoException,
			PublicadorException {
		try {
			BandejaFactory bandejaFactory = new BandejaFactory();
			return bandejaFactory.actualizaPeticion(dp);
		} catch (FactoryException e) {
			log.error("FactoryException en Publicador:",e);
			getSessionContext().setRollbackOnly();
			throw new PublicadorException(
				"No se pudo actualizar registro: " + e);
		}

	}

	public int eliminaRegistro(DatosPeticion dp)
		throws ParametrosIncorrectosException, PublicadorException {

		int eliminados = 0;
		try {
			BandejaFactory bandeja = new BandejaFactory();
			eliminados = bandeja.eliminaPeticiones(dp);
		} catch (FactoryException e) {
			// Elimino el SetRollbackOnly para el caso de la Despublicacion
			// Lo otro ser�a Marcar el MDB como New Transaction. Para que no se devuelva
			// el mensaje a la Cola. 
			  
			//getSessionContext().setRollbackOnly();
			log.error("FactoryException en Publicador:",e);
			throw new PublicadorException(
				"No se pudo eliminar registros con el criterio dado: " + e);
		}

		return eliminados;
	}

	public int cambiaUsuario(DatosPeticion dp)
		throws ParametrosIncorrectosException, PublicadorException {

		int cambiados = 0;
		try {
			BandejaFactory bandeja = new BandejaFactory();
			cambiados = bandeja.cambiaUsuario(dp);
		} catch (FactoryException e) {
			// Elimino el SetRollbackOnly para el caso de la Despublicacion
			// Lo otro ser�a Marcar el MDB como New Transaction. Para que no se devuelva
			// el mensaje a la Cola. 
			  
			//getSessionContext().setRollbackOnly();
			log.error("FactoryException en Publicador:",e);
			throw new PublicadorException(
				"No se pudo eliminar registros con el criterio dado: " + e);
		}

		return cambiados;
	}
	public void borrarPeticionBandejaIntegrada(Long nroPeticion) throws PublicadorException {
		try {
			log.debug("Borrando peticion nro " + nroPeticion);

			BintegradaLocalHome biLocalHome =
				(BintegradaLocalHome) HomeFactory.getHome(
					BintegradaLocalHome.JNDI_NAME);
			BintegradaLocal biLocal =
				biLocalHome.findByNroPeticion(nroPeticion);
			log.debug("Buscando valores variables asociados");
			if (biLocal.getValor_variable()!=null){
				Collection valoresVariables = biLocal.getValor_variable();
				ArrayList valores = new ArrayList();
				valores.addAll(valoresVariables);
				Iterator it = valores.iterator();
				while (it.hasNext()){
					log.debug("Eliminando valores variables");
					Valor_variableLocal valorVariableEntityLocal = (Valor_variableLocal)it.next();
					valorVariableEntityLocal.remove();
					log.debug("Valores variables eliminados");
				}
			}
									
			log.debug("Intentando eliminar peticion");
			
			biLocal.remove();
			log.debug("Peticion eliminada");
		} catch (Exception e) {
			String msg = "No se pudo eliminar registros con nroPeticion=["+nroPeticion + "] : " + e;
			log.error(msg, e);
			throw new PublicadorException(msg);
		}

	}
	
	/**
	 * Permite ocultar la peticion en la bandeja integrada.
	 * Cambia el valor del campo BI_VISIBLE a 0 en la tabla BINTEGRADA
	 * @param nroPeticion
	 * @throws PublicadorException
	 */
	public void ocultarPeticion(Long nroPeticion) throws PublicadorException {
		log.debug("Ocutando peticion " + nroPeticion);
		try {
			BintegradaLocalHome biLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			BintegradaLocal biLocal =	biLocalHome.findByNroPeticion(nroPeticion);
			biLocal.setBi_visible(new Integer(0));
			log.debug("Peticion ya no es visible");
		} catch (Exception e) {
			log.error("Exception en Publicador:",e);
			throw new PublicadorException("No se pudo ocultar la peticion "+ nroPeticion);
		}
	}
	
	/**
	 * Permite visualizar la peticion en la bandeja integrada
	 * Cambia el valor del campo BI_VISIBLE a 1 en la tabla BINTEGRADA
	 * @param nroPeticion
	 * @throws PublicadorException
	 */
	public void muestraPeticion(Long nroPeticion) throws PublicadorException {
		log.debug("Mostrando peticion " + nroPeticion);
		try {
			BintegradaLocalHome biLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			BintegradaLocal biLocal =	biLocalHome.findByNroPeticion(nroPeticion);
			biLocal.setBi_visible(new Integer(1));
			log.debug("Peticion ya es visible");
		} catch (Exception e) {
			log.error("Exception en Publicador:",e);
			throw new PublicadorException("No se pudo habilitar la peticion "+ nroPeticion);
		}
	}


	/**
		 * Permite ocultar la peticion en la bandeja integrada.
		 * Cambia el valor del campo BI_VISIBLE a 0 en la tabla BINTEGRADA
		 * Considera el numero de peticion y el id de aplicacion y deja no visible TODAS las peticiones 
		 * que encuentre con ese filtro.
		 * @param nroPeticion
		 * @param idAplicacion
		 * @throws PublicadorException
		 */
		public void ocultarPeticion(Long nroPeticion, Long idAplicacion) throws PublicadorException {
			log.debug("Ocutando peticion " + nroPeticion);
			try {
				BintegradaLocalHome biLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				Collection peticiones =	biLocalHome.findByIdAplicacionNroPeticion(idAplicacion, nroPeticion);
				Iterator it = peticiones.iterator();
				while (it.hasNext()){
					BintegradaLocal biLocal = (BintegradaLocal)it.next();
					biLocal.setBi_visible(new Integer(0));
				}				
				log.debug("Peticiones con Numero =["+nroPeticion+"] para la Aplicacion ID =["+idAplicacion+" ya NO son visibles");
			} catch (Exception e) {
				log.error("Exception en Publicador:",e);
				throw new PublicadorException("No se pudo ocultar la peticion "+ nroPeticion);
			}
		}
	
		/**
		 * Permite visualizar la peticion en la bandeja integrada
		 * Cambia el valor del campo BI_VISIBLE a 1 en la tabla BINTEGRADA
		 * Considera el numero de peticion y el id de aplicacion y deja visible TODAS las peticiones 
		 * que encuentre con ese filtro.
		 * @param nroPeticion
		 * @param idAplicacion
		 * @throws PublicadorException
		 */
		public void muestraPeticion(Long nroPeticion, Long idAplicacion) throws PublicadorException {
			log.debug("Mostrando peticion " + nroPeticion);
			try {
				BintegradaLocalHome biLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				Collection peticiones =	biLocalHome.findByIdAplicacionNroPeticion(idAplicacion, nroPeticion);
				Iterator it = peticiones.iterator();
				while (it.hasNext()){
					BintegradaLocal biLocal = (BintegradaLocal)it.next();
					biLocal.setBi_visible(new Integer(1));
				}
				log.debug("Peticiones con Numero =["+nroPeticion+"] para la Aplicacion ID =["+idAplicacion+" ya son visibles");
			} catch (Exception e) {
				log.error("Exception en Publicador:",e);
				throw new PublicadorException("No se pudo habilitar la peticion "+ nroPeticion);
			}
		}

		public int cuentaPublicacionesVisiblesIgualImplCorrelID( Long nroPeticion, String nomAplicacion,String codActividad, String actImplCorrelID)throws ParametrosIncorrectosException, PublicadorException {
			int cantidadPublic = 0;
			try {
				BandejaFactory bandeja = new BandejaFactory();
				cantidadPublic = bandeja.cuentaPublicacionesVisiblesIgualImplCorrelID(nroPeticion,nomAplicacion,codActividad,actImplCorrelID);
			} catch (FactoryException e) {
				// Elimino el SetRollbackOnly para el caso de la Despublicacion
				// Lo otro ser�a Marcar el MDB como New Transaction. Para que no se devuelva
				// el mensaje a la Cola. 
		  
				//getSessionContext().setRollbackOnly();
				log.error("FactoryException en Publicador:",e);
				throw new PublicadorException(
					"No se pudo contar registros con el criterio dado: " + e);
			}

			return cantidadPublic;
		}

}
