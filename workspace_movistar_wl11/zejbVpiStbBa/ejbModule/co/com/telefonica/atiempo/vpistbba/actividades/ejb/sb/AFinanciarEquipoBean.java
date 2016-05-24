package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: AFinanciarEquipo
 */
public class AFinanciarEquipoBean extends ActividadAutomaticaEJB implements javax.ejb.SessionBean {


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setRealizarTerminoInmediato(true);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
			log.debug("Inicio Actividad Reversa de Configurar Internet [" + act.getNumeroPeticion() + "]");
			 try{
			     PeticionesInterfaces peticoInterface = new PeticionesDelegate();
			     peticoInterface.eliminarEqInternetEquipado( act.getNumeroPeticion() );
			     act.setObservacion("Se envia el mensaje de Reversa Instalación Internet Equipado.");
			 }catch(Exception e){
			     e.printStackTrace();
			     log.error("Error en la reversión de financiar equipo ",e);
			 }
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	    if (act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC")) ||
	            act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGI"))){
	        act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
		}
	}
}
