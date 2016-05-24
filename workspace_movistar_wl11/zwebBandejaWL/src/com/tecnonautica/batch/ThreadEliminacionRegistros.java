/*
 * Created on Sep 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tecnonautica.batch;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Marco Alarcón "Dono"
 *
 * Clase ejecutora de la lógica para la eliminación de registros en batch
 * Usa patrón Singleton
 */
public class ThreadEliminacionRegistros {

	private static ThreadEliminacionRegistros instancia = null;
	
	protected Timer timer;
	protected Date hora_inicio;
	protected Long periodicidad;
	protected String dataSource;
	protected Collection tablasParams;

	/**
	 * Método getInstance para patrón Singleton. Toma como parámetro un archivo XML
	 * Se puede usar la instancia para detener el thread
	 * @param configXML URL al archivo XML
	 * @return una instancia de ThreadEliminacionRegistros iniciada (la verdad es que no sirve de mucho la instancia, sino el thread por debajo)
	 */
	public static ThreadEliminacionRegistros getInstance(URL configXML) throws ThreadEliminacionRegistrosException {
		if (instancia == null) {
			instancia = new ThreadEliminacionRegistros(configXML);
		}
		return instancia;
	}

	/**
	 * Constructor que toma como parámetro un archivo XML de configuración, y lo "parsea" 
	 * @param configXML URL al archivo XML
	 */
	protected ThreadEliminacionRegistros(URL configXML) throws ThreadEliminacionRegistrosException {
		DefinicionDeTablas defTablas = new DefinicionDeTablas(configXML);
		
		periodicidad = new Long(defTablas.getPeriodicidad());
		
		dataSource = defTablas.getDatasource();
		
		tablasParams = defTablas.getTablasParams();
		
		String hora = defTablas.getHora_inicio();
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
		GregorianCalendar calendar = new GregorianCalendar();
		GregorianCalendar nowCalendar = new GregorianCalendar();
		try {
			hora_inicio = parser.parse(hora);
		} catch (ParseException e) {
			throw new ThreadEliminacionRegistrosException(e.toString());
		}
		calendar.setTime(hora_inicio);
		calendar.set(GregorianCalendar.YEAR, nowCalendar.get(GregorianCalendar.YEAR));
		calendar.set(GregorianCalendar.MONTH, nowCalendar.get(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DATE, nowCalendar.get(GregorianCalendar.DATE));
		if (calendar.getTime().getTime() <= (new Date().getTime() + 5000)) {
			calendar.add(GregorianCalendar.DATE, 1);
		}
		hora_inicio = calendar.getTime();
		
		iniciaThread();
	}

	private void iniciaThread() {
		timer = new Timer(true);
		timer.scheduleAtFixedRate(new ThreadEliminacionRegistrosSheduler(), hora_inicio, periodicidad.longValue());
	}
	
	/**
	 * Método que detiene la instancia del Thread
	 */
	public void stopThread() {
		timer.cancel();
	}
	
	/**
	 * @author Marco Alarcón "Dono"
	 * Clase privada anidada que realiza el trabajo real de eliminación de registros en batch
	 */
	private class ThreadEliminacionRegistrosSheduler extends TimerTask {
	
		/**
		 * Obtencion de logger.
		 * @author amartoq
		 */
		protected transient Logger log = LoggerFactory.getLogger(getClass());
		
		public void run() {
			DataSource ds = null;
			UserTransaction ut = null;
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup(dataSource);
				ut = (UserTransaction) ctx.lookup("jta/usertransaction");
			} catch (NamingException e) {
				log.fatal(e.toString());
				return;
			}
			
			for (Iterator it = tablasParams.iterator(); it.hasNext();) {
				try {
					String[] params = (String[]) it.next();
					
					long duracion = new Long(params[DefinicionDeTablas.INDEX_DURACION]).longValue();
					
					Object fecha_expiracion = null;
					if (params[DefinicionDeTablas.INDEX_TIPO_CAMPO].equals("TIMESTAMP")) {
						fecha_expiracion = new Timestamp(new Date().getTime() - duracion);
					}
					if (params[DefinicionDeTablas.INDEX_TIPO_CAMPO].equals("DATE")) {
						fecha_expiracion = new java.sql.Date(new Date().getTime() - duracion);
					}
					
					String sqlStatement = "DELETE FROM " + params[DefinicionDeTablas.INDEX_NOMBRE_TABLA] + " WHERE " + params[DefinicionDeTablas.INDEX_NOMBRE_CAMPO] + " < '" + fecha_expiracion.toString() + "'";
					
					if (params[DefinicionDeTablas.INDEX_NOMBRE_ESTADO] != null) {
						if (!params[DefinicionDeTablas.INDEX_NOMBRE_ESTADO].trim().equals("")) {
							Object valor_estado = null;
							if (params[DefinicionDeTablas.INDEX_TIPO_ESTADO].equals("SMALLINT")) {
								valor_estado = new Short(params[DefinicionDeTablas.INDEX_VALOR_ESTADO]);
							}
							if (params[DefinicionDeTablas.INDEX_TIPO_ESTADO].equals("INTEGER")) {
								valor_estado = new Integer(params[DefinicionDeTablas.INDEX_VALOR_ESTADO]);
							}
							if (params[DefinicionDeTablas.INDEX_TIPO_ESTADO].equals("BIGINT")) {
								valor_estado = new Long(params[DefinicionDeTablas.INDEX_VALOR_ESTADO]);
							}
							
							sqlStatement += " AND " + params[DefinicionDeTablas.INDEX_NOMBRE_ESTADO] + " = " + valor_estado.toString();
						}
					}
					
					sqlStatement += ";";
					log.debug("Ejecutando comando SQL de eliminación:");
					log.debug(sqlStatement);
					
					ut.begin();
					Connection oConn = ds.getConnection();
					oConn.setAutoCommit(false);

					//Gustavo - CR 16440 - Cambio comandoSQL de Statement a PreparedStatement
					PreparedStatement comandoSQL = null;
					//Gustavo - CR 16440 - Ejecuto comandoSQL como PreparedStatement
					oConn.prepareStatement(sqlStatement);
					int rows = comandoSQL.executeUpdate(sqlStatement);
					
					log.debug("Comando SQL de eliminación ejecutado: " + rows + " registros eliminados");
					
					comandoSQL.close();
					oConn.close();
					ut.commit();
				} catch (Exception e) {
					log.error(e.toString());
					try {
						ut.rollback();
					} catch (Exception e1) {
						log.warn(e1);
					}
				}
			}
		}
	}

}
