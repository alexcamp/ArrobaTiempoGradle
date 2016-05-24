package co.com.telefonica.atiempo.timerListeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.vpistbba.bitacora.dto.BitacoraDTO;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocal;
import com.telefonica_chile.vpistbba.bitacora.session.BitacoraLocalHome;
import commonj.timers.Timer;
import commonj.timers.TimerListener;

/**
 * @author cacano
 * 
 * Esta clase tiene como responsabilidad la ejecuci�n de una tarea en un timer
 * para el cierre de peticiones autom�ticas
 */
public class CerrarPeticionesTimerListener implements TimerListener {

	private transient Logger log = LoggerFactory.getLogger(getClass());

	/*
	 * (sin Javadoc)
	 * 
	 * @see commonj.timers.TimerListener#timerExpired(commonj.timers.Timer)
	 */
	public void timerExpired(Timer timer) {
		ejecutarTareaCerrarPeticiones();
	}

	private void ejecutarTareaCerrarPeticiones() {
		Thread aux = new Thread(new Runnable() {

			public void run() {
				log.debug("Tarea iniciada");
				try {


					BitacoraLocalHome bHome = (BitacoraLocalHome) HomeFactory.getHome(BitacoraLocalHome.JNDI_NAME);
					Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
					DecoModemPeticionLocalHome dmpHome = (DecoModemPeticionLocalHome) HomeFactory.getHome (DecoModemPeticionLocalHome.JNDI_NAME) ;
					PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
					String ciclo[] = propertiesBDLocalHome.findByPrimaryKey("CICLOS_CIERRE_PETI_AUTO_RE").getValor().split(",");
					DateFormat fechaInicio = new SimpleDateFormat("yyyyMMdd");
					//fechaInicio.parse(propertiesBDLocalHome.findByPrimaryKey("CICLOS_CIERRE_PETI_AUTO_RE").getValor());
					DateFormat fechaFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date diaActual = new Date();
					int diferenciasDias = 0;
					
					for(int i =0;i<ciclo.length;i++){
						Collection peticiones = dmpHome.findByCiclo(new Long(ciclo[i]),new Long(1));
						BitacoraLocal bLocal = bHome.create();
						for(Iterator iter = peticiones.iterator();iter.hasNext();){
							DecoModemPeticionLocal decoModemPeticionLocal = (DecoModemPeticionLocal) iter.next();
							ArrayList listadoBitacora = bLocal.getListadoBitacora(new Long(decoModemPeticionLocal.getPeticion()));
							BitacoraDTO bitacoraDto = null;
							for (int j=0; listadoBitacora!=null && j<listadoBitacora.size(); j++) {
								BitacoraDTO bDto =(BitacoraDTO) listadoBitacora.get(j);
								if ( bDto.getFechaFin() == null && bDto.getIdActividad() == decoModemPeticionLocal.getId_act())
									bitacoraDto = bDto;
							}
							if(bitacoraDto != null){
								fechaInicio.parse(bitacoraDto.getFechaInicio().toString());
								diferenciasDias = calcularFecha(fechaInicio,fechaFinal);
								ActividadLocal actividaLocal;
								   ActividadLocalHome actividaLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
								    log.debug("Obteniendo Peticiones de DECOMODEM_PETICION");
								    
								if(diferenciasDias > 59){
									PeticionLocal peticion = peticionLocalHome.findByPrimaryKey(new PeticionKey (new Long(decoModemPeticionLocal.getPeticion())));
									ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
						 		 	 actividaLocal= actividaLocalHome.findByPK(decoModemPeticionLocal.getId_act()); 
						 		 	 
						 		 	//IActividadEJB actividadEJB =  actividadFactoryEJB.getActividad(actividaLocal.getAct_codigo());
						 		 	IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actividaLocal.getAct_codigo());
						 		 	ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(new Long(decoModemPeticionLocal.getPeticion()), bitacoraDto.getCodigoActividad());
						 		 	actDto.setObservacion("Se terimina actividad porque la petci�n tiene mas de 30 dias.",true);
						 		 	actDto.setRealizarTerminoInmediato(true);
						 			peticion.setControl_rec_eq("S");
						 			decoModemPeticionLocal.setEstadopet(new Long (2));
						 		    actividadEJB.terminar(actDto);
								}
							}
						}
						
					}
					
				} catch (Exception e) {
					log.error(e);
				} finally {
					log.debug("Tarea terminada");
				}
			}

			private int calcularFecha(DateFormat fechaInicio, DateFormat fechaFinal) {
				// TODO Ap�ndice de m�todo generado autom�ticamente
				Calendar c = Calendar.getInstance();
				Calendar fechaInicial = new GregorianCalendar();
				fechaInicial = fechaInicio.getCalendar();
				Calendar fechaFin = new GregorianCalendar();
				fechaFin = fechaFinal.getCalendar();
				c.setTimeInMillis(fechaFin.getTime().getTime() - fechaInicial.getTime().getTime());
				return c.DAY_OF_YEAR;
			}

		}, "CierrePeticiones");
		aux.start();
		
	}

}