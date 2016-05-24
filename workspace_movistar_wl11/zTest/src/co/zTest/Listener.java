package co.zTest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author cacano
 * 
 * La responsabilidad de esta clase es la ejecuci�n de un sonda que act�a cada
 * cierto tiempo para borrar las sesiones que han estado inactivas durante un
 * determinado umbral de tiempo
 */
public class Listener implements ServletContextListener {


	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener.contextInitialized() ::: "+arg0);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

}