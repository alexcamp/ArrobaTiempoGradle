package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AObtenerConfiguracionDTH
 */
public class AObtenerConfiguracionDTHBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion
	{
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
				delegate.enviaSolicitudInformacionTecnicaTV(act.getNumeroPeticion().longValue(),act.getCodigoActividad());	
			}
		}
		catch (ATiempoAppEx e)
		{
			log.warn("Error en Actividad Obtener Configuracion DTH Bean",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Configuracion DTH Bean", e);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
