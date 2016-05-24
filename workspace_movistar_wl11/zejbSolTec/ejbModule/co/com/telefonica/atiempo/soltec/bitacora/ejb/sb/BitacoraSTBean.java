package co.com.telefonica.atiempo.soltec.bitacora.ejb.sb;

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
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.soltec.bitacora.dto.BitacoraSTDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.usuario.dto.UsuarioDTO;
import com.telefonica_chile.comun.usuario.session.UsuarioSessionLocal;
import com.telefonica_chile.comun.usuario.session.UsuarioSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: BitacoraST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class BitacoraSTBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
	//private UsuarioSessionLocal uLocal;
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
		//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
		
//		try {
//			UsuarioSessionLocalHome uHome = (UsuarioSessionLocalHome) HomeFactory.getHome(UsuarioSessionLocalHome.JNDI_NAME);
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

	public boolean crearRegistroBitacora(Long idPeticion, Long idActividad, Long idUsuario) throws ATiempoAppEx {
		
		return crearRegistroBitacora(idPeticion, idActividad, idUsuario, "");

	}

	public boolean crearRegistroBitacora(Long idPeticion, Long idActividad, Long idUsuario, String obs) throws ATiempoAppEx {
		try {
			Short esReversa = esReversa(idPeticion, null); //getReversa(idPeticion);
			
			Bitacora_peticionLocalHome bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			Bitacora_peticionLocal bitacora = bHome.create(idActividad, null, idPeticion, idUsuario, new Date(), null, null, esReversa);
			
			if ( obs != null && obs.length()>0 ) {
				if ( obs.length() > 1000 )
					obs = obs.substring(0, 999);
				bitacora.setBipe_observacion( obs );
			}

			return true;
		} catch (NamingException e) {
			log.error("NamingException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
//			CR16193 - adocarmo - fin				
		} catch (CreateException e) {
			log.error("CreateException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
			
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
//			CR16193 - adocarmo - fin						
		}
		//return false;
	}

	public boolean crearRegistroBitacora(Long idPeticion, Long idActividad, Long idUsuario, Integer idEstado) throws ATiempoAppEx {
		try {
			Short esReversa = esReversa( idPeticion, idEstado );
			
			Bitacora_peticionLocalHome bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			Bitacora_peticionLocal bitacora = bHome.create(idActividad, null, idPeticion, idUsuario, new Date(), null, null, esReversa);
			return true;
		} catch (NamingException e) {
			log.error("NamingException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
//			CR16193 - adocarmo - fin				
		} catch (CreateException e) {
			log.error("CreateException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
			
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
//			CR16193 - adocarmo - fin						
		}
		//return false;
	}

	public boolean crearRegistroBitacoraCerrado(Long idPeticion, Long idActividad, Long idUsuario) throws ATiempoAppEx {
		return crearRegistroBitacoraCerrado(idPeticion, idActividad, idUsuario, null);
	}

	public boolean crearRegistroBitacoraCerrado(Long idPeticion, Long idActividad, Long idUsuario, String obs) throws ATiempoAppEx {
		return crearRegistroBitacoraCerrado(idPeticion, idActividad, idUsuario, obs, null);
	}

	public boolean crearRegistroBitacoraCerrado(Long idPeticion, Long idActividad, Long idUsuario, String obs, Integer idEstado) throws ATiempoAppEx {
		try {
			Short esReversa = esReversa(idPeticion, idEstado); //getReversa(idPeticion);
			
			Bitacora_peticionLocalHome bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			try {
				// Valido si ya existe la Actividad en la bitacora la Cierro.
				Bitacora_peticionLocal bLocal = bHome.findByPeticionActividad(idPeticion, idActividad);
				bLocal.setBipe_fecha_fin( bLocal.getBipe_fecha_inicio() );
				bLocal.setUsua_id( idUsuario );
				bLocal.setBipe_es_reversa( esReversa );
				if ( obs != null)
					bLocal.setBipe_observacion( obs );
			} catch (FinderException e1) {
				Bitacora_peticionLocal bitacora = 
					bHome.create(idActividad, null, idPeticion, idUsuario, new Date(), new Date(), obs, esReversa);
			}
			return true;
		} catch (javax.ejb.DuplicateKeyException e) {
			log.error("DuplicateKeyException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.EJBEXCEPTION,e);
//			CR16193 - adocarmo - fin				
		} catch (NamingException e) {
			log.error("NamingException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
//			CR16193 - adocarmo - fin				
		} catch (CreateException e) {
			log.error("CreateException: No se pudo Crear Bitacora [" + idPeticion + "," + idActividad + "," + idUsuario + "]:" + e.getMessage());
			
//			CR16193 - adocarmo - inicio		
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
//			CR16193 - adocarmo - fin						
		}
		//return false;
	}
	
	private Integer getReversa(Long idPeticion) {
		try {
			Peticion_stLocalHome pet_home  = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey petiKey = new Peticion_stKey(idPeticion);
			Peticion_stLocal pet = pet_home.findByPrimaryKey( petiKey );
			Integer estado = pet.getEstado_peticion();
			
			return estado;

		} catch (NamingException e) {
			log.error("No se pudo obtener PeticionLocalHome", e);
		} catch (FinderException e) {
			log.error("No se pudo obtener PeticionLocal para " + idPeticion , e);
		}

		return null;
	}

	private Short esReversa(Long idPeticion, Integer idEstado) {
		if ( idEstado == null )
			idEstado = getReversa( idPeticion );
		
		if ( idEstado == null )
			return null;

		if( idEstado.intValue() == 3 || idEstado.intValue() == 7 
			|| idEstado.intValue() == 8 || idEstado.intValue() == 9 ) {
			return new Short("1");
		} 

		return null;
	}

	public boolean realizoActividad(Long idPeticion, Long idActividad) {
		if ( idActividad == null )
			return false;
		try {
			Bitacora_peticionLocalHome bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			Collection c = bHome.findByPetiOrden(idPeticion);
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				Bitacora_peticionLocal bLocal = (Bitacora_peticionLocal) iter.next();

				Long idAct = bLocal.getAct_id();
				if ( idAct != null && idAct.intValue()==idActividad.intValue() )
					return true;
			}
		} catch (NamingException e) {
			log.error("NamingException BuscandoBitacora. [" + idPeticion + "]");
		} catch (FinderException e) {
			log.debug("FinderException BuscandoBitacora. [" + idPeticion + "]");
		}
		return false;
	}
	
	public boolean cierra(Long idPetico, Long idAct, Long idUsuarioCierre, String obs, Long idCausa)  {
		Bitacora_peticionLocalHome bHome = null;
		Bitacora_peticionLocal bLocal  = null;
		if ( obs!=null && obs.length() > 1000)
			obs = obs.substring(0, 999);

		try {
			bHome= (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			bLocal = (Bitacora_peticionLocal) bHome.findByPeticionActividad(idPetico, idAct);
				
			bLocal.setBipe_fecha_fin( new Timestamp(new Date().getTime()) );
			if ( obs!=null ) {
				String obsAux = "";
				if ( bLocal.getBipe_observacion() != null )
					obsAux = bLocal.getBipe_observacion();
				obsAux = obsAux + "." + obs;

				if ( obsAux!=null && obsAux.length() > 1000)
					obsAux = obsAux.substring(0, 999);
					
				bLocal.setBipe_observacion( obsAux );
			}

			if (idUsuarioCierre != null)
				bLocal.setUsua_id_cierre( idUsuarioCierre );
			if (idCausa != null)
				bLocal.setCaus_id( idCausa );
		} catch (NamingException e) {
			log.error("Naming Exception. [" + idPetico + "," + idAct + "," + idUsuarioCierre + "," + idCausa + "] : " + e.getMessage());
			return false;
		} catch (FinderException e) {
			Date fechaNow = new Date();
			log.error("FinderException. [" + idPetico + "," + idAct + "," + idUsuarioCierre + "," + idCausa + "] : " + e.getMessage());
			// Puede ser que haya mas de una Bitacora Abierta para la Misma Actividad
			try {
				if ( bHome == null )
					bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
				
				bLocal = bHome.findByPeticionActividad(idPetico, idAct);					
				if ( bLocal.getBipe_fecha_fin() == null ) {
					bLocal.setBipe_fecha_fin( new Timestamp(fechaNow.getTime()) );
					// Validamos que no se caiga el seteo de la obs.
					if ( obs!=null )
						bLocal.setBipe_observacion( obs );
					if (idUsuarioCierre != null)
						bLocal.setUsua_id_cierre( idUsuarioCierre );
					if (idCausa != null)
						bLocal.setCaus_id( idCausa );
				}
			} catch (NamingException e1) {
				log.error("Segundo NamingException. [" + idPetico + "," + idAct + "," + idUsuarioCierre + "] : " + e.getMessage());
				return false;
			} catch (FinderException e1) {
				log.error("Segundo FinderException. [" + idPetico + "," + idAct + "," + idUsuarioCierre + "] : " + e.getMessage());
				return false;
			}

			return true;
		}
		
		return true;

	}

	public ArrayList getListadoBitacora(Long idPeticion) {
		ArrayList listaBitacora = new ArrayList();
		try {
			UsuarioSessionLocalHome uHome = (UsuarioSessionLocalHome) HomeFactory.getHome(UsuarioSessionLocalHome.JNDI_NAME);
			UsuarioSessionLocal uLocal = uHome.create(); 
			Bitacora_peticionLocalHome bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			Collection c = bHome.findByPetiOrden(idPeticion);
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				Bitacora_peticionLocal bLocal = (Bitacora_peticionLocal) iter.next();
					
				//Busco la actividad para obtener su descripcion
				ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				ActividadKey aK= new ActividadKey(bLocal.getAct_id());
				ActividadLocal actividadLocal = actividadLocalHome.findByPrimaryKey(aK);
				BitacoraSTDTO b = new BitacoraSTDTO();
				b.setActividad( actividadLocal.getAct_descripcion() );
				if ( bLocal.getCaus_id() != null){
					//TODO: Falta buscar la causa
				}

				b.setFechaFin( bLocal.getBipe_fecha_fin() );
				b.setFechaInicio( bLocal.getBipe_fecha_inicio() );
				b.setObservaciones( bLocal.getBipe_observacion() );
				if ( bLocal.getBipe_es_reversa() != null )
					b.setReversa( "(REV)");
				else
					b.setReversa( "" );

				if (actividadLocal.getFk_act_rol() != null )
					b.setRol( actividadLocal.getFk_act_rol().getRol_nombre() );
//				log.debug("Buscando UsuaBitacora:" + bLocal.getUsua_id());
				UsuarioDTO uDto = uLocal.recuperaUsuario( bLocal.getUsua_id() );
				b.setUsuario( uDto.getNombre() );

				listaBitacora.add( b );
			}
		} catch (NamingException e) {
			log.error("NamingException BuscandoBitacora. [" + idPeticion + "]");
		} catch (FinderException e) {
			log.debug("FinderException BuscandoBitacora. [" + idPeticion + "]");
		} catch (CreateException e) {
			log.error("No se pudo crear la Session Local", e);
		}

		return listaBitacora;
	}

	public boolean actualizaObservacionBitacora(Long idPetico, Long idAct,
			String obs, boolean overwriteObs) {
		Bitacora_peticionLocalHome bHome = null;
		Bitacora_peticionLocal bLocal = null;
		if (obs != null && obs.length() > 1000)
			obs = obs.substring(0, 999);

		try {
			bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			bLocal = (Bitacora_peticionLocal) bHome.findByPeticionActividad(idPetico, idAct);

			if (obs != null) {
				String obsAux = "";
				if (!overwriteObs) {

					if (bLocal.getBipe_observacion() != null)
						obsAux = bLocal.getBipe_observacion();
					obsAux = obsAux + "." + obs;

					if (obsAux != null && obsAux.length() > 1000)
						obsAux = obsAux.substring(0, 999);
				} else {
					obsAux = obs;
				}
				bLocal.setBipe_observacion(obsAux);
			}
		} catch (NamingException e) {
			log.error("Naming Exception. [" + idPetico + "," + idAct + "] : " + e.getMessage());
			return false;
		} catch (FinderException e) {
			Date fechaNow = new Date();
			log.error("FinderException. [" + idPetico + "," + idAct + "] : " + e.getMessage());
			// Puede ser que haya mas de una Bitacora Abierta para la Misma Actividad
			try {
				if (bHome == null)
					bHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);

				bLocal = bHome.findByPeticionActividad(idPetico, idAct);
				if (bLocal.getBipe_fecha_fin() == null) {
					bLocal.setBipe_fecha_fin(new Timestamp(fechaNow.getTime()));
					// Validamos que no se caiga el seteo de la obs.
					if (obs != null)
						bLocal.setBipe_observacion(obs);
				}
			} catch (NamingException e1) {
				log.error("Segundo NamingException. [" + idPetico + "," + idAct	+ "," + e.getMessage());
				return false;
			} catch (FinderException e1) {
				log.error("Segundo FinderException. [" + idPetico + "," + idAct	+ "," + e.getMessage());
				return false;
			}

			return true;
		}

		return true;
	}
}
