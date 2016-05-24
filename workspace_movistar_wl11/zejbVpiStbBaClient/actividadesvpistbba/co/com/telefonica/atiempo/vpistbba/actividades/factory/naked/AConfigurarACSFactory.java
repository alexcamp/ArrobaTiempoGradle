/*
 * Creado el 2/04/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory.naked;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.naked.sb.AConfigurarACSLocalHome;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AConfigurarACSFactory implements IActividadFactoryEJBService{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		IActividadEJB actEJB = null;
		try {
			AConfigurarACSLocalHome ejbHome = (AConfigurarACSLocalHome) HomeFactory
					.getHome(AConfigurarACSLocalHome.JNDI_NAME);
			actEJB = ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "
					+ AConfigurarACSLocalHome.JNDI_NAME
					+ " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "
					+ AConfigurarACSLocalHome.JNDI_NAME
					+ " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
