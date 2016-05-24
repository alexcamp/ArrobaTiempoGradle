package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ABajaBASigres
 */

/**
 * onInicioActividadVPI
 */
public class ABajaBASigresBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		RecursosBADelegate delegate;
		try {
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				Iterator it_ps = act.getPsOk().iterator();
				delegate = new RecursosBADelegate();
				delegate.enviaBajaBASigres(act.getNumeroPeticion(), act.getCodigoActividad(), (PsVsOcVO)it_ps.next());
			}else{
				act.setObservacion("Reversa Baja.");
				act.setRealizarTerminoInmediato(true);	
			}
		} catch (ATiempoAppEx e) {
			log.debug("Error en actividad Baja Sigres BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Baja Sigres BA Bean");
		}
		log.debug("Fin Actividad Baja Sigres BA [" + act.getNumeroPeticion() + "]");	
	}


	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	}
}