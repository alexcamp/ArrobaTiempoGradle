package com.tecnonautica.utiles.db;

public class DBException extends RuntimeException {
	private java.sql.SQLException sqlExc = null;

	public DBException() {
		this("");
	}
	public DBException(String s) {
		super(s);
		sqlExc = null;
	}
	public DBException(java.sql.SQLException e) {
		super(e.getMessage());
		sqlExc = e;
	}
	public java.sql.SQLException getSqlException() {
		return sqlExc;
	}
}
