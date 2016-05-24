package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarPdVA
 */
public class AConfigurarPdVABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB	
								implements	javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)|
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadVPI de AConfigurarPdBABean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
		try {
			
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				RecursosDelegate recursosDelegate=new RecursosDelegate();
				Calendar calendar = Calendar.getInstance();
				Date date = calendar.getTime();
				Timestamp currentTimestamp = new Timestamp(date.getTime());
 				recursosDelegate.configurarPdVa(act, act.getNumeroPeticion(),act.getIdActividadFlujo(),act.getCodigoActividad());
			} else{
		
			}
						
		} catch (Exception e) {
			log.error("AVistaGlobalHCBean: onInicioActividadVPI: Error intentando enviar la solicitud de la Configuracion de Presencia Digital Gestionada. ", e);
		} 
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}