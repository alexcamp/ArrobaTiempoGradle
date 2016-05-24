package co.com.telefonica.atiempo.vpistbba.actividades.factory.df.configuracion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AObtenerInfBASigresLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AObtenerInfBASigresFactory implements IActividadFactoryEJBService {

	   /* (non-Javadoc)
		* @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
		*/
	   public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		   IActividadEJB actEJB=null;
		   try {
			   AObtenerInfBASigresLocalHome ejbHome= (AObtenerInfBASigresLocalHome)HomeFactory.getHome(AObtenerInfBASigresLocalHome.JNDI_NAME);
			   actEJB= ejbHome.create();
		   } catch (CreateException e) {
			   throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AObtenerInfBASigresLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		   } catch (NamingException e) {
			   // TODO Auto-generated catch block
			   throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AObtenerInfBASigresLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		   }
		   return actEJB;
	   }
}
