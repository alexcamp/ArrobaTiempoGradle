/*
 * Created on 03-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.actividades.factory.df.solucion;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb.BandejaProcesoDeBajaLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.df.solucion_averias.ejb.sb.SolucionTecnicaAveriasLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;


/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BandejaProcesoDeBajafactory implements IActividadFactoryEJBService {

	public BandejaProcesoDeBajafactory() {
		super();
	}
	
	
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
	 */
	public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
		
		IActividadEJB actEJB=null;		
		try {
			
			BandejaProcesoDeBajaLocalHome ejbHome= (BandejaProcesoDeBajaLocalHome)HomeFactory.getHome(BandejaProcesoDeBajaLocalHome.JNDI_NAME);
			actEJB=ejbHome.create();
			
		} catch (CreateException e) {
			
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + BandejaProcesoDeBajaLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		} catch (NamingException e) {
			
			throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + SolucionTecnicaAveriasLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
		}
		return actEJB;
	}





}
