package com.telefonica_chile.bandeja.ejbutiles;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

public class DataSourceFactory {
	private static DataSource ds = null;

//	public static DataSource getDataSource() throws NamingException {
//		if (ds == null)
//			ds = (DataSource) new InitialContext().lookup("jdbc/bandeja");
//
//		return ds;
//	}
	
	//private static String prefix="java:comp/env/";
	
	public static DataSource getDataSource(String nombreConexion) {
		// "jdbc/adsl2" "java:comp/env"
		DataSource dataSource = null;
		Context context = null;
		try
		{
			if (ServiceLocatorEmulator.emuladorActivado()) {	
				dataSource = (DataSource) new InitialContext().lookup(nombreConexion);
			}else{
				dataSource = (DataSource) new InitialContext().lookup("java:comp/env/"+nombreConexion);
			}
			
			return dataSource;
			
		} catch (NamingException ex)
		{
			return null;
		}		
	}
}
