/*
 * Created on 01-mar-07
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades.factory.recepcion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb.ASTGeneraFlujoPeticionLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ASTGeneraFlujoPeticionFactory implements IActividadFactoryEJBService{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.factory.IActividadFactoryEJBService#getActividadEJB()
	 */

	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB=null;
		try {
			ASTGeneraFlujoPeticionLocalHome ejbHome= (ASTGeneraFlujoPeticionLocalHome)HomeFactory.getHome(ASTGeneraFlujoPeticionLocalHome.JNDI_NAME);
			actEJB=ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTGeneraFlujoPeticionLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTGeneraFlujoPeticionLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
