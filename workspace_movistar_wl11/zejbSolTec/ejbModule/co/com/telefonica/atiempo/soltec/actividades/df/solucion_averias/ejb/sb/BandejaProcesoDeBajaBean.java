package co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;


/**
 * Bean implementation class for Enterprise Bean: BandejaProcesoDeBaja
 */
public class BandejaProcesoDeBajaBean extends ActividadManualSTEJB{

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
		try {	
			log.debug("**********ON TERMINO onTerminoActividadST BandejaProcesoDeBajaBean "+act.getNumeroPeticion()+"*******************************");
			Peticion_flujoLocalHome peticion_flujoLocalHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticiones = peticion_flujoLocalHome.findByPeticionOrden(act.getNumeroPeticion());
			
//			Se valida SOL_TOA = 2004 para que cuando se reintente desde la bandeja de PROCESO BAJA hacia Planta Externa no genere el error duplicate key exception en wfintancia
			if( act.getDato("SOL_TOA").equals("2004") ||act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("ID_PROCESO_BAJA"))){
				act.setGrabarWfInstancia(false);
				log.debug("obtengo actividad para reintentar " + act.getCodigoActividad()+"on termino BANDEJAPROCESOBAJA" +act.getNumeroPeticion());	
				for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
					Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
					if(peticionFlujo.getPefl_estado()!=null && peticionFlujo.getPefl_estado().equals("NOOK")){
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
						log.debug("se obtiene el orden de actividad en reintento se setea el flujo orden de actual"+peticionFlujo.getPefl_orden().toString()+" actividad "+peticionFlujo.getActividad_flujo().getActi_codigo());
						break;
					}
				}
				
			}

			//Se valida si se continua actividad desde el front opcion Solucionado del menu
			if (act.getDato("ACT_OK").equals("S")){
				act.setGrabarWfInstancia(false);
				log.debug("Se Continua Desde la Bandeja de Peticion BAJA "+act.getNumeroPeticion());
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba, "S");
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv, "S");
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, "S");
				for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
					Peticion_flujoLocal peticionFlujo = (Peticion_flujoLocal) iter.next();
					if(peticionFlujo.getPefl_estado()!=null && peticionFlujo.getPefl_estado().equals("NOOK")){
						log.debug("Marco OK Subflujo Actividad:" + act.getCodigoActividad()+"on termino soluciontecnica" +act.getNumeroPeticion());	
						peticionFlujo.setPefl_estado("OK");
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden,peticionFlujo.getPefl_orden().toString() );
						log.debug("se marca ok y se setea el flujo orden de actual"+peticionFlujo.getPefl_orden().toString()+" actividad "+peticionFlujo.getActividad_flujo().getActi_codigo());
						break;
					}
				}
			//	act.setRealizarTerminoInmediato(true);
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