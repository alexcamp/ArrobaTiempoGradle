package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;

/**
 * Local interface for Enterprise Bean: RecursosServicios
 */
public interface RecursosServiciosLocal
	extends
		RecursosInterfaces,
		javax.ejb.EJBLocalObject {

	/**
	 * @param act
	 * @param numeroPeticion
	 * @param codigoActividad
	 */
	void configurarPresenciaDigital(ActividadEJBDTO act, Long numeroPeticion, String codigoActividad);

	/**
	 * @param tr054s
	 */
	void procesarRespuestaConfiguracionPresenciaDigital(TR054S tr054s);
}
