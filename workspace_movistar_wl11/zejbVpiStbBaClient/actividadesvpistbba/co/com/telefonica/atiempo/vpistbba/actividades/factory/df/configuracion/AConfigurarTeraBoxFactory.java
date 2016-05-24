/*
 * Created on Jan 17, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory.df.configuracion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarTeraBoxLocalHome;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarWebFilterOptenetLocalHome;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AConfigurarTeraBoxFactory implements IActividadFactoryEJBService {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB = null;
		try {
			AConfigurarTeraBoxLocalHome ejbHome = (AConfigurarTeraBoxLocalHome) HomeFactory.getHome(AConfigurarTeraBoxLocalHome.JNDI_NAME);
			actEJB = ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "+ AConfigurarTeraBoxLocalHome.JNDI_NAME+ " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB "+ AConfigurarTeraBoxLocalHome.JNDI_NAME+ " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
