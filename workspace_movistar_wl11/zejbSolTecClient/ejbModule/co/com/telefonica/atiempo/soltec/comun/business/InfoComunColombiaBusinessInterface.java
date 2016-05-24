package co.com.telefonica.atiempo.soltec.comun.business;

import java.util.ArrayList;
import java.util.Collection;

import co.com.telefonica.atiempo.soltec.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.RecursosBADTO;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
/*import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPCDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPSDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;*/

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface InfoComunColombiaBusinessInterface {
/*	public InformacionAdslDTO obtenerInformacionAdsl(Long nroPeticion) throws ATiempoAppEx;
	public InformacionPeticionDTO obtenerInformacionPeticion(Long nroPeticionVpi) throws ATiempoAppEx;
	public InformacionTecnicaDTO obtenerInformacionTecnica(Long nroPeticion) throws ATiempoAppEx;*/
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario);
//	public ArrayList getPsYTipoPcNoRealizado(Long idPeticion);
	public Collection obtenerDecoTarjetas(Long idPeticion) throws ATiempoAppEx;
	public ArrayList obtenerLocalizacionesPorActividad(Long idActividad) throws ATiempoAppEx;
	public ArrayList obtenerLocalizaciones(Long fapsId) throws ATiempoAppEx;
	public ArrayList obtenerCierresAverias(Integer codLocal) throws ATiempoAppEx;
	public Collection obtenerUsuariosCompatibles(Long idPeticion,Long  idUser, String codAct, Long idAplicacion);
	public RecursosBADTO obtenerRecursosBA(Long codAveOri) throws ATiempoAppEx;
	public ArrayList getDatosPeticionMasivo(ArrayList arrCodigoAve) throws ATiempoAppEx;
	public InformacionTecnicaDTO obtenerDatosTecnicosLB(Long codAveOri) throws ATiempoAppEx;
	public Peticion_stDTO getDatosPeticion (Long codigoAve) throws ATiempoAppEx;  
	public PruebaLineaDTO getUtlimaPruebaLineaByPeticion(Long idPeticion);
	public ArrayList getPruebasLineaByPeticion(Long idPeticion);
	public ArrayList getCodPruebaLineaByFamilia(Long familia);
	public Collection obtenerTematico(Long codAveOri);
	public InformacionAgendamientoDTO obtenerInformacionAgendamiento(Long codAveOri);	
	public ArrayList getHistorico(Long codigoAve);
	public ArrayList getTecnicos(Long idUsuario);
	public ArrayList getCodigoDiagnostico(); 
	public ArrayList recuperaControlesVisita(Long codAveCd) throws ATiempoAppEx;
	public ArrayList recuperarListaAgendaSC(Long codAve);
}
