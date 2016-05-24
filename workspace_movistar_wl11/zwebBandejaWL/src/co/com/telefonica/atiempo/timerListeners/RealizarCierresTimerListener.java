package co.com.telefonica.atiempo.timerListeners;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

import commonj.timers.Timer;
import commonj.timers.TimerListener;

/**
 * @author
 * 
 * Esta clase tiene como responsabilidad la ejecución de un proceso en un timer
 * para la baja automática del servicio de velocidad adicional temporal VAT
 */
public class RealizarCierresTimerListener implements TimerListener {

	private transient Logger log = LoggerFactory.getLogger(getClass());

	/*
	 * (sin Javadoc)
	 * 
	 * @see commonj.timers.TimerListener#timerExpired(commonj.timers.Timer)
	 */
	public void timerExpired(Timer timer) {
		ejecutarValidacionCierres();
	}

	private void ejecutarValidacionCierres() {
		Thread aux = new Thread(new Runnable() {

			public void run() {
				log.debug("Tarea iniciada bajaAutomaticaVAB");
				try {
					Long idAplicacionVPI= new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
					ActividadLocalHome actHome = (ActividadLocalHome) HomeFactory.getHome (ActividadLocalHome.JNDI_NAME) ;
					ActividadLocal actLocal = actHome.findByCodigoActividadIdAplicacion(ComunInterfaces.ACT_TMP_VELOCIDAD_ADICIONAL,idAplicacionVPI);
					ActividadKey actKey = (ActividadKey) actLocal.getPrimaryKey();
					
					Long idActVelTMP = actKey.act_id;
					
					log.debug("Se validan peticiones para actividad nùmero: "+idActVelTMP);
					
					RecursosBADelegate recursosDelegate = new RecursosBADelegate();
					Bitacora_peticionLocalHome bitacotaHome = (Bitacora_peticionLocalHome) HomeFactory.getHome (Bitacora_peticionLocalHome.JNDI_NAME) ;
					Collection bitacoraArray = bitacotaHome.findByOpenAct(idActVelTMP);
					for(Iterator iter = bitacoraArray.iterator();iter.hasNext(); ){
						Bitacora_peticionLocal bitacoralocal = (Bitacora_peticionLocal)iter.next();
						PeticionLocal petiLocal = bitacoralocal.getFk_peticion();
						PeticionKey petiKey = (PeticionKey)petiLocal.getPrimaryKey();
						log.debug("Se se verifica estado de la peticiòn nùmero: "+petiKey.peti_numero);
						if(petiLocal.getEspe_id().intValue() == ComunInterfaces.estadoEspera){
							log.debug("Peticion es estado en espera");
							ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
							IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actLocal.getAct_codigo());
							ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(petiKey.peti_numero, actLocal.getAct_codigo());
							petiLocal.setEspe_id(new Integer(ComunInterfaces.estadoPeticionEnCurso));
							log.debug("Se envìa a validar la peticiòn");
							recursosDelegate.ejecutarVelocidadAdicionalTMP(actDto);
						}
					}
					
				} catch (Exception e) {
					log.error(e);
				} finally {
					log.debug("Tarea terminada");
				}
			}
			

		}, "bajaAutomaticaVAT");
		
		aux.start();
	}

}