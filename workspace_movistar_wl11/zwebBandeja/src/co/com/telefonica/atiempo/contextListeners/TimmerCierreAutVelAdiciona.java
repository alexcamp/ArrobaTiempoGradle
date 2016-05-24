package co.com.telefonica.atiempo.contextListeners;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.timerListeners.RealizarCierresTimerListener;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import commonj.timers.TimerListener;
import commonj.timers.TimerManager;

public class TimmerCierreAutVelAdiciona implements ServletContextListener {
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	
	private transient Logger log = LoggerFactory.getLogger(getClass());

	private static final String TIMER_VAB = "java:comp/env/tm/TimerVAT";

	private TimerManager bajaVAB = null;
	

	/**
	 * @return Devuelve bajaVAT.
	 */
	private TimerManager getBajaVAB() throws NamingException {
		if (this.bajaVAB == null) {
			InitialContext ic = new InitialContext();
			this.bajaVAB = (TimerManager) ic.lookup(TIMER_VAB);
		} 
		return this.bajaVAB;
	}
	
	private String getValorPropertiesBD(String codigo) throws NamingException, FinderException{
		Properties_BDLocalHome propertiesLH = (Properties_BDLocalHome)HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
		Properties_BDLocal propertiesLocal =  propertiesLH.findByPrimaryKey(codigo);
		return propertiesLocal.getValor();
	}
	
	private void iniciarProcesoAutVAB(){
		try{
			
			//Se crean variable iniciales para la ejecuci�n del Timer CARM
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			TimerListener cerrarPeticionesListener = new RealizarCierresTimerListener();
			String inicio = getValorPropertiesBD("INICIO_TIMER_VAB");
			String periodo = getValorPropertiesBD("PERIODO_TIMER_VAB");
			String ejecutaProceso = getValorPropertiesBD("ESTADO_TIMMER_VAB");
			
			//Se valida si el proceso se encuentra activo CARM
			if(ejecutaProceso.equals("1")){
				log.debug("Iniciando timer para el proceso VAB...");
				getBajaVAB().schedule(cerrarPeticionesListener,dateFormat.parse(inicio),Long.valueOf(periodo).longValue());
				log.debug("Timer de proceso autom�tico para VAT iniciado satisfactoriamente.");
			}else{
				log.debug("El proceso autom�tico para VAB se encuentra inactivo. Para activar habilite el valor ESTADO_TIMMER_VAB en 1 en la tabla ATIEMPO.PROPERTIES_BD");
			}
		} catch (NumberFormatException e) {
			log.error("Error formateando el valor de PROPERTIES_BD PERIODO_TIMER_VAB. El valor debe estar en milisegundos.");
		} catch (ParseException e) {
			log.error("Error parseando el valor de PROPERTIES_BD INICIO_TIMER_VAB, el valor debe seguir el formato yyyyMMddHHmmss. Ejemplo: 20130101235959 El primero de enero del 2013 en el �ltimo segundo del d�a.");
			log.error(e);
		} catch (Exception e){
			log.error(e);
		}
		
	}
	
	//Proceso para cierre de Timer CARM
	private void pararProcesoAutVAB(){
		try {
			String ejecutaProceso = getValorPropertiesBD("ESTADO_TIMMER_VAB");
			if(ejecutaProceso.equals("1")){
				log.debug("Deteniendo proceso autom�tico para VAB...");
				getBajaVAB().stop();
				log.debug("Proceso autom�tico para VAB detenido satisfactoriamente.");
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("TimmerCierreAutVelAdiciona.contextInitialized() ::: Listenerrr");
		iniciarProcesoAutVAB();
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		pararProcesoAutVAB();
	}

	
}