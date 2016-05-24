package com.tecnonautica.utiles.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.ejbutiles.DataSourceFactory;

public class DBManager
{
	private Connection con = null;
	public static final String JDBC_VPISTBBA="jdbc/vpistbba";
	public static final String JDBC_BANDEJA="jdbc/bandeja";
	public static final String JDBC_SOLTEC="jdbc/soltec";
	//public static final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private boolean inTransaction = false;
	private String dataSource = null;

	public void begin() {
		try {
			getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DBException(e);
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
		inTransaction = true;
	}

	public void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			throw new DBException(e);
		}
		inTransaction = false;
		close();
	}

	public void rollback() {
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new DBException(e);
		}
		inTransaction = false;
		close();
	}

	public Hashtable ejecutaConsultaHash(String consulta) {
		Hashtable data = null;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_CONSULTA_HASH);		
		try{		
			traceOp.init(log);		
		  	traceManager.traceOpStart(traceOp);
		  	
		data = new Hashtable();
		//Gustavo - CR 16440 - Cambio Statement stmt PreparedStatement psmtm
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date dateIni = new Date();
		try {
			getConnection();
			//con.setReadOnly(true);
			//Scon.setReadOnly(true);
			//con.setTransactionIsolation(Connection.TRANSACTION_NONE);
			//con.setAutoCommit(false);
			//Gustavo - CR 16440 - Ejecuto pstmt como PreparedStatement
			pstmt = con.prepareStatement(consulta);
			rs = pstmt.executeQuery();
			traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(columnas)); //@Trace
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					String name = rsmd.getColumnName(i);
					if (data.get(name) == null)
						data.put(name, new Vector());
					Vector v = (Vector) data.get(name);
					v.addElement(rs.getObject(i) == null ? "" : "" + rs.getObject(i));
				}
			}
			rs.close();
			pstmt.close();
			close();
			Enumeration e = data.keys();
			while (e.hasMoreElements()) {
				Object aux = e.nextElement();
				Vector v = (Vector) data.get(aux);
				String[] linea = new String[v.size()];
				for (int i = 0; i < v.size(); ++i)
					linea[i] = (String) v.elementAt(i);
				data.put(aux, linea);
			}
		} catch (Exception exc) {
			data = null;
			log.warn("Problema ejecutando consulta " + consulta, exc);

			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
			try { if (rs != null) rs.close(); } catch (Exception e) { }
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return data;
	}

	public Hashtable selectCommit(String consulta) {
		Hashtable data = null;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_SELECT_COMMIT);		
		try{		
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
			
		data = new Hashtable();
		//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date dateIni = new Date();
		try {
			getConnection();
			//Gustavo - CR 16440 - Ejecuto stmt como PreparedStatement
			pstmt = con.prepareStatement(consulta);
			rs = pstmt.executeQuery();
			traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(columnas)); //@Trace
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					String name = rsmd.getColumnName(i);
					if (data.get(name) == null)
						data.put(name, new Vector());
					Vector v = (Vector) data.get(name);
					v.addElement(rs.getObject(i) == null ? "" : "" + rs.getObject(i));
				}
			}
			//con.commit();
			rs.close();
			pstmt.close();
			close();
			Enumeration e = data.keys();
			while (e.hasMoreElements()) {
				Object aux = e.nextElement();
				Vector v = (Vector) data.get(aux);
				String[] linea = new String[v.size()];
				for (int i = 0; i < v.size(); ++i)
					linea[i] = (String) v.elementAt(i);
				data.put(aux, linea);
			}
		} catch (Exception exc) {
			data = null;
			log.warn("Problema ejecutando consulta " + consulta, exc);

			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {log.debug("Stack Trace "+e); }
			try { if (rs != null) rs.close(); } catch (Exception e) { log.debug("Stack Trace "+e); }
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return data;
	}
	
	
	public Hashtable selectCommitPrepared(String consulta)
	{
		Hashtable data = null;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_SELECT_COMMIT_PREPARED);		
		try{		
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
			
		data = new Hashtable();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date dateIni = new Date();
		try {
			getConnection();
			pstmt = con.prepareStatement(consulta);
			traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(columnas)); //@Trace
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					String name = rsmd.getColumnName(i);
					if (data.get(name) == null)
						data.put(name, new Vector());
					Vector v = (Vector) data.get(name);
					v.addElement(rs.getObject(i) == null ? "" : "" + rs.getObject(i));
				}
			}
			//con.commit();
			rs.close();
			pstmt.close();
			close();
			Enumeration e = data.keys();
			while (e.hasMoreElements()) {
				Object aux = e.nextElement();
				Vector v = (Vector) data.get(aux);
				String[] linea = new String[v.size()];
				for (int i = 0; i < v.size(); ++i)
					linea[i] = (String) v.elementAt(i);
				data.put(aux, linea);
			}
		} catch (Exception exc) {
			data = null;
			log.warn("Problema ejecutando consulta " + consulta, exc);

			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return data;
	}

	public Hashtable selectReadUncommitted(String consulta) {
		Hashtable data = null;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_SELECT_READ_UNCOMMIT);		
		try{		
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
			
		data = new Hashtable();
		//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date dateIni = new Date();
		try {
			getConnection();
//			TRANSACTION_NONE=0
//			TRANSACTION_READ_UNCOMMITTED=1
//			TRANSACTION_READ_COMMITTED=2
//			TRANSACTION_REPEATABLE_READ=4
//			TRANSACTION_SERIALIZABLE=8

			//log.debug("Iniciando TRANSACTION_READ_UNCOMMITTED");
			//log.debug("Tipo de transaccion:"+con.getTransactionIsolation());
			con.setReadOnly(true);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			//log.debug("Tipo de transaccion:"+con.getTransactionIsolation());
			con.setAutoCommit(true);
			//Gustavo - CR 16440 - Ejecuto stmt como PreparedStatement
			pstmt = con.prepareStatement(consulta);
			rs = pstmt.executeQuery();
			traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace
			//log.debug("Fin Consulta:"+sdf.format(new Date()));
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(columnas)); //@Trace
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					String name = rsmd.getColumnName(i);
					if (data.get(name) == null)
						data.put(name, new Vector());
					Vector v = (Vector) data.get(name);
					v.addElement(rs.getObject(i) == null ? "" : "" + rs.getObject(i));
				}
			}
//			con.commit();
			rs.close();
			pstmt.close();
			close();
			Enumeration e = data.keys();
			while (e.hasMoreElements()) {
				Object aux = e.nextElement();
				Vector v = (Vector) data.get(aux);
				String[] linea = new String[v.size()];
				for (int i = 0; i < v.size(); ++i)
					linea[i] = (String) v.elementAt(i);
				data.put(aux, linea);
			}
		} catch (Exception exc) {
			data = null;
			log.warn("Problema ejecutando consulta " + consulta, exc);

			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
			try { if (rs != null) rs.close(); } catch (Exception e) { }
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

	return data;
}
	
	// CR17800 - adocarmo - inicio
	public Hashtable selectReadUncommitted(String consulta,ArrayList tipoParametros,ArrayList valorParametros) {
		Hashtable data = null;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_SELECT_READ_UNCOMMIT);		
		try{		
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
			
		data = new Hashtable();
		//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date dateIni = new Date();
			try {
				getConnection();

				con.setReadOnly(true);
				con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

				con.setAutoCommit(true);
				//Gustavo - CR 16440 - Ejecuto stmt como PreparedStatement
				pstmt = con.prepareStatement(consulta);
			
				cargoParametros(pstmt,tipoParametros,valorParametros);			
			
				rs = pstmt.executeQuery();

				traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace

				ResultSetMetaData rsmd = rs.getMetaData();
				int columnas = rsmd.getColumnCount();
				traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(columnas)); //@Trace
				while (rs.next()) {
					for (int i = 1; i <= columnas; i++) {
						String name = rsmd.getColumnName(i);
						if (data.get(name) == null)
							data.put(name, new Vector());
						Vector v = (Vector) data.get(name);
						v.addElement(rs.getObject(i) == null ? "" : "" + rs.getObject(i));
					}
				}
				rs.close();
				pstmt.close();
				close();
				Enumeration e = data.keys();
				while (e.hasMoreElements()) {
					Object aux = e.nextElement();
					Vector v = (Vector) data.get(aux);
					String[] linea = new String[v.size()];
					for (int i = 0; i < v.size(); ++i)
						linea[i] = (String) v.elementAt(i);
					data.put(aux, linea);
				}
			} catch (Exception exc) {
				data = null;
				log.warn("Problema ejecutando consulta " + consulta, exc);

				if (exc instanceof SQLException)
					throw new DBException((SQLException) exc);
				else
					throw new DBException(exc.getMessage());
			} finally {
				try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
				try { if (rs != null) rs.close(); } catch (Exception e) { }
				try { if (con != null) con.close(); } catch (Exception e) { }
			}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return data;
	}
	// CR17800 - adocarmo - fin

	// CR17800 - adocarmo - inicio
	public void cargoParametros(PreparedStatement pStm, ArrayList tipoParametros, ArrayList valorParametros) {
			for (int i=0; i<tipoParametros.size(); i++) {
				try {
					if (tipoParametros.get(i).equals("N")) {
						pStm.setObject(i+1,valorParametros.get(i),java.sql.Types.NUMERIC);
					}
					if (tipoParametros.get(i).equals("S")) {
						pStm.setObject(i+1,valorParametros.get(i),java.sql.Types.VARCHAR);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}									
			}
	}
	// CR17800 - adocarmo - fin

	public int ejecutaUpdate(String consulta) {
		int res;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_UPDATE);		
		try{		
			traceOp.init(log);		
			traceManager.traceOpStart(traceOp);
			
		
		//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt
		PreparedStatement pstmt = null;
		//log.debug(consulta);
		try {
			getConnection();
			//Gustavo - CR 16440 - Seteo conexión con como readOnly y nivel de aislamiento
			con.setReadOnly(true);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			con.setAutoCommit(true);
			//Gustavo - CR 16440 - Ejecuto stmt como PreparedStatement
			pstmt = con.prepareStatement(consulta);
			res = pstmt.executeUpdate();
			traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(res)); //@Trace
			pstmt.close();
			close();
		} catch (Exception exc) {
			log.warn("Problema ejecutando consulta " + consulta, exc);
			res = 0;
			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return res;
	}

	public boolean ejecuta(String consulta) {
		boolean res;
		//Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		TraceOperation traceOp = traceManager.newOperation(TraceManager.OP_DBMANAGER_EJECUTA);		
		try{		
		  	 traceOp.init(log);		
			 traceManager.traceOpStart(traceOp);
			 
		
			//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt
			PreparedStatement pstmt = null;
		//log.debug(consulta);
		try {
			getConnection();
			//Gustavo - CR 16440 - Ejecuto stmt como PreparedStatement
			pstmt = con.prepareStatement(consulta);
			res = pstmt.execute();
			traceOp.setColumn(TraceManager.COL_SQL,consulta); //@Trace
			traceOp.setColumn(TraceManager.COL_CANT_REGISTROS,String.valueOf(res)); //@Trace
			pstmt.close();
			close();
		} catch (Exception exc) {
			log.warn("Problema ejecutando consulta " + consulta, exc);
			res = false;
			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
			try { if (con != null) con.close(); } catch (Exception e) { }
		}
		}finally{
			traceManager.traceOpEnd(traceOp); //@Trace
		}

		return res;
	}


	public long seqNextValLong(String secuencia) {
		
		long seq = 0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			getConnection();
			pstmt=con.prepareStatement("values nextval for "+secuencia);
			rs=pstmt.executeQuery();
			while(rs.next()){
				seq = rs.getLong( 1 );
				//log.debug( "valor sequence " + seq );
			}

		}catch (Exception exc) {
			log.warn("Problema ejecutando la Secuencia " + secuencia, exc);
			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
		try { if (rs != null) rs.close(); } catch (Exception e) { }
		try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
		try { if (con != null) con.close(); } catch (Exception e) { }
		}

			return seq;
	}
	
	public int seqNextValInteger(String secuencia)
	{
		
			int seq = 0;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				getConnection();
				pstmt=con.prepareStatement("values nextval for "+secuencia);
				rs=pstmt.executeQuery();
				while(rs.next()){
					seq = rs.getInt( 1 );
					//log.debug( "valor sequence " + seq );
				}

			}catch (Exception exc) {
				log.warn("Problema ejecutando la Secuencia " + secuencia, exc);
				if (exc instanceof SQLException)
					throw new DBException((SQLException) exc);
				else
					throw new DBException(exc.getMessage());
			} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) { }
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
			try { if (con != null) con.close(); } catch (Exception e) { }
			}
			return seq;
	}
	
	public short seqNextValShort(String secuencia)
	{
		short seq = 0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			getConnection();
			pstmt=con.prepareStatement("values nextval for "+secuencia);
			rs=pstmt.executeQuery();
			while(rs.next()){
				seq = rs.getShort( 1 );
				//log.debug( "valor sequence " + seq );
			}

		}catch (Exception exc) {
			log.warn("Problema ejecutando la Secuencia " + secuencia, exc);
			if (exc instanceof SQLException)
				throw new DBException((SQLException) exc);
			else
				throw new DBException(exc.getMessage());
		} finally {
		try { if (rs != null) rs.close(); } catch (Exception e) { }
		try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
		try { if (con != null) con.close(); } catch (Exception e) { }
			if(con!=null) log.debug("Error esta guea no cierra");
			else
			log.debug("SI esta guea cierra");
		}

		return seq;
	}
	
	public void getConnection() throws SQLException, ClassNotFoundException, NamingException {
		if (inTransaction && con != null) {
			return;
		}
		DataSource ds = DataSourceFactory.getDataSource(dataSource);
		con = ds.getConnection();
	}
	
	public void setDataSource(String dataSource){
		this.dataSource=dataSource;
	}
	
	public void close() {
		try {
			if (!inTransaction) {
				con.close();
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public static Connection getConnection(String dataSource) throws SQLException
	{
		DataSource ds = DataSourceFactory.getDataSource(dataSource);
		return ds.getConnection();
	}
}