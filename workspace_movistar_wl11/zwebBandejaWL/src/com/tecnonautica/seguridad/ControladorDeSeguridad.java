package com.tecnonautica.seguridad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletRequest;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ControladorDeSeguridad {

	public static final String CONTROLADOR_DE_SEGURIDAD =
		"controladorDeSeguridad";

	private HashMap recursos;
	private Vector patrones;

	private ControladorDeRecurso controladorPorDefecto = null;

	/**
	 * @deprecated
	 */
	public void cargarRecursos(HashMap recursos) {
		this.recursos = recursos;

		MapaDeRecurso mdr = (MapaDeRecurso) recursos.get("porDefecto");

		String nombreControladorPorDefecto = mdr.getControlador();

		try {
			controladorPorDefecto =
				(ControladorDeRecurso) getClass()
					.getClassLoader()
					.loadClass(nombreControladorPorDefecto)
					.newInstance();
		} catch (Exception e) {
			System.err.println(
				"No se pudo cargar el controlador de seguridad por defecto: "
					+ e);
		}
	}

	public void setRecursos(HashMap recursos) {
		this.recursos = recursos;

		MapaDeRecurso mdr = (MapaDeRecurso) recursos.get("porDefecto");

		String nombreControladorPorDefecto = mdr.getControlador();

		try {
			controladorPorDefecto =
				(ControladorDeRecurso) getClass()
					.getClassLoader()
					.loadClass(nombreControladorPorDefecto)
					.newInstance();
		} catch (Exception e) {
			System.err.println(
				"No se pudo cargar el controlador de seguridad por defecto: "
					+ e);
		}
	}
	public void setPatrones(Vector patrones) {
		this.patrones = patrones;
	}

	public boolean autoriza(String idRecurso, ServletRequest request) {
		String nombreControlador;
		MapaDeRecurso mdr = (MapaDeRecurso) recursos.get(idRecurso);

		//Si no hay mapa de recurso lo controla el controlador por defecto.
		if (mdr == null) {
			if (controladorPorDefecto == null) {
				throw (new NoExisteControladorDeSeguridadPorDefecto(idRecurso));
			}
			return controladorPorDefecto.autoriza(idRecurso, request);
		}

		//Hay algun patron que corresponda ?
		String nombreControl = getMatchingController(idRecurso);
		if (nombreControl != null) {
			ControladorDeRecurso controlador;

			try {
				controlador =
					(ControladorDeRecurso) getClass()
						.getClassLoader()
						.loadClass(mdr.getControlador())
						.newInstance();
				return controlador.autoriza(idRecurso, request);
			} catch (Exception e) {
				System.err.println(
					"No se pudo cargar el controlador de seguridad para el recurso :"
						+ idRecurso
						+ ": "
						+ e);
				return false;
			}
		} else {

			ControladorDeRecurso controlador;

			try {
				controlador =
					(ControladorDeRecurso) getClass()
						.getClassLoader()
						.loadClass(mdr.getControlador())
						.newInstance();
				return controlador.autoriza(idRecurso, request);
			} catch (Exception e) {
				System.err.println(
					"No se pudo cargar el controlador de seguridad para el recurso :"
						+ idRecurso
						+ ": "
						+ e);
				return false;
			}
		}

	}

	private String getMatchingController(String idRecurso) {
		Iterator it = patrones.iterator();
		while (it.hasNext()) {
			MapaDeRecurso mdr = (MapaDeRecurso) it.next();
			//se cambio de matches a equals para compatibilizar con jdk 1.3
			if (idRecurso.equals(mdr.getNombre())) {
				return mdr.getControlador();
			}
		}
		return null;
	}

}
