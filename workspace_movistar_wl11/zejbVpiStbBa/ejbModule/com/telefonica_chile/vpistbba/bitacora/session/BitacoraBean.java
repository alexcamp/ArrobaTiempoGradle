package com.telefonica_chile.vpistbba.bitacora.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.usuario.dto.UsuarioDTO;
import com.telefonica_chile.comun.usuario.session.UsuarioSessionLocal;
import com.telefonica_chile.comun.usuario.session.UsuarioSessionLocalHome;
import com.telefonica_chile.vpistbba.bitacora.dto.BitacoraDTO;

/**
 * Bean implementation class for Enterprise Bean: Bitacora
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class BitacoraBean implements javax.ejb.SessionBean {
	// AT-1773 Memory issue 31-Oct-2008 - guido - 14-Nov-2008 - remove this line
	//private UsuarioSessionLocal uLocal;

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private javax.ejb.SessionContext mySessionCtx;
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
		// AT-1773 Memory issue 31-Oct-2008 - guido - 14-Nov-2008 - remove these line
//		try {
//			UsuarioSessionLocalHome uHome =
//				(UsuarioSessionLocalHome) HomeFactory.getHome(
//					UsuarioSessionLocalHome.JNDI_NAME);
//			uLocal = uHome.create();
//		} catch (NamingException e) {
//		} catch (CreateException e) {
//		}
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

	public boolean crearRegistroBitacora(
		Long idPeticion,
		Long idActividad,
		Long idUsuario) throws ATiempoAppEx {

		return crearRegistroBitacora(idPeticion, idActividad, idUsuario, "");

	}

	public boolean crearRegistroBitacora(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		String obs) throws ATiempoAppEx {
			
//			CR16193 - adocarmo - inicio		
			//throw new ATiempoAppEx("jjjj");
//			CR16193 - adocarmo - fin	
		
		
		try {
			Short esReversa = esReversa(idPeticion, null);
			//getReversa(idPeticion);

			Bitacora_peticionLocalHome bHome =
				(Bitacora_peticionLocalHome) HomeFactory.getHome(
					Bitacora_peticionLocalHome.JNDI_NAME);
			Bitacora_peticionLocal bitacora =
				bHome.create(
					idActividad,
					null,
					idPeticion,
					idUsuario,
					new Date(),
					null,
					null,
					esReversa);
			// AT-1773 Memory issue 31-Oct-2008 - Sreejith Vadakara - 20-Nov-2008 - Start
         if(obs!=null) {
         	int length = obs.length();
         	if(length > 0) {
	         	if (length > 1000) {
					obs = obs.substring(0, 999);
					}
				bitacora.setBipe_observacion(obs);
			}
        }
//			
//			if (obs != null && obs.length() > 0) {
//				if (obs.length() > 1000)
//					obs = obs.substring(0, 999);
//				bitacora.setBipe_observacion(obs);
//			}
			// AT-1773 Memory issue 31-Oct-2008 - Sreejith Vadakara - 20-Nov-2008 - END
			return true;
		} catch (NamingException e) {
			log.error(
				"NamingException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
//			CR16193 - adocarmo - fin					
					
		} catch (CreateException e) {
			log.error(
				"CreateException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
			
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
//			CR16193 - adocarmo - fin
		}
		//return false;
	
	}

	public boolean crearRegistroBitacora(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		Integer idEstado) throws ATiempoAppEx {
		try {
			Short esReversa = esReversa(idPeticion, idEstado);

			Bitacora_peticionLocalHome bHome =
				(Bitacora_peticionLocalHome) HomeFactory.getHome(
					Bitacora_peticionLocalHome.JNDI_NAME);
			Bitacora_peticionLocal bitacora =
				bHome.create(
					idActividad,
					null,
					idPeticion,
					idUsuario,
					new Date(),
					null,
					null,
					esReversa);
			return true;
		} catch (NamingException e) {
			log.error(
				"NamingException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
					
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
//			CR16193 - adocarmo - fin						
					
		} catch (CreateException e) {
			log.error(
				"CreateException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
//			CR16193 - adocarmo - fin
		}
		//return false;
	}

	public boolean crearRegistroBitacoraCerrado(
		Long idPeticion,
		Long idActividad,
		Long idUsuario) throws ATiempoAppEx {
		return crearRegistroBitacoraCerrado(
			idPeticion,
			idActividad,
			idUsuario,
			null);
	}

	public boolean crearRegistroBitacoraCerrado(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		String obs) throws ATiempoAppEx {
		return crearRegistroBitacoraCerrado(
			idPeticion,
			idActividad,
			idUsuario,
			obs,
			null);
	}

	public boolean crearRegistroBitacoraCerrado(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		String obs,
		Integer idEstado) throws ATiempoAppEx {
		try {
			Short esReversa = esReversa(idPeticion, idEstado);
			//getReversa(idPeticion);

			Bitacora_peticionLocalHome bHome =
				(Bitacora_peticionLocalHome) HomeFactory.getHome(
					Bitacora_peticionLocalHome.JNDI_NAME);
			try {
				// Valido si ya existe la Actividad en la bitacora la Cierro.
				Bitacora_peticionLocal bLocal =
					bHome.findByPeticionActividad(idPeticion, idActividad);
				bLocal.setBipe_fecha_fin(bLocal.getBipe_fecha_inicio());
				bLocal.setUsua_id(idUsuario);
				bLocal.setBipe_es_reversa(esReversa);
				if (obs != null)
					bLocal.setBipe_observacion(obs);
			} catch (FinderException e1) {
				Bitacora_peticionLocal bitacora =
					bHome.create(
						idActividad,
						null,
						idPeticion,
						idUsuario,
						new Date(),
						new Date(),
						obs,
						esReversa);
			}
			return true;
		} catch (javax.ejb.DuplicateKeyException e) {
			log.error(
				"DuplicateKeyException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
//			CR16193 - adocarmo - fin						
		} catch (NamingException e) {
			log.error(
				"NamingException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
//			CR16193 - adocarmo - fin						
		} catch (CreateException e) {
			log.error(
				"CreateException: No se pudo Crear Bitacora ["
					+ idPeticion
					+ ","
					+ idActividad
					+ ","
					+ idUsuario
					+ "]:"
					+ e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
//			CR16193 - adocarmo - fin					
		}
		//return false;
	}

	private Integer getReversa(Long idPeticion) {
		try {
			PeticionLocalHome pet_home =
				(PeticionLocalHome) HomeFactory.getHome(
					PeticionLocalHome.JNDI_NAME);
			PeticionKey petiKey = new PeticionKey(idPeticion);
			PeticionLocal pet = pet_home.findByPrimaryKey(petiKey);
			Integer estado = pet.getEspe_id();

			return estado;

		} catch (NamingException e) {
			log.error("No se pudo obtener PeticionLocalHome", e);
		} catch (FinderException e) {
			//log.error("No se pudo obtener PeticionLocal para " + idPeticion , e);
		}

		return null;
	}

	private Short esReversa(Long idPeticion, Integer idEstado) {
		if (idEstado == null)
			idEstado = getReversa(idPeticion);
		// AT-1773 Memory issue 31-Oct-2008 - Sreejith Vadakara - 20-Nov-2008 - Start
//		if (idEstado == null)
//			return null;
		if (idEstado != null) {
			int idEstadoVal = idEstado.intValue();
	        if (idEstadoVal== 3 || idEstadoVal == 7
	    			|| idEstadoVal == 8
	    			|| idEstadoVal == 9) {
			return new Short("1");
		}
		}
//		if (idEstado.intValue() == 3
//			|| idEstado.intValue() == 7
//			|| idEstado.intValue() == 8
//			|| idEstado.intValue() == 9) {
//			return new Short("1");
//		}
//		 AT-1773 Memory issue 31-Oct-2008 - Sreejith Vadakara - 20-Nov-2008 - END
		return null;
	}

	public boolean realizoActividad(Long idPeticion, Long idActividad) {
		if (idActividad == null)
			return false;
		try {
			Bitacora_peticionLocalHome bHome =
				(Bitacora_peticionLocalHome) HomeFactory.getHome(
					Bitacora_peticionLocalHome.JNDI_NAME);
			Collection c = bHome.findByPetiOrden(idPeticion);
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				Bitacora_peticionLocal bLocal =
					(Bitacora_peticionLocal) iter.next();

				Long idAct = bLocal.getIdActividad();
				if (idAct != null
					&& idAct.intValue() == idActividad.intValue())
					return true;
			}
		} catch (NamingException e) {
			log.error("NamingException BuscandoBitacora. [" + idPeticion + "]");
		} catch (FinderException e) {
			log.debug("FinderException BuscandoBitacora. [" + idPeticion + "]");
		}
		return false;
	}
	public boolean actualizaObservacionBitacora(
		Long idPetico,
		Long idAct,
		String obs,
		boolean overwriteObs) {
		Bitacora_peticionLocalHome bHome = null;
		Bitacora_peticionLocal bLocal = null;
		if (obs != null && obs.length() > 1000)
			obs = obs.substring(0, 999);

		try {
			bHome =
				(Bitacora_peticionLocalHome) HomeFactory.getHome(
					Bitacora_peticionLocalHome.JNDI_NAME);
			bLocal =
				(Bitacora_peticionLocal) bHome.findByPeticionActividad(
					idPetico,
					idAct);

			if (obs != null) {
				String obsAux = "";
				if(!overwriteObs){
				
				if (bLocal.getBipe_observacion() != null)
					obsAux = bLocal.getBipe_observacion();
				obsAux = obsAux + "." + obs;

				if (obsAux != null && obsAux.length() > 1000)
					obsAux = obsAux.substring(0, 999);
				}else{
					obsAux = obs;
				}
				bLocal.setBipe_observacion(obsAux);
			}
		} catch (NamingException e) {
			log.error(
				"Naming Exception. ["
					+ idPetico
					+ ","
					+ idAct
					+ "] : "
					+ e.getMessage());
			return false;
		} catch (FinderException e) {
			Date fechaNow = new Date();
			log.error(
				"FinderException. ["
					+ idPetico
					+ ","
					+ idAct
					+ "] : "
					+ e.getMessage());
			// Puede ser que haya mas de una Bitacora Abierta para la Misma Actividad
			try {
				if (bHome == null)
					bHome =
						(Bitacora_peticionLocalHome) HomeFactory.getHome(
							Bitacora_peticionLocalHome.JNDI_NAME);

				bLocal = bHome.findByPeticionActividad(idPetico, idAct);
				if (bLocal.getBipe_fecha_fin() == null) {
					bLocal.setBipe_fecha_fin(new Timestamp(fechaNow.getTime()));
					// Validamos que no se caiga el seteo de la obs.
					if (obs != null)
						bLocal.setBipe_observacion(obs);
				}
			} catch (NamingException e1) {
				log.error(
					"Segundo NamingException. ["
						+ idPetico
						+ ","
						+ idAct
						+ ","
						+ e.getMessage());
				return false;
			} catch (FinderException e1) {
				log.error(
					"Segundo FinderException. ["
						+ idPetico
						+ ","
						+ idAct
						+ ","
						+ e.getMessage());
				return false;
			}

			return true;
		}

		return true;
	}
	public boolean cierra(
		Long idPetico,
		Long idAct,
		Long idUsuarioCierre,
		String obs,
		Long idCausa, boolean overwriteObs) {
		Bitacora_peticionLocalHome bHome = null;
		Bitacora_peticionLocal bLocal = null;
		if (obs != null && obs.length() > 1000)
			obs = obs.substring(0, 999);

		try {
			bHome =
				(Bitacora_peticionLocalHome) HomeFactory.getHome(
					Bitacora_peticionLocalHome.JNDI_NAME);
			bLocal =
				(Bitacora_peticionLocal) bHome.findByPeticionActividad(
					idPetico,
					idAct);

			bLocal.setBipe_fecha_fin(new Timestamp(new Date().getTime()));			
			if (obs != null) {
				String obsAux = "";
				if(!overwriteObs){
				
				
				if (bLocal.getBipe_observacion() != null)
					obsAux = bLocal.getBipe_observacion();
				obsAux = obsAux + "." + obs;

				if (obsAux != null && obsAux.length() > 1000)
					obsAux = obsAux.substring(0, 999);
					
				}else{
					obsAux = obs;
				}

				bLocal.setBipe_observacion(obsAux);
			}

			if (idUsuarioCierre != null)
				bLocal.setUsua_id_cierre(idUsuarioCierre);
			if (idCausa != null)
				bLocal.setIdCausa(idCausa);
		} catch (NamingException e) {
			log.error(
				"Naming Exception. ["
					+ idPetico
					+ ","
					+ idAct
					+ ","
					+ idUsuarioCierre
					+ ","
					+ idCausa
					+ "] : "
					+ e.getMessage());
			return false;
		} catch (FinderException e) {
			Date fechaNow = new Date();
			log.error(
				"FinderException. ["
					+ idPetico
					+ ","
					+ idAct
					+ ","
					+ idUsuarioCierre
					+ ","
					+ idCausa
					+ "] : "
					+ e.getMessage());
			// Puede ser que haya mas de una Bitacora Abierta para la Misma Actividad
			try {
				if (bHome == null)
					bHome =
						(Bitacora_peticionLocalHome) HomeFactory.getHome(
							Bitacora_peticionLocalHome.JNDI_NAME);

				bLocal = bHome.findByPeticionActividad(idPetico, idAct);
				if (bLocal.getBipe_fecha_fin() == null) {
					bLocal.setBipe_fecha_fin(new Timestamp(fechaNow.getTime()));
					// Validamos que no se caiga el seteo de la obs.
					if (obs != null)
						bLocal.setBipe_observacion(obs);
					if (idUsuarioCierre != null)
						bLocal.setUsua_id_cierre(idUsuarioCierre);
					if (idCausa != null)
						bLocal.setIdCausa(idCausa);
				}
			} catch (NamingException e1) {
				log.error(
					"Segundo NamingException. ["
						+ idPetico
						+ ","
						+ idAct
						+ ","
						+ idUsuarioCierre
						+ "] : "
						+ e.getMessage());
				return false;
			} catch (FinderException e1) {
				log.error(
					"Segundo FinderException. ["
						+ idPetico
						+ ","
						+ idAct
						+ ","
						+ idUsuarioCierre
						+ "] : "
						+ e.getMessage());
				return false;
			}

			return true;
		}

		return true;

	}

	public ArrayList getListadoBitacora(Long idPeticion) {
		ArrayList listaBitacora = new ArrayList();
		
		try {
			Bitacora_peticionLocalHome bHome =(Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			
			Collection c = bHome.findByPetiOrden(idPeticion);
			// AT-1773 Memory issue 31-Oct-2008 - guido - 14-Nov-2008 - start
			BitacoraDTO b=null;
			UsuarioSessionLocalHome uHome =(UsuarioSessionLocalHome) HomeFactory.getHome(UsuarioSessionLocalHome.JNDI_NAME);
			UsuarioSessionLocal uLocal = uHome.create();
			
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				Bitacora_peticionLocal bLocal =(Bitacora_peticionLocal) iter.next();

				// AT-1773 Memory issue 31-Oct-2008 - guido - 14-Nov-2008
				b = new BitacoraDTO();
				ActividadLocal actiLocal = bLocal.getFk_acti_2_bipe();
				ActividadKey actiKey = (ActividadKey) actiLocal.getPrimaryKey();
								
				b.setActividad(actiLocal.getAct_descripcion());
				
				boolean esAutoInstalacionSoloBA=peticionesDelegate.esAutoInstalacionSoloBA(idPeticion);
				if (esAutoInstalacionSoloBA && actiLocal.getAct_codigo().equals(ComunInterfaces.ACT_INSTALAR)){
					b.setActividad(ComunInterfaces.CRUZADA_BA);
				}
				
				if (esAutoInstalacionSoloBA && actiLocal.getAct_codigo().equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
					b.setActividad(ComunInterfaces.CTRL_CRUZADA_BA);
				}
				if(actiLocal.getAct_codigo().equals(ComunInterfaces.ACT_INSTALAR_TOA)&&esAutoInstalacionSoloBA){
					b.setActividad(ComunInterfaces.CRUZADA_BA_TOA);
				}
				if(actiLocal.getAct_codigo().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)&&esAutoInstalacionSoloBA){
					b.setActividad(ComunInterfaces.CTRL_CRUZADA_BA_TOA);
				}
				
				b.setIdActividad(actiKey.act_id);
				if (bLocal.getFk_caus_2_bita() != null)
					b.setCausa(bLocal.getFk_caus_2_bita().getCaus_nombre());

				b.setFechaFin(bLocal.getBipe_fecha_fin());
				b.setFechaInicio(bLocal.getBipe_fecha_inicio());
				b.setObservaciones(bLocal.getBipe_observacion());
				if (bLocal.getBipe_es_reversa() != null){
					b.setActividad(actiLocal.getAct_nombre_reversa());
					b.setReversa("(REV)");
				}
				else
					b.setReversa("");

				if (bLocal.getFk_acti_2_bipe().getFk_act_rol() != null)
					b.setRol(bLocal.getFk_acti_2_bipe().getFk_act_rol().getRol_nombre());

				UsuarioDTO uDto = uLocal.recuperaUsuario(bLocal.getUsua_id());
				b.setUsuario(uDto.getNombre());

				listaBitacora.add(b);
			}
		} catch (NamingException e) {
			log.error("NamingException BuscandoBitacora. [" + idPeticion + "]",e);
		} catch (FinderException e) {
			log.debug("FinderException BuscandoBitacora. [" + idPeticion + "]",e);
		} catch (CreateException e) {
			log.debug("CreateException BuscandoBitacora. [" + idPeticion + "]",e);
			// AT-1773 Memory issue 31-Oct-2008 - guido - 14-Nov-2008 - END
		} catch (ATiempoAppEx e){
			log.debug("ATiempoAppEx BuscandoBitacora. [" + idPeticion + "]",e);
		}

		return listaBitacora;
	}

}