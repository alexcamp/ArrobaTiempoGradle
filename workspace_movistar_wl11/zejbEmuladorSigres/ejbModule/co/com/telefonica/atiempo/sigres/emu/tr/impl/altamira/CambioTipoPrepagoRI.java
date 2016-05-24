/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CambioTipoPrepagoRI extends ServicioAltamira {

	private static final String NOMBRE_SERVICIO = "CAMBIO_TIPO_PREPAGO";
		
	public CambioTipoPrepagoRI(){
		this.setNombreServicio(NOMBRE_SERVICIO);
	}

	public String getEstadoRespuestaSincrona() {
		return ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.resp_sincrona_cambio_tipo_prep.status");
	}
	
	public String getEstadoRespuestaAsincrona() {
		return ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.resp_asincrona_cambio_tipo_prep.status");
	}	
	
	public boolean generarRespuestaAsincrona() {
		return (ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.generar_resp_asincrona_cambio_tipo_prep.status").equals("true"));
	}
	
}
