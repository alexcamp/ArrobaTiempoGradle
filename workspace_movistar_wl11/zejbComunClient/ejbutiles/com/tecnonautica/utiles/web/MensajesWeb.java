package com.tecnonautica.utiles.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class MensajesWeb {
	public static final String NOMBRE_PARAMETRO = "mensajesWeb";
	private MensajesWeb() {
		// El objeto se construye con getInstance() 
	}

	public static MensajesWeb getInstance(HttpServletRequest request) {
		MensajesWeb mensajes = (MensajesWeb)request.getAttribute(NOMBRE_PARAMETRO);
		if (mensajes == null) {
			mensajes = new MensajesWeb();
			request.setAttribute(NOMBRE_PARAMETRO, mensajes);
		}

		return mensajes;
	}

	private ArrayList mensajesOk = new ArrayList();
	private ArrayList mensajesError = new ArrayList();
	
	public void addOk(String msg) {
		mensajesOk.add(msg);
	}
	
	public void addError(String msg) {
		mensajesError.add(msg);
	}
	
	public ArrayList getErrores() {
		return mensajesError;
	}

	public ArrayList getOks() {
		return mensajesOk;
	}

	public boolean hayErrores() {
		return mensajesError.size() > 0;
	}
	
	public boolean hayOks() {
		return mensajesOk.size() > 0;
	}
}
