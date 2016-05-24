package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb;

import java.util.ArrayList;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR610S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;
/**
 * Local interface for Enterprise Bean: RecursosBA
 */
public interface RecursosBALocal extends RecursosBAInterfaces,
											javax.ejb.EJBLocalObject{
	public void grabaModemsBaVpi(
		Long nroPeticion,
		Long telAsignado,
		ArrayList modems);
	public boolean noHayModemParaActualizarInventarioBa(Long nroPeticion)
		throws ATiempoAppEx;
	public ArrayList recuperaModemsDePet(Long nroPeticion) throws ATiempoAppEx;
	public ArrayList recuperaModemsBaVPi(Long nroPeticion);
	public ArrayList recuperaModemsAnyWay(Long nroPeticion);
	
	/*RQ 5606 - Internet Movil*/
	public void enviarConfiguracionInternetMovil(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx;
	
	public void procesarRespuestaInternetMovil(TR610S tr610s) throws ATiempoAppEx;
	/*FIN - RQ 5606 - Internet Movil*/
	/*RQ 6142 - WS Aula*/
	public String validaOperacionComercialSVA(ActividadEJBDTO act, Long nroPeticion) throws ATiempoAppEx;
	public PsVsOcVO devuelvePSPrioridadAltaSVA(ActividadEJBDTO act) throws ATiempoAppEx;
	/*FIN - RQ 6142 - WS Aula*/

	public void ejecutarVelocidadAdicionalTMP(ActividadEJBDTO act)throws ATiempoAppEx;
}
