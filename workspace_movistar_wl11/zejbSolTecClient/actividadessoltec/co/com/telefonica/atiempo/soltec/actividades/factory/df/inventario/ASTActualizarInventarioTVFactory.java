/*
 * Created on 20-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades.factory.df.inventario;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb.ASTActualizarInventarioTVLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ASTActualizarInventarioTVFactory
	implements IActividadFactoryEJBService {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB=null;
		try {
			ASTActualizarInventarioTVLocalHome ejbHome= (ASTActualizarInventarioTVLocalHome)HomeFactory.getHome(ASTActualizarInventarioTVLocalHome.JNDI_NAME);
			actEJB=ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTActualizarInventarioTVLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTActualizarInventarioTVLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}

}
