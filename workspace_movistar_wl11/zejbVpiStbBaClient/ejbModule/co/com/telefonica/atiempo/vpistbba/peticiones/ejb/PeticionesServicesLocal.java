package co.com.telefonica.atiempo.vpistbba.peticiones.ejb;

import java.util.ArrayList;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705S;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
/**
 * Local interface for Enterprise Bean: PeticionesServices
 */
public interface PeticionesServicesLocal extends PeticionesInterfaces, javax.ejb.EJBLocalObject {
	
	// CR18865 - ana santos	
	boolean esBaja(Long idPeticion);
	boolean esAlta(Long idPeticion);

//	CR-22569 agonz 25 feb 2009
	boolean tienePS(ActividadEJBDTO act, int familia);

//	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario);
//	public ArrayList listaPsDePeticionQuePasaPorActividad(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx;
//	public ArrayList listaDePsDelaActividadEstadoFinal(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx;
//	public ArrayList listaDePsDelaActividadEstadoOKFinal(Long nroPeticion,Integer actividad_flujo_id) throws ATiempoAppEx;
//	public ArrayList listaDePsDePeticion(Long nroPeticion) throws ATiempoAppEx;
//	public void propagaQuiebrePCaPS(Long petiNumero, Long idPs, Long idOC) throws ATiempoAppEx;
//	public String obtenerCuentaCorreoPadre(Long idPeticion) throws ATiempoAppEx;
	
//Gustavo - CR 25403
//	public ArrayList recuperarCausaDemora() throws ATiempoAppEx;
//	public ArrayList recuperaPsVisita(Long idPeticion) throws ATiempoAppEx;
	public ArrayList recuperarCausaDemora();
	public ArrayList recuperaPsVisita(Long idPeticion);	
//Gustavo - CR 25403 - Fin
	
	String obtenerTipoOpCoPorPs(Long idPeticion);
	/**
	 * @param petiNumero
	 * @return
	 */
	boolean esActuacionCCF(Long petiNumero);
	/**
	 * @param correlativoID
	 * @return
	 */
	TR705S obtenerMensajeReagendamientoASC(Long correlativoID);
}
