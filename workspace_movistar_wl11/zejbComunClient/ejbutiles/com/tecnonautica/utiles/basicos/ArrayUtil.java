/*
 * Created on 11-01-2006
 */
package com.tecnonautica.utiles.basicos;

/**
 * @author M.Alarcón "Dono"
 */
public class ArrayUtil {

	/**
	 * Método que verifica si un arreglo cualquiera contiene un objeto determinado
	 * @param array arreglo que se revisará
	 * @param object objeto que se buscará
	 * @return verdadero si <b>object</b> existe en <b>array</b>, falso en caso contrario
	 */
	public static boolean contains(Object[] array, Object object) {
		if (array == null || object == null) return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(object)) return true;
		}
		return false;
	}

}
