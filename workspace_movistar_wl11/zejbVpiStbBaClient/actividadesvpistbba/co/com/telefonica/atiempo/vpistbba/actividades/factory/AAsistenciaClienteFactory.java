/*
 * Creado el 20/04/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.actividades.factory;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService;
import co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb.AAsistenciaClienteLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AAsistenciaClienteFactory implements IActividadFactoryEJBService {

    public AAsistenciaClienteFactory() {
  	  super();  		
    }

        /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.actividades.IActividadFactoryEJBService#getActividadEJB()
     */
    public IActividadEJB getActividadEJB() throws TnProcesoExcepcion {
        IActividadEJB actEJB=null;		
  	  try {
  	  AAsistenciaClienteLocalHome ejbHome= (AAsistenciaClienteLocalHome)HomeFactory.getHome(AAsistenciaClienteLocalHome.JNDI_NAME);
  		  actEJB=(IActividadEJB) ejbHome.create();
  	  } catch (CreateException e) {
  		  throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AAsistenciaClienteLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
  	  } catch (NamingException e) {  			
  		  throw new TnProcesoExcepcion(e.getClass().getName() + " : El EJB " + AAsistenciaClienteLocalHome.JNDI_NAME + " no es posible levantarlo" + e.getMessage());
  	  }catch (Exception e) {  			
		  e.printStackTrace();
  	  }
  	  return actEJB;
    }

}
