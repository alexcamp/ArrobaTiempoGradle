/*
 * Created on Mar 28, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb.ADesconfigurarModemLocalHome;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ADesconfigurarModemFactory implements IActividadFactoryEJBService {

	public ADesconfigurarModemFactory(){
		super();
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		 IActividadEJB actEJB=null;	
		 try{
		 	ADesconfigurarModemLocalHome ejbHome = (ADesconfigurarModemLocalHome) HomeFactory.getHome(ADesconfigurarModemLocalHome.JNDI_NAME);
		 	actEJB = (IActividadEJB) ejbHome.create();
		 }catch(CreateException ce){
		 	throw new TnProcesoExcepcion(ce.getClass().getName() + " : EL EJB "+ADesconfigurarModemLocalHome.JNDI_NAME + " no es posible levantarlo "+ce.getMessage() );
		 } catch (NamingException e) {
		 	throw new TnProcesoExcepcion(e.getClass().getName() + ": EL EJB "+ADesconfigurarModemLocalHome.JNDI_NAME + " no es posible levantarlo "+e.getMessage() );
		}
		 return actEJB;
	}
}
