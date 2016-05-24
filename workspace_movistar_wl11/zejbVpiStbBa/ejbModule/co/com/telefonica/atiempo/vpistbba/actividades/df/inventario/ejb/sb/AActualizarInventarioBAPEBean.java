
package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AActualizarInventarioBAPE
 */
public class AActualizarInventarioBAPEBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Actualizar Inventario BA Planta Externa [" + act.getNumeroPeticion() + "]");
		
		try{
			
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	boolean usaGranite = pI.usaGranite(act.getNumeroPeticion());
		   	boolean soportaConfAutomatica = pI.centralSoportaConfAutomatica(act.getNumeroPeticion());
		   	boolean trasladoConOrigenyDestinoIguales = pI.esTrasladoConIgualOrigenYDestino(act.getNumeroPeticion());
		   	boolean vaAGranite = usaGranite && soportaConfAutomatica;
			
		   	if (usaGranite && soportaConfAutomatica) {
		   		//LCA LOGICA
				RecursosBADelegate recursosBADelegate;
				try{
					recursosBADelegate = new RecursosBADelegate();
					recursosBADelegate.enviaActualizaInventarioBAGranite(act.getNumeroPeticion().toString(),act.getCodigoActividad(),act);						
				} 
				catch (ATiempoAppEx e){
					log.warn("Error en Actividad Actualizar Inventario BA Planta Externa",e);
					throw new TnProcesoExcepcion("Error en Actualizar Inventario BA Planta Externa", e);
				}
		   	}
		   	// si no es granite o es granite pero no soporta conf automatica
		   	else {
		   		act.setGrabaEnBitacora(false); // creo que a este seteo no se le da corte, pero la idea es que no imprima en bitacora si no es granite
		   		// Si la peticion no es de Granite termina y va  aconf manual sin grabar en bitacora
		   		//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
		   		log.debug("Se inhibe la actividad porque es granite");
		   		act.setRealizarTerminoInmediato(true);
		   	}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configuracion Automatica STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurcion Automatica STB", e);
		}

		

		log.debug("Fin Actividad Actualizar Inventario BA Planta Externa[" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	
}
