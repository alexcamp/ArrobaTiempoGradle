package co.com.telefonica.atiempo.vpistbba.recursos.ejb;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR055S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
/**
 * Local interface for Enterprise Bean: RecursosServicios
 */
public interface RecursosServiciosLocal
	extends
		co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces,
		javax.ejb.EJBLocalObject {
	public InformacionTecnicaDTO obtenerRecursosTecnicos(Long idPeticion) throws ATiempoAppEx;

	public void consultaRecursoSTB_BA(Long peticion, String idActividad,String codActividad)
		throws ATiempoAppEx;

	/**
	 * @param tr055s
	 */
	public void procesarRespuestaConfiguracionPdVA(TR055S tr055s);

	/**
	 * Metodo para enviar la congfiguracion de Puesto de voz avanzado(PdVA)
	 * @param numeroPeticion
	 * @return
	 */
	public void configurarPdVa(ActividadEJBDTO act, Long numeroPeticion, Integer idActividadFlujo, String codigoActividad);
}
