package com.telefonica_chile.comun.cierre_observaciones.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CausaKey;
import co.com.telefonica.atiempo.ejb.eb.CausaLocal;
import co.com.telefonica.atiempo.ejb.eb.Causa_cierreLocal;
import co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey;
import co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal;
import co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: CierreActividadSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class CierreActividadSessionBean implements javax.ejb.SessionBean {
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

	/** Métoodo que retorna CIERRE de acuerdo a la Actividad **/
	public ArrayList recuperaCierre(String actividad, Long idAplicacion) {
		ArrayList listAllCierre = new ArrayList();
		//Declaro Entity Actividad
		try {
			ActividadLocalHome home = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal local = home.findByCodigoActividadIdAplicacion(actividad, idAplicacion);

			Long idActividad;
			ActividadKey actividadKey = (ActividadKey) local.getPrimaryKey();
			idActividad = actividadKey.act_id;
			try {
				//CierreActividad
				Collection collectionCierre = (Collection) local.getCierre_actividad();
				
				log.debug("Entra en cargue de todas las opciones de cierre asociadas a la actividad " + idActividad.toString() + " " + actividad);
				for (Iterator iter = collectionCierre.iterator(); iter.hasNext();) {					
					
					Cierre_actividadLocal element = (Cierre_actividadLocal) iter.next();

					Map cierre = new HashMap();
					Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) element.getPrimaryKey();
					cierre.put("id", cierre_actividadKey.ciac_id);
					cierre.put("desc", element.getCiac_nombre());
					String name = element.getCiac_nombre();

					ArrayList aux = new ArrayList();
					boolean agregado = false;
					HashMap hashAux = null;
					for (int j = 0; j < listAllCierre.size(); j++) {
						hashAux = (HashMap) listAllCierre.get(j);
						if (hashAux == null)
							continue;
						String oldName = (String) hashAux.get("desc");
						if (!agregado && oldName != null && oldName.compareTo(name) > 0) {
							aux.add(cierre);
							agregado = true;
						}
						aux.add(hashAux);
					}
					if (!agregado)
						aux.add(cierre);

					listAllCierre = aux;
				}
			} catch (Exception e) {
				
				log.debug("error recuperaCierre");
				log.debug("Mensaje de Error ==> " + e.toString());
				e.printStackTrace();
			}
		} catch (NamingException e) {
			log.error("Referencia incorrecta. " + e);
			return null;
		} catch (FinderException e) {
			log.warn("No existen registros en Actividad. " + e);
			return null;
		}

		return listAllCierre;
	}
	/** ESTO ES EL ENTITY PARA ACTIVIDAD: obtengo por Código de Actividad **/
	public ActividadLocal getIdActEntity(String codigo, Long idAplicacion) {
		try {
			ActividadLocalHome home = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal local = home.findByCodigoActividadIdAplicacion(codigo, idAplicacion);
			return local;
		} catch (Exception e) {
			log.error("No existen registros en Actividad. " + e);
			return null;
		}
	}

	public ArrayList recuperaCausa(String actividad, Integer idAmbito, Long idAplicacion) {

		ArrayList listAllCausa = new ArrayList();
		ActividadLocal actividadLocal;
		log.debug("entro a recuperaCausa con la actividad =>" + actividad + " ambito => " + idAmbito);
		try {
			ActividadLocalHome home = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			actividadLocal = home.findByCodigoActividadIdAplicacion(actividad, idAplicacion);

			Long idActividad;
			ActividadKey actividadKey = (ActividadKey) actividadLocal.getPrimaryKey();
			idActividad = actividadKey.act_id;
			try {
				Collection collectionCierre = (Collection) actividadLocal.getCierre_actividad();
				for (Iterator iter = collectionCierre.iterator(); iter.hasNext();) {

					Cierre_actividadLocal element = (Cierre_actividadLocal) iter.next();
					Collection collectionCausa = (Collection) element.getCausa_cierre();

					for (Iterator iter1 = collectionCausa.iterator(); iter1.hasNext();) {
						Causa_cierreLocal elementCausa = (Causa_cierreLocal) iter1.next();

						CausaLocal fk_caus_caci = elementCausa.getFk_caus_caci();
						String name = fk_caus_caci.getCaus_nombre();
						Map causa = new HashMap();
						//log.debug("elementCausa.getAmbito() =>" + elementCausa.getIdAmbito());
						if (elementCausa.getAmbi_id().equals(idAmbito)) {
							CausaKey caKey = (CausaKey) fk_caus_caci.getPrimaryKey();
							Cierre_actividadKey ciKey = (Cierre_actividadKey) elementCausa.getFk_ciac_2_caci().getPrimaryKey();
							
							causa.put("id", caKey.caus_id);
							causa.put("idCierre", ciKey.ciac_id);
							causa.put("desc", fk_caus_caci.getCaus_nombre());
							causa.put("cod", fk_caus_caci.getCaus_codigo());
						}

						ArrayList aux = new ArrayList();
						boolean agregado = false;
						HashMap hashAux = null;
						for (int j = 0; j < listAllCausa.size(); j++) {
							hashAux = (HashMap) listAllCausa.get(j);
							if (hashAux == null)
								continue;
							String oldName = (String) hashAux.get("desc");
							if (!agregado && oldName != null && oldName.compareTo(name) > 0) {
								aux.add(causa);
								agregado = true;
							}
							aux.add(hashAux);
						}
						if (!agregado)
							aux.add(causa);

						listAllCausa = aux;
					}
				}
				log.debug("listAllCausa.size() =>" + listAllCausa.size());
			} catch (Exception e) {
				
				log.debug("Exception:",e);
			}
		} catch (NamingException e1) {
			log.debug("Referencia incorrecta. " + e1);
			return null;
		} catch (FinderException e1) {
			log.debug("No existen registros en Actividad. " + e1);
		}
		return listAllCausa;
	}

	public CierreActividadDTO recuperaCierreActividadId(Long id) {
		log.debug("SESSION REMOTO Q RETORNA LISTADO DE CIERRE");

		//Declaro Entity Actividad
		Cierre_actividadLocal Cierre_actividadLocal = (Cierre_actividadLocal) getCierreActEntity(id);
		CierreActividadDTO dto = new CierreActividadDTO();

		//id
		Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) Cierre_actividadLocal.getPrimaryKey();
		dto.setId(cierre_actividadKey.ciac_id);

		//idAct
		ActividadKey actividadKey = (ActividadKey) Cierre_actividadLocal.getFk_cierre_act().getPrimaryKey();
		dto.setIdAct(actividadKey.act_id);

		//nombre Cierre Actividad
		dto.setNombre(Cierre_actividadLocal.getCiac_nombre());

		//codigo Cierre Actividad
		dto.setCodigo(Cierre_actividadLocal.getCiac_codigo());
		dto.setValor(Cierre_actividadLocal.getCiac_valor());

		//reversa Cierre Actividad
		dto.setReversa(new Byte(Cierre_actividadLocal.getCiac_reversa().byteValue()));

		return dto;
	}

	public CierreActividadDTO recuperaCierreActividadCiacNombre(Long idActividad) {
		log.debug("SESSION REMOTO Q RETORNA LISTADO DE CIERRE");

		//Declaro Entity Actividad
		Cierre_actividadLocal Cierre_actividadLocal = (Cierre_actividadLocal) getCierreActividadEntityCiacNombre(idActividad);
		CierreActividadDTO dto = new CierreActividadDTO();

		Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) Cierre_actividadLocal.getPrimaryKey();
		dto.setId(cierre_actividadKey.ciac_id);

		//idAct
		ActividadKey actividadKey = (ActividadKey) Cierre_actividadLocal.getFk_cierre_act().getPrimaryKey();
		dto.setIdAct(actividadKey.act_id);

		//nombre Cierre Actividad
		dto.setNombre(Cierre_actividadLocal.getCiac_nombre());

		//codigo Cierre Actividad
		dto.setCodigo(Cierre_actividadLocal.getCiac_codigo());

		//reversa Cierre Actividad
		dto.setReversa(new Byte(Cierre_actividadLocal.getCiac_reversa().byteValue()));
		dto.setValor(Cierre_actividadLocal.getCiac_valor());

		return dto;
	}

	public CierreActividadDTO cancelarActividad(Long idActividad) {
		log.debug("SESSION REMOTO Q RETORNA LISTADO DE CIERRE");
		//Declaro Entity Actividad

		try {
			Cierre_actividadLocalHome cierreActividadHome = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal local = cierreActividadHome.findCancelarAct(idActividad);
			CierreActividadDTO dto = new CierreActividadDTO();
			Cierre_actividadKey key = (Cierre_actividadKey) local.getPrimaryKey();
			dto.setId(key.ciac_id);
			ActividadKey actividadKey = (ActividadKey) local.getFk_cierre_act().getPrimaryKey();
			dto.setIdAct(actividadKey.act_id);
			dto.setNombre(local.getCiac_nombre());
			dto.setCodigo(local.getCiac_codigo());
			dto.setReversa(new Byte(local.getCiac_reversa().byteValue()));
			dto.setValor(local.getCiac_valor());
			return dto;
		} catch (Exception e) {
			log.info("CierreActividadDTO cancelarActividad (Actividad) => (" + idActividad + ")");
			log.error("Exception:", e);
			return null;
		}

	}

	public CierreActividadDTO recuperaCierreActividadProblema(Long idActividad) {
		log.debug("SESSION REMOTO Q RETORNA LISTADO DE CIERRE PROBLEMA");

		//Declaro Entity Actividad
		Cierre_actividadLocal Cierre_actividadLocal = (Cierre_actividadLocal) getCierreActividadEntityProblema(idActividad);
		CierreActividadDTO dto = new CierreActividadDTO();

		Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) Cierre_actividadLocal.getPrimaryKey();
		dto.setId(cierre_actividadKey.ciac_id);

		//idAct
		ActividadKey actividadKey = (ActividadKey) Cierre_actividadLocal.getFk_cierre_act().getPrimaryKey();
		dto.setIdAct(actividadKey.act_id);

		//nombre Cierre Actividad
		dto.setNombre(Cierre_actividadLocal.getCiac_nombre());

		//codigo Cierre Actividad
		dto.setCodigo(Cierre_actividadLocal.getCiac_codigo());

		//reversa Cierre Actividad
		dto.setReversa(new Byte(Cierre_actividadLocal.getCiac_reversa().byteValue()));
		dto.setValor(Cierre_actividadLocal.getCiac_valor());

		return dto;
	}

	public CierreActividadDTO recuperaCancelacionActividadCiacNombre(Long idActividad) {
		log.debug("SESSION REMOTO Q RETORNA LISTADO DE CIERRE");

		//Declaro Entity Actividad
		Cierre_actividadLocal Cierre_actividadLocal = (Cierre_actividadLocal) getCancelarActividadEntityCiacNombre(idActividad);
		CierreActividadDTO dto = new CierreActividadDTO();

		//id
		Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) Cierre_actividadLocal.getPrimaryKey();
		dto.setId(cierre_actividadKey.ciac_id);

		//idAct
		ActividadKey actividadKey = (ActividadKey) Cierre_actividadLocal.getFk_cierre_act().getPrimaryKey();
		dto.setIdAct(actividadKey.act_id);

		//nombre Cierre Actividad
		dto.setNombre(Cierre_actividadLocal.getCiac_nombre());

		//codigo Cierre Actividad
		dto.setCodigo(Cierre_actividadLocal.getCiac_codigo());

		//reversa Cierre Actividad
		dto.setReversa(new Byte(Cierre_actividadLocal.getCiac_reversa().byteValue()));
		dto.setValor(Cierre_actividadLocal.getCiac_valor());

		return dto;
	}

	/*retorno registro con idActividad dado y cuyo 	Nombre de cierre tenga
	 * dentro de su string la cadena Termina
	 */
	public CierreActividadDTO getCierreByIdAndNombre(Long idActividad) {
		CierreActividadDTO dto = new CierreActividadDTO();
		//Declaro Entity Actividad

		try {
			Cierre_actividadLocalHome home = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal Cierre_actividadLocal = home.findByIdActAndLike(idActividad, "Termina%");
			Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) Cierre_actividadLocal.getPrimaryKey();
			dto.setId(cierre_actividadKey.ciac_id);

			//idAct
			ActividadKey actividadKey = (ActividadKey) Cierre_actividadLocal.getFk_cierre_act().getPrimaryKey();
			dto.setIdAct(actividadKey.act_id);

			//nombre Cierre Actividad
			dto.setNombre(Cierre_actividadLocal.getCiac_nombre());

			//codigo Cierre Actividad
			dto.setCodigo(Cierre_actividadLocal.getCiac_codigo());

			//reversa Cierre Actividad
			dto.setReversa(new Byte(Cierre_actividadLocal.getCiac_reversa().byteValue()));
			dto.setValor(Cierre_actividadLocal.getCiac_valor());

		}catch (FinderException e) {
			log.warn("No se encontro cierre.");
		}catch (Exception e) {
			log.error("Exception:",e);
		}

		return dto;
	}

	/*retorno registro con idActividad dado y cuyo 	Nombre de cierre tenga
	 * dentro de su string la cadena Solucion
	 */
	public CierreActividadDTO getCierrePendiente(Long idActividad) {
		CierreActividadDTO dto = new CierreActividadDTO();
		//Declaro Entity Actividad

		try {
			Cierre_actividadLocalHome home = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal Cierre_actividadLocal = home.findByIdActAndLike(idActividad, "Solucion%");
			//id
			Cierre_actividadKey cierre_actividadKey = (Cierre_actividadKey) Cierre_actividadLocal.getPrimaryKey();
			dto.setId(cierre_actividadKey.ciac_id);

			//idAct
			ActividadKey actividadKey = (ActividadKey) Cierre_actividadLocal.getFk_cierre_act().getPrimaryKey();
			dto.setIdAct(actividadKey.act_id);

			//nombre Cierre Actividad
			dto.setNombre(Cierre_actividadLocal.getCiac_nombre());

			//codigo Cierre Actividad
			dto.setCodigo(Cierre_actividadLocal.getCiac_codigo());

			//reversa Cierre Actividad
			dto.setReversa(new Byte(Cierre_actividadLocal.getCiac_reversa().byteValue()));
			dto.setValor(Cierre_actividadLocal.getCiac_valor());


		}catch (FinderException e) {
			log.warn("No se encontro cierre.");
		}catch (Exception e) {
			log.error("Exception:",e);
		}

		return dto;
	}

	/** ESTO ES EL ENTITY PARA ACTIVIDAD: obtengo por Código de Actividad **/
	public Cierre_actividadLocal getCierreActEntity(Long id) {
		try {
			Cierre_actividadLocalHome cierreActividadHome = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal local = (Cierre_actividadLocal) cierreActividadHome.findByPrimaryKey(new Cierre_actividadKey(id));
			return local;

		}catch (FinderException e) {
			log.warn("No se encontro cierre.");
		}catch (Exception e) {
			log.error("Exception:",e);
		}
		return null;
	}

	/** ESTO ES EL ENTITY PARA ACTIVIDAD: obtengo cierre para (ACCIÓN MASIVA TERMINAR) **/
	public Cierre_actividadLocal getCierreActividadEntityCiacNombre(Long idActividad) {
		try {
			Cierre_actividadLocalHome cierreActividadHome = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal local = cierreActividadHome.findByIdActAndLike(idActividad, "Terminar%");
			return local;
		}catch (FinderException e) {
			log.warn("No se encontro cierre.");
		}catch (Exception e) {
			log.error("Exception:",e);
		}
		return null;
	}

	/** ESTO ES EL ENTITY PARA ACTIVIDAD: obtengo Cierre para (ACCIÓN MASIVA CANCELAR) **/
	public Cierre_actividadLocal getCancelarActividadEntityCiacNombre(Long idActividad) {
		try {
			Cierre_actividadLocalHome cierreActividadHome = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal local = (Cierre_actividadLocal) cierreActividadHome.findCancelarActividadCiacNombre(idActividad);
			return local;
		}catch (FinderException e) {
			log.warn("No se encontro cierre.");
		}catch (Exception e) {
			log.error("Exception:",e);
		}
		return null;
	}

	/** ESTO ES EL ENTITY PARA ACTIVIDAD: obtengo cierre para (ACCIÓN MASIVA ENVIAR SSVA) **/
	public Cierre_actividadLocal getCierreActividadEntityProblema(Long idActividad) {
		try {
			Cierre_actividadLocalHome cierreActividadHome = (Cierre_actividadLocalHome) HomeFactory.getHome(Cierre_actividadLocalHome.JNDI_NAME);
			Cierre_actividadLocal local = cierreActividadHome.findByCierreActividadProblema(idActividad);
			return local;
		}catch (FinderException e) {
			log.warn("No se encontro cierre.");
		}catch (Exception e) {
			log.error("Exception:",e);
		}
		return null;
	}
}