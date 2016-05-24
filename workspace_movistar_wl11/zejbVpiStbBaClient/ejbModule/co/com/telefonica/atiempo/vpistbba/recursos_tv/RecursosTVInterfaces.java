
package co.com.telefonica.atiempo.vpistbba.recursos_tv ;

import java.util.ArrayList;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR605S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InfoInvTvDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InfoPostVentaTV;

import com.telefonica_chile.comun.ComunInterfaces;


/**
 * This is the business interface for RecursosTV enterprise bean.
 */
public interface RecursosTVInterfaces
    extends ComunInterfaces
{
	//TODO PVR SE CAMBIO LA FIRMA DEL METODO PARA QUE MANEJE EL TIPO DE DECO
    Long enviaDecoTarjetaPorUtilizar (long idPeticion, String ult4digitosTarjeta, String ult4digitosDeco, long idContratista,String tipoDeco) throws ATiempoAppEx;
	TR016S buscarRespuestaMensaje(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
    void actualizaDecoTarjetaPorUtilizar (TR016S tr016s) throws ATiempoAppEx;
    Long enviaConfiguracionServiciosTV (long idPeticion) throws ATiempoAppEx;
	Long enviaConfiguracionServiciosTVEqYPares(long idPeticion) throws ATiempoAppEx;
	Long enviaConfiguracionServiciosTVAuto (long idPeticion,String idActividad,Integer idActividadFlujo) throws ATiempoAppEx;
	Long enviaConfiguracionServiciosTVDesactivacion(long l, String string, Integer integer) throws ATiempoAppEx;
	Long enviaConfiguracionServiciosTVAgendaSC (long idPeticion, ArrayList decosAInstalar, boolean seInstalanPaquetes, String idAgenda) throws ATiempoAppEx;
    void actualizaConfiguracionServiciosTV (TR017S tr017s, boolean esAgendaSC) throws ATiempoAppEx;
	Long enviarActivacion(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx;
	ArrayList getListaDecoTarjetas(Long idPeticion) throws ATiempoAppEx;
	ArrayList getListaDecoTarjetasActivas(Long idPeticion) throws ATiempoAppEx;
	ArrayList getListaDecoTarjetasEnCas(Long idPeticion) throws ATiempoAppEx;
    Long enviaActualizacionInventarioTV (long idPeticion, String idActividad) throws ATiempoAppEx;
    void recibeActualizacionInventarioTV (TR018S tr018s) throws ATiempoAppEx;
    Long enviaSolicitudInformacionTecnicaTV (long idPeticion, String idActividad) throws ATiempoAppEx;
    void recibeSolicitudInformacionTecnicaTV (TR019S tr019s) throws ATiempoAppEx;
	Long enviarActivacionSoloEq(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx;
	Long enviarActivacionMigracion(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx;
	Long enviarActivacionEqYPares(Long idPeticion, ArrayList listaEq) throws ATiempoAppEx;
	Long enviarActivacionCI(Long idPeticion, ArrayList listaActivar,ArrayList listaEliminar) throws ATiempoAppEx;
	InfoPostVentaTV recuperaInfoTvPostVenta(Long long1) throws ATiempoAppEx;
	void grabaDecosPostVenta(Long nroPeticion, ArrayList listaPares, long opCoPostPar) throws ATiempoAppEx;
	ArrayList recuperaDecosPostVenta(Long nroPeticion) throws ATiempoAppEx;
	void actualizaAccionParesPostVenta(Long nroPeticion,ArrayList listaPares) throws ATiempoAppEx;
	boolean tienePcEnAlta(Long nroPeticion) throws ATiempoAppEx;
	void enviaRefrescoPares(Long nroPeticion) throws ATiempoAppEx;
	ArrayList getListaTematicos(Long idPeticion) throws ATiempoAppEx;
	InfoInvTvDTO recuperaInfoInvTv(Long idPeticion) throws ATiempoAppEx;
	void limpiarInventarioDTH(Long id_peticion) throws ATiempoAppEx;
	boolean tieneAltaPcYaEcha(Long idPeticion) throws ATiempoAppEx;
	void revisaYMarcaNovedadAutoTv(Long petiNumero) throws ATiempoAppEx;
	String cargarInfoMigracionDeco2(Long idPeticion)throws ATiempoAppEx ;
	int cargarInfoMigracionDeco(Long idPeticion)throws ATiempoAppEx ;
//	<!--TODO: Defecto AT-1138 Se agrega metodo que retorna la cantidad de Ps de alta PVR- PVR- Inicio - Juan-->
	int retornarCantidadDecosAltaPvr(Long idPeticion) throws ATiempoAppEx;
	int pasoPorInstalacion(Long idPeticion) throws ATiempoAppEx;
	void marcarNovedadQuiebrePeticionesVueloAgendaSC(Long petiNumero, ArrayList listaDecosTarjetasAgendaSC) throws ATiempoAppEx;
    Long enviaConfiguracionServiciosTVEnVuelo (long idPeticion, boolean elementosViejos, boolean tienefamiliaPaqueteTV, boolean tienefamiliaTematicoTV) throws ATiempoAppEx;
    
    public void enviaConfiguracionMovistarPlay(long petiNumero, ActividadEJBDTO act)throws ATiempoAppEx;
    public void respuestaConfigMovistarPlay(TR605S tr605s)throws ATiempoAppEx;
}
