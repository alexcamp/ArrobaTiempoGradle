package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: ADesconfigurarRI
 */
public class ADesconfigurarRIBean extends ActividadManualEJB{

	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		PeticionLocalHome peticionLocalHome = null;
		try {
			//AT-2384 - agonz - 01/07/2009
			log.debug("Inicio Actividad Desconfigurar RI");
			PeticionesInterfaces pI = new PeticionesDelegate();
			boolean trasladoConOrigenyDestinoIguales = pI.esTrasladoConIgualOrigenYDestino(act.getNumeroPeticion());
			if (trasladoConOrigenyDestinoIguales) {
				act.setObservacion("No se envia el mensaje de Desconfiguración RI ya que se trata de un traslado con numeros origen y destino iguales ");					
				act.setRealizarTerminoInmediato(true);								
			}else{
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
			}
			log.debug("Fin Actividad Configurar RI");
			
		} catch (NamingException e) {
			log.warn("Error en Actividad Desconfiguracion RI");
			e.printStackTrace();
		}catch (FinderException e1) {
			log.warn("Error en Actividad Desconfiguracion RI");
			e1.printStackTrace();
		} catch (ATiempoAppEx e2) {
			log.warn("Error en Actividad Desconfiguracion RI");
			e2.printStackTrace();
		}
		
	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

}
