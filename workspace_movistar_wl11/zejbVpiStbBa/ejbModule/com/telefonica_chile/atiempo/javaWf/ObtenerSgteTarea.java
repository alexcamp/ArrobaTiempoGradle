/*
 * Creado el 7/07/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package com.telefonica_chile.atiempo.javaWf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.control.GenerarCorrelativoWF;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

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
			GenerarCorrelativoWF generarCorrelativoWF = new GenerarCorrelativoWF();
			String correlativo = act.getActImplCorrelID();
			//String correlativo = generarCorrelativoWF.RandomString(80);
			log.debug("Se genera el correlativo: "+correlativo);
			
//			Se crea la nueva Actividad
			ActividadFactoryEJB actF = new ActividadFactoryEJB();
			String codigoActividad  = this.obtenerCodActividad(act, ordenFlujo);
			IActividadEJB actividad = actF.getActividad(codigoActividad);
			
			ActividadEJBDTO act2 = actividad.getActividadEJBDTO(numeroPeticion,codigoActividad,correlativo,act.getXMLDatos());
			ordenFlujo = new Integer( ordenFlujo.intValue()+1 );
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
			
			for (Iterator iter = peticionFlujoList.iterator(); iter.hasNext();) {
				Peticion_flujoLocal tarea = (Peticion_flujoLocal) iter.next();
				if ( tarea.getPefl_estado()== null || !(tarea.getPefl_estado().equals("OK")) ){
					//LE PASA LOS ID DE LAS ACTIVIDAD A SEGUIR
					Collection actividades = actividadHome.findByActi_id(tarea.getIdActividad(), act.getIdAplicacion());
					for (Iterator iter2 = actividades.iterator(); iter2.hasNext();) {
						//Iterator iter2 = actividades.iterator();
						ActividadLocal actividad = (ActividadLocal)iter2.next();
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