package com.telefonica_chile.atiempo.javaWf;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.vpistbba.servicios.WFMessageServicio;

/**
 * @author VictorMan
 * 
 * Bean implementation class for Enterprise Bean: WFMessage
 */
public class WFMessageBean extends MDServicioBean {



//	public void onMessage(javax.jms.Message msg) {
//
//		String entradaXML = null;
//		String msgErr = "";
//		String result = "";
//		Date dateIni = new Date(); 
//		try {
//			TextMessage textMessage = (TextMessage) msg;
//			// Obtengo el XML de entrada
//			entradaXML = textMessage.getText();
//			if ( log.isDebugEnabled() )
//				log.debug("\n************** Mensaje Recibido:\n" + entradaXML + "\n");
//
//			//Aquí se parsea el XML, se crea un objeto Actividad y se entrega el EJB que la representa
//			IActividadEJB actividad = this.getActividadFromXML(entradaXML);
//			//Si falla retorna un AtiempoException
//
////TODO: Falta controlar las excepciones y dirigirlas a una cola			
//			actividad.iniciar();
//
//		} catch (JMSException e) {
//			result = ": JMSException: " + e.getMessage() + ". " + e.getClass().getName();
//		} catch (NamingException e) {
//			result = ": NamingException: " + e.getMessage() + ". " + e.getClass().getName();
//		} catch (CreateException e) {
//			result = ": CreateException: " + e.getMessage() + ". " + e.getClass().getName();
//		} catch (ClassCastException e) {
//			result = ": ClassCastException: " + e.getMessage() + ". " + e.getClass().getName();
//		} catch (ATiempoAppEx e) {
//			result = ": ATiempoAppEx: " + e.getMessage() + ". " + e.getClass().getName();
//		} catch (Exception e) {
//			result = ": Exception: " + e.getMessage() + ". " + e.getClass().getName();
//			log.error("[" + msgErr + "]: Expetion.", e);
//		} finally {
//			if ( result.length() > 0 ) {
//				reInyectarMensaje( msgErr, entradaXML );				
//			}
//			if ( msgErr.indexOf("Inicio_Subflujo") < 0 ) {
//				log.info("Mensaje Procesado [" + msgErr + "," + ( (new Date()).getTime() - dateIni.getTime() )+ "]" + result );
//			}
//		}
//	
//		
//	}
	
//	private void reInyectarMensaje( String msgErr, String entradaXML ) {
//		log.info("Error... ReInyectando Mensaje. [" + msgErr + "]");
//		try {
//			ReinyeccionMensajeLocalHome reinyectarLocalHome =
//				(ReinyeccionMensajeLocalHome) HomeFactory.getHome(ReinyeccionMensajeLocalHome.JNDI_NAME);
//			ReinyeccionMensajeLocal reinyectar =
//				(ReinyeccionMensajeLocal) reinyectarLocalHome.create();
//			reinyectar.reinyectarMensaje(entradaXML);
//		} catch (NamingException e) {
//			log.error("Reinyectar NamingException [" + msgErr + "]: " + e.getMessage() + ". " + e.getClass().getName());
//		} catch (CreateException e) {
//			log.error("Reinyectar CreateException [" + msgErr + "]: " + e.getMessage() + ". " + e.getClass().getName());
//		} catch (Exception e) {
//			log.error("Reinyectar Exception [" + msgErr + "]: " + e.getMessage() + ". " + e.getClass().getName());
//		}
//	}

//	private IActividadEJB getActividadFromXML(String entradaXML)
//		throws ATiempoAppEx, Exception{
//		ParseadorXML parseador = new ParseadorXML();
//		HashMap campos = parseador.parse(entradaXML);
//
//		String idProceso = (String) campos.get("_PROCESS");
//		String sNumeroPeticion = null;
//		Long numeroPeticion = null;
////		String template = (String) campos.get("_PROCESS_MODEL");
//		String codigoActividad = (String) campos.get("_ACTIVITY");
//		String actImplCorrelID = (String) campos.get("ActImplCorrelID");
//
//		// Si el mensaje no es formato WF, no se debe realizar nada. 
//		if (idProceso==null || codigoActividad==null) {
//			log.info("No fue posible reconocer Actividad de Mensaje: '" + campos.get("") + "'");
//			return null;
//		}
//
//		// JVR.
//		if ( log.isDebugEnabled() )
//			log.debug("Mensaje Recibido [" + idProceso + "," + codigoActividad + "," + actImplCorrelID + "]");
//
//		try {
//			sNumeroPeticion = idProceso.substring(idProceso.indexOf("-") + 1);
//			numeroPeticion = new Long(sNumeroPeticion);
//		} catch (Exception ex) {
//			log.info("No fue posible reconocer Peticion de Mensaje: '" + campos.get("") + "'");
//			return null;
//		}
//
////		ResourceBundle resBundle = ResourceBundle.getBundle(WFMessageBean.WF_PROPERTIES_FILE);
////		String nombreClaseActividadFactory = resBundle.getString(WFMessageBean.WF_ATRIBUTO_ACTIVIDADFACTORY);
////		IActividadFactory actividadFactory = (IActividadFactory) Class.forName(nombreClaseActividadFactory).newInstance();
//		ActividadFactoryEJB actF = new ActividadFactoryEJB();
//		IActividadEJB actividad = actF.getActividad(numeroPeticion, codigoActividad,actImplCorrelID);
//		new ParamsExtracterEJB(entradaXML, actividad).setMapDatos();
//		
//		return actividad;
//	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		return new WFMessageServicio();
	}
	
}
