package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AConfirmacionPagoRecibo
 */
public class AConfirmacionPagoReciboBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Entro a AConfirmacionPagoReciboBean para la petición: "+act.getNumeroPeticion());
		try{		
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Se inhibe la actividad de Confirmación de pagos por ir en reversa, para la petición: "+ act.getNumeroPeticion());
				act.setObservacion("Se inhibe la actividad de Confirmación de pagos por ir en reversa", true);
				act.setRealizarTerminoInmediato(true);
			}else{
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
				PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
				if(peticionLocal.getPago_tv_sola_ok()!=null &&peticionLocal.getPago_tv_sola_ok().equalsIgnoreCase("true")){
					act.setObservacion("Se inhibe la actividad de Confirmación de pagos por ir en reversa", true);
					act.setRealizarTerminoInmediato(true);
				}else if(peticionLocal.getPago_tv_sola_ok()!=null &&peticionLocal.getPago_tv_sola_ok().equalsIgnoreCase("false")){
					act.setObservacion("Se envia a PGF porque si este valor no es nulo o false se entre´go el equipo sin recibir pago", true);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,VpistbbaConfig.getVariable("IDPGF"));
	                act.setRealizarTerminoInmediato(true);
				}
				log.debug("Estoy en AConfirmacionPagoReciboBean para la petición: "+act.getNumeroPeticion()+", quedo en espera de un mensaje de confirmación de pago");
			}
		}catch(Exception e){
			log.error("Error en la actividad ",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	
	
}