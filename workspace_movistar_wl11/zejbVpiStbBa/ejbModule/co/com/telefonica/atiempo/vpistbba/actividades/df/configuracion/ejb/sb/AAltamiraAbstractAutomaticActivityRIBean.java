package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Actividad abstracta de altamira, para usar para todas las actividades automaticas de altamira la misma logica, 
 * solo cambia servicio altamira a invocar y valor de variable de WF, que son los metodos abstractas.
 */
public abstract class AAltamiraAbstractAutomaticActivityRIBean	extends	co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	private static final Logger log = LoggerFactory.getLogger(AAltamiraAbstractAutomaticActivityRIBean.class);
	
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		Long petiNumero = act.getNumeroPeticion();
		String msg = "OnInicio Altamira Activity petiNumero=" + petiNumero + " codigoActividid: " + act.getCodigoActividad();
		log.debug(msg);
		try {
			//TODO si ya pasé por esta actividad y no estoy en reversa, implica que fué derivada a PGI/PGC, 
			//por lo tanto la tengo que enviar a Aprovisionamieno red inteligente(manual)
			//en tabla PETICION_FLUJO peti_nro = act.getNumeroPeticion() //nro peticion  
			//and acti_id = act.getIdActividadFlujo()//nro actividad 
			//and fefl_estado = 'OK'    ==> lo deribo nuevamente a Aprovisionamieno red inteligente
			RecursosInterfaces recursosInterfaces = new RecursosDelegate();
			if (act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Estoy en reversa petiNumero=" + petiNumero);
				String serviceEnReversa = getAtiempoServiceNameEnReversa();
				if (serviceEnReversa != null) {
					log.debug("Estoy en reversa petiNumero=" + petiNumero + " serviceEnReversa=" + serviceEnReversa);
					recursosInterfaces.altamiraEnvioTr601(act, serviceEnReversa, true);
				} else {
					log.debug("NO hay reversa para esta actividad petiNumero=" + petiNumero);
					act.setRealizarTerminoInmediato(true);
					// TODO: Verificar con algun ejemplo si va mensaje en la bitacora
					act.setObservacion("NO hay reversa para esta actividad");
				}
							
			} else {
				boolean paso = pasoPorActividad(act, petiNumero);
				
				//log.debug(">>>>>>>>>>>>>>>>>>>>>>>PASO:" + paso);
				
				if(!paso){
					String atiempoService = getATiempoServiceName();
					recursosInterfaces.altamiraEnvioTr601(act, atiempoService, false);
				} else { //estoy viniendo de PGI/PGC, solucionando quiebres, por lo que derivo a Aprovisionamiento red inteligente
					//String fluj_mult_conf_int = getWFVar_fluj_mult_conf_int();
					String fluj_mult_conf_int = ComunInterfaces.WF_VALUE_ALTAMIRA_APROVISIONAMIENTO_RI;
					log.debug("Estoy viniendo de PGI/PGC solucionando quiebres petiNumero=" + petiNumero + " fluj_mult_conf_int=" + fluj_mult_conf_int);
					act.setRealizarTerminoInmediato(true);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_int, fluj_mult_conf_int);
				}	
			}	
		} catch (ATiempoAppEx e) {
			log.warn("ERROR en " + msg,e);
			throw new TnProcesoExcepcion("Error en " + msg, e);
		}
	}
	/**
	 * Retorna el servicio de Altamira a invocar (en realidad broker homologa a un servicio de altamira)
	 */
	protected abstract String getATiempoServiceName();

	
	/**
	 * Retorna el servicio de Altamira a invocar en modo reversa, 
	 * si no tiene reversa debe devolver null 
	 */
	protected abstract String getAtiempoServiceNameEnReversa();
	
	private boolean pasoPorActividad(ActividadEJBDTO act, Long petiNumero) {
		boolean paso = false;
		Collection c = null;
		try {
			Peticion_flujoLocalHome petFlujoHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			c = petFlujoHome.findByActividadEstado(act.getNumeroPeticion(), act.getIdActividadFlujo());
			paso = !c.isEmpty();
		} catch (NamingException e1) {
			log.debug("Error al consultar si paso o no", e1);
		} catch (FinderException e) {
			log.debug("La Peticion petiNumero=" + petiNumero + " aun NO paso por la actividad " + act.getActividadBD().getDescActividad() + " Exception: " + e);
		}
		return paso;
	}

	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}


	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		//TODO solo para pruebas, en on termino hago nada.
		//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_int,"AltaAbonadoRIS");//lo deriva a Aprovisionamieno red inteligente(manual)
	}
}
