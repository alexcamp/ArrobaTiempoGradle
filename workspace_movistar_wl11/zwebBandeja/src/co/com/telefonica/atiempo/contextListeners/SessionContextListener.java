package co.com.telefonica.atiempo.contextListeners;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import co.com.atiempo.sesion.SessionManager;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author cacano
 * 
 * La responsabilidad de esta clase es la ejecuci�n de un sonda que act�a cada
 * cierto tiempo para borrar las sesiones que han estado inactivas durante un
 * determinado umbral de tiempo
 */
public class SessionContextListener implements ServletContextListener {
	private Thread threadOldSessions = null;

	private volatile static boolean stop = false;

	private volatile static boolean stopped = false;

	private transient Logger log = LoggerFactory.getLogger(getClass());

	private SessionManager session = null;

	private SessionManager getSessionManager() throws NamingException {
		if (session == null) {
			session = new SessionManager(getDataSource());
		}
		return session;
	}

	private DataSource getDataSource() throws NamingException {
		DataSource ds = null;
		return (DataSource) new InitialContext().lookup("jdbc/vpistbba");
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("SessionContextListener.contextInitialized() ::: LISTENER!!!");
		
		final Integer maxTimeSessionMinutes = Integer.valueOf(arg0
				.getServletContext().getInitParameter("MaxTimeSessionMinutes"));
		this.threadOldSessions = new Thread(new Runnable() {
			public void run() {
				while (!stop) {
					try {
						int count = getSessionManager().removeOldSessions(
								maxTimeSessionMinutes.intValue());
						Thread.sleep(30000);
					} catch (Exception e) {
						log.error("Error en base de datos. " + e.getMessage());
						e.printStackTrace();
					}
				}
				stopped = true;
			}
		});
		log.info("Intentando iniciar OldSessionsRemover.");
		this.threadOldSessions.setName("OldSessionsRemover");
		log.info("Sonda OldSessionsRemover en ejecuci�n...");
		this.threadOldSessions.start();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		stop = true;
		log.info("Deteniendo sonda OldSessionsRemover...");
		while (!stopped) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				log.error("SessionContextListener.contextDestroyed. "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		log.info("Sonda OldSessionsRemover detenida satisfactoriamente.");
	}

}