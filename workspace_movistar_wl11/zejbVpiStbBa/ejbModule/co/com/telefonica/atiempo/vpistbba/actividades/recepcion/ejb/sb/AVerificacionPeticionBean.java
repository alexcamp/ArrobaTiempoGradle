package co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AVerificacionPeticion
 */
public class AVerificacionPeticionBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	 
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Verificar Peticion [" + act.getNumeroPeticion() + "]");

		act.setDato(DATOS_ATVPISTBBA.RECEPCION.recep_es_cancela, "N"); //  
		
		//Verificar si es una baja de BA con traslado. Si es así y no existe el Alta, entonces bloquear la baja.
		
		try
		{
			PeticionesDelegate delegate=new PeticionesDelegate();
			
			int tipoTrasladoSoloBa=delegate.tipoTrasladoSoloBa(act.getNumeroPeticion());
			
			log.debug("Tipo Traslado Solo BA:"+tipoTrasladoSoloBa);
			switch(tipoTrasladoSoloBa)
			{
				case 0:
					act.setDato(DATOS_ATVPISTBBA.ini_es_bloqueante, "N");
					break;
				case 1:
					log.debug("Verificacion de Peticion para Peticion de Traslado solo BA Alta");
				
					//CR26362 - adocarmo - inicio
					if (!delegate.existeBajaAsociada(act.getNumeroPeticion()) || delegate.existeBajaConIgualNro(act.getNumeroPeticion())) {
						act.setDato(DATOS_ATVPISTBBA.ini_es_bloqueante, "S");
					}else {
						act.setDato(DATOS_ATVPISTBBA.ini_es_bloqueante, "N");
					}					
					//CR26362 - adocarmo - fin					
					break;
				case 2:
					//Es la baja de traslado solo BA
					log.debug("Verificacion de Peticion para Peticion de Traslado solo BA Baja");
					Integer estaCerradaElAlta=delegate.estaCerradaElAlta(act.getNumeroPeticion());
					
					//ahora nunca entra ya que el alta se bloquea esperando la baja
					if(estaCerradaElAlta.intValue()==1)
					{
						log.debug("Está cerrada el Alta NO debo BLoquear la Peticion "+act.getNumeroPeticion());
						act.setDato(DATOS_ATVPISTBBA.ini_es_bloqueante, "N");
					}
					
					//ahora nunca entra ya que el alta se bloquea esperando la baja
					else if(estaCerradaElAlta.intValue()==2)
					{
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza,"3");	
						log.debug("El alta se cerro con quiebre completo se anula la baja.");
					}
					
					// siempre entra aqui y el alta esta esperando en la actividad EsperaDesbloqueo
					else if(estaCerradaElAlta.intValue()==3)
					{
						act.setDato(DATOS_ATVPISTBBA.ini_es_bloqueante, "S");
						log.debug("No está cerrada el Alta debo BLoquear la Peticion "+act.getNumeroPeticion());
						
						//CR26362 - adocarmo - inicio
						//log.debug(">>>>>>>>>>>>>DESBLOQUEO ALTA");
						
						delegate.desbloqueaAltaTrasladoBa(act.getNumeroPeticion());
						
						//CR26362 - adocarmo - fin						
					}
					break;
				case 3:
					act.setDato(DATOS_ATVPISTBBA.RECEPCION.recep_es_cancela,"S");
					break;
			}
		}
		catch (Exception e)
		{
			log.error("Exception",e);
			throw new TnProcesoExcepcion("Error en Actividad Verificar Peticion",e);
		}

		log.debug("Fin Actividad Verificar Peticion [" + act.getNumeroPeticion() + "]");
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
//		Esta actividad Termina de Inmediato al finalizar su Logica de inicio
		act.setRealizarTerminoInmediato(true);
	}
	
}
