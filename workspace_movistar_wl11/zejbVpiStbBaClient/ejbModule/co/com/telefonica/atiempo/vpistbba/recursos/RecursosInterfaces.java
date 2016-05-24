/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.recursos;

import java.util.ArrayList;

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

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author TCS
 */
public interface RecursosInterfaces extends ComunInterfaces {
	
	public void asignarRecursoSTB(TR010S tr010s) throws ATiempoAppEx;
	
	public void enviarRecursoSTB(Long peticion, String idActividad,Integer idActividadFlujo) throws ATiempoAppEx;
	
	public void asignarRecursoManualSTB(TR003S tr003s) throws ATiempoAppEx;
	
	public void asignarRecursoManualSTBGR(TR510S tr510s) throws ATiempoAppEx;

	public InformacionTecnicaDTO obtenerRecursosTecnicos( Long idPeticion ) throws ATiempoAppEx;
		
	public void envioPuntosRedSTB(String peticion, String idActividad) throws ATiempoAppEx;
	
	public void envioPuntosRedSTB(String telefonoConsulta, String peticion, String idActividad) throws ATiempoAppEx;
	
	public void consultaPuntosRedSTB(TR012S tr012s) throws ATiempoAppEx;
	
	public void envioLiberacionRecursos(Long peticion, String idActividad, Integer actividadFlujo)throws ATiempoAppEx;
	
	public void recepcionLiberacionRecursos(TR002S tr002s)throws ATiempoAppEx ;
	
	public void actualizarLecturaContador(Long idPeticion, String lecContadorDesde, String lecContadorHasta) throws ATiempoAppEx;
	
	public void eliminarLecturaContador(Long idPeticion) throws ATiempoAppEx;

	public ArrayList obtenerListaServiciosSuplementarios( Long idPeticion );
	
	public void consultaRecursoSTB_BA(Long peticion, String idActividad, String codActividad) throws ATiempoAppEx;

	// CR20948 - Altamira - guido - 20/Abr/2009
	public void altamiraEnvioTr601(ActividadEJBDTO act, String atiempoServiceName, boolean reversa) throws ATiempoAppEx;
	//CR-14657 - Granite - agonz - 15/10/2008
	public void enviarRecursoGraniteSTB(Long numeroPeticion, String codigoActividad, Integer idActividadFlujo)throws ATiempoAppEx;

	public void asignarRecursoGraniteSTB(TR510S tr510s)throws ATiempoAppEx;

	public void consultaRecursoGraniteSTB_BA(Long numeroPeticion, String codigoActividad, String codActividad)throws ATiempoAppEx;

	public void envioPuntosRedGraniteSTB(String string, String codigoActividad)throws ATiempoAppEx;
	
	// CR20948 - Altamira - adocarmo - 20/Abr/2009
	public void altamiraRecepcionTr601(TR601S tr601s) throws ATiempoAppEx;
	
	public void consultaPuntosRedGraniteSTB(TR514S tr514s) throws ATiempoAppEx;
	
	public void recepcionConfiguracionAutomaticaSTB(TR511S tr511s) throws ATiempoAppEx;
	
	public boolean enviaConfiguracionAutomaticaSTB (ActividadEJBDTO act) throws ATiempoAppEx;

	public void envioLiberacionRecursosGranite(Long numeroPeticion, String codigoActividad, Integer idActividadFlujo)throws ATiempoAppEx;	
	
	public boolean enviaDesconfigurarConfiguracionAutomaticaSTB (ActividadEJBDTO act) throws ATiempoAppEx;
	
	public void reversaAsignarRecursoGraniteSTB(TR513S tr513s)throws ATiempoAppEx;

	public boolean tieneCambioNumeroLB(Long idPeticion)  throws ATiempoAppEx ;

	public boolean esCruzada(Long idPeticion)throws ATiempoAppEx;
	
	public boolean tienePcLinea(Long idPeticion)  throws ATiempoAppEx ;

	public boolean tieneTrasladoLB(Long numeroPeticion)throws ATiempoAppEx ;

	public boolean esBajaLB(Long numeroPeticion)throws ATiempoAppEx ;
	
	public int enviaReversaConfiguracionAutomaticaSTB(ActividadEJBDTO act)throws ATiempoAppEx;
	public int enviaACrearODS(ActividadEJBDTO act)throws ATiempoAppEx;
	public void respuestaCreaOdsGranite(TR517S tr517s) throws ATiempoAppEx ;
	
	public void enviaMensajeTutorWeb(Long idPeticion, String idActividad, boolean yaEnvioPrimerMensajeMT, ActividadEJBDTO act) throws ATiempoAppEx;
	public void respuestaMensajeTutorWeb(TR047S tr047s) throws ATiempoAppEx;
	
	
	//@idrincon Req 3274 - 22/12/2010
	public void respuestaConsultaTroncalSip( TR049S tr049s ) throws ATiempoAppEx;
	
	//@idrincon req 3274 - 28/12/2010
	public void respuestaActivarLineasTRoncalSip( TR050S tr050s ) throws ATiempoAppEx;
	
	/**
	 * Metodo para enviar el mensaje TR052E cuando se presente un quiebre de un ps
	 * 	que sea de familia BA o LB
	 * @author mfmendez - Req 4659
	 * @param numeroPeticion, el identificador de la petición atiempo
	 * @param tr052e, el objeto con algunos de los datos que se deben enviar
	 * @return error, indica si se presento error en el envio del mensaje (true) o no (false)
	 */
	public boolean enviarInformeQuiebreFamiliaBAoLB(Long numeroPeticion, TR052E tr052e);
	
	/*RQ.10402 - mfmendez*/
	public void configurarPresenciaDigital(ActividadEJBDTO act, Long nroPeticion, String actGeneradora) throws ATiempoAppEx;
	
	public void procesarRespuestaConfiguracionPresenciaDigital(TR054S tr054s) throws ATiempoAppEx;	
	/*FIN - RQ.10402 - mfmendez*/
	
	public void configurarMediacionMovil(ActividadEJBDTO act) throws ATiempoAppEx;
	
	public void recibirResConfMediacionMovil(TR612S tr) throws ATiempoAppEx;
	
	public void configurarPaqueteMovil(ActividadEJBDTO act) throws ATiempoAppEx;
	
	public void recibirResConPaqueteMovil(TR613S tr) throws ATiempoAppEx;
	
	public void recargaFijaMovil(ActividadEJBDTO act)throws ATiempoAppEx;
	
	public void respuestaFijaMovil(TR614S tr614s)throws ATiempoAppEx;
	
	public void configurarClienteZTE(ActividadEJBDTO act) throws ATiempoAppEx;
	
	public void respuestaClienteZTE(TR056S tr) throws ATiempoAppEx;
	
	public void configurarCamaraZTE(Long numPeticion) throws ATiempoAppEx;
	
	public void respuestaCamaraZTE(TR057S tr) throws ATiempoAppEx;

	public void recepcionActivarCamaraAgendaSC(TR709S tr709s) throws ATiempoAppEx;

	public void enviarActivarCamaraAgendaSC(TR709E tr) throws ATiempoAppEx;
	
		/**
	
	/**
	 * @param idPeticion
	 * @param id_actuacion
	 * @param id
	 */
	public void enviarRefrecarDatos(Long idPeticion)throws ATiempoAppEx;
	public void ConfiguracionAutomaticaEOC(ActividadEJBDTO act)throws ATiempoAppEx;
	
	public void respuestaConfiguracionAutomaticaEOC(TR518S tr518s)throws ATiempoAppEx;
	
	
public void configuracionAutIMS(ActividadEJBDTO act, String operacionComercial)throws ATiempoAppEx;

public void respuestaConfiguracionAutIMS(TR602S tr602s)throws ATiempoAppEx;


public void configuracionAutMSAN(ActividadEJBDTO act)throws ATiempoAppEx;

public void respuestaConfiguracionAutMSAN(TR603S tr603s)throws ATiempoAppEx;


public void desconfiguracionAutIMS(ActividadEJBDTO act, String operacionComercial)throws ATiempoAppEx;

public void respuestaDesconfiguracionAutIMS(TR602S tr602s)throws ATiempoAppEx;

	public boolean recursosIgualesFTTC(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx ;
	
	//requerimiento cofigurar Napster David Cardenas
	public void configurarNapster(ActividadEJBDTO act)throws ATiempoAppEx;
	
	public void respuestaConfigurarNapster(TR604S tr604s)throws ATiempoAppEx;
	//fin requerimiento cofigurar Napster
	
	public void desconfiguracionAutMSAN(ActividadEJBDTO act)throws ATiempoAppEx;

	public void CorregirDireccionPeticionTraslado(Long numeroAtis,Long petiNumero) throws ATiempoAppEx;
	
	public void actualizarDireccionAtis(Long numeroAtis,Long petiNumero) throws ATiempoAppEx;
	
}
