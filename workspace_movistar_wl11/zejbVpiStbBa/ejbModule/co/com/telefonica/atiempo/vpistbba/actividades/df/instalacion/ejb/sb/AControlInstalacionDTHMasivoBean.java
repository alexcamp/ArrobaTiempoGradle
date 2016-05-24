package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

//CR24918 - TV Masivo - PCawen
/**
 * Bean implementation class for Enterprise Bean: AControlInstalacionDTHMasivo
 */
public class AControlInstalacionDTHMasivoBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		Long nroPeticion = act.getNumeroPeticion();
		log.debug("onInicioActividad AControlInstalacionDTHMasivoBean " + nroPeticion);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		Long nroPeticion = act.getNumeroPeticion();
		log.debug("onTerminoActividad AControlInstalacionDTHMasivoBean " + nroPeticion);
	}
}
