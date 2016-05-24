/*
 * Created on Feb 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CambioPrepagoRI extends ServicioAltamira {

	private static final String NOMBRE_SERVICIO = "CAMBIO_PREPAGO";
	
	public CambioPrepagoRI(){
		this.setNombreServicio(NOMBRE_SERVICIO);
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira.ServicioAltamira#getEstadoRespuestaSincrona()
	 */
	protected String getEstadoRespuestaSincrona() {
		return ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.resp_cambio_prepago.status");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira.ServicioAltamira#getEstadoRespuestaAsincrona()
	 */
	protected String getEstadoRespuestaAsincrona() {
		return ServiceLocatorEmulator.getTRProperties().getProperty("tr_601_s.resp_asincrona_cambio_prepago.status");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira.ServicioAltamira#generarRespuestaAsincrona()
	 */
	protected boolean generarRespuestaAsincrona() {
		// TODO Auto-generated method stub
		return false;
	}

}
