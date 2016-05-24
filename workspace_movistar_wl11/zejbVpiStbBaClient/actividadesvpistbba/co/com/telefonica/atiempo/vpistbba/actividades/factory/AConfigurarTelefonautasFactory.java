/*
 * Created on Aug 15, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory;

//import co.com.telefonica.atiempo.actividades.IActividadEJB;
//import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;

//import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
//-------------------

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AConfigurarTelefonautasLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author 804226
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AConfigurarTelefonautasFactory
	implements IActividadFactoryEJBService {

  /**
   * 
   */
  public AConfigurarTelefonautasFactory() {
	  super();
		
  }

  /* (non-Javadoc)
   * @see co.com.telefonica.atiempo.vpistbba.actividades.factory.IActividadFactoryEJBService#getActividadEJB()
   */
  public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
	  IActividadEJB actEJB=null;		
	  try {
		AConfigurarTelefonautasLocalHome ejbHome= (AConfigurarTelefonautasLocalHome)HomeFactory.getHome(AConfigurarTelefonautasLocalHome.JNDI_NAME);
		  actEJB=(IActividadEJB) ejbHome.create();
	  } catch (CreateException e) {
		  throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AConfigurarTelefonautasLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
	  } catch (NamingException e) {
			
		  throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AConfigurarTelefonautasLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
	  }
	  return actEJB;
  }

}
