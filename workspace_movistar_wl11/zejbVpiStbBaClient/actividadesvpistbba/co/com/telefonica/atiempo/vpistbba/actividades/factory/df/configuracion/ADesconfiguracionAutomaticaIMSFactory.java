package co.com.telefonica.atiempo.vpistbba.actividades.factory.df.configuracion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ADesconfiguracionAutomaticaIMSLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ADesconfiguracionAutomaticaIMSFactory  implements IActividadFactoryEJBService{

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB = null;
		try {
			ADesconfiguracionAutomaticaIMSLocalHome ejbHome = (ADesconfiguracionAutomaticaIMSLocalHome) HomeFactory
					.getHome(ADesconfiguracionAutomaticaIMSLocalHome.JNDI_NAME);
			actEJB = ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "
					+ ADesconfiguracionAutomaticaIMSLocalHome.JNDI_NAME
					+ " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "
					+ ADesconfiguracionAutomaticaIMSLocalHome.JNDI_NAME
					+ " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
