package co.com.telefonica.atiempo.vpistbba.comun.business;

import java.util.ArrayList;

import javax.naming.NamingException;

import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;

public interface InfoComunColombiaBusinessInterface
{
	public InformacionAdslDTO obtenerInformacionAdsl(Long nroPeticion) throws ATiempoAppEx;
	public InformacionPeticionDTO obtenerInformacionPeticion(Long nroPeticionVpi) throws ATiempoAppEx;
	public InformacionPeticionDTO obtenerSimpleInfoPeticion(Long nroPeticionVpi) throws ATiempoAppEx;
	public InformacionTecnicaDTO obtenerInformacionTecnica(Long nroPeticion) throws ATiempoAppEx;
	public ArrayList getProductoServicio(Long nroPeticion) throws ATiempoAppEx;
	public ArrayList getProductoServicioMasQuiebres(Long nroPeticion) throws ATiempoAppEx;
	public ArrayList getPSQuiebresYChulo(Long nroPeticion,Long codigoActividad) throws ATiempoAppEx;
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario);
	public ArrayList obtenerServiciosSuplementarios(Long idPeticion) throws ATiempoAppEx;
	public ArrayList getPsYTipoPcNoRealizado(Long idPeticion);
	public InformacionAdslDTO obtenerInformacionAdslActual(Long nroPeticion) throws ATiempoAppEx;
//	public String obtenerCuentaCorreoPadre(Long idPeticion) throws ATiempoAppEx;
	public ArrayList recuperaControlesVisita(Long petiNumero) throws ATiempoAppEx;
	public ArrayList recuperaCaracteristicasVisualizacion(Long petiNumero) throws Exception;
	public Long obtenerCodigoAPSC(Long codLoc, String subLocalidad);
	/**
	 * Obtiene la caracteristica basda en el id de la peticion
	 */
	public Subpeticion_caracteristicasLocal obtenerCaracteristica (Long idPeticion,Long codigoCaracteristica) throws ATiempoAppEx, NamingException;
}
