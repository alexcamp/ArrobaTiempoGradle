package co.com.atiempo.sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author cacano
 * 
 * Clase cuyo fin es realizar consultas nativas a la base de datos de atiempo
 */
class SessionManagerDAO {

	private static Connection getConnection(DataSource ds) throws SQLException {
		return ds.getConnection();
	}

	private static void read(DataSource ds, IInjectorSql injector, String sql)
			throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection(ds);
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs != null && rs.next()) {
				injector.executeCode(rs);
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private static Object readScalar(DataSource ds, String sql)
			throws SQLException {
		final List buf = new ArrayList();
		IInjectorSql injector = new IInjectorSql() {
			public void executeCode(ResultSet rs) throws SQLException {
				buf.add(0, rs.getObject(1));
			}
		};
		read(ds, injector, sql);
		return buf == null ? buf : buf.get(0);
	}

	private static int executeUpdateStatemet(DataSource ds, String sql)
			throws SQLException {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection(ds);
			PreparedStatement statement = conn.prepareStatement(sql);
			result = statement.executeUpdate();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}

	private interface IInjectorSql {
		public void executeCode(ResultSet rs) throws SQLException;
	}

	static boolean existSession(DataSource ds, String login)
			throws SQLException {
		boolean result = false;
		String sql = "SELECT COUNT(LOGIN) FROM ATIEMPO.SESION_USUARIO WHERE LOGIN = '"
				+ login + "'";
		try {
			Integer count = Integer.valueOf(readScalar(ds, sql).toString());
			result = count.intValue() == 0 ? false : true;
		} catch (NumberFormatException ex) {
			throw new RuntimeException(ex);
		}
		return result;
	}

	static void removeSession(DataSource ds, String login) throws SQLException {
		String sql = "DELETE FROM ATIEMPO.SESION_USUARIO WHERE LOGIN = '"
				+ login + "'";
		int result = executeUpdateStatemet(ds, sql);
		if (result != 1) {
			throw new RuntimeException(
					"Error en el borrado de la sesión. Registros borrados: "
							+ result);
		}
	}

	static void refreshSesion(DataSource ds, String login) throws SQLException {
		String sql = "UPDATE ATIEMPO.SESION_USUARIO SET INICIO_SESION = CURRENT_TIMESTAMP WHERE LOGIN = '"
				+ login + "'";
		int result = executeUpdateStatemet(ds, sql);
		if (result != 1) {
			throw new RuntimeException(
					"Error en la actualización de datos. Registros actualizados: "
							+ result);
		}
	}

	static void addSession(DataSource ds, String login) throws SQLException {
		String sql = "INSERT INTO ATIEMPO.SESION_USUARIO (LOGIN,INICIO_SESION) VALUES('"
				+ login + "', CURRENT_TIMESTAMP)";
		int result = executeUpdateStatemet(ds, sql);
		if (result != 1) {
			throw new RuntimeException(
					"Error en la creación de la sesión. Registros creados: "
							+ result);
		}
	}

	static int removeAllSessions(DataSource ds) throws SQLException {
		String sql = "DELETE FROM ATIEMPO.SESION_USUARIO";
		return executeUpdateStatemet(ds, sql);
	}

	static Timestamp getStartSession(DataSource ds, String login)
			throws SQLException {
		String sql = "SELECT INICIO_SESION FROM ATIEMPO.SESION_USUARIO WHERE LOGIN = '"
				+ login + "'";
		return (Timestamp) readScalar(ds, sql);
	}

	static int removeOldSessions(DataSource ds, int maxSeconds)
			throws SQLException {
		String sql = "DELETE FROM ATIEMPO.SESION_USUARIO WHERE ((DAYS(current_timestamp) - DAYS(INICIO_SESION)) * 86400 + (MIDNIGHT_SECONDS(current_timestamp) - MIDNIGHT_SECONDS(INICIO_SESION))) > "
				+ maxSeconds;
		return executeUpdateStatemet(ds, sql);
	}

	static long getTranscurredTimeSession(DataSource ds, String login)
			throws SQLException {
		String sql = "select ((DAYS(current_timestamp) - DAYS(INICIO_SESION)) * 86400 + (MIDNIGHT_SECONDS(current_timestamp) - MIDNIGHT_SECONDS(INICIO_SESION))) FROM ATIEMPO.SESION_USUARIO WHERE LOGIN = '"
				+ login + "'";
		Object result = readScalar(ds, sql);
		Long returnLong = result == null ? Long.valueOf("-1") : Long
				.valueOf(result.toString());
		return returnLong.longValue();
	}

	static boolean isEnabledApplication(DataSource ds) throws SQLException {
		String sql = "SELECT CAST(VALOR AS CHAR) FROM ATIEMPO.PROPERTIES_BD WHERE CODIGO = 'VULNERABILIDADES'";
		Object result = readScalar(ds, sql);
		System.out.print("Valor vulnerabilidades " + result);
		return (result != null && result.toString().equals("1")) ? true : false;
	}
}