package com.telefonica_chile.bandeja.torreControl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.AplicacionKey;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.MunicipioLocal;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: ParametrosTCSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ParametrosTCSessionBean implements javax.ejb.SessionBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7860562196829898015L;

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


	//Metodo que retorna los nombres de la entidad Aplicacion
	public TreeMap getNombreAplicacion() {
		AplicacionLocal aplicacion = null;
		Collection elementos;

		try {
			AplicacionLocalHome home =
				(AplicacionLocalHome) HomeFactory.getHome(AplicacionLocalHome.JNDI_NAME);
			elementos = home.findAll();
		} catch (NamingException e) {
			log.debug(
				"Ocurrio problema al asignar JNDI para EJB AplicacionEntity...");
			return null;
		} catch (FinderException e) {
			log.debug("Ocurrio problema por problema de base de datos...");
			return null;
		}

		TreeMap tm = new TreeMap();
		Iterator it = elementos.iterator();
		while (it.hasNext()) {
			aplicacion = (AplicacionLocal) it.next();
			AplicacionKey aplicacionKey=(AplicacionKey) aplicacion.getPrimaryKey();
			tm.put(aplicacion.getAp_nombre(), aplicacionKey.ap_id );
		}

		return tm;
	}

	//Metodo que retorna los nombres de la entidad Famllia Producto
	// CR16429 FindAll - ana santos
	/*public TreeMap getNombreFamiilaProductoServicio()
	{
		Familia_producto_servicioLocal familia = null;
		Collection elementos;

		try {
			Familia_producto_servicioLocalHome home =
				(Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
			elementos = home.findAll();
		} catch (NamingException e) {
			log.debug(
				"Ocurrio problema al asignar JNDI para EJB FamiliaProductoServicio...");
			return null;
		} catch (FinderException e) {
			log.debug("Ocurrio problema por problema de base de datos...");
			return null;
		}

		TreeMap tm = new TreeMap();
		Iterator it = elementos.iterator();
		while (it.hasNext()) {
			familia = (Familia_producto_servicioLocal) it.next();
			Familia_producto_servicioKey key=(Familia_producto_servicioKey) familia.getPrimaryKey();
			tm.put(familia.getFaps_nombre(), key.faps_id);
		}

		return tm;
	}*/

	public TreeMap getNombreDepartamento() {
		Collection elementos;

		TreeMap tm = new TreeMap();

		try {
			DepartamentoLocalHome deptoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			Collection c = deptoHome.findAll();
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				DepartamentoLocal deptoLocal = (DepartamentoLocal) it.next();
				DepartamentoKey deptoKey = (DepartamentoKey) deptoLocal.getPrimaryKey();
				tm.put(deptoLocal.getNombre_departamento() ,deptoKey.cod_dpt);
			}
		} catch (NamingException e) {
			log.debug("Ocurrio problema al asignar JNDI para EJB FamiliaProductoServicio...");
			return null;
		} catch (FinderException e) {
			log.debug("Ocurrio problema por problema de base de datos...");
			return null;
		}

		return tm;
	}

	//Metodo que retorna los nombres de la entidad Segmento
	/*
	public TreeMap getNombreSegmento() {
		SegmentoLocal segmento = null;
		Collection elementos;

		try {
			SegmentoLocalHome home = (SegmentoLocalHome) HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
			elementos = home.findAll();
		} catch (NamingException e) {
			log.debug(
				"Ocurrio problema al asignar JNDI para EJB Segmento...");
			return null;
		} catch (FinderException e) {
			log.debug("Ocurrio problema por problema de base de datos...");
			return null;
		}

		TreeMap tm = new TreeMap();
		Iterator it = elementos.iterator();
		while (it.hasNext()) {
			segmento = (SegmentoLocal) it.next();
			SegmentoKey key=(SegmentoKey) segmento.getPrimaryKey();
			tm.put(segmento.getSegm_descripcion(), key.segm_id );
		}

		return tm;
	}
	*/
	//	Metodo que retorna los nombres de la entidad Tipo Trabajo
	  public TreeMap getNombreTiopTrabajo() {
	  	//TODO: revisar tabla tipo trabajo
//		  Tipo_TrabajoLocal trabajo = null;
//		  Collection elementos;

//		  try {
//			TipoTrabajoLocalHome home = (TipoTrabajoLocalHome) HomeFactory.getHome(TipoTrabajoLocalHome.JNDI_NAME);
//			  elementos = home.findAll();
//		  } catch (NamingException e) {
//			  log.debug(
//				  "Ocurrio problema al asignar JNDI para EJB Tipo Trabajo...");
//			  return null;
//		  } catch (FinderException e) {
//			  log.debug("Ocurrio problema por problema de base de datos...");
//			  return null;
//		  }

			TreeMap tm = new TreeMap();
//		  Iterator it = elementos.iterator();
//		  while (it.hasNext()) {
//			  trabajo = (TipoTrabajoLocal) it.next();
//			  tm.put(trabajo.getNombre(), (Integer) trabajo.getPrimaryKey());
//		  }

		  return tm;
	  }
	  
//	Metodo que retorna los nombres de la entidad ISP
	  public TreeMap getNombreISP() {
//		  ISPEntityLocal isp = null;
//		  Collection elementos;
//
//		  try {
//			ISPEntityLocalHome home = (ISPEntityLocalHome) HomeFactory.getHome(ISPEntityLocalHome.JNDI_NAME);
//			  elementos = home.findAll();
//		  } catch (NamingException e) {
//			  log.debug(
//				  "Ocurrio problema al asignar JNDI para EJB ISP...");
//			  return null;
//		  } catch (FinderException e) {
//			  log.debug("Ocurrio problema por problema de base de datos...");
//			  return null;
//		  }

		  TreeMap tm = new TreeMap();
//		  Iterator it = elementos.iterator();
//		  while (it.hasNext()) {
//			  isp = (ISPEntityLocal) it.next();
//			  tm.put(isp.getCodigo(), (Long)isp.getPrimaryKey());
//		  }
		  return tm;
	  }

	public HashMap buscarLocalidadesByDepartamento( String codDepto ) {
		HashMap hashLocalidad = new HashMap();
		try {
			DepartamentoLocalHome deptoHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			DepartamentoLocal deptoLocal = deptoHome.findByPrimaryKey( new DepartamentoKey(codDepto) );
			
			Collection c = deptoLocal.getMunicipio();
			if ( c!=null )
				for (Iterator it=c.iterator(); it.hasNext(); ) {
					MunicipioLocal mnLocal = (MunicipioLocal) it.next();
					Collection c1 = mnLocal.getLocalidad();
					for ( Iterator it1=c1.iterator(); it1.hasNext(); ) {
						LocalidadLocal locLocal = (LocalidadLocal) it1.next();
						LocalidadKey locKey = (LocalidadKey) locLocal.getPrimaryKey();
						hashLocalidad.put( locKey.cod_loc, locLocal.getNombre_localidad() );
					}
				}
		} catch (EJBException e) {
			
			e.printStackTrace();
		} catch (NamingException e) {
			
			e.printStackTrace();
		} catch (FinderException e) {
			
			e.printStackTrace();
		}
		
		return hashLocalidad;

	}
}
