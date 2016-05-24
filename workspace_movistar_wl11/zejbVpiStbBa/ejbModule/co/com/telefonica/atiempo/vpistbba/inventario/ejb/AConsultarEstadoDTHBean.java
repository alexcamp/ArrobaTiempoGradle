package co.com.telefonica.atiempo.vpistbba.inventario.ejb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.ejb.eb.EquipoLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: AConsultarEstadoDTH
 */
public class AConsultarEstadoDTHBean extends
co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
implements
	javax.ejb.SessionBean {

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
	log.debug("--Pasa por metodo onInicioActividadVPI de AConsultarEstadoDTHBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
	
	try {
		PeticionLocalHome petHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		PeticionLocal petLocal = petHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
		
		if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")
				&& !petLocal.getTica_id().equals("A")){
			log.debug("Ejecuta la actividad de Colsultar estado DTH.");
			EquipoLocal delegateEquipos = new EquipoDelegate();			
			delegateEquipos.ConsultarEstadoDTH(act, act.getNumeroPeticion(),act.getCodigoActividad());
		} else{
			/*SE INHIBE LA ACTIVIDAD POR IR EN REVERSA*/
			log.debug("Se inhibe la actividad de Colsultar estado DTH.");
        	act.setObservacion("Se inhibe la actividad de Colsultar estado DTH.", true);
			act.setRealizarTerminoInmediato(true);
		}
		
		
	} catch (ATiempoAppEx e) {
		log.error("AConsultarEstadoDTHBean: onInicioActividadVPI: Error intentando enviar la información de Cunsulta  Estado DTH"+e);
	} catch (NamingException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	} catch (FinderException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}		
}

/* (non-Javadoc)
 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
 */
protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	log.debug("--Pasa por metodo onInicioActividadVPI de AConsultarEstadoDTHBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
	
}
}