package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;

/**
 * Clase que representa la respuesta desde los session bean facade de cada aplicacion 
 * encargados de procesar la accion masiva (batch)
 */
public class RespuestaBatch implements Serializable {
	
	public static final int OK = 1;
	public static final int ERROR= 2;
	public static final int WARNING = 3;
		
	public static final String PROCESS_OK = "Peticiones procesadas exitosamente";
	public static final String APP_UNKNOWN = "Aplicacion desconocida";
	public static final String SERVICE_UNAVAILABLE = "Servicio no esta disponible";
	public static final String NO_PETICIONES = "No hay peticiones para proceso";
	public static final String UNKNOWN_ERROR = "Servicio lanza error desconocido";
	public static final String PROCESING_ERROR = "Servicio con errores de procesamiento";
	public static final String PROCESS_PARTIAL = "Peticiones con error. No todas las peticiones han sido procesadas";

	private int codigo;
	private String descripcion;

	/**
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param i
	 */
	public void setCodigo(int i) {
		codigo = i;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

}
