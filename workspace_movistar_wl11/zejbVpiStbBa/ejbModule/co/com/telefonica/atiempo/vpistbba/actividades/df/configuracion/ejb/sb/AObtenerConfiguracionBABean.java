package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: AObtenerConfiguracionBA
 */
public class AObtenerConfiguracionBABean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Obtener Configuracion BA [" + act.getNumeroPeticion() + "]");

		try
		{
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
				//veo si es alta traslado
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				boolean esTrasladoAlta=false;
				Traslado_solobaLocal traslado_solobaLocal=null;
				try{
					traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(act.getNumeroPeticion());
					esTrasladoAlta=true;
				} catch (FinderException e) {
					log.warn("No es traslado alta BA");
				}
				if(esTrasladoAlta){
					//si es traslado alta, busco la configuracion adsl de donde esta instalada la linea
					//Sigres Refrescar Datos - agonz
					recursosBADelegate.enviarConfiguracionActualBA(traslado_solobaLocal.getLinea_anterior(),String.valueOf(act.getNumeroPeticion()),act.getCodigoActividad());
					//recursosBADelegate.enviarConfiguracionActualBASigres(traslado_solobaLocal.getLinea_anterior(),String.valueOf(act.getNumeroPeticion()),act.getCodigoActividad());
				}else{
//					Si no, busco el adsl del num_ide_nu de la peticion
					//Sigres Refrescar Datos - agonz
					recursosBADelegate.enviarConfiguracionActualBA(String.valueOf(act.getNumeroPeticion()),act.getCodigoActividad());
					//recursosBADelegate.enviarConfiguracionActualBASigres(String.valueOf(act.getNumeroPeticion()),act.getCodigoActividad());
				}
			}else{
				act.setObservacion("En reversa no es necesario realizar la consulta.");
				act.setRealizarTerminoInmediato(true);	
			}
		} catch (ATiempoAppEx e) {
			log.debug("Error en Obtener Configuracion BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Configuracion BA Bean");
		} 
		catch (NamingException e) {
			log.debug("Error en Obtener Configuracion BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Configuracion BA Bean");
		}
		log.debug("Fin Actividad Obtener Configuracion BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		log.debug("cod:" + act.getCodigoActividad());
		if(VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CONF_BA").equals(act.getCodigoActividad())){ //Si solo Obt config BA normal, no para CDS
			if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba))
			{//La seteo solo en los flujos que conocen esa variable
				//Para que no haga el obtener configuracion BA Terra
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba,"S");
			}
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
