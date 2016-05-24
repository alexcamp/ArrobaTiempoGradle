/*
 * Created on 01-mar-07
 */
package co.com.telefonica.atiempo.soltec.actividades.factory.recepcion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb.ASTVerificacionPeticionLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class ASTVerificacionPeticionFactory implements IActividadFactoryEJBService{
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		IActividadEJB actEJB=null;
		try {
			ASTVerificacionPeticionLocalHome ejbHome= (ASTVerificacionPeticionLocalHome)HomeFactory.getHome(ASTVerificacionPeticionLocalHome.JNDI_NAME);
			actEJB=ejbHome.create();
		} catch (CreateException e) {
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTVerificacionPeticionLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + ASTVerificacionPeticionLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}



}
