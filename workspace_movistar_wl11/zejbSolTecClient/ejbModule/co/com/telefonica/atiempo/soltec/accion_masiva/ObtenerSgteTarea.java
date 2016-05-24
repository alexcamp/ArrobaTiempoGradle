/*
 * Creado el 7/07/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.soltec.accion_masiva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ObtenerSgteTarea {

	/**
	 * @param act
	 */
	
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public ObtenerSgteTarea(ActividadEJBDTO act, Integer ordenFlujo) {
		
		log.debug("Entro a calcula la siguiente Tarea");
		
		
		try {
//			se instancia la actividad
			Long numeroPeticion = act.getNumeroPeticion();
			//GenerarCorrelativoWF generarCorrelativoWF = new GenerarCorrelativoWF();
			String correlativo = act.getActImplCorrelID();
			//String correlativo = generarCorrelativoWF.RandomString(80);
			log.debug("Se genera el correlativo: "+correlativo);
			
//			Se crea la nueva Actividad
			ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
			String codigoActividad  = this.obtenerCodActividad(act, ordenFlujo);
			IActividadEJB actividad = actF.getActividad(codigoActividad);
			
			ActividadEJBDTO act2 = actividad.getActividadEJBDTO(numeroPeticion,codigoActividad,correlativo,act.getXMLDatos());
			act2.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
			actividad.iniciar(act2);
			
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @param actEjb
	 */
	public ObtenerSgteTarea(ActividadEJBDTO actEjb) {
		
		try {
			// TODO Apéndice de constructor generado automáticamente
			log.debug("Inicio de seteo de la actual actividad");
			ActividadLocalHome actividadHome = (ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			Long newActivity = null;
			
			String bandejaPGIAverias	= VpistbbaConfig.getVariable("ID_PGI_AVERIAS"); //REQ TOA FASE III
			String bandejaProcesoBAJAS	= VpistbbaConfig.getVariable("ID_PROCESO_BAJA"); //REQ TOA FASE III
			
			if(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba) != null
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba).equals(""))
				newActivity  = new Long(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba));
			if(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb) != null
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb).equals(""))
				newActivity  = new Long(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb));
			if(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv) != null
					&& !actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv).equals(""))
				newActivity  = new Long(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv));
			if(actEjb.getDato("SOL_TOA") != null
					&& !actEjb.getDato("SOL_TOA").equals(""))
				newActivity  = new Long(actEjb.getDato("SOL_TOA"));
			//REQ TOA FASE III DCARDENA
			//se agrega validacion del forza actividad el cual llegara con la nueva bandeja de PGI_Averias 23
			if(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) != null
					&& actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaPGIAverias)){
				log.debug("Actividad es PGI AveriasBN "+actEjb.getNumeroPeticion());
				newActivity  = new Long(ComunInterfaces.ID_ACTIVIDAD_PGI_AVERIAS);
			}
			//se agrega validacion del forza actividad el cual llegara con la nueva bandeja de PROCESO DE BAJA 24
			if(actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) != null
					&& actEjb.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(bandejaProcesoBAJAS)){
				log.debug("Actividad es BANDEJA PROCESO BAJA "+actEjb.getNumeroPeticion());
				newActivity  = new Long(ComunInterfaces.ID_ACTIVIDAD_PROCESO_BAJA);
			}
			// FIN REQ TOA DCARDENA
			
			log.debug("Actividad que se informa: "+ newActivity);
			
			ActividadLocal actividadLocal = actividadHome.findByPK(newActivity);
			ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
			String codigoActividad  = actividadLocal.getAct_codigo();
			IActividadEJB actividad = actF.getActividad(codigoActividad);
			
			ActividadEJBDTO act2 = actividad.getActividadEJBDTO(actEjb.getNumeroPeticion(),codigoActividad,actEjb.getActImplCorrelID(),actEjb.getXMLDatos());
			String orden = actEjb.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden);
			act2.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, orden);
			actividad.iniciar(act2);
			
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ObtenerSgteTarea NamingException"+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ObtenerSgteTarea FinderException"+e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ObtenerSgteTarea FinderException"+e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error ObtenerSgteTarea TnProcesoExcepcion"+e);
		}
	}

	/**
	 * @param act
	 * @return
	 */
	private String obtenerCodActividad(ActividadEJBDTO act, Integer ordenFlujo) {
		// TODO Apéndice de método generado automáticamente
		List datos = new ArrayList();
		String codigoActividad;
		Peticion_flujoLocalHome peticionFlujoLocalHome;
		try {
			// Se obtiene la siguiente actividad
			peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticionFlujoList = peticionFlujoLocalHome.findActividadesByOrden(act.getNumeroPeticion(), ordenFlujo);
			ActividadLocalHome actividadHome = (ActividadLocalHome)HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			log.debug("Se entra a codigo para el orden: "+ ordenFlujo + " Para la peticion: "+act.getNumeroPeticion());
			for (Iterator iter = peticionFlujoList.iterator(); iter.hasNext();) {
				Peticion_flujoLocal tarea = (Peticion_flujoLocal) iter.next();
				if ( tarea.getPefl_estado()== null || !(tarea.getPefl_estado().equals("OK")) ){
					//LE PASA LOS ID DE LAS ACTIVIDAD A SEGUIR
					Actividad_flujoLocal actividad_flujo = tarea.getActividad_flujo();
					Actividad_flujoKey actividad_flujoKey = (Actividad_flujoKey) actividad_flujo.getPrimaryKey();
					Collection actividades = actividadHome.findByIdActIdApliIdrol(new Long(1),new Integer( act.getIdAplicacion().intValue()),actividad_flujoKey.acti_id);
					log.debug("Se obtienen tareas para id :"+ actividad_flujoKey.acti_id);
					for (Iterator iter2 = actividades.iterator(); iter2.hasNext();) {
						//Iterator iter2 = actividades.iterator();
						ActividadLocal actividad = (ActividadLocal)iter2.next();
						log.debug("Se evalua tarea para id :"+ actividad.getAct_codigo());
						if(!actividad.getAct_codigo().equals(act.getCodigoActividad())){
							codigoActividad = actividad.getAct_codigo();
							log.debug("Codigo A Seguir: " + codigoActividad);
							return codigoActividad;
						}
						
					}
				}
			}
			
		}catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al instanciar el Bean: "+e);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error al instanciar el Bean: "+e);
			return null;
		}
		return null;
	}

}