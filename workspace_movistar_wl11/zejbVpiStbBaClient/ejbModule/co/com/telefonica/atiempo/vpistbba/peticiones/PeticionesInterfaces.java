/*
 * Created on Feb 21, 2007
 */
package co.com.telefonica.atiempo.vpistbba.peticiones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import co.com.atiempo.dto.AgendaScDTO;
import co.com.atiempo.dto.BuscadorPeticionVpiDTO;
import co.com.atiempo.dto.PeticionDTO;
import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_ACSLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR001S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;

import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;

/**
 * fbd 20-03-2007: cierre de la peticion
 *
 * @author TCS
 */
public interface PeticionesInterfaces extends ComunInterfaces
{
	public ArrayList salvarPeticionATIS(TR001S peticionAtis) throws ATiempoAppEx;
	// fbd
	public void cerrarPeticion (Long nroPeticionVPI) throws ATiempoAppEx ;
	public void cerrarPeticionPrimaria (Long nroPeticionVPI) throws ATiempoAppEx ;
	public void cerrarPeticionPrimariaTv (Long nroPeticionVPI) throws ATiempoAppEx ;
	public ArrayList buscarPorCliente(Long idPeticionAtis, String rutCli, String rutDv) throws ATiempoAppEx;
	// LCA
	public PeticionDTO obtenerPeticionVPI(Long nroPeticionVPI) throws ATiempoAppEx ;
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario);
	public boolean pasaPSyOcXActividad(Long nroPeticion,Long psId,Long opcoId,Integer actividad_flujo_id) throws ATiempoAppEx;
	public ArrayList listaPsDePeticionQuePasaPorActividad(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx;
	public ArrayList recuperaPsParaQuiebresYNovedad(ActividadDTO actividadDTO, Long idPeticionAtiempo,Integer estado);
	public void grabarQuiebresNovedades(ArrayList listaQN,Long idPeticionAtiempo) throws Exception;
	public void grabarFechasCierrePorPs(ArrayList fechasCierrexPs,Long idPeticion) throws ATiempoAppEx;
	public InformacionAgendamientoDTO obtenerDatosAgendamiento (Long nroPeticionVPI) throws ATiempoAppEx;
	public ArrayList getListaAsignaciones(Long nroPeticion);
	public ArrayList listaDePsDelaActividadConEstadoOKEnAct(Long nroPeticion,Integer actividad_flujo_id,Long codigoActividad) throws ATiempoAppEx;
	public ArrayList listaDePsDelaActividadEstadoFinal(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx;
	public ArrayList listaDePsDelaActividadEstadoOKFinal(Long nroPeticion, Integer actividad_flujo_id) throws ATiempoAppEx;	
	public boolean estaOKProductoServicioPeticion(Producto_servicio_peticionLocal local);
	public boolean estaOKProductoServicioPeticion(Long peticion,PsVsOcVO psVsOcVO);
	public ArrayList listaDePsDePeticion(Long nroPeticion) throws ATiempoAppEx;
	public void propagaCausasPeticion(Long peticion,Long codActividadActual,Integer idActividadFlujoActual,Long idUsuario) throws ATiempoAppEx;
	public int tipoTrasladoSoloBa(Long nroPeticion) throws ATiempoAppEx;
	public Integer estaCerradaElAlta(Long nroPeticionBaja) throws ATiempoAppEx;
	public void propagaQuiebrePCaPS(Long petiNumero,Long idPs, Long idOC)throws ATiempoAppEx;
	public int esTraslado(Long peticion) throws ATiempoAppEx;
	public int indicadorLecturaContador(ActividadDTO actividadDTO,Long nroPet);
	public String obtenerCuentaCorreoUsuarioAcceso(Long idPeticion) throws ATiempoAppEx;
	public ArrayList estaOKPCTV(Long nroPeticion) throws ATiempoAppEx;
	public void insertarCausalesPeticion(Long peticion, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx;
	public boolean pasaXActividad(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx;
	public void setearFechaBajaPs(Long nroPeticion) throws ATiempoAppEx;
	public void marcaPeticionUsuario(Long nroPeticion,Long usuaId) throws ATiempoAppEx;
	public void actualizarEstadoPS(Long nroPeticion,Integer actividad_flujo_id,Long codigoActividad) throws ATiempoAppEx;
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - INICIO
	public ArrayList buscarPorCliente(Long idPeticionAtis) throws ATiempoAppEx;
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - FIN
	
	//CR-14657 Granite
	public boolean usaGranite(Long nroPeticion) throws ATiempoAppEx;
	
//	CR-14657 Granite - adocarmo
	public boolean centralSoportaConfAutomatica(Long nroPeticion) throws ATiempoAppEx;
	
//	CR-14657 Granite - adocarmo
	public boolean esTrasladoConIgualOrigenYDestino(Long nroPeticion)throws ATiempoAppEx;
//	CR-22569 agonz 25 feb 2009
	public boolean tienePS (ActividadEJBDTO act, int familia)throws ATiempoAppEx;	
	public Regla_RetencionesLocal verificarRetencion(Long peticion)throws ATiempoAppEx;
	public void marcarGranite(Long nroPeticion) throws ATiempoAppEx;
	/*
	 * CR 00024805 - May 12, 2009 - 1
	 * 		Se agrega metodo para busqueda por cliente con nuevos campos y consultas sql.
	 */
	public ArrayList buscarPeticiones(BuscadorPeticionVpiDTO busquedaPeticionDTO) throws ATiempoAppEx;
	
	
	// CR25865 CRE - adocarmo - inicio
	public String getPeti_id_instancia_con_pdti(Long nroPet);
	public Long getGrupoPS(Long ps);
	// CR25865 CRE - adocarmo - fin	
	public boolean validaAltaDuoUmts (Long nroPet,Iterator iterTemp) throws ATiempoAppEx;
	public boolean validaBajaDuoUmts (Long nroPet,Iterator iterTemp) throws ATiempoAppEx;
//	 CR25996 UMTS - agonz - 25/06/2009
    public HashMap cargarHashFamilias() throws ATiempoAppEx;
    public boolean esGrupoUmts(Long nroPet) throws ATiempoAppEx;
	
	//Gustavo - CR 25403
	public ArrayList recuperarCausaDemora() throws ATiempoAppEx;
	//Gustavo - CR 25403 - Fin
	
	public String operacionComercialUmts (Long nroPet) throws ATiempoAppEx;		
	
	//CR26362 - adocarmo - inicio
	public boolean existeBajaAsociada(Long nroPeticion);
	public Long petiAltaAsociada(Long nroPeticion);
	public void desbloqueaAltaTrasladoBa(Long numeroPeticion) throws ATiempoAppEx;
	public boolean nroOrigenyDestinoIguales(Long petiBaja, Long petiAlta);
	public boolean existeBajaConIgualNro(Long petiAlta);	
	boolean esBajaTrasladoSoloBA(Long idPeticion);
	boolean esBajaTrasladoSimple(Long idPeticion);
	public boolean pasoPorActividad(Long idPeticion,Integer idActividad);
	//CR26362 - adocarmo - fin
	public long operacionUmts(Long nroPet) throws ATiempoAppEx;
	
	public boolean getPeti_CRE(Long nroPet);
	
	//TODO: RETA - 24/09/2009 - Internet Equipado
	public Collection buscarEqInternetEquipado(Long nroPeticion) throws ATiempoAppEx;
	public void eliminarEqInternetEquipado(Long nroPeticion) throws ATiempoAppEx;
	//End TODO
	
	 /**
     * DAVID: se adiciona el siguiente código para RQ 28274, cobro incidencias.
     */
    public ArrayList obtenerSubpeticionesDesdePeticion(Long petiNumero);
    
    public ArrayList obtenerCaracteristicasDeSubpeticion(Subpeticion_atisLocal subpeticion_atisLocal);
    //    DAVID: fin
    
    
    public boolean validaDuoUmtsQuiebre(Long nroPet) throws ATiempoAppEx;
    
    public int esPsAsistenciaBAoLB(Producto_servicioKey producto_servicioKey);
    
    public void propagaQuiebresLBoBAaAsistencia(Long idPeticionAtiempo,String tipoAsistencia,Long idAct);
    
    public void insertarQuiebresFinalesPeticionesVuelo (Long nroPeticion);
    
    public Integer verificarEstadoPsPeticionPDTI(Long nroPeticion);
    public boolean esGrupoPdti(Long nroPet) throws ATiempoAppEx;
    
    //@idrincon - req 1716 - 13/10/2010
    public String buscarModemWifi(Long idPd);
    //fin - 13/10/2010
    
        // @author idrincon reagendamiento agenda sc - 10/09/2010
    public AgendaScDTO recuperaIdActuacionAgendaSC (Long petiNumero);
    //fin
    
    // @author idrincon reagendamiento agenda sc - 13/10/2010
    public boolean getEstadoAgendaSC(Long petiNumero);
    //fin
    
    //@author idrincon agendamiento agenda sc inserta la fecha en la tabla agenda_sc - 15/09/2010
    public boolean setearFechaAgendaSC( String idActuacion, String fechaPactada, boolean instalacionServicios, Long petiNumero);
    //fin
    
    //@author idrincon agendamiento agenda sc recuperar Lista de agendas sc - 17/ 09 /2010
    public ArrayList  recuperarListaAgendaSC(Long petiNumero);
    //fin
    
    public boolean tienePCAltaLB (Long idPd);
    // req 3709
    public ArrayList recuperarListaMensajesACS(Long petiNumero);
    // req 3709
    public ArrayList recuperarListaMensajesSMSACS(Long petiNumero);
    //req 3709
    public String recuperarParametroFromPropertiesBD(String codigo);    
	//req 3709
	public String extraerCellPhone(Long idPeticion);
	//req 3709
	public Long recuperaUltimaFechaSMSACS(Long petiNumero);
	//req 3709
	public Mensaje_ACSLocal recuperaUltimoMensajeACS(Long petiNumero);
	//req 3709
	public boolean esAutoInstalacionSoloBA(Long petiNumero);
	//req 3709
	public ArrayList recuperarListaMensajesConfModemsACS(Long petiNumero);
	//req 3709
	public boolean esPSDeRetencion(Long petiNumero);
	
	public boolean tieneCodActividadNoAvance(String codActividad);

	public TR703S obtenerMensajeAgendaSC(Long correlativoID);
	//Correccion para traslado con cambio de len sin cambio de numero
	public boolean esTrasladoConIgualLen(Long numeroPeticion) throws ATiempoAppEx;

//	//Validaciòn para centrales APSC
//	public boolean centralSoportaConfAutomaticaEOC(Long numeroPeticion)throws ATiempoAppEx;

	/**
	 * @param numeroPeticion
	 * @return
	 */
	public boolean esSVATemp(Long numeroPeticion);
	//Validaciòn para centrales APSC
	public boolean centralSoportaConfAutomaticaEOC(Long numeroPeticion)throws ATiempoAppEx;
	
	public boolean centralAntSoportaConfAutomaticaEOC(Long codCentral) throws ATiempoAppEx;
}
