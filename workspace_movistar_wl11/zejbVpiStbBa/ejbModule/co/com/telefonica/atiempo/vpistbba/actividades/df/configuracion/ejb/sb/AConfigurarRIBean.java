package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarRI
 */
public class AConfigurarRIBean extends ActividadManualEJB{

	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		PeticionLocalHome peticionLocalHome = null;
		try {
			peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
				
			if (peticionLocal.getRecursos_linea_basica().size()>0){
					
				for(Iterator iter = peticionLocal.getRecursos_linea_basica().iterator();iter.hasNext(); )
				{	
					recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) iter.next();
				}
				if ( String.valueOf(recursos_linea_basicaLocal.getCod_central()).equals(VpistbbaConfig.getVariable("COD_CENTRAL_UV"))){				
					act.setObservacion("Petición de UV - Se inhibe la actividad.");
					act.setRealizarTerminoInmediato(true);
				}
			}else{
				log.warn("No se encontro código central. Se instancia la actividad");				
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (FinderException e1) {
			e1.printStackTrace();
		}

	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
	
	
}
