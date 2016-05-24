package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ADesconfigurarSTB
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ADesconfigurarSTBBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB
	implements javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Colocar metodo para identificar si es baja por traslado y colocar los datos de central, localidad y departamento correspondiente
		try
		{
			PeticionesInterfaces pI = new PeticionesDelegate();
			PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal=peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			Collection recursosLinea =  peticionLocal.getRecursos_linea_basica();
			Iterator list = recursosLinea.iterator();
			Recursos_linea_basicaLocal recursosLineaLocal = (Recursos_linea_basicaLocal) list.next();
			boolean resultado=peticionLocal.setDatosPeticionBajaXTraslado();
			log.debug("Resultado del Seteo de los datos de Baja por Traslado:"+resultado);
		} catch (NamingException e)
		{
			log.debug("NamingException",e);
			throw new TnProcesoExcepcion(e.getMessage(),e);
		} catch (FinderException e) {
			log.debug("FinderException",e);
			throw new TnProcesoExcepcion(e.getMessage(),e);
		} catch (Exception e) {
			log.debug("Exception",e);
			throw new TnProcesoExcepcion(e.getMessage(),e);
		}
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}