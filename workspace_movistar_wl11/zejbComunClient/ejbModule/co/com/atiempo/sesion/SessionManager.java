package co.com.atiempo.sesion;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author cacano
 * 
 * Realiza la lógica en el manejo de sesiones para las aplicaciones web de
 * atiempo
 */
public class SessionManager {
	private static Object sync = new Object();

	private transient Logger log = LoggerFactory.getLogger(getClass());

	private DataSource ds;

	/**
	 * Constructor de la clase
	 * 
	 * @param ds
	 *            Datasource origen atiempo para las consultas de sesión
	 */
	public SessionManager(DataSource ds) {
		this.ds = ds;
	}

	public void addSession(String login) throws SQLException {
		synchronized (sync) {
			if (!SessionManagerDAO.existSession(this.ds, login)) {
				SessionManagerDAO.addSession(this.ds, login);
			}
		}
	}

	public void removeSession(String login) throws SQLException {
		synchronized (sync) {
			if (SessionManagerDAO.existSession(this.ds, login)) {
				SessionManagerDAO.removeSession(this.ds, login);
			}
		}
	}

	public boolean isValidSession(String login, int maxMinutes)
			throws SQLException {
		boolean result = false;
		synchronized (sync) {
			result = SessionManagerDAO.existSession(this.ds, login);
			if (result) {
				long transcurredTimeSession = SessionManagerDAO
						.getTranscurredTimeSession(ds, login);
				long maxSeconds = maxMinutes * 60;
				log.info("Tiempo de sesión transcurrido para login " + login
						+ " es " + transcurredTimeSession
						+ ". Máximo de tiempo disponible " + maxSeconds);
				transcurredTimeSession = transcurredTimeSession == -1 ? (maxSeconds + 1)
						: transcurredTimeSession;
				result = result && (transcurredTimeSession < maxSeconds);
				if (!result) {
					log.info("Sesión con login " + login
							+ " inválida. Removiendo sesión...");
					SessionManagerDAO.removeSession(this.ds, login);
					log.info("Sesión con login" + login
							+ " ha sido removida satisfactoriamente.");
				}
			}
		}
		return result;
	}

	public int removeAllSessions() throws SQLException {
		synchronized (sync) {
			return SessionManagerDAO.removeAllSessions(this.ds);
		}
	}

	public void refreshSession(String login) throws SQLException {
		synchronized (sync) {
			SessionManagerDAO.refreshSesion(this.ds, login);
		}
	}

	public int removeOldSessions(int maxMinutes) throws SQLException {
		int maxSeconds = maxMinutes * 60;
		synchronized (sync) {
			return SessionManagerDAO.removeOldSessions(this.ds, maxSeconds);
		}
	}

	public boolean isEnabledApplication() throws SQLException {
		return SessionManagerDAO.isEnabledApplication(this.ds);
	}
}