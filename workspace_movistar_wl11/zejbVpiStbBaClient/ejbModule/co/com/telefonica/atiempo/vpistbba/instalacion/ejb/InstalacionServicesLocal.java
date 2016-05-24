package co.com.telefonica.atiempo.vpistbba.instalacion.ejb;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import co.com.atiempo.dto.Elemento_PeticionVpiDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;

import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
/**
 * Local interface for Enterprise Bean: InstalacionServices
 */
public interface InstalacionServicesLocal
	extends
		co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface,
		javax.ejb.EJBLocalObject {
	public ArrayList listaRangosAgendamiento();
	public ArrayList listaCausasReagendamiento();
	// CR21938 - ana santos - inicio
	public ArrayList getListaAsignaciones(Long nroPeticion, UsuarioWeb usuario);	
	public InformacionAgendamientoDTO grabarAsignacion(
		String nroPeticion,
		String codTecnico,
		String fComp,
		String nroRango,
		String nroCare,
		UsuarioWeb usuario);
	public void actualizarTecnico(String idTecnico, Long nroPeticion, UsuarioWeb usuario)
		throws ATiempoAppEx;
	// CR21938 - ana santos - fin	
	public String tecnicoAsignadoPeticion(Long nroPeticion)
		throws ATiempoAppEx;

	//Gustavo - CR 25403
	public InformacionControlVisitaDTO asignaTecnicoVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora);
	
	public ArrayList getListaVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora);
	
	public InformacionControlVisitaDTO asignaTecnicoVisita(Long idPeticion, HttpServletRequest request);
	//Gustavo - CR 25403 - Fin

	public Elemento_PeticionVpiDTO grabarInternetEquipado(String nroPeticion, String tipoEquipo, String serialEquipo, UsuarioWeb usuario, String ps_id); 
}
