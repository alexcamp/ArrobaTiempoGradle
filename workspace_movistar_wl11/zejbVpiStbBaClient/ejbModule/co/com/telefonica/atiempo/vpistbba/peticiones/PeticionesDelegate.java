/*
 * Created on Feb 21, 2007
 */
package co.com.telefonica.atiempo.vpistbba.peticiones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

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
import co.com.telefonica.atiempo.interfaces.atiempo.TR705S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocal;
import co.com.telefonica.atiempo.vpistbba.peticiones.ejb.PeticionesServicesLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;

/**
 * @author TCS
 */
public class PeticionesDelegate implements PeticionesInterfaces {

	private PeticionesServicesLocal servicios;

	public PeticionesDelegate() throws ATiempoAppEx {
		try {
			servicios =
				((PeticionesServicesLocalHome) HomeFactory.getHome(PeticionesServicesLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	public ArrayList salvarPeticionATIS(TR001S peticionAtis) throws ATiempoAppEx {
		return servicios.salvarPeticionATIS(peticionAtis);
	}

    // fbd
    public void cerrarPeticion (Long numeroPeticion) throws ATiempoAppEx 
    {
        servicios.cerrarPeticion (numeroPeticion) ;
    }

    //Agregado el 6/5/2008
    //Para realizar el cierre anticipado
	public void cerrarPeticionPrimaria (Long numeroPeticion) throws ATiempoAppEx 
	{
		servicios.cerrarPeticionPrimaria (numeroPeticion) ;
	}
	
	public void cerrarPeticionPrimariaTv (Long numeroPeticion) throws ATiempoAppEx 
	{
		servicios.cerrarPeticionPrimariaTv(numeroPeticion) ;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#obtenerPeticionVPI(java.lang.Long)
	 */
	public PeticionDTO obtenerPeticionVPI(Long nroPeticionVPI) throws ATiempoAppEx {
		
		return servicios.obtenerPeticionVPI(nroPeticionVPI);
	}

	public ArrayList buscarPorCliente(Long idPeticionAtis, String rutCli, String rutDv) throws ATiempoAppEx {
		return servicios.buscarPorCliente(idPeticionAtis, rutCli, rutDv);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#obtenerInformacionEmpresa(java.lang.Long)
	 */
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		return servicios.obtenerInformacionEmpresa(idUsuario);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#pasaPSyOcXActividad(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public boolean pasaPSyOcXActividad(Long nroPeticion,Long psId,Long opcoId,Integer actividad_flujo_id) throws ATiempoAppEx
	{
		return servicios.pasaPSyOcXActividad(nroPeticion,psId,opcoId,actividad_flujo_id);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#listaPsDePeticionQuePasaPorActividad(java.lang.Long, java.lang.Long)
	 */
	public ArrayList listaPsDePeticionQuePasaPorActividad(Long nroPeticion, Integer actividad_flujo_id) throws ATiempoAppEx {
		return servicios.listaPsDePeticionQuePasaPorActividad(nroPeticion,actividad_flujo_id);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#recuperaPsParaQuiebresYNovedad(java.lang.String, java.lang.Long)
	 */
	public ArrayList recuperaPsParaQuiebresYNovedad(ActividadDTO actividadDTO, Long idPeticionAtiempo,Integer estado)
	{
		return servicios.recuperaPsParaQuiebresYNovedad(actividadDTO,idPeticionAtiempo,estado);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#grabarQuiebresNovedades(java.util.ArrayList, java.lang.Long)
	 */
	public void grabarQuiebresNovedades(ArrayList listaQN, Long idPeticionAtiempo) throws Exception {
		servicios.grabarQuiebresNovedades(listaQN,idPeticionAtiempo);
		
	}

	public InformacionAgendamientoDTO obtenerDatosAgendamiento (Long nroPeticionVPI) throws ATiempoAppEx {
		return servicios.obtenerDatosAgendamiento( nroPeticionVPI );
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#listaDePsDelaActividadEstadoFinal(java.lang.Long, java.lang.Integer)
	 */
	public ArrayList listaDePsDelaActividadEstadoFinal(Long nroPeticion, Integer actividad_flujo_id) throws ATiempoAppEx {
		return servicios.listaDePsDelaActividadEstadoFinal(nroPeticion,actividad_flujo_id);
	}
	
	public ArrayList listaDePsDelaActividadEstadoOKFinal(Long nroPeticion, Integer actividad_flujo_id) throws ATiempoAppEx {
		return servicios.listaDePsDelaActividadEstadoOKFinal(nroPeticion,actividad_flujo_id);
	}
	
	public ArrayList getListaAsignaciones(Long nroPeticion) {
		return servicios.getListaAsignaciones( nroPeticion );
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#estaOKProductoServicioPeticion(co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal)
	 */
	public boolean estaOKProductoServicioPeticion(Producto_servicio_peticionLocal local)
	{
		return servicios.estaOKProductoServicioPeticion(local);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#estaOKProductoServicioPeticion(java.lang.Long, co.com.atiempo.dto.PsVsOcVO)
	 */
	public boolean estaOKProductoServicioPeticion(Long peticion, PsVsOcVO psVsOcVO)
	{
		return servicios.estaOKProductoServicioPeticion(peticion,psVsOcVO);
	}
	
	public ArrayList listaDePsDePeticion(Long nroPeticion) throws ATiempoAppEx
	{
			return servicios.listaDePsDePeticion(nroPeticion);
	}

	public void insertarQuiebresFinalesPeticionesVuelo (Long nroPeticion)
	{
			servicios.insertarQuiebresFinalesPeticionesVuelo(nroPeticion);
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#propagaCausasPeticion(java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Long)
	 */
	public void propagaCausasPeticion(Long peticion, Long codActividadActual, Integer idActividadFlujoActual, Long idUsuario) throws ATiempoAppEx
	{
		servicios.propagaCausasPeticion(peticion,codActividadActual,idActividadFlujoActual,idUsuario);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#listaDePsDelaActividadConEstadoOKEnAct(java.lang.Long, java.lang.Integer, java.lang.Long)
	 */
	public ArrayList listaDePsDelaActividadConEstadoOKEnAct(Long nroPeticion, Integer actividad_flujo_id, Long codigoActividad) throws ATiempoAppEx
	{
		return servicios.listaDePsDelaActividadConEstadoOKEnAct(nroPeticion,actividad_flujo_id,codigoActividad);
	}
	
	public int tipoTrasladoSoloBa(Long nroPeticion) throws ATiempoAppEx
	{
		return servicios.tipoTrasladoSoloBa(nroPeticion);
	}
	
	public Integer estaCerradaElAlta(Long nroPeticionBaja) throws ATiempoAppEx 
	{
		return servicios.estaCerradaElAlta(nroPeticionBaja);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#propagaQuiebrePCaPS(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public void propagaQuiebrePCaPS(Long petiNumero, Long idPs, Long idOC) throws ATiempoAppEx {
		servicios.propagaQuiebrePCaPS(petiNumero,idPs,idOC);
		
	}

	public int esTraslado(Long peticion) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		return servicios.esTraslado(peticion);
	}
	
	public int indicadorLecturaContador(ActividadDTO actividadDTO,Long nroPet)
	{
		return servicios.indicadorLecturaContador(actividadDTO,nroPet);
	}

	public String obtenerCuentaCorreoUsuarioAcceso(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.obtenerCuentaCorreoUsuarioAcceso(idPeticion);
	}
	
	public ArrayList estaOKPCTV(Long nroPeticion) throws ATiempoAppEx
	{
		return servicios.estaOKPCTV(nroPeticion);
	}

	public void insertarCausalesPeticion(Long peticion, String cod_actividad, Long codCausal, Integer idActividadFlujo) throws ATiempoAppEx
	{
		servicios.insertarCausalesPeticion(peticion,cod_actividad,codCausal,idActividadFlujo);	
	}

	public boolean pasaXActividad(Long nroPeticion, Integer actividad_flujo_id) throws ATiempoAppEx
	{
		return servicios.pasaXActividad(nroPeticion,actividad_flujo_id);
	}

	public void grabarFechasCierrePorPs(ArrayList fechasCierrexPs,Long idPeticion) throws ATiempoAppEx
	{
		servicios.grabarFechasCierrePorPs(fechasCierrexPs,idPeticion);		
	}
	
	public void setearFechaBajaPs(Long nroPeticion) throws ATiempoAppEx
	{
		servicios.setearFechaBajaPs(nroPeticion);		
	}

	public void marcaPeticionUsuario(Long nroPeticion,Long usuaId) throws ATiempoAppEx
	{
		servicios.marcaPeticionUsuario(nroPeticion,usuaId);
	}
	public void actualizarEstadoPS(Long nroPeticion,Integer actividad_flujo_id,Long codigoActividad) throws ATiempoAppEx{
		
			servicios.actualizarEstadoPS( nroPeticion, actividad_flujo_id, codigoActividad);
	}
	
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - INICIO
	public ArrayList  buscarPorCliente(Long idPeticionAtis) throws ATiempoAppEx
	{
		return servicios.buscarPorCliente(idPeticionAtis);
	}
	//TODO: CR4860 SIGRES - BANDEJA ESPERA - GUSTAVO - FIN
    // CR18865 - ana santos
	public boolean esBaja(Long idPeticion) {
		return servicios.esBaja(idPeticion);
	}
	// CR18865 - ana santos

	public boolean esAlta(Long idPeticion) {
		return servicios.esAlta(idPeticion);
	}
	
	public boolean usaGranite(Long nroPeticion) throws ATiempoAppEx{
		return servicios.usaGranite(nroPeticion);
	}

	/*
	 * CR 00024805 - May 12, 2009 - 1
	 * 		Función para la busqueda de peticiones por clientes.
	 */
	public ArrayList buscarPeticiones(BuscadorPeticionVpiDTO busquedaPeticionDTO) throws ATiempoAppEx {
		return servicios.buscarPeticiones(busquedaPeticionDTO);
	}
	
	// CR25865 CRE - adocarmo - inicio
	public String getPeti_id_instancia_con_pdti(Long nroPet) {
		return servicios.getPeti_id_instancia_con_pdti(nroPet);
	}
	
	
	public boolean centralSoportaConfAutomatica(Long nroPeticion) throws ATiempoAppEx{
		return servicios.centralSoportaConfAutomatica(nroPeticion);
	}

	public Long getGrupoPS(Long ps){
		return servicios.getGrupoPS(ps);
	}
	// CR25865 CRE - adocarmo - fin

//	 CR25996 UMTS - agonz - 25/06/2009
    public HashMap cargarHashFamilias() throws ATiempoAppEx {
        return servicios.cargarHashFamilias();
    }
    public boolean esGrupoUmts(Long nroPet) throws ATiempoAppEx {
        return servicios.esGrupoUmts(nroPet);
    }
    public boolean validaAltaDuoUmts(Long nroPet,Iterator iterTemp) throws ATiempoAppEx {
        return servicios.validaAltaDuoUmts(nroPet,iterTemp);
    }
    public boolean validaBajaDuoUmts(Long nroPet,Iterator iterTemp) throws ATiempoAppEx {
        return servicios.validaBajaDuoUmts(nroPet,iterTemp);
    }
	//Gustavo - CR 25403
	public ArrayList recuperarCausaDemora() throws ATiempoAppEx{
		return servicios.recuperarCausaDemora();
	}

	public ArrayList recuperaPsVisita(Long nroPeticion) throws ATiempoAppEx{
		return servicios.recuperaPsVisita(nroPeticion);
	}
	//Gustavo - CR 25403 - Fin
	
	public String operacionComercialUmts (Long nroPet) throws ATiempoAppEx{
		return servicios.operacionComercialUmts(nroPet);
	}
	
	public boolean esTrasladoConIgualOrigenYDestino(Long nroPeticion)throws ATiempoAppEx {
		return servicios.esTrasladoConIgualOrigenYDestino(nroPeticion);
	}
//	CR-22569 agonz 25 feb 2009
	public boolean tienePS(ActividadEJBDTO act, int familia) throws ATiempoAppEx {
		return servicios.tienePS(act,familia);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#estaOKProductoServicioPeticion(java.lang.Long, co.com.atiempo.dto.PsVsOcVO)
	 */
	public Regla_RetencionesLocal verificarRetencion(Long peticion)throws ATiempoAppEx {
		return servicios.verificarRetencion(peticion);
	}
	public void marcarGranite(Long nroPeticion) throws ATiempoAppEx{
		 servicios.marcarGranite(nroPeticion);
		
	}
		
	//CR26362 - adocarmo - inicio
	public boolean existeBajaAsociada(Long nroPeticion) {
		return servicios.existeBajaAsociada(nroPeticion);
	}

	public Long petiAltaAsociada(Long nroPeticion) {
		return servicios.petiAltaAsociada(nroPeticion);
	}
	
	public void desbloqueaAltaTrasladoBa(Long nroPeticion) throws ATiempoAppEx {
		servicios.desbloqueaAltaTrasladoBa(nroPeticion);
	}
	
	public boolean nroOrigenyDestinoIguales(Long petiBaja, Long petiAlta) {
		return servicios.nroOrigenyDestinoIguales(petiBaja,petiAlta);
	}
	
	public boolean existeBajaConIgualNro(Long petiAlta) {
		return servicios.existeBajaConIgualNro(petiAlta);
	}
	
	public boolean esBajaTrasladoSoloBA(Long idPeticion) {
		return servicios.esBajaTrasladoSoloBA(idPeticion);
	}
	
	public boolean esBajaTrasladoSimple(Long idPeticion) {
		return servicios.esBajaTrasladoSimple(idPeticion);
	}
	
	public boolean pasoPorActividad(Long idPeticion,Integer idActividad) {
		return servicios.pasoPorActividad(idPeticion,idActividad);
	}
	public long operacionUmts(Long nroPet) throws ATiempoAppEx{
		return servicios.operacionUmts( nroPet);
	}
	//CR26362 - adocarmo - fin	

	public String obtenerTipoOpCoPorPs(Long idPeticion) throws ATiempoAppEx{
		// TODO Apéndice de método generado automáticamente
		return servicios.obtenerTipoOpCoPorPs(idPeticion);
	}
	
	public boolean getPeti_CRE(Long nroPet) {
		return servicios.getPeti_CRE(nroPet);
	}
    
	/* (non-Javadoc)
     * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#buscarEqInternetEquipado(java.lang.Long)
     */
	public Collection buscarEqInternetEquipado(Long nroPeticion) throws ATiempoAppEx{
        return servicios.buscarEqInternetEquipado(nroPeticion);
    }

	public void eliminarEqInternetEquipado(Long nroPeticion) throws ATiempoAppEx{
		servicios.eliminarEqInternetEquipado(nroPeticion);
	}
	
	/**
     * DAVID: se adiciona el siguiente código para RQ 28274, cobro incidencias.
     */
    public ArrayList obtenerSubpeticionesDesdePeticion(Long petiNumero){
        return servicios.obtenerSubpeticionesDesdePeticion(petiNumero);
    }
    public ArrayList obtenerCaracteristicasDeSubpeticion(Subpeticion_atisLocal subpeticion_atisLocal){
        return servicios.obtenerCaracteristicasDeSubpeticion(subpeticion_atisLocal);
    }
    
    //DAVID: fin
    
    public boolean validaDuoUmtsQuiebre(Long nroPet) throws ATiempoAppEx{
    	return servicios.validaDuoUmtsQuiebre(nroPet);
    }
    
    public int esPsAsistenciaBAoLB(Producto_servicioKey producto_servicioKey){
    	return servicios.esPsAsistenciaBAoLB(producto_servicioKey);
    }
    
    public void propagaQuiebresLBoBAaAsistencia(Long idPeticionAtiempo,String tipoAsistencia, Long idAct){
    	servicios.propagaQuiebresLBoBAaAsistencia(idPeticionAtiempo,tipoAsistencia,idAct);
    }
    public Integer verificarEstadoPsPeticionPDTI(Long nroPeticion){		
    	return servicios.verificarEstadoPsPeticionPDTI(nroPeticion);
    }
    public boolean esGrupoPdti(Long nroPet) throws ATiempoAppEx {
        return servicios.esGrupoPdti(nroPet);
    }
    
    // @author idrincon reagendamiento agenda sc - 10/09/2010
    public AgendaScDTO recuperaIdActuacionAgendaSC( Long petiNumero ){
    	return servicios.recuperaIdActuacionAgendaSC(petiNumero);
    }
    //fin

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#getEstadoAgendaSC(java.lang.Long)
	 */
	public boolean getEstadoAgendaSC(Long petiNumero) {
		return servicios.getEstadoAgendaSC(petiNumero);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#setearFechaAgendaSC(java.lang.String)
	 */
	public boolean setearFechaAgendaSC(String idActuacion, String fechaPactada, boolean instalacionServicios, Long petiNumero ) {
		return servicios.setearFechaAgendaSC( idActuacion, fechaPactada, instalacionServicios, petiNumero );
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#recuperarListaAgendaSC()
	 */
	public ArrayList recuperarListaAgendaSC(Long petiNumero) {
		return servicios.recuperarListaAgendaSC(petiNumero);
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#buscarModenWifi()
	 */
	public String buscarModemWifi(Long idPd) {
		return servicios.buscarModemWifi(idPd);
	}

	public boolean tienePCAltaLB (Long idPd){
		return servicios.tienePCAltaLB (idPd);
	}
	//req 3709
	public ArrayList recuperarListaMensajesACS(Long petiNumero) {
		return servicios.recuperarListaMensajesACS(petiNumero);
	}
	//req 3709
	public ArrayList recuperarListaMensajesSMSACS(Long petiNumero) {
		return servicios.recuperarListaMensajesSMSACS(petiNumero);
	}
	//req 3709
	public String recuperarParametroFromPropertiesBD(String codigo) {
		return servicios.recuperarParametroFromPropertiesBD(codigo);
	}
	//req 3709
	public String extraerCellPhone(Long idPeticion){
		return servicios.extraerCellPhone(idPeticion);
	}
	//req 3709
	public Long recuperaUltimaFechaSMSACS(Long petiNumero){
		return servicios.recuperaUltimaFechaSMSACS(petiNumero);
	}
	//req 3709
	public Mensaje_ACSLocal recuperaUltimoMensajeACS(Long petiNumero){
		return servicios.recuperaUltimoMensajeACS(petiNumero);
	}
	//req 3709
	public boolean esAutoInstalacionSoloBA(Long petiNumero){
		return servicios.esAutoInstalacionSoloBA(petiNumero);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#recuperarListaMensajesConfModemsACS(java.lang.Long)
	 */
	public ArrayList recuperarListaMensajesConfModemsACS(Long petiNumero) {
		return servicios.recuperarListaMensajesConfModemsACS(petiNumero);
	}
	public boolean esPSDeRetencion(Long petiNumero){
		return servicios.esPSDeRetencion(petiNumero);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#tieneCodActividadNoAvance(java.lang.String)
	 */
	public boolean tieneCodActividadNoAvance(String codActividad) {
		return servicios.tieneCodActividadNoAvance(codActividad);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#obtenerEstadoMensajeAgendaSC(java.lang.Long)
	 */
	public TR703S obtenerMensajeAgendaSC(Long correlativoID) {
		return servicios.obtenerMensajeAgendaSC(correlativoID);
	}
	
	public TR705S obtenerMensajeReagendamientoASC(Long correlativoID) {
		return servicios.obtenerMensajeReagendamientoASC(correlativoID);
	}

	/**
	 * @param nroPeticion
	 * @return
	 */
	public boolean esActuacionCCF(Long petiNumero){		
		return servicios.esActuacionCCF(petiNumero);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces#esTrasladoConIgualLen(java.lang.Long)
	 */
	public boolean esTrasladoConIgualLen(Long numeroPeticion) throws ATiempoAppEx {
		return servicios.esTrasladoConIgualLen(numeroPeticion);
	}

	/**
	 * @param numeroPeticion
	 * @return
	 */
	public boolean esSVATemp(Long numeroPeticion) {
		// TODO Apéndice de método generado automáticamente
		return servicios.esSVATemp(numeroPeticion);
	}
	
	public boolean centralSoportaConfAutomaticaEOC(Long numeroPeticion)throws ATiempoAppEx{
		return servicios.centralSoportaConfAutomaticaEOC(numeroPeticion);
	}
	
	public boolean centralAntSoportaConfAutomaticaEOC(Long codCentral) throws ATiempoAppEx{
		return servicios.centralAntSoportaConfAutomaticaEOC(codCentral);
	}
}
