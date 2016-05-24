package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AConfiguracionMovistarPlay
 */
public class AConfiguracionMovistarPlayBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		try
		{
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			{
				act.setObservacion("No es necesario reversar consulta. Se Termina la Actividad");
				act.setRealizarTerminoInmediato(true);
			}
			else
			{
				RecursosTVDelegate delegate=new RecursosTVDelegate();
				delegate.enviaConfiguracionMovistarPlay(act.getNumeroPeticion().longValue(),act);	
			}
		}
		catch (ATiempoAppEx e)
		{
			log.warn("Error en Actividad Configuraciòn Movistar Play Bean",e);
			throw new TnProcesoExcepcion("Error en Actividad Configuraciòn Movistar Play Bean", e);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
	
}