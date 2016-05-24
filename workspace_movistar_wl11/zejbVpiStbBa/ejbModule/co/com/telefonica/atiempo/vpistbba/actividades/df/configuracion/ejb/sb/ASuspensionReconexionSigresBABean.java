
package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

/**
 * Bean implementation class for Enterprise Bean: ASuspensionReconexionSigresBABean
 */
public class ASuspensionReconexionSigresBABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{
	/**
	 * onInicioActividadVPI
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		RecursosBADelegate delegate;
		try {
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				Iterator it_ps = act.getPsOk().iterator();
				delegate = new RecursosBADelegate();
				delegate.enviaSuspensionReconexionBASigres(act.getNumeroPeticion(), act.getCodigoActividad(), (PsVsOcVO)it_ps.next());
			}else{
				act.setObservacion("Reversa Suspension - Reconexion.");
				act.setRealizarTerminoInmediato(true);	
			}
		} catch (ATiempoAppEx e) {
			log.debug("Error en actividad Suspension Reconexion Sigres BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Suspension Reconexion Sigres BA Bean");
		}
		log.debug("Fin Actividad Suspension Reconexion Sigres BA [" + act.getNumeroPeticion() + "]");	
	}

	/**
	 * Estos métodos no se usan en este caso.
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	}

}
