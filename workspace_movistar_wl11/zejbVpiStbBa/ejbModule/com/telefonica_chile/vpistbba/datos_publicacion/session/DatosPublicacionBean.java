package com.telefonica_chile.vpistbba.datos_publicacion.session;

import java.util.Date;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PeticionDTO;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;

/**
 * Bean implementation class for Enterprise Bean: DatosPublicacion
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class DatosPublicacionBean implements javax.ejb.SessionBean {

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
	/**,
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

	/*
	 * Retorna el Objeto para realizar la Publicacion en la BD.
	 * Aca se setean todos los Datos de la Peticion, incluidos los Campos Variables. 
	 */
	public DatosPeticion getDatosPublicacion(Long idPeticion, String codigoActividad)
	{
		DatosPeticion objDatoPeticion = new DatosPeticion(ApplicationConfig.APP_VPISTBBA, idPeticion, codigoActividad);
		try {
			log.debug("Obteniendo Datos de Publicacion para la Peticion:"+idPeticion);
			PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
			PeticionDTO peticionDTO = peticionesInterface.obtenerPeticionVPI(idPeticion);

			objDatoPeticion.setNumeroPeticion(idPeticion);
			objDatoPeticion.setFechaAsignacion(new Date());
			objDatoPeticion.setFechaInicio(peticionDTO.getPetiFechaIngreso());
			objDatoPeticion.setFechaCompromiso(peticionDTO.getPetiFechaCompromiso());

			objDatoPeticion.setAreaCliente("");
			objDatoPeticion.setCodigoAgencia("0");

			objDatoPeticion.setEstadoPeticion(peticionDTO.getEspeId());
			
			objDatoPeticion.setIdGrupoSegmento(null);
			objDatoPeticion.setRutCliente(peticionDTO.getNumDocCliCd());
			objDatoPeticion.setRutdvCliente(peticionDTO.getDigCtlDocCd());
			
			/**Aqui esto se cambia por segmento y subsegmento de la cuenta **/
			objDatoPeticion.setSegmentoCliente(Utiles.longSinNull(peticionDTO.getCodSgmCtaCd(),""));
			objDatoPeticion.setSubSegmentoCliente(Utiles.longSinNull(peticionDTO.getCodSbgCtaCd(),""));
						
			/*objDatoPeticion.setSegmentoCliente(Utiles.longSinNull(peticionDTO.getCodSgmCliCd(),""));
			objDatoPeticion.setSubSegmentoCliente(Utiles.longSinNull(peticionDTO.getCodSbgCliCd(),""));*/
			
			objDatoPeticion.setServicioCliente("");
			objDatoPeticion.setTipoTrabajo(null);

			objDatoPeticion.setNombreCliente(Utiles.sinNull(peticionDTO.getNomDs(), ""));
			objDatoPeticion.setApellidosCliente(Utiles.sinNull(peticionDTO.getPriApeDs(), "") + Utiles.sinNull(peticionDTO.getSegApeDs(), ""));

			//no hay puntaje
			objDatoPeticion.setPuntaje(new Integer(0));

			objDatoPeticion.setCodigoDepartamento(peticionDTO.getCodDpt());
			objDatoPeticion.setCodigoLocalidad(peticionDTO.getCodLoc());
			//La central puede ser nula
			objDatoPeticion.setCodigoCentral(peticionDTO.getCodCentral());
			objDatoPeticion.setNroPeticionAtis(peticionDTO.getCodPetCd());
			objDatoPeticion.setAgrupaciones(peticionDTO.getAgrupaciones());
			objDatoPeticion.setFamiliaPS(peticionDTO.getFamiliasEnPeticion());
			objDatoPeticion.setCategoriaOpCo(peticionDTO.getCategoriaOpCo());
			if(peticionDTO.isTienePBX())
				objDatoPeticion.setTienePBX(new Integer(1));
			else
				objDatoPeticion.setTienePBX(new Integer(0));
//			} catch (ATiempoAppEx e) {
			} catch (Exception  e) {
			log.error("AtiempoException.", e);
		}

		// Valores variables.
		String[][] aux = null;
		objDatoPeticion.setValoresVariables(aux);

		return objDatoPeticion;
	}
	/**
	 * @param fReg
	 * @return
	 */
//	private Date formatFecha(String fReg) {
//		try {
//			if (fReg != null && fReg.length() > 0) {
//				return sdfFecha.parse(fReg);
//			}
//		} catch (ParseException e) {
//			log.debug("No se Pudo Parsear Fecha. [" + fReg + "]");
//		}
//
//		return null;
//	}

}
