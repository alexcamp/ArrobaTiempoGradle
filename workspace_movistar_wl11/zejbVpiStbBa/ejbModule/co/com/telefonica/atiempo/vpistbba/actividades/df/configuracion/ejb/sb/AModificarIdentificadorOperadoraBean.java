package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: modificarIdentificadorOperadora
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class AModificarIdentificadorOperadoraBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {

	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	 
//	 cr16290 - pcawen
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		RecursosBADelegate recursosBADelegate;
		try
		{
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Inicio Actividad Modificar Identificador Operadora BA [" + act.getNumeroPeticion() + "]");
				recursosBADelegate = new RecursosBADelegate();
				if(recursosBADelegate.envioCambioNroSigres(act)){
					
					Iterator iterTemp = act.getPsOk().iterator();
					PsVsOcVO psTemp=null;
					while(iterTemp.hasNext()) {
						psTemp= (PsVsOcVO) iterTemp.next();
					}
					
					recursosBADelegate.enviaNotificacionCambioNumero(act.getNumeroPeticion(),act.getCodigoActividad(),psTemp);
					
					act.setObservacion("Se envia el mensaje de Modificar Identificador Operadora.");	
				}else{
					act.setObservacion("No corresponde Modificar Identificador Operadora; No se envia el mensaje.");
					act.setRealizarTerminoInmediato(true);
				}
				log.debug("Fin Actividad Modificar Identificador Operadora BA [" + act.getNumeroPeticion() + "]");
			}else{
				log.debug("Inicio Actividad Reversa Modificar Identificador Operadora BA [" + act.getNumeroPeticion() + "]");
				act.setObservacion("No esta definido que hacer en la reversa. Se cierra la actividad.");
				act.setRealizarTerminoInmediato(true);	 
			}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Modificar Identificador Operadora BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Modificar Identificador Operadora BA", e);
		} 
	}
//	cr16290 - Fin - pcawen

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}
}
