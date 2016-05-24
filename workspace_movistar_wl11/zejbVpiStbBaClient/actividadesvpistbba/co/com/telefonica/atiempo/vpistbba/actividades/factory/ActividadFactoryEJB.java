/*
 * Created on 08-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.ActividadFactoryConfig;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;


/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActividadFactoryEJB {
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private static final String AF_DEFAULT_KEY = "defaultEJBActividad";

	//Asumo el template de VPI
	public IActividadEJB getActividad(
		String codigoActividad)
		throws ATiempoAppEx {

		String idTemplateWf = VpistbbaConfig.getVariable("ID_TEMPLATE");
		IActividadEJB actEJB = null;
		String nombreActFactoryEJB = "";

		try {
			log.debug("Nombre ACTIVIDAD " + idTemplateWf + "-" + codigoActividad);
			//Se obtiene del propertie el nombre de la clase factory que permite levantar la Actividad EJB
			nombreActFactoryEJB = ActividadFactoryConfig.getVariable(idTemplateWf + "-" + codigoActividad);
			if (nombreActFactoryEJB == null) {
				log.debug("Si todo falla, retorno la Factory por defecto");
				nombreActFactoryEJB = ActividadFactoryConfig.getVariable(ActividadFactoryEJB.AF_DEFAULT_KEY);
			}
			//Se crea una instancia de la Clase Factory
			Class actFactoryEJB = Class.forName(nombreActFactoryEJB);
			log.debug("Se instancia la clase: "+actFactoryEJB.getName());
			IActividadFactoryEJBService iActFactoryEJB = (IActividadFactoryEJBService) actFactoryEJB.newInstance();
			//Se levanta el EJB
			actEJB = iActFactoryEJB.getActividadEJB();


		} catch (Exception e) {
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
