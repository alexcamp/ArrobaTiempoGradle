/*
 * Created on 03-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades.factory;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.ActividadFactorySTConfig;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActividadFactorySTEJB {

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private static final String AF_DEFAULT_KEY = "defaultEJBActividad";

	//TODO: Falta la Factory EJB por defecto

//	public IActividadEJB getActividad(
//		Long numeroPeticion,
//		String codigoActividad)
//		throws ATiempoAppEx {
//		return this.getActividad(numeroPeticion, codigoActividad, null,null);
//	}
//	
//	//Asumo el template de ST
//	public IActividadEJB getActividad(
//		Long numeroPeticion,
//		String codigoActividad,
//		String actImplCorrelID,
//		String entradaXML)		
//		throws ATiempoAppEx {
//
//		String idTemplateWf = STConfig.getVariable("ID_TEMPLATE");
//		IActividadEJB actEJB = null;
//		String nombreActFactoryEJB = "";
//
//		try {
//			log.debug("Nombre ACTIVIDAD " + idTemplateWf + "-" + codigoActividad);
//			//Se obtiene del propertie el nombre de la clase factory que pertmite levantar la Actividad EJB
//			nombreActFactoryEJB = ActividadFactorySTConfig.getVariable(idTemplateWf + "-" + codigoActividad);
//			if (nombreActFactoryEJB == null) {
//				log.debug("Si todo falla, retorno la Factory por defecto");
//				nombreActFactoryEJB = ActividadFactorySTConfig.getVariable(ActividadFactorySTEJB.AF_DEFAULT_KEY);
//			}
//			//Se crea una instancia de la Clase Factory
//			Class actFactoryEJB = Class.forName(nombreActFactoryEJB);
//			IActividadFactoryEJBService iActFactoryEJB = (IActividadFactoryEJBService) actFactoryEJB.newInstance();
//			//Se levanta el EJB
//			actEJB = iActFactoryEJB.getActividadEJB();
//			//Se setean los valores iniciales de la  actividad EJB
//			actEJB.setInfo(
//				idTemplateWf,
//				numeroPeticion,
//				codigoActividad,
//				actImplCorrelID);
//			//Se setean las variables del WF
//			if (entradaXML!=null && !entradaXML.equals("")){
//				new ParamsExtracterEJB(entradaXML, actEJB).setMapDatos();
//			}
//			//Se cargan los datos de la actividad desde la BD y se verifican los PS ok y no ok
//			actEJB.cargaActividadEJB();						
//
//		} catch (Exception e) {
//			log.debug("Horror",e);
//			throw new ATiempoAppEx(
//				e.getClass().getName()
//					+ " en ActividadFactoryEJB.getActividad : La Factory "
//					+ nombreActFactoryEJB
//					+ " no es posible levantarla) "
//					+ e.getMessage());
//		}
//		return actEJB;
//	}

	
	//Asumo el template de ST
	public IActividadEJB getActividad(
		String codigoActividad)
		throws ATiempoAppEx {

		String idTemplateWf = STConfig.getVariable("ID_TEMPLATE");
		IActividadEJB actEJB = null;
		String nombreActFactoryEJB = "";

		try {
			log.debug("Nombre ACTIVIDAD " + idTemplateWf + "-" + codigoActividad);
			//Se obtiene del propertie el nombre de la clase factory que pertmite levantar la Actividad EJB
			nombreActFactoryEJB = ActividadFactorySTConfig.getVariable(idTemplateWf + "-" + codigoActividad);
			if (nombreActFactoryEJB == null) {
				log.debug("Si todo falla, retorno la Factory por defecto");
				nombreActFactoryEJB = ActividadFactorySTConfig.getVariable(ActividadFactorySTEJB.AF_DEFAULT_KEY);
			}
			//Se crea una instancia de la Clase Factory
			Class actFactoryEJB = Class.forName(nombreActFactoryEJB);
			IActividadFactoryEJBService iActFactoryEJB = (IActividadFactoryEJBService) actFactoryEJB.newInstance();
			//Se levanta el EJB
			actEJB = iActFactoryEJB.getActividadEJB();
//			//Se setean los valores iniciales de la  actividad EJB
//			actEJB.setInfo(
//				idTemplateWf,
//				numeroPeticion,
//				codigoActividad,
//				actImplCorrelID);
//			//Se setean las variables del WF
//			if (entradaXML!=null && !entradaXML.equals("")){
//				new ParamsExtracterEJB(entradaXML, actEJB).setMapDatos();
//			}
//			//Se cargan los datos de la actividad desde la BD y se verifican los PS ok y no ok
//			actEJB.cargaActividadEJB();						

		} catch (Exception e) {
			log.debug("Error en ActividadFactoryEJB.getActividad.",e);
			throw new ATiempoAppEx(
				e.getClass().getName()
					+ " en ActividadFactoryEJB.getActividad : La Factory "
					+ nombreActFactoryEJB
					+ " no es posible levantarla) "
					+ e.getMessage());
		}
		return actEJB;
	}


}
