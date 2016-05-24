// CR 27638
package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 810884
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AltaClaveSecreta extends ServicioAltamira {

	private static final String NOMBRE_SERVICIO = "ALTA_CLAVE_SECRETA";
		
	public AltaClaveSecreta(){
		this.setNombreServicio(NOMBRE_SERVICIO);
	}

	public String getEstadoRespuestaSincrona() {
		return ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.resp_sincrona_alta_clave_secreta.status");
	}
	
	public String getEstadoRespuestaAsincrona() {
		return ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.resp_asincrona_alta_clave_secreta.status");
	}	
	
	public boolean generarRespuestaAsincrona() {
		return (ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.generar_resp_asincrona_alta_clave_secreta.status").equals("true"));
	}
	
}
