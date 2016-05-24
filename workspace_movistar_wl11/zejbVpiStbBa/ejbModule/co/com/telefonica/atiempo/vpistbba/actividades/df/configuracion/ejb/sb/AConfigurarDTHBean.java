package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;


import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarDTH
 */
public class AConfigurarDTHBean
	extends ActividadAutomaticaEJB{

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion
	{
		
		log.debug("Inicio Actividad Configurar DTH [" + act.getNumeroPeticion() + "]"); 
		try
		{
			RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
			PeticionesDelegate peticionesDelegate=new PeticionesDelegate();
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			{
				log.debug("Flujo Positivo [" + act.getNumeroPeticion() + "]");
				boolean tienePcEnAlta=recursosTVDelegate.tienePcEnAlta(act.getNumeroPeticion());
				Long retorno=null;
				if(!tienePcEnAlta)
				{
					retorno=recursosTVDelegate.enviaConfiguracionServiciosTVAuto(act.getNumeroPeticion().longValue(),act.getCodigoActividad(),act.getIdActividadFlujo());
					if(retorno==null)
					{
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
						act.setObservacion("Peticion de Reseteo Control Parental. No fue posible recuperar y parear el Deco informado en las caracteristicas");
						act.setRealizarTerminoInmediato(true);
						Long causalAuto=new Long(VpistbbaConfig.getVariable("COD_CAU_ERR_AUTO"));
						peticionesDelegate.insertarCausalesPeticion(act.getNumeroPeticion(),act.getCodigoActividad(),causalAuto,act.getIdActividadFlujo());
					}
					else
						peticionesDelegate.setearFechaBajaPs(act.getNumeroPeticion());
				}
				else
				{
					act.setObservacion("Tengo Ps PC Tv en Alta . Se inhibe la Actividad");
					act.setRealizarTerminoInmediato(true);
				}
			}
			else
			{
				log.debug("Flujo Reversa [" + act.getNumeroPeticion() + "]");
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("No es necesario desconfigurar. Ya se han desactivado todos los servicios");
				//recursosTVDelegate.enviaConfiguracionServiciosTVDesactivacion(this.getNumeroPeticion().longValue(),this.getCodigoActividad(),this.getIdActividadFlujo());
			}
		} catch (ATiempoAppEx e)
		{
			e.printStackTrace();
			log.warn("Error en Actividad Configurar DTH",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar DTH", e);
		}

		log.debug("Fin Actividad Configurar DTH [" + act.getNumeroPeticion() + "]");

	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}


}
