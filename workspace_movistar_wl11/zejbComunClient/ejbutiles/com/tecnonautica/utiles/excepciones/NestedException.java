package com.tecnonautica.utiles.excepciones;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * - Al parecer las clases Exception/RuntimeException del jdk 1.4
 * ya soportan esta funcionalidad.
 * 
 * - Hay que tener cuidado con las excepciones que se anidan: Si se va a lanzar
 * a traves de un ejb (remoto), la excepcion al salir se Serializa por el lado
 * del servidor y luego se "desserializa" por el lado del cliente.
 * Si la excepcion que esta anidada no esta en el classpath del cliente
 * el resultado es un UnmarshalException o algo por el estilo.
 * 
 * - Si hay problemas, lo que se puede hacer es NO guardar la excepcion
 * si no exc.toString(), aunque asi se pierde la traza... De todas formas
 * un buen uso de log4j podria compensar eso.
 * 
 * Ver: [http://c2.com/cgi/wiki?NestedException]
 * 
 */
public class NestedException extends Exception {
	private Exception e;

	public NestedException() {
		super();
	}
	public NestedException(String msg) {
		super(msg);
	}
	public NestedException(Exception e) {
		this.e = e;
	}
	public NestedException(String msg, Exception e) {
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
