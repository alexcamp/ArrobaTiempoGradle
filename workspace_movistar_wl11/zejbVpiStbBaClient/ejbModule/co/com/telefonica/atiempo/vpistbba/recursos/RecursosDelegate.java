/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.recursos;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR002S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR003S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR047S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR049S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR050S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR052E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR055S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR056S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR511S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR513S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR517S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR518S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR601S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR603S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR604S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR612S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR613S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR614S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.recursos.ejb.RecursosServiciosLocal;
import co.com.telefonica.atiempo.vpistbba.recursos.ejb.RecursosServiciosLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class RecursosDelegate implements RecursosInterfaces {

	private RecursosServiciosLocal servicios;

	public RecursosDelegate() throws ATiempoAppEx {
		try {
			servicios = ((RecursosServiciosLocalHome) HomeFactory
					.getHome(RecursosServiciosLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#asignarRecurso(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Long,
	 *      co.com.telefonica.atiempo.vpistbba.dto.RecursoAPSCDTO)
	 */
	public void asignarRecursoSTB(TR010S tr010s) throws ATiempoAppEx {
		servicios.asignarRecursoSTB(tr010s);
	}

	public void enviarRecursoSTB(Long peticion, String idActividad,
			Integer idActividadFlujo) throws ATiempoAppEx {
		servicios.enviarRecursoSTB(peticion, idActividad, idActividadFlujo);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#asignarRecursoManualSTB(co.com.telefonica.atiempo.interfaces.atiempo.TR003S)
	 */
	public void asignarRecursoManualSTB(TR003S tr003s) throws ATiempoAppEx {
		servicios.asignarRecursoManualSTB(tr003s);
	}

	public InformacionTecnicaDTO obtenerRecursosTecnicos(Long idPeticion)
			throws ATiempoAppEx {
		return servicios.obtenerRecursosTecnicos(idPeticion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioPuntosRedSTB(java.lang.String,
	 *      java.lang.String)
	 */
	public void envioPuntosRedSTB(String peticion, String idActividad)
			throws ATiempoAppEx {

		servicios.envioPuntosRedSTB(peticion, idActividad);
	}

	public void consultaPuntosRedSTB(TR012S tr012s) throws ATiempoAppEx {

		servicios.consultaPuntosRedSTB(tr012s);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#recepcionLiberacionRecursos(co.com.telefonica.atiempo.interfaces.atiempo.TR002S)
	 */
	public void recepcionLiberacionRecursos(TR002S tr002s) throws ATiempoAppEx {

		servicios.recepcionLiberacionRecursos(tr002s);

	}

	/**
	 * @param lecContadorDesde
	 * @param lecContadorHasta
	 */
	public void actualizarLecturaContador(Long idPeticion,
			String lecContadorDesde, String lecContadorHasta)
			throws ATiempoAppEx {
		servicios.actualizarLecturaContador(idPeticion, lecContadorDesde,
				lecContadorHasta);
	}

	public ArrayList obtenerListaServiciosSuplementarios(Long idPeticion) {
		return servicios.obtenerListaServiciosSuplementarios(idPeticion);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#consultaRecursoSTB_BA(java.lang.Long,
	 *      java.lang.String)
	 */
	public void consultaRecursoSTB_BA(Long peticion, String idActividad, String codActividad)
			throws ATiempoAppEx {
		servicios.consultaRecursoSTB_BA(peticion, idActividad, codActividad);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioLiberacionRecursos(java.lang.Long,
	 *      java.lang.String, java.lang.Integer)
	 */
	public void envioLiberacionRecursos(Long peticion, String idActividad,
			Integer actividadFlujo) throws ATiempoAppEx {

		servicios
				.envioLiberacionRecursos(peticion, idActividad, actividadFlujo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#eliminarLecturaContador(java.lang.Long)
	 */
	public void eliminarLecturaContador(Long idPeticion) throws ATiempoAppEx {
		servicios.eliminarLecturaContador(idPeticion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioPuntosRedSTB(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void envioPuntosRedSTB(String telefonoConsulta, String peticion,
			String idActividad) throws ATiempoAppEx {
		servicios.envioPuntosRedSTB(telefonoConsulta, peticion, idActividad);
	}

	// CR20948 - Altamira - guido - 20/Abr/2009
	public void altamiraEnvioTr601(ActividadEJBDTO act,
			String atiempoServiceName, boolean reversa) throws ATiempoAppEx {
		servicios.altamiraEnvioTr601(act, atiempoServiceName, reversa);
	}

	/*
	 * CR-14657 Granite - agonz - 15/10/2008
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#enviarRecursoGraniteSTB(java.lang.Long,
	 *      java.lang.String, java.lang.Integer)
	 */
	public void enviarRecursoGraniteSTB(Long numeroPeticion,
			String codigoActividad, Integer idActividadFlujo)
			throws ATiempoAppEx {
		servicios.enviarRecursoGraniteSTB(numeroPeticion, codigoActividad,
				idActividadFlujo);
	}

	/*
	 * (CR-14657 Granite - agonz - 16/10/2008
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioLiberacionRecursosGranite(java.lang.Long,
	 *      java.lang.String, java.lang.Integer)
	 */
	public void asignarRecursoGraniteSTB(TR510S tr510s) throws ATiempoAppEx {
		servicios.asignarRecursoGraniteSTB(tr510s);

	}

	public void consultaRecursoGraniteSTB_BA(Long numeroPeticion,
			String codigoActividad, String codActividad) throws ATiempoAppEx {
		servicios.consultaRecursoGraniteSTB_BA(numeroPeticion, codigoActividad, codActividad);
	}

	public void envioPuntosRedGraniteSTB(String string, String codigoActividad)
			throws ATiempoAppEx {
		servicios.envioPuntosRedGraniteSTB(string, codigoActividad);
	}

	public void consultaPuntosRedGraniteSTB(TR514S tr514s) throws ATiempoAppEx {
		servicios.consultaPuntosRedGraniteSTB(tr514s);
	}

	/*
	 * (CR-14657 Granite - Pablo L - 16/10/2008 (non-Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#asignarRecursoManualSTBGR(co.com.telefonica.atiempo.interfaces.atiempo.TR510S)
	 */
	public void asignarRecursoManualSTBGR(TR510S tr510s) throws ATiempoAppEx {
		servicios.asignarRecursoManualSTBGR(tr510s);

	}

	//	CR14657 - Granite - adocarmo- 21-10-2008
	public void recepcionConfiguracionAutomaticaSTB(TR511S tr511s)
			throws ATiempoAppEx {
		servicios.recepcionConfiguracionAutomaticaSTB(tr511s);
	}

	//	CR14657 - Granite - agonz- 16-10-2008
	public boolean enviaConfiguracionAutomaticaSTB(ActividadEJBDTO act)
			throws ATiempoAppEx {
		return servicios.enviaConfiguracionAutomaticaSTB(act);
	}

	public void envioLiberacionRecursosGranite(Long numeroPeticion,
			String codigoActividad, Integer idActividadFlujo)
			throws ATiempoAppEx {
		servicios.envioLiberacionRecursosGranite(numeroPeticion,
				codigoActividad, idActividadFlujo);

	}

	//	CR14657 - Granite - adocarmo- 16-10-2008
	public boolean enviaDesconfigurarConfiguracionAutomaticaSTB(
			ActividadEJBDTO act) throws ATiempoAppEx {
		return servicios.enviaDesconfigurarConfiguracionAutomaticaSTB(act);
	}

	//	CR14657 - Granite - agonz- 17-11-2008
	public void reversaAsignarRecursoGraniteSTB(TR513S tr513s)
			throws ATiempoAppEx {
		servicios.reversaAsignarRecursoGraniteSTB(tr513s);
	}

	//	CR14657 - Granite - agonz- 27-03-2009
	public boolean tieneCambioNumeroLB(Long idPeticion) throws ATiempoAppEx {
		return servicios.tieneCambioNumeroLB(idPeticion);
	}

	public boolean esCruzada(Long idPeticion) throws ATiempoAppEx {
		return servicios.esCruzada(idPeticion);
	}

	//	AT-2104- Granite - agonz- 21-04-2009
	public boolean tienePcLinea(Long idPeticion) throws ATiempoAppEx {
		return servicios.tienePcLinea(idPeticion);
	}

	// CR20948 - Altamira - adocarmo - 20/Abr/2009
	public void altamiraRecepcionTr601(TR601S tr601s) throws ATiempoAppEx {
		servicios.altamiraRecepcionTr601(tr601s);
	}

	public boolean tieneTrasladoLB(Long idPeticion) throws ATiempoAppEx {
		return servicios.tieneTrasladoLB(idPeticion);
	}

	public boolean esBajaLB(Long idPeticion) throws ATiempoAppEx {
		return servicios.esBajaLB(idPeticion);
	}

	public int enviaACrearODS(ActividadEJBDTO act) throws ATiempoAppEx {
		return servicios.enviaACrearODS(act);
	}

	public int enviaReversaConfiguracionAutomaticaSTB(ActividadEJBDTO act)
			throws ATiempoAppEx {
		return servicios.enviaReversaConfiguracionAutomaticaSTB(act);
	}

	public void respuestaCreaOdsGranite(TR517S tr517s) throws ATiempoAppEx {
		servicios.respuestaCreaOdsGranite(tr517s);
	}

	public void enviaMensajeTutorWeb(Long idPeticion, String idActividad,
			boolean yaEnvioPrimerMensajeMT, ActividadEJBDTO act)
			throws ATiempoAppEx {
		servicios.enviaMensajeTutorWeb(idPeticion, idActividad,
				yaEnvioPrimerMensajeMT, act);
	}

	public void respuestaMensajeTutorWeb(TR047S tr047s) throws ATiempoAppEx {
		servicios.respuestaMensajeTutorWeb(tr047s);
	}

	//@idrincon req 3274
	public void respuestaConsultaTroncalSip(TR049S tr049s) throws ATiempoAppEx {
		servicios.respuestaConsultaTroncalSip(tr049s);
	}

	//@idrincon req 3274
	public void respuestaActivarLineasTRoncalSip(TR050S tr050s)
			throws ATiempoAppEx {
		servicios.respuestaActivarLineasTRoncalSip(tr050s);
	}

	/**
	 * Metodo para enviar el mensaje TR052E cuando se presente un quiebre de un
	 * ps que sea de familia BA o LB
	 * 
	 * @author mfmendez - Req 4659
	 * @param numeroPeticion,
	 *            el identificador de la petición atiempo
	 * @param tr052e,
	 *            el objeto con algunos de los datos que se deben enviar
	 * @return error, indica si se presento error en el envio del mensaje (true)
	 *         o no (false)
	 */
	public boolean enviarInformeQuiebreFamiliaBAoLB(Long numeroPeticion,
			TR052E tr052e) {
		return servicios.enviarInformeQuiebreFamiliaBAoLB(numeroPeticion,
				tr052e);
	}

	/* RQ.10402 - mfmendez */
	public void configurarPresenciaDigital(ActividadEJBDTO act,
			Long nroPeticion, String actGeneradora) throws ATiempoAppEx {
		servicios.configurarPresenciaDigital(act, nroPeticion, actGeneradora);
	}

	public void procesarRespuestaConfiguracionPresenciaDigital(TR054S tr054s)
			throws ATiempoAppEx {
		servicios.procesarRespuestaConfiguracionPresenciaDigital(tr054s);
	}

	/* FIN - RQ.10402 - mfmendez */

	/**
	 * Metodo que recibe la respuesta de puesto de voz avanzado
	 * 
	 * @param tr055s
	 */
	public void procesarRespuestaConfiguracionPdVA(TR055S tr055s) {
		servicios.procesarRespuestaConfiguracionPdVA(tr055s);
	}

	/**
	 * @param numeroPeticion
	 * @param idActividadFlujo
	 * @param codigoActividad
	 */
	public void configurarPdVa(ActividadEJBDTO act, Long numeroPeticion,
			Integer idActividadFlujo, String codigoActividad) {
		servicios.configurarPdVa(act, numeroPeticion, idActividadFlujo,
				codigoActividad);

	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarMediacionMovil()
	 */
	public void configurarMediacionMovil(ActividadEJBDTO act) throws ATiempoAppEx  {
		servicios.configurarMediacionMovil(act);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#recibirResConfMediacionMovil()
	 */
	public void recibirResConfMediacionMovil(TR612S tr) throws ATiempoAppEx {
		servicios.recibirResConfMediacionMovil(tr);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarPaqueteMovil(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void configurarPaqueteMovil(ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.configurarPaqueteMovil(act);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#recibirResConPaqueteMovil(co.com.telefonica.atiempo.interfaces.atiempo.TR612S)
	 */
	public void recibirResConPaqueteMovil(TR613S tr) throws ATiempoAppEx {
		servicios.recibirResConPaqueteMovil(tr);
	}
	
	public void recargaFijaMovil(ActividadEJBDTO act)throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.recargaFijaMovil(act);
	}
	/**
	 * @param act
	 * @param numeroPeticion
	 * @param codigoActividad
	 * @param ocVO
	 */
	public void respuestaFijaMovil(TR614S tr614s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.respuestaFijaMovil(tr614s);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarClienteZTE(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void configurarClienteZTE(ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.configurarClienteZTE(act);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaClienteZTE(co.com.telefonica.atiempo.interfaces.atiempo.TR612S)
	 */
	public void respuestaClienteZTE(TR056S tr) throws ATiempoAppEx {
		servicios.respuestaClienteZTE(tr);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarCamaraZTE(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void configurarCamaraZTE(Long numPeticion) throws ATiempoAppEx {
		servicios.configurarCamaraZTE(numPeticion);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaCamaraZTE(co.com.telefonica.atiempo.interfaces.atiempo.TR612S)
	 */
	public void respuestaCamaraZTE(TR057S tr) throws ATiempoAppEx {
		servicios.respuestaCamaraZTE(tr);
	}

	public void recepcionActivarCamaraAgendaSC(TR709S tr) throws ATiempoAppEx {
		servicios.recepcionActivarCamaraAgendaSC(tr);
	}

	public void enviarActivarCamaraAgendaSC(TR709E tr) throws ATiempoAppEx {
		servicios.enviarActivarCamaraAgendaSC(tr);
	}
		/**
	
		/**
	 * @param act
	 * @param numeroPeticion
	 * @param codigoActividad
	 * @param ocVO
	 */

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#enviarRefrecarDatos(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void enviarRefrecarDatos(Long idPeticion) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviarRefrecarDatos(idPeticion);
	}
	

	/**
	 * @param act
	 */
	 //requerimiento cofigurar Napster David Cardenas
	public void configurarNapster(ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.configurarNapster(act);
	}

	public void respuestaConfigurarNapster(TR604S tr) throws ATiempoAppEx {
		servicios.respuestaConfigurarNapster(tr);
	}
	//fin requerimiento cofigurar Napster


	public void configuracionAutIMS(ActividadEJBDTO act, String operacionComercial) throws ATiempoAppEx {
		servicios.configuracionAutIMS(act,operacionComercial);
	}

	public void respuestaConfiguracionAutIMS(TR602S tr) throws ATiempoAppEx {
		servicios.respuestaConfiguracionAutIMS(tr);
	}
	
	public void configuracionAutMSAN(ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.configuracionAutMSAN(act);
	}

	public void respuestaConfiguracionAutMSAN(TR603S tr) throws ATiempoAppEx {
		servicios.respuestaConfiguracionAutMSAN(tr);
	}
	
	
	public void desconfiguracionAutIMS(ActividadEJBDTO act, String operacionComercial) throws ATiempoAppEx {
		servicios.desconfiguracionAutIMS(act,operacionComercial);
	}

	public void respuestaDesconfiguracionAutIMS(TR602S tr) throws ATiempoAppEx {
		servicios.respuestaDesconfiguracionAutIMS(tr);
	}
	
		//FIN REQ FTTC

	
	public boolean recursosIgualesFTTC(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx {
		return servicios.recursosIgualesFTTC (recursos_linea_basicaLocal);
	}
	public void desconfiguracionAutMSAN(ActividadEJBDTO act) throws ATiempoAppEx {
		servicios.desconfiguracionAutMSAN(act);
	}
	
	public void CorregirDireccionPeticionTraslado(Long numeroAtis,Long petiNumero) throws ATiempoAppEx {
		servicios.CorregirDireccionPeticionTraslado(numeroAtis,petiNumero);
	}
	
	public void actualizarDireccionAtis(Long numeroAtis,Long petiNumero) throws ATiempoAppEx {
		servicios.actualizarDireccionAtis(numeroAtis,petiNumero);
	}
	
	public void ConfiguracionAutomaticaEOC(ActividadEJBDTO act)throws ATiempoAppEx{
	servicios.ConfiguracionAutomaticaEOC(act);
	}
	public void respuestaConfiguracionAutomaticaEOC(TR518S tr518s) throws ATiempoAppEx {
		servicios.respuestaConfiguracionAutomaticaEOC(tr518s);
	}
}