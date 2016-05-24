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
import co.com.telefonica.atiempo.timerListeners.CerrarPeticionesTimerListener;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import commonj.timers.TimerListener;
import commonj.timers.TimerManager;

/**
 * @author cacano
 * 
 * La responsabilidad de esta clase es inicializar y terminar los timers que
 * están en la aplicación cuando se suba y baje el contexto respectivamente
 */
public class TimersContextListener implements ServletContextListener {

	private transient Logger log = LoggerFactory.getLogger(getClass());

	private static final String TIMER_PETICIONES = "java:comp/env/tm/TimersVPI";

	private TimerManager tmCerrarPeticiones = null;

	/**
	 * Obtiene el timermanager para los procesos de temporización
	 * @return ekl timermanager
	 * @throws NamingException Excepción de naming
	 */
	private TimerManager getTimerManagerCerrarPeticiones() throws NamingException {
		if (this.tmCerrarPeticiones == null) {
			InitialContext ic = new InitialContext();
			this.tmCerrarPeticiones = (TimerManager) ic.lookup(TIMER_PETICIONES);
		} 
		return this.tmCerrarPeticiones;
	}
	
	/**
	 * Obtiene valores de la tabla properties_bd del esquema atiempo
	 * @param codigo Código del registro
	 * @return El valor sí el código ingresado es válido
	 * @throws NamingException 
	 * @throws FinderException 
	 */
	private String getValorPropertiesBD(String codigo) throws NamingException, FinderException{
		Properties_BDLocalHome propertiesLH = (Properties_BDLocalHome)HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
		Properties_BDLocal propertiesLocal =  propertiesLH.findByPrimaryKey(codigo);
		return propertiesLocal.getValor();
	}
	
	/**
	 * Inicializa la construcción para la tarea de temporización del cierre
	 * automático de peticiones
	 */
	private void iniciarCierrePeticionesAutomaticas(){
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			TimerListener cerrarPeticionesListener = new CerrarPeticionesTimerListener();
			String inicio = getValorPropertiesBD("INICIO_CIERRE_PETI_AUTO");
			String periodo = getValorPropertiesBD("PERIODO_CIERRE_PETI_AUTO");
			String ejecutaProceso = getValorPropertiesBD("CIERRE_PETI_AUTO");
			if(ejecutaProceso.equals("1")){
				log.info("Iniciando timer cierre de peticiones automáticas...");
				getTimerManagerCerrarPeticiones().schedule(cerrarPeticionesListener,dateFormat.parse(inicio),Long.valueOf(periodo).longValue());
				log.info("Timer de cierre peticiones automáticas iniciado satisfactoriamente.");
			}else{
				log.info("El proceso de cierre de peticiones automáticas se encuentra inactivo. Para activar habilite el valor CIERRE_PETI_AUTO en 1 en la tabla ATIEMPO.PROPERTIES_BD");
			}
		} catch (NumberFormatException e) {
			log.error("Error formateando el valor de PROPERTIES_BD PERIODO_CIERRE_PETI_AUTO. El valor debe estar en milisegundos.");
		} catch (ParseException e) {
			log.error("Error parseando el valor de PROPERTIES_BD INICIO_CIERRE_PETI_AUTO, el valor debe seguir el formato yyyyMMddHHmmss. Ejemplo: 20130101235959 El primero de enero del 2013 en el último segundo del día.");
			log.error(e);
		} catch (Exception e){
			log.error(e);
		}
	}
	
	/**
	 * Detiene el proceso de cierre automático de peticiones
	 *
	 */
	private void pararCierrePeticionesAutomaticas(){
		try {
			String ejecutaProceso = getValorPropertiesBD("CIERRE_PETI_AUTO");
			if(ejecutaProceso.equals("1")){
				log.info("Deteniendo cierre peticiones automáticas...");
				getTimerManagerCerrarPeticiones().stop();
				log.info("Cierre de peticiones automáticas detenido satisfactoriamente.");
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	/*
	 * (Evento que se ejecuta cuando se inicializa el contexto de la aplicación
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		iniciarCierrePeticionesAutomaticas();
	}
	
	/*
	 * Evento que se ejecuta cuando se detiene el contexto de la aplicación
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		pararCierrePeticionesAutomaticas();
	}

}