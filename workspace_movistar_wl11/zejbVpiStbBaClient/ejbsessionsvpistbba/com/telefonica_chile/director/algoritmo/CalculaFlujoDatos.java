/*
 * Created on Nov 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.director.algoritmo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

import com.telefonica_chile.director.dto.ProductoServicio;

/**
 * SOLO PARA PROTOTIPO!!!
 * @author dfiguer
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalculaFlujoDatos implements Serializable  {
	private HashSet productos = new HashSet();
	private boolean mutacion = false;
	private boolean pasaPorMDF = true;
	private Long peticion = null;
	private HashMap contextoFiltros = null;

	/**
	 * @return
	 */
	public boolean tieneActividadMDF() {
		return pasaPorMDF;
	}

	/**
	 * @return
	 */
	public boolean tieneMutacion() {
		return mutacion;
	}

	/**
	 * @return
	 */
	public HashSet getProductos() {
		return productos;
	}

	/**
	 * @param string
	 */
	public void setMutacion(String string) {
		if (string.equals("0")) {
			mutacion = false;
		} else {
			mutacion = true;
		}
	}

	/**
	 * @param string
	 */
	public void setProducto(ProductoServicio string) {
		productos.add(string);
	}

	/**
	 * @param b
	 */
	public void setPasaPorMDF(String string) {
		if (string.equals("0")) {
			pasaPorMDF = false;
		} else {
			pasaPorMDF = true;
		}
	}

	public void setPasaPorMDF(boolean seter) {
		pasaPorMDF = seter;
	}

	/**
	 * @return
	 */
	public Long getPeticion() {
		return peticion;
	}

	/**
	 * @param integer
	 */
	public void setPeticion(Long integer) {
		peticion = integer;
	}

	/**
	 * @param map
	 */
	public void setContextoFiltros(HashMap map) {
		contextoFiltros = map;
	}

	/**
	 * @return
	 */
	public HashMap getContextoFiltros() {
		return contextoFiltros;
	}

}
