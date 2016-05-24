/*
 * Created on 06-nov-14
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AConfigurarSxRxIMSLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.AAsistenciaRemotaLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author @Dcardena
 */
//RQ BA Naked @dcardena 06/11/2014
public class AConfigurarSxRxIMSFactory implements IActividadFactoryEJBService {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.factory.IActividadFactoryEJBService#getActividadEJB()
	 */
	public AConfigurarSxRxIMSFactory() {
		super();
		
	}
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB=null;		
		try {
			AConfigurarSxRxIMSLocalHome ejbHome= (AConfigurarSxRxIMSLocalHome)HomeFactory.getHome(AConfigurarSxRxIMSLocalHome.JNDI_NAME);
			actEJB= ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AConfigurarSxRxIMSLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AConfigurarSxRxIMSLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
