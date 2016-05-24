package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.inventario.InventarioDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AActualizarInventarioSS
 */
public class AActualizarInventarioSSBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Actualizar Inventario SS [" + act.getNumeroPeticion() + "]");

		//Logica LCA
		try
		{
			
//			  CR-14657 Granite - adocarmo - inicio			
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
		   	
			if(esGranite){
				act.setGrabaEnBitacora(false); // creo que a este seteo no se le da corte, pero la idea es que no imprima en bitacora si no es granite
				// Si la peticion es de Granite no hace nada (termina sin grabar en bitacora)
				act.setRealizarTerminoInmediato(true);		   						
			}
			else {
				InventarioDelegate inventarioDelegate=new InventarioDelegate();
				inventarioDelegate.enviarInventarioSS(act.getNumeroPeticion().longValue(),act.getCodigoActividad(),act.getIdActividadFlujo());
			}	
//			  CR-14657 Granite - adocarmo - fin			
		}
		catch (ATiempoAppEx e)
		{
			e.printStackTrace();
			log.warn("Error en Actividad Actualizacion Inventario SS",e);
			throw new TnProcesoExcepcion("Error en Actividad Actualizacion Inventario SS", e);
		}

		log.debug("Fin Actividad Actualizar Inventario SS [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
