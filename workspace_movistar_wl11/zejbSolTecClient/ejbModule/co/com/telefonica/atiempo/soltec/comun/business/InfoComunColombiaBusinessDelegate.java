package co.com.telefonica.atiempo.soltec.comun.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.soltec.comun.ejb.InfoComunColombiaSTLocal;
import co.com.telefonica.atiempo.soltec.comun.ejb.InfoComunColombiaSTLocalHome;
import co.com.telefonica.atiempo.soltec.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.RecursosBADTO;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InfoComunColombiaBusinessDelegate
	implements InfoComunColombiaBusinessInterface {

	private InfoComunColombiaSTLocal ejbInfoComunColombia;
	
	public InfoComunColombiaBusinessDelegate() throws ATiempoAppEx {
		try {
			ejbInfoComunColombia =
				((InfoComunColombiaSTLocalHome) HomeFactory.getHome(
					InfoComunColombiaSTLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}

	}
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario)
	{
		return ejbInfoComunColombia.obtenerInformacionEmpresa(idUsuario);
	}

	//TCS-gquevedo Jun 18, 2009 CR.25666 INICIO
	public ArrayList obtenerLocalizacionesPorActividad(Long idActividad) throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerLocalizacionesPorActividad(idActividad);
	}
	//TCS-gquevedo Jun 18, 2009 CR.25666 FIN
	
	public ArrayList obtenerLocalizaciones(Long fapsId) throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerLocalizaciones(fapsId);
	}
	
	public ArrayList obtenerCierresAverias(Integer codLocal) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.obtenerCierresAverias(codLocal);
	}
	
	public Collection obtenerDecoTarjetas(Long idPeticion) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.obtenerDecoTarjetas(idPeticion);
	}
	
	public Collection obtenerUsuariosCompatibles(Long idPeticion,Long idUser, String codAct, Long idAplicacion) {
		return ejbInfoComunColombia.obtenerUsuariosCompatibles(idPeticion,idUser,codAct,idAplicacion);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#obtenerRecursosBA(java.lang.Long)
	 */
	public RecursosBADTO obtenerRecursosBA(Long codAveOri) throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerRecursosBA(codAveOri);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getDatosPeticionMasivo(java.util.ArrayList)
	 */
	public ArrayList getDatosPeticionMasivo(ArrayList arrCodigoAve) throws ATiempoAppEx {
		return ejbInfoComunColombia.getDatosPeticionMasivo(arrCodigoAve);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#obtenerDatosTecnicosLB(java.lang.Long)
	 */
	public InformacionTecnicaDTO obtenerDatosTecnicosLB(Long codAveOri) throws ATiempoAppEx {
		return ejbInfoComunColombia.obtenerDatosTecnicosLB(codAveOri);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getDatosPeticion(java.lang.Long)
	 */
	public Peticion_stDTO getDatosPeticion(Long codigoAve) throws ATiempoAppEx {
		return ejbInfoComunColombia.getDatosPeticion(codigoAve);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getUtlimaPruebaLineaByPeticion(java.lang.Long)
	 */
	public PruebaLineaDTO getUtlimaPruebaLineaByPeticion(Long idPeticion) {
		return ejbInfoComunColombia.getUtlimaPruebaLineaByPeticion(idPeticion);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#obtenerTematico(java.lang.Long)
	 */
	public Collection obtenerTematico(Long codAveOri) {
		return ejbInfoComunColombia.obtenerTematico(codAveOri);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getCodPruebaLineaByFamilia(java.lang.Long)
	 */
	public ArrayList getCodPruebaLineaByFamilia(Long familia) {
		return ejbInfoComunColombia.getCodPruebaLineaByFamilia(familia);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getPruebasLineaByPeticion(java.lang.Long)
	 */
	public ArrayList getPruebasLineaByPeticion(Long idPeticion) {
		return ejbInfoComunColombia.getPruebasLineaByPeticion(idPeticion);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#obtenerInformacionAgendamiento(java.lang.Long)
	 */
	public InformacionAgendamientoDTO obtenerInformacionAgendamiento(Long codAveOri) {
		return ejbInfoComunColombia.obtenerInformacionAgendamiento(codAveOri);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getHistorico(java.lang.Long)
	 */
	public ArrayList getHistorico(Long codigoAve) {
		return ejbInfoComunColombia.getHistorico(codigoAve);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getTecnicos(java.lang.Long)
	 */
	public ArrayList getTecnicos(Long idUsuario) {
		return ejbInfoComunColombia.getTecnicos(idUsuario);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#getCodigoDiagnostico()
	 */
	public ArrayList getCodigoDiagnostico() {
		return ejbInfoComunColombia.getCodigoDiagnostico();
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface#recuperaControlesVisita(java.lang.Long)
	 */
	public ArrayList recuperaControlesVisita(Long codAveCd) throws ATiempoAppEx
	{
		return ejbInfoComunColombia.recuperaControlesVisita(codAveCd);
	}
	
	public ArrayList recuperarListaAgendaSC(Long codAve){
		return ejbInfoComunColombia.recuperarListaAgendaSC(codAve);
	}
	
}
