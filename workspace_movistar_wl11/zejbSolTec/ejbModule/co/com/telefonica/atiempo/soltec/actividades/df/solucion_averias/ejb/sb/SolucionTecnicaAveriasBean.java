package co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

/**
 * Bean implementation class for Enterprise Bean: SolucionTecnicaAverias
 */
public class SolucionTecnicaAveriasBean extends ActividadManualSTEJB{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		log.debug("inicializa obtengo WF intancia "+	act.isGrabarWfInstancia());
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		log.debug("onInicio inicia WF intancia "+	act.isGrabarWfInstancia());

	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		//REQ TOA FASE III DCARDENA 
		//Se valida pgi_averias != S para que cuando se reintente desde la bandeja de averias PGI no genere el error duplicate key exception en wfintancia
		try {	
			
			Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticiones = peticion_flujoLocalHome.findByPeticionOrden(act.getNumeroPeticion());
			
			if(act.getDato("PGI_AVERIAS_OK").equals("S")||act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("ID_PGI_AVERIAS"))){
				act.setGrabarWfInstancia(false);
				for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
					log.debug("obtengo actividad para reintentar " + act.getCodigoActividad()+"on termino soluciontecnica" +act.getNumeroPeticion());		
					Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
					if(peticionFlujo.getPefl_estado()!=null && peticionFlujo.getPefl_estado().equals("NOOK")){
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
						log.debug("se obtiene el orden de actividad en reintento se setea el flujo orden de actual"+peticionFlujo.getPefl_orden().toString()+" actividad "+peticionFlujo.getActividad_flujo().getActi_codigo());
						break;
					}
				}
				
			}
			if (act.getDato("ACT_OK").equals("S")){
				act.setGrabarWfInstancia(true);
				for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
					log.debug("Marco OK Subflujo Actividad:" + act.getCodigoActividad()+"on termino soluciontecnica" +act.getNumeroPeticion());		
					Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
					if(peticionFlujo.getPefl_estado()!=null && peticionFlujo.getPefl_estado().equals("NOOK")){
						peticionFlujo.setPefl_estado("OK");
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
						log.debug("se marca ok y se setea el flujo orden de actual"+peticionFlujo.getPefl_orden().toString()+" actividad "+peticionFlujo.getActividad_flujo().getActi_codigo());
						break;
					}
				}
			}
			log.debug("ontermino inicia WF intancia "+	act.isGrabarWfInstancia());
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("onTerminoActividadST error al consultar por find"+ e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("onTerminoActividadST error al levantar el localhome"+ e);
		}
	}
}