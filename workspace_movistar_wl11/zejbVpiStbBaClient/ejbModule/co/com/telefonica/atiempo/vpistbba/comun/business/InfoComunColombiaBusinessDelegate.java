package co.com.telefonica.atiempo.vpistbba.comun.business;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.ejb.InfoComunColombiaLocal;
import co.com.telefonica.atiempo.vpistbba.comun.ejb.InfoComunColombiaLocalHome;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import com.telefonica_chile.atiempo.utiles.HomeFactory;


public class InfoComunColombiaBusinessDelegate
	implements InfoComunColombiaBusinessInterface {

	private InfoComunColombiaLocal ejbInfoComunColombia;
	
	public InfoComunColombiaBusinessDelegate() throws ATiempoAppEx {
		try {
			ejbInfoComunColombia =
				((InfoComunColombiaLocalHome) HomeFactory.getHome(
					InfoComunColombiaLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}

	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerInformacionPeticionDTO()
	 */
	public InformacionAdslDTO obtenerInformacionAdsl(Long nroPeticion) throws ATiempoAppEx {
		
		return ejbInfoComunColombia.obtenerInformacionAdsl(nroPeticion);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerInformacionAdsl()
	 */
	public InformacionPeticionDTO obtenerInformacionPeticion(Long nroPeticionVpi)
		throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerInformacionPeticion(nroPeticionVpi);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerInformacionPeticion()
	 */
	public InformacionTecnicaDTO obtenerInformacionTecnica(Long nroPeticion) throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerInformacionTecnica(nroPeticion);
	}

	public void guardarConfiguracionSTB(InformacionPeticionDTO infoPet, InformacionTecnicaDTO infoTec, InformacionAdslDTO infoAdsl) throws ATiempoAppEx
	{
		
		
	}

	public ArrayList getProductoServicio(Long nroPeticion) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.getProductoServicio(nroPeticion);
	}

	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		return ejbInfoComunColombia.obtenerInformacionEmpresa(idUsuario);
	}

	public ArrayList obtenerServiciosSuplementarios(Long idPeticion) throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerServiciosSuplementarios( idPeticion );
	}

	public ArrayList getPsYTipoPcNoRealizado(Long idPeticion) {
		return ejbInfoComunColombia.getPsYTipoPcNoRealizado(idPeticion);
	}

	public InformacionAdslDTO obtenerInformacionAdslActual(Long nroPeticion) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.obtenerInformacionAdslActual(nroPeticion);
	}

//	public String obtenerCuentaCorreoPadre(Long idPeticion) throws ATiempoAppEx
//	{
//		return ejbInfoComunColombia.obtenerCuentaCorreoPadre(idPeticion);
//	}

	public ArrayList getProductoServicioMasQuiebres(Long nroPeticion) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.getProductoServicioMasQuiebres(nroPeticion);
	}

	public InformacionPeticionDTO obtenerSimpleInfoPeticion(Long nroPeticionVpi) throws ATiempoAppEx
	{		
		return ejbInfoComunColombia.obtenerSimpleInfoPeticion(nroPeticionVpi);
	}

	public ArrayList recuperaControlesVisita(Long petiNumero) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.recuperaControlesVisita(petiNumero);
	}

	public ArrayList getPSQuiebresYChulo(Long nroPeticion, Long codigoActividad) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.getPSQuiebresYChulo(nroPeticion,codigoActividad);
	}

	public Long obtenerCodigoAPSC(Long codLoc, String subLocalidad){
		return ejbInfoComunColombia.obtenerCodigoAPSC(codLoc, subLocalidad);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#obtenerCaracteristica(java.lang.Long, java.lang.Long)
	 */
	public Subpeticion_caracteristicasLocal obtenerCaracteristica(Long idPeticion, Long codigoCaracteristica) throws ATiempoAppEx, NamingException {
		return ejbInfoComunColombia.obtenerCaracteristica(idPeticion,codigoCaracteristica);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface#recuperaCaracteristicasVisualizacion(java.lang.Long)
	 */
	public ArrayList recuperaCaracteristicasVisualizacion(Long petiNumero) throws Exception {
		
		return ejbInfoComunColombia.recuperaCaracteristicasVisualizacion(petiNumero);
	}


}