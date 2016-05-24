/*
 * Created on 03-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import java.util.HashMap;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;

import com.tecnonautica.utiles.basicos.ParseadorXML;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WFMessageSTServicio extends ServicioBasico {

	protected static Logger log = LoggerFactory.getLogger(WFMessageSTServicio.class);
	
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
//	 */
//	protected void procesar(Object[] obj) throws ATiempoAppEx {
//		try {		
//			IActividadEJB actividadEJB = (IActividadEJB) obj[0];
//			actividadEJB.iniciar();
//		} catch (TnProcesoExcepcion e) {
//			log.debug("Horror1",e);
//			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
//	 */
//	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx{
//		String entradaXML = null;
//		//Obtengo el XML de entrada
//		entradaXML = mensaje;
////		if ( log.isDebugEnabled() )
////				log.debug("\n************** Mensaje WT ST Recibido:\n" + entradaXML + "\n");
//
//		//Aquí se parsea el XML, se crea un objeto Actividad y se entrega el EJB que la representa
//		IActividadEJB actividadEJB = this.getActividadFromXML(entradaXML);
//
//		//Si falla retorna un AtiempoException
//		Object[] obj = new Object[1];
//		obj[0] = actividadEJB;
//		return obj;
//	
//
//	}
//
//	private IActividadEJB getActividadFromXML(String entradaXML)
//		throws ATiempoAppEx{
//		ParseadorXML parseador = new ParseadorXML();
//		HashMap campos;
//		try {
//			campos = parseador.parse(entradaXML);
//
//			String idProceso = (String) campos.get("_PROCESS");
//			String sNumeroPeticion = null;
//			Long numeroPeticion = null;
//	//		String template = (String) campos.get("_PROCESS_MODEL");
//			String codigoActividad = (String) campos.get("_ACTIVITY");
//			String actImplCorrelID = (String) campos.get("ActImplCorrelID");
//	
//			// Si el mensaje no es formato WF, no se debe realizar nada. 
//			if (idProceso==null || codigoActividad==null) {
//				log.info("No fue posible reconocer Actividad de Mensaje: '" + campos.get("") + "'");
//				return null;
//			}
//	
//			// JVR.
//			if ( log.isDebugEnabled() )
//				log.debug("Mensaje Recibido [" + idProceso + "," + codigoActividad + "," + actImplCorrelID + "]");
//	
//			try {
//				sNumeroPeticion = idProceso.substring(idProceso.indexOf("_") + 1);
//				numeroPeticion = new Long(sNumeroPeticion);
//			} catch (Exception ex) {
//				log.info("No fue posible reconocer Peticion de Mensaje: '" + campos.get("") + "'");
//				return null;
//			}
//	
//	//		ResourceBundle resBundle = ResourceBundle.getBundle(WFMessageBean.WF_PROPERTIES_FILE);
//	//		String nombreClaseActividadFactory = resBundle.getString(WFMessageBean.WF_ATRIBUTO_ACTIVIDADFACTORY);
//	//		IActividadFactory actividadFactory = (IActividadFactory) Class.forName(nombreClaseActividadFactory).newInstance();
//			ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
//			IActividadEJB actividad = actF.getActividad(numeroPeticion, codigoActividad,actImplCorrelID,entradaXML);
////			new ParamsExtracterEJB(entradaXML, actividad).setMapDatos();
//			return actividad;
//		
//		} catch (Exception e) {
//			log.debug("Horror2",e);
//			throw new ATiempoAppEx(ATiempoAppEx.XML,e);
//		}
//		
//	}	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		try {		
			if (obj != null){
				IActividadEJB actividadEJB = (IActividadEJB) obj[0];
				ActividadEJBDTO act= (ActividadEJBDTO)obj[1];
				actividadEJB.iniciar(act);	
			}else{
				log.debug("No se conoce el mensaje y no se realiza niguna actividad.");
			}
		} catch (TnProcesoExcepcion e) {
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx{
		String entradaXML = null;
		//Obtengo el XML de entrada
		entradaXML = mensaje;
//		if ( log.isDebugEnabled() )
//				log.debug("\n************** Mensaje WT ST Recibido:\n" + entradaXML + "\n");

		//Aquí se parsea el XML, se crea un objeto Actividad y se entrega el EJB que la representa
		Object[] obj = this.getActividadFromXML(entradaXML);

		return obj;
	}

	private Object[] getActividadFromXML(String entradaXML)
		throws ATiempoAppEx{
		ParseadorXML parseador = new ParseadorXML();
		HashMap campos;
		try {
			try{
				entradaXML=entradaXML.replaceAll("%2B","+");
				entradaXML=entradaXML.replaceAll("%2F","/");
				campos = parseador.parse(entradaXML);
			}catch(Exception e){
				log.info("No fue posible reconocer Mensaje");
				return null;
			}

			String idProceso = (String) campos.get("_PROCESS");
			String sNumeroPeticion = null;
			Long numeroPeticion = null;
	//		String template = (String) campos.get("_PROCESS_MODEL");
			String codigoActividad = (String) campos.get("_ACTIVITY");
			String actImplCorrelID = (String) campos.get("ActImplCorrelID");
	
			// Si el mensaje no es formato WF, no se debe realizar nada. 
			if (idProceso==null || codigoActividad==null) {
				log.info("No fue posible reconocer Actividad de Mensaje: '" + campos.get("") + "'");
				return null;
			}
	
			// JVR.
			if ( log.isDebugEnabled() )
				log.debug("Mensaje Recibido [" + idProceso + "," + codigoActividad + "," + actImplCorrelID + "]");
	
			try {
				sNumeroPeticion = idProceso.substring(idProceso.indexOf("_") + 1);
				numeroPeticion = new Long(sNumeroPeticion);
			} catch (Exception ex) {
				log.info("No fue posible reconocer Peticion de Mensaje: '" + campos.get("") + "'");
				return null;
			}
	
	//		ResourceBundle resBundle = ResourceBundle.getBundle(WFMessageBean.WF_PROPERTIES_FILE);
	//		String nombreClaseActividadFactory = resBundle.getString(WFMessageBean.WF_ATRIBUTO_ACTIVIDADFACTORY);
	//		IActividadFactory actividadFactory = (IActividadFactory) Class.forName(nombreClaseActividadFactory).newInstance();
//			ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
//			IActividadEJB actividad = actF.getActividad(numeroPeticion, codigoActividad,actImplCorrelID,entradaXML);
////			new ParamsExtracterEJB(entradaXML, actividad).setMapDatos();
//			return actividad;
//
  			ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
			IActividadEJB actividad = actF.getActividad(codigoActividad);
			
			ActividadEJBDTO act = actividad.getActividadEJBDTO(numeroPeticion,codigoActividad,actImplCorrelID,entradaXML);			
//			new ParamsExtracterEJB(entradaXML, actividad).setMapDatos();
			Object[] obj = new Object[2];
			obj[0] = actividad;//ejb
			obj[1] = act;//Dto
			return obj;					
		
		} catch (Exception e) {
			log.debug("Horror2",e);
			throw new ATiempoAppEx(ATiempoAppEx.XML,e);
		}
		
	}	


}
