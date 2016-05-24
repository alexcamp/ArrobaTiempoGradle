package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
import java.util.ArrayList;

import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;

import co.com.telefonica.atiempo.soltec.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
/**
 * Local interface for Enterprise Bean: InstalacionServiceST
 */
public interface InstalacionServiceSTLocal extends javax.ejb.EJBLocalObject {
	// CR21938 - ana santos - inicio
	public InformacionAgendamientoDTO grabarAsignacion(
		String nroPeticion,
		String codTecnico,
		String fComp,
		String nroRango,
		String nroCare, UsuarioWeb usuario)
		throws java.text.ParseException;
	// CR21938 - ana santos - fin
	public ArrayList getListaAsignaciones(Long nroPeticion);
	public ArrayList listaCausasReagendamiento();
	public ArrayList listaRangosAgendamiento();
	public InformacionAgendamientoDTO recuperaAsignacion(Long nroPeticion);
	public void actualizarTecnico(String idTecnico, Long nroPeticion, UsuarioWeb usuario)
		throws ATiempoAppEx;
	public String tecnicoAsignadoPeticion(Long nroPeticion)
		throws ATiempoAppEx;
}
