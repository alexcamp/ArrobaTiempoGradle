package com.telefonica_chile.vpistbba.grabacion;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: GrabadorVIPSTBBA
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class GrabadorVIPSTBBABean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public void grabar(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String nombreActividad = request.getParameter("codigoActividad");
		String nombreClaseGrabadora = null;
		
		nombreActividad="Flujo_AT_VPI_STB_BA-" + nombreActividad;
		log.debug("Codigo Actividad:" + nombreActividad);
		// Tengo el hash con la asociación de Tarea -> Grabador
		Map grabadorMap = PropertiesGrabadores.getDatos();
	
		if (!grabadorMap.containsKey(nombreActividad)) {
			nombreClaseGrabadora = GrabadorBaseVPISTBBA.class.getName();
		}
		else
		{
			nombreClaseGrabadora = (String) grabadorMap.get(nombreActividad);
		}


		/*
		 * Una vez que se ha determinado la clase grabadora, se ejecuta el método grabar(request)
		 * para proceder a procesar los campos ingresados por el usuario. La idea es que cada
		 * grabador está asociado a una (o unas pocas) actividades, pero tiene muy claro cuales son
		 * los campos que vienen de cada pantalla, así que sabe que cosas validar y cuales no.
		 */
		Class claseGrabadora = Class.forName(nombreClaseGrabadora);
		Grabador grabador = (Grabador) claseGrabadora.newInstance();

		if (null != request.getParameter("terminar_act_wf") && "0".equals(request.getParameter("terminar_act_wf")) ) {
			log.debug("Inicio GrabaSinTerminar [" + nombreActividad + "," + nombreClaseGrabadora + "]");
			grabador.grabarSinTerminar(request);
			log.debug("Termino GrabaSinTerminar [" + nombreActividad + "," + nombreClaseGrabadora + "]");
		}else
		{
			log.debug("Inicio Grabando [" + nombreActividad + "," + nombreClaseGrabadora + "]");
			grabador.grabar(request);
			log.debug("Termino Grabando [" + nombreActividad + "," + nombreClaseGrabadora + "]");
		}
	}
}
