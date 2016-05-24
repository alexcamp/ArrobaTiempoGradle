package co.com.telefonica.atiempo.vpistbba.actividades.factory.df;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ASuspensionReconexionSigresBABeanLocalHome;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ASuspensionReconexionSigresBAFactory implements IActividadFactoryEJBService {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB=null;
		try {
			ASuspensionReconexionSigresBABeanLocalHome ejbHome= (ASuspensionReconexionSigresBABeanLocalHome)HomeFactory.getHome(ASuspensionReconexionSigresBABeanLocalHome.JNDI_NAME);
			System.out.println("en el factory");
			actEJB=ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASuspensionReconexionSigresBABeanLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASuspensionReconexionSigresBABeanLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
