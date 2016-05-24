/*
 * Creado el 11/10/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory.df.configuracion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.AObtenerInfBASigresSVALocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AObtenerInfBASigresSVAFactory implements IActividadFactoryEJBService {

	   /* (non-Javadoc)
		* @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
		*/
	   public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		   IActividadEJB actEJB=null;
		   try {
			   AObtenerInfBASigresSVALocalHome ejbHome= (AObtenerInfBASigresSVALocalHome)HomeFactory.getHome(AObtenerInfBASigresSVALocalHome.JNDI_NAME);
			   actEJB= ejbHome.create();
		   } catch (CreateException e) {
			   throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AObtenerInfBASigresSVALocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		   } catch (NamingException e) {
			   // TODO Auto-generated catch block
			   throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AObtenerInfBASigresSVALocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		   }
		   return actEJB;
	   }
}
