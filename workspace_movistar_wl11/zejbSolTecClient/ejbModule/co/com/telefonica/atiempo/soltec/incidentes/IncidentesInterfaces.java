/*
 * Created on Feb 21, 2007
 */
package co.com.telefonica.atiempo.soltec.incidentes;

import java.util.Collection;

import co.com.telefonica.atiempo.interfaces.atiempo.TR005S;
import co.com.telefonica.atiempo.soltec.dto.CierrePeticionDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * fbd 20-03-2007: cierre de la peticion
 *
 * @author TCS
 */
public interface IncidentesInterfaces extends ComunInterfaces {

//	public TR005S obtenerIncidenteATIS(Long numeroIncidenteAtis) throws ATiempoAppEx;

	public Peticion_stLocal salvarIncidenteATIS(TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx;

	public boolean existeIncidenteATIS(Long numeroIncidenteAtis) throws ATiempoAppEx;

	public void cancelarIncidente (Long nroIncidenteST) throws ATiempoAppEx ;

	public int reiterarIncidente (TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx ;

    public void cerrarIncidente (Long nroIncidenteST) throws ATiempoAppEx ;
    
	public CierrePeticionDTO agregarSolucion(Long numeroPeticion,String codigoActividad, Long idUser, String codCierre, String fecha) throws ATiempoAppEx;
	
	public Collection historicoSoluciones(Long numeroPeticion) throws ATiempoAppEx;
       
	public Long terminarIncidente (TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx;  
	
	public Peticion_stDTO getDatosPeticion (Long codigoAve);
	
	public void grabarControlVisita(Long codAveCd, Fecha fechaHoraVisitaDesde, Fecha fechaHoraVisitaHasta) throws ATiempoAppEx;
	
	public void ActividadManualMarcaCierreIncidente(Long numeroIncidente) throws ATiempoAppEx;  
    
    //	 CR17031 - adocarmo - inicio
	public boolean modificarCategoria(TR005S incidenteAtis) throws ATiempoAppEx;
//	 CR17031 - adocarmo - fin
    
	 public String recuperarParametroFromPropertiesBD(String codigo)throws ATiempoAppEx;
}
