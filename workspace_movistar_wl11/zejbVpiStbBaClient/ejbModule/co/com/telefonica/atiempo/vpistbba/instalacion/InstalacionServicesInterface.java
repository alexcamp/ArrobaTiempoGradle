/*
 * Created on 16-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.instalacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InstalacionDTO;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
/**
 * @author admin
 */
public interface InstalacionServicesInterface {
	public InstalacionDTO obtenerInfoInstalacion() throws ATiempoAppEx;
	public void instalar() throws ATiempoAppEx;
	public void controlarInstalacion() throws ATiempoAppEx;
	public void desinstalar() throws ATiempoAppEx;
	public void controlarDesinstalacion() throws ATiempoAppEx;
	public ArrayList listaRangosAgendamiento();
	public ArrayList listaCausasReagendamiento();

	// CR21938 - ana santos - inicio
	public ArrayList getListaAsignaciones(Long nroPeticion, UsuarioWeb usuario);
	public InformacionAgendamientoDTO grabarAsignacion(String nroPeticion,String codTecnico,String fComp,String nroRango,String nroCare,UsuarioWeb usuario);
	public void actualizarTecnico(String idTecnico,Long nroPeticion, UsuarioWeb usuario) throws ATiempoAppEx;
	// CR21938 - ana santos - fin
	public String tecnicoAsignadoPeticion(Long nroPeticion) throws ATiempoAppEx;
	public InformacionAgendamientoDTO recuperaAsignacion(Long nroPeticion);
	public void actualizaAgenda(String fComp, String nroRango, String nroCare, Long peticion) throws ATiempoAppEx;
	public void grabarControlVisita(Long petiNumero, Fecha fechaHoraVisitaDesde, Fecha fechaHoraVisitaHasta) throws ATiempoAppEx;

	//Gustavo - CR 25403
	public void grabarControlVisita(ArrayList listaVisitas) throws ATiempoAppEx;
	public InformacionControlVisitaDTO asignaTecnicoVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora);
	public ArrayList getListaVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora);
	public InformacionControlVisitaDTO asignaTecnicoVisita(Long idPeticion, HttpServletRequest request);
	//Gustavo - CR 25403 - Fin
}
