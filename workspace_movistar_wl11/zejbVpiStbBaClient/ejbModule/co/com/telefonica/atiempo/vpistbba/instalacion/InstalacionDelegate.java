/*
 * Created on Feb 19, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.instalacion;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import co.com.atiempo.dto.Elemento_PeticionVpiDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InstalacionDTO;
import co.com.telefonica.atiempo.vpistbba.instalacion.ejb.InstalacionServicesLocal;
import co.com.telefonica.atiempo.vpistbba.instalacion.ejb.InstalacionServicesLocalHome;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;

/**
 * @author admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InstalacionDelegate
	implements InstalacionServicesInterface {
	
	private InstalacionServicesLocal ejbInstalacionServices;
	
	public InstalacionDelegate() throws ATiempoAppEx {
		try{
			ejbInstalacionServices = ((InstalacionServicesLocalHome) HomeFactory
			.getHome(InstalacionServicesLocalHome.JNDI_NAME))
			.create();
		}
		catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		}
		catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	
	public InstalacionDTO obtenerInfoInstalacion() throws ATiempoAppEx{
		return ejbInstalacionServices.obtenerInfoInstalacion();
	}
	
	public void instalar() throws ATiempoAppEx{
		ejbInstalacionServices.instalar();
	}
	public void controlarInstalacion() throws ATiempoAppEx{
		ejbInstalacionServices.controlarInstalacion();
	}
	public void desinstalar() throws ATiempoAppEx{
		ejbInstalacionServices.desinstalar();
	}
	public void controlarDesinstalacion() throws ATiempoAppEx{
		ejbInstalacionServices.controlarDesinstalacion();
	}

	public ArrayList listaRangosAgendamiento()
	{
		return ejbInstalacionServices.listaRangosAgendamiento();
	}

	public ArrayList listaCausasReagendamiento()
	{
		return ejbInstalacionServices.listaCausasReagendamiento();
	}
	
	// CR21938 - ana santos - inicio
	public ArrayList getListaAsignaciones(Long nroPeticion, UsuarioWeb usuario)
	{
		return ejbInstalacionServices.getListaAsignaciones(nroPeticion, usuario);
	}
	
	public InformacionAgendamientoDTO grabarAsignacion(String nroPeticion, String codTecnico, String fComp, String nroRango, String nroCare, UsuarioWeb usuario)
	{
		return ejbInstalacionServices.grabarAsignacion(nroPeticion,codTecnico,fComp,nroRango,nroCare,usuario);
	}
	
	public void actualizarTecnico(String idTecnico, Long nroPeticion, UsuarioWeb usuario) throws ATiempoAppEx
	{
		ejbInstalacionServices.actualizarTecnico(idTecnico,nroPeticion, usuario);
	}
	// CR21938 - ana santos - fin
	public String tecnicoAsignadoPeticion(Long nroPeticion) throws ATiempoAppEx
	{
		return ejbInstalacionServices.tecnicoAsignadoPeticion(nroPeticion);
	}

	public InformacionAgendamientoDTO recuperaAsignacion(Long nroPeticion)
	{
		return ejbInstalacionServices.recuperaAsignacion(nroPeticion);
	}

	public void actualizaAgenda(String fComp, String nroRango, String nroCare, Long peticion) throws ATiempoAppEx
	{
		ejbInstalacionServices.actualizaAgenda(fComp,nroRango,nroCare,peticion);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#grabarControlVisita(java.lang.Long, com.telefonica_chile.atiempo.utiles.Fecha, com.telefonica_chile.atiempo.utiles.Fecha)
	 */
	public void grabarControlVisita(Long petiNumero, Fecha fechaHoraVisitaDesde, Fecha fechaHoraVisitaHasta) throws ATiempoAppEx
	{
		ejbInstalacionServices.grabarControlVisita(petiNumero,fechaHoraVisitaDesde,fechaHoraVisitaHasta);			
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#grabarControlVisita(java.lang.Long, com.telefonica_chile.atiempo.utiles.Fecha, com.telefonica_chile.atiempo.utiles.Fecha)
	 */

	//Gustavo - CR 25403
	public void grabarControlVisita(ArrayList listaVisitas) throws ATiempoAppEx
	{
		ejbInstalacionServices.grabarControlVisita(listaVisitas);			
	}

	public InformacionControlVisitaDTO asignaTecnicoVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora) {
		// TODO Auto-generated method stub
		return ejbInstalacionServices.asignaTecnicoVisita(nroPeticion, codTecnico, fLlegada, fSalida, codPsPeticion, codCausaDemora);
	}

	public ArrayList getListaVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora) {
		return ejbInstalacionServices.getListaVisita(nroPeticion, codTecnico, fLlegada, fSalida, codPsPeticion, codCausaDemora);
	}

	/**
	 * @param idPeticion
	 * @return
	 */
	public InformacionControlVisitaDTO asignaTecnicoVisita(Long idPeticion, HttpServletRequest request) {
		// TODO Apéndice de método generado automáticamente
		return ejbInstalacionServices.asignaTecnicoVisita(idPeticion,request);
	}
	//Gustavo - CR 25403 - Fin
	
	public Elemento_PeticionVpiDTO grabarInternetEquipado(String nroPeticion, String tipoEquipo, String serialEquipo, UsuarioWeb usuario, String ps_id){
		return ejbInstalacionServices.grabarInternetEquipado(nroPeticion,tipoEquipo,serialEquipo,usuario, ps_id);
	}
}
