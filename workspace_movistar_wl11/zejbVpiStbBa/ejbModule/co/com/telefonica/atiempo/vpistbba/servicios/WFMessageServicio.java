/*
 * Created on 03-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import java.util.HashMap;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;

import com.tecnonautica.utiles.basicos.ParseadorXML;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WFMessageServicio extends ServicioBasico {

//	protected static Logger log = LoggerFactory.getLogger(WFMessageServicio.class);
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
//	 */
//	protected void procesar(Object[] obj) throws ATiempoAppEx {
//		try {		
//			IActividadEJB actividadEJB = (IActividadEJB) obj[0];
//			actividadEJB.iniciar();
//		} catch (TnProcesoExcepcion e) {
//			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
//	 */
//	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
//		String entradaXML = null;
//		//Obtengo el XML de entrada
//		entradaXML = mensaje;
////		if ( log.isDebugEnabled() )
////				log.debug("\n************** Mensaje WT VPI Recibido:\n" + entradaXML + "\n");
//
//		//Aquí se parsea el XML, se crea un objeto Actividad y se entrega el EJB que la representa
//		IActividadEJB actividadEJB = this.getActividadFromXML(entradaXML);
//
//		//Si falla retorna un AtiempoException
//		Object[] obj = new Object[1];
//		obj[0] = actividadEJB;
//		return obj;
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
//				sNumeroPeticion = idProceso.substring(idProceso.indexOf("-") + 1);
//				numeroPeticion = new Long(sNumeroPeticion);
//			} catch (Exception ex) {
//				log.info("No fue posible reconocer Peticion de Mensaje: '" + campos.get("") + "'");
//				return null;
//			}
//	
//	//		ResourceBundle resBundle = ResourceBundle.getBundle(WFMessageBean.WF_PROPERTIES_FILE);
//	//		String nombreClaseActividadFactory = resBundle.getString(WFMessageBean.WF_ATRIBUTO_ACTIVIDADFACTORY);
//	//		IActividadFactory actividadFactory = (IActividadFactory) Class.forName(nombreClaseActividadFactory).newInstance();
//			ActividadFactoryEJB actF = new ActividadFactoryEJB();
//			IActividadEJB actividad = actF.getActividad(numeroPeticion, codigoActividad,actImplCorrelID,entradaXML);
////			new ParamsExtracterEJB(entradaXML, actividad).setMapDatos();
//			return actividad;
//		
//		} catch (Exception e) {
//			throw new ATiempoAppEx(ATiempoAppEx.XML,e);
//		}
//		
//	}	

	protected static Logger log = LoggerFactory.getLogger(WFMessageServicio.class);
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {

		// CR15338 - @Trace - ana santos - 04/08 - Inicio 
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
		TraceManager traceManager = traceConf.getManager();
		CurrentExecution cExecution = null;
		TraceOperation traceOp = null;		
		try
		{		
			if (obj != null){
				IActividadEJB actividadEJB = (IActividadEJB) obj[0];
				ActividadEJBDTO act = (ActividadEJBDTO) obj[1];
				Long idAct = act.getActividadBD().getIdActividad();
				String nomAct = act.getActividadBD().getDescActividad();
				String rolAct = act.getActividadBD().getDescRol();
				cExecution = traceManager.newCurrentExecution();				
				cExecution.init();
				
				traceOp = traceManager.newOperation(TraceAdapter.getIdOp(idAct), cExecution);
				traceOp.setColumn(TraceManager.COL_NUM_PET_ATIEMPO,act.getNumeroPeticion());
				traceOp.setColumn(TraceManager.COL_ID_ACTIVIDAD, idAct);					
				traceOp.setColumn(TraceManager.COL_NOM_ACTIVIDAD,nomAct);
				traceOp.setColumn(TraceManager.COL_ROL_ACTIVIDAD,rolAct);
				traceOp.setColumn(TraceManager.COL_TIPO_OPERACION, TraceAdapter.COL_TIPO_OP_ACTIVIDAD_WF);					
				
				traceOp.init(log);				
				
				traceManager.traceOpStart(traceOp);			
	
				actividadEJB.iniciar(act);
			}else{
				log.debug("No se conoce el mensaje y no se realiza niguna actividad.");
			}			
		} catch (TnProcesoExcepcion e) {
			traceOp.setColumn(TraceManager.COL_ERROR,Boolean.TRUE);
			throw new ATiempoAppEx(ATiempoAppEx.TN_PROCESS,e);
		}finally{
			traceManager.traceOpEnd(traceOp);
			traceManager.closeCurrentExecution(cExecution); 
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		String entradaXML = null;
		//Obtengo el XML de entrada
		entradaXML = mensaje;
//		if ( log.isDebugEnabled() )
//				log.debug("\n************** Mensaje WT VPI Recibido:\n" + entradaXML + "\n");

		//Aquí se parsea el XML, se crea un objeto Actividad y se entrega el EJB que la representa
		Object[] obj = this.getActividadFromXML(entradaXML);
		
		//Si falla retorna un AtiempoException
//		Object[] obj = new Object[1];2
//		obj[0] = actividadEJB;
		return obj;
	}

	private Object[] getActividadFromXML(String entradaXML)
		throws ATiempoAppEx{
		ParseadorXML parseador = new ParseadorXML();
		HashMap campos;
		try {
			try{
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
//areglo fttc WF Dcardena 15/02/2015--------------------------------------------------------------------------------------------------------
				//modificacion FTTC para regularizacion de las actividades que estan en vuelo se debe cambiar el nommbre de la actividad
				Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
				Properties_BDLocal propertiesDBLocalNueva = propertiesBDLocalHome.findByPrimaryKey("NUEVA_ACTIVIDAD");
				Properties_BDLocal propertiesDBLocalAntigua= propertiesBDLocalHome.findByPrimaryKey("ANTIGUA_ACTIVIDAD");
				//se valida que si el nombre de la actividad que viene de WF esta en el parametro de BD se debe actualziar al nuevo nombre
				if(codigoActividad.equals(propertiesDBLocalAntigua.getValor())){
					log.debug("La actividad Actual " + codigoActividad + " se encuntra desactualizada se actualizara el nombre de la actividad por " +
							propertiesDBLocalNueva.getValor()+" para el la peti numero " + idProceso + "]");
					//se cambia el nombre de la actividad
					codigoActividad=propertiesDBLocalNueva.getValor();	
				}
// fin-------------------------------------------------------------------------------------------------------------------------------------
			try {
				sNumeroPeticion = idProceso.substring(idProceso.indexOf("-") + 1);
				numeroPeticion = new Long(sNumeroPeticion);
			} catch (Exception ex) {
				log.info("No fue posible reconocer Peticion de Mensaje: '" + campos.get("") + "'");
				return null;
			}
	
	//		ResourceBundle resBundle = ResourceBundle.getBundle(WFMessageBean.WF_PROPERTIES_FILE);
	//		String nombreClaseActividadFactory = resBundle.getString(WFMessageBean.WF_ATRIBUTO_ACTIVIDADFACTORY);
	//		IActividadFactory actividadFactory = (IActividadFactory) Class.forName(nombreClaseActividadFactory).newInstance();
			ActividadFactoryEJB actF = new ActividadFactoryEJB();
			IActividadEJB actividad = actF.getActividad(codigoActividad);
			
			ActividadEJBDTO act = actividad.getActividadEJBDTO(numeroPeticion,codigoActividad,actImplCorrelID,entradaXML);			
//			new ParamsExtracterEJB(entradaXML, actividad).setMapDatos();
			Object[] obj = new Object[2];
			obj[0] = actividad;
			obj[1] = act;
			return obj;			
//			return actividad;
		
		} catch (Exception e) {
			log.debug("PeticionesServicesBean.listaDePsDePeticion: ",e);
			log.error("PeticionesServicesBean.listaDePsDePeticion: ",e);
			throw new ATiempoAppEx(ATiempoAppEx.XML,e);
		}
		
	}	


}
