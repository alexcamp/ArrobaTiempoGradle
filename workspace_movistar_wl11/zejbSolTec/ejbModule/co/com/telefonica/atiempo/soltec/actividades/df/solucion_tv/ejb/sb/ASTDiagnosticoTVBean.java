package co.com.telefonica.atiempo.soltec.actividades.df.solucion_tv.ejb.sb;

import java.util.Collection;

import javax.ejb.FinderException;
import javax.naming.NamingException; 

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.reglas.ReglasDelegate;
import co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTDiagnosticoTV
 */
public class ASTDiagnosticoTVBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Diagnostico TV");
		try{
			if(act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv)){
				if(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv) == null || act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv).trim().equals("")){
					//Es la primera vez del diagnostico, por lo tanto hago el ruteo.
					long regla=0;
					ReglasInterfaces rI = new ReglasDelegate();
					regla = rI.getRegla(act.getNumeroPeticion());
					log.debug("Regla:" + regla);
					
					boolean tieneEquipos = validarEquiposRegistrados(act.getNumeroPeticion());
					//si cumple esta regla, se rutea y se cierra la actividad
					if(regla == ComunInterfaces.codigoReglaPlantaExterna && tieneEquipos){
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv, ""+ComunInterfaces.idActividadPETV); // La envio a bloqueo y despues a anulacion cuando se desbloquea
						act.setObservacion("Se redirige a Planta Externa Automáticamente");
						act.setRealizarTerminoInmediato(true);	
					}
					//Si no es una de esas reglas... se queda en diagnostico	
				}
			}
		}catch(Exception e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en actividad Diagnostico BA");
		}
		log.debug("Fin Diagnostico TV");
	}

	/**
	 * @param numeroPeticion
	 * @return
	 */
	private boolean validarEquiposRegistrados(Long numeroPeticion) {
		try {
			// TODO Apéndice de método generado automáticamente
			Deco_tarjetaLocalHome decoTarjetaHome = (Deco_tarjetaLocalHome) HomeFactory.getHome(Deco_tarjetaLocalHome.JNDI_NAME);
			Collection decos = decoTarjetaHome.findPeticion(numeroPeticion);
			if(decos == null || decos.isEmpty() || decos.size()< 1){
				return false;
			}
			else
				return true;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error instanciando el EJB. "+e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			return false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Este Control de Cambio se volvio atras. Se envia la hora de la ultima solucion ingresada.
//		try{
//			if (act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv)){
//				if (act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv).equals("S")){
//					//si se va a actualizar inventario, es pk ya tiene solucion y es la utlima vez que se ejecuta esta actividad manual
//					IncidentesInterfaces iI = new IncidentesDelegate();
//					iI.ActividadManualMarcaCierreIncidente(act.getNumeroPeticion());
//				}
//			}
//		}catch(Exception e){
//			log.debug("Exception",e);
//			throw new TnProcesoExcepcion("Excepcion al terminar Diagnostico TV",e);
//		}		
	}
}
