package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

import co.com.telefonica.atiempo.interfaces.atiempo.TR601E;
import co.com.telefonica.atiempo.sigres.emu.tr.NoMessageDefFoundException;

/**
 * TRMessageProcessFactory
 * Factory de procesos emulados para las distintas interfaces (TR).
 * 
 * @author Gonzalo Arreche
 *
 */
public class ServicioAltamiraFactory {
	/**
	 * Devuelve el proceso de aceurdo al valor de algun campo de la interfaz (seguramente el id de la op comercial)
	 * que recibe como parametro.
	 * DEFINIR CADA OP COMO UNA CONSTANTE
	 * @param type es el tipo de Intefaz(TR)
	 * @return el proceso emulado que debe ejecutarse.
	 */
		
	public static ServicioAltamira getServicio(TR601E entrada)
		throws NoMessageDefFoundException {
		
		ServicioAltamira servicio = null; 
		
		String serviceName = entrada.getAtiempoServiceName();
		if (serviceName.equals("ALTA_ABONADO")) {
			servicio = new AltaAbonadoRI();
		}else if (serviceName.equals("ALTA_PERIODIFICACION")) {
			servicio = new AltaPeriodificacionRI();
		}else if (serviceName.equals("BAJA_ABONADO")) {
			servicio = new BajaAbonadoRI();
		}else if (serviceName.equals("BAJA_PERIODIFICACION")) {
			servicio = new BajaPeriodificacionRI();
		}else if (serviceName.equals("CAMBIO_NUMERO")) {
			servicio = new CambioNumeroRI();
		}else if (serviceName.equals("CAMBIO_TIPO_PREPAGO")) {
			servicio = new CambioTipoPrepagoRI();
		}else if (serviceName.equals("ALTA_CLAVE_SECRETA")) {
			servicio = new AltaClaveSecreta();
		}else if (serviceName.equals("DESACT_CLAVE_SECRETA")) {
			servicio = new DesactClaveSecreta();
		}else if (serviceName.equals("CAMBIO_PREPAGO")) {
			servicio = new CambioPrepagoRI();
		}

		return servicio;
	}
}
