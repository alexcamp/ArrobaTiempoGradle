/*
 * Created on 06-jun-14
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.servicios.AAsistenciaRemotaLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author @Dcardena
 */
//RQ Cambio plan BA 25956 @dcardena 10/06/2014
public class AAsistenciaRemotaFactory implements IActividadFactoryEJBService {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.factory.IActividadFactoryEJBService#getActividadEJB()
	 */
	public AAsistenciaRemotaFactory() {
		super();
		
	}
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB=null;		
		try {
			AAsistenciaRemotaLocalHome ejbHome= (AAsistenciaRemotaLocalHome)HomeFactory.getHome(AAsistenciaRemotaLocalHome.JNDI_NAME);
			actEJB=ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AAsistenciaRemotaLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AAsistenciaRemotaLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
