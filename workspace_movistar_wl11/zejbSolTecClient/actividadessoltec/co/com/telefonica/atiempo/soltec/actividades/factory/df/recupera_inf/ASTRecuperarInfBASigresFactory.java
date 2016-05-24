/*
 * Created on Jun 30, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades.factory.df.recupera_inf;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb.ASTRecuperarInfBASigresLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author 804233
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ASTRecuperarInfBASigresFactory implements IActividadFactoryEJBService {

	   /* (non-Javadoc)
		* @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
		*/
	   public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		   IActividadEJB actEJB=null;
		   try {
			   ASTRecuperarInfBASigresLocalHome ejbHome= (ASTRecuperarInfBASigresLocalHome)HomeFactory.getHome(ASTRecuperarInfBASigresLocalHome.JNDI_NAME);
			   actEJB= ejbHome.create();
		   } catch (CreateException e) {
			   throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTRecuperarInfBASigresLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		   } catch (NamingException e) {
			   // TODO Auto-generated catch block
			   throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTRecuperarInfBASigresLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		   }
		   return actEJB;
	   }
}
