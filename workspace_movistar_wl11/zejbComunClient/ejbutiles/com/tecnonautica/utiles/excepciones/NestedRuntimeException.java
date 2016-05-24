package com.tecnonautica.utiles.excepciones;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Ver javadoc de NestedException.
 */
public class NestedRuntimeException extends RuntimeException {
	private Exception e;

	public NestedRuntimeException() {
		super();
	}
	public NestedRuntimeException(String msg) {
		super(msg);
	}
	public NestedRuntimeException(Exception e) {
		this.e = e;
	}
	public NestedRuntimeException(String msg, Exception e) {
		super(msg);
		this.e = e;
	}

	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		if (e != null) {
			s.println("... causa...");
			e.printStackTrace(s);
		}
	}
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
		if (e != null) {
			s.println("... causa...");
			e.printStackTrace(s);
		}
	}
	public void printStackTrace() {
		super.printStackTrace();
		if (e != null) {
			System.err.println("... causa...");
			e.printStackTrace();
		}
	}
}
