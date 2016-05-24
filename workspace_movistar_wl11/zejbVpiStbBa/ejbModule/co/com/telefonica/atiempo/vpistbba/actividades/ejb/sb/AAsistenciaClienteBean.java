package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AAsistenciaCliente
 */
public class AAsistenciaClienteBean	extends	ActividadAutomaticaEJB implements javax.ejb.SessionBean {
	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setRealizarTerminoInmediato(false);
		act.setGrabarWfInstancia(true);
	}
	
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {		
		log.debug("ESTAMOS EN AASISTENCIA CLIENTE!. Id Actividad: "+act.getIdActividadFlujo());
		
		try{
			RecursosDelegate delegate = new RecursosDelegate();
		 	delegate.enviaMensajeTutorWeb(act.getNumeroPeticion(), act.getCodigoActividad(), false, act);	
		}catch(ATiempoAppEx ex){
			log.debug("Error AAsistenciaClienteBean: ATiempoAppEx:"+ex);
		}
	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
}
