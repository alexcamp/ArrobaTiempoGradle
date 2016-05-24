package co.com.telefonica.atiempo.ejb.eb;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJBLocalObject;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR029S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR053S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR611S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * Local interface for Enterprise Bean: Equipo
 */
public interface EquipoLocal extends EJBLocalObject {   
	public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos) throws ATiempoAppEx;
	// German P. - Se pasa contratista como argumento
	public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos, Long idEmContratista) throws ATiempoAppEx;
	public Collection esTipoPDTI(Long ps_id) throws ATiempoAppEx ;
	public TR026S buscarRespuestaMensajeEquipo(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
	public void actualizaEquipoPorUtilizar(TR026S tr026s) throws ATiempoAppEx;
	Collection getEquipos(Long idPeticion) throws ATiempoAppEx;
	public Collection pestanaEquipos(Long ps_id) throws ATiempoAppEx;
	public Collection obtenerElementos(Long ps_id) throws ATiempoAppEx;
	public void grabaEquiposVpi(Long nroPeticion,Long telAsignado,ArrayList equipos);
	public boolean noHayEquipoParaActualizarInventarioBa(Long nroPeticion) throws ATiempoAppEx;
	public void enviaActualizaInventarioEquipo(String peticion, String id_actividad) throws ATiempoAppEx;
	public void recepcionActualizaInventarioBA(TR028S tr028s) throws ATiempoAppEx;
	public Integer identificarOperacion(Long nroPeticion);
	public ArrayList loadJSP(TR026S tr026s,Long idPeticion);	
	public void recepcionConfiguracionActualEquipo(TR027S tr027s) throws ATiempoAppEx;
	public void enviarConfiguracionActualEquipos(String telefonoConsulta, String peticion, String id_actividad) throws ATiempoAppEx;
	public void enviarConfiguracionActualEquipos(String peticion, String id_actividad) throws ATiempoAppEx ;
	public ArrayList recuperaEquiposVPi(Long nroPeticion);
	//UMTS Se copia el metodo "enviaEquipoPorUtilizar" agregando un nuevo parametro de entrada 
	public Long enviaEquipoPorUtilizar(long idPeticion, String ult4Digitos,String tipoEquipo, String tipoElemento) throws ATiempoAppEx;
	
	public ArrayList recuperaEquiposVPiUmts(Long nroPeticion);
	public ArrayList recuperaEquiposVPiPdti(Long nroPeticion);
	public Collection getEquiposGrupo(Long idPeticion, int ID_GRUPO_PDTI)throws ATiempoAppEx  ;
	
	public void recibirPrimeraFacturaInternetEquipado(TR044S tr044s) throws ATiempoAppEx;
	
	/*RQ 6895 - Interfaz Atiempo - MM SAP*/
	public boolean enviarInformacionEquiposMMSAP(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;
	public void procesarRespuestaInformacionEquiposMMSAP(TR029S tr029s) throws ATiempoAppEx;
	
	//RQ 7803 - tv sola
	public void recibirConfirmacionPagosTVSola(TR611S tr611s, String CorrelationID) throws ATiempoAppEx;

	/*RQ.8595 - mfmendez*/
	public void procesarRespuestaVistaGlobal(TR025S tr025s) throws ATiempoAppEx;	
	public void procesarRespuestaNotificacionVentaMinoristasSAP(TR020S tr020s) throws ATiempoAppEx;
	public void enviarSolicitudVistaGlobal(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;	
	public void enviarInformacionEquiposVentaMinoristasSAP(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;
	/*FIN - RQ.8595 - mfmendez*/
	/**
	 * @param act
	 * @param numeroPeticion
	 * @param codigoActividad
	 */
	public void ConsultarEstadoDTH(ActividadEJBDTO act, Long numeroPeticion, String codigoActividad);
	/**
	 * @param tr053s
	 */
	public void recepcionConsultarEstadoDTH(TR053S tr053s);
	
}

