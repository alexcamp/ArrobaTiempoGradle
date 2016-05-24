/*
 * Creado el 20/02/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory.df.configuracion;
import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarNapsterLocalHome;


import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author dcardena
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AConfigurarNapsterFactory implements IActividadFactoryEJBService {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB = null;
		try {
			AConfigurarNapsterLocalHome ejbHome = (AConfigurarNapsterLocalHome) HomeFactory
					.getHome(AConfigurarNapsterLocalHome.JNDI_NAME);
			actEJB = ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "
					+ AConfigurarNapsterLocalHome.JNDI_NAME
					+ " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "
					+ AConfigurarNapsterLocalHome.JNDI_NAME
					+ " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}