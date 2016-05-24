/*
 * Created on Jan 12, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR719E extends RequestHeaderAgendaSC{
	
	private String id_sistema_origen;
	private String id_actuacion;
	private String notif_refresh;
	private String codigo_error;
	private String descripcion;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR719E){
			TR719E other = (TR719E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(id_sistema_origen, other.id_sistema_origen)
				&& EqualityUtilities.equals(id_actuacion, other.id_actuacion)
				&& EqualityUtilities.equals(notif_refresh, other.notif_refresh)
				&& EqualityUtilities.equals(codigo_error, other.codigo_error)
				&& EqualityUtilities.equals(descripcion, other.descripcion);
		}else{
			return false;
		}
	}

	/**
	 * @return Devuelve codigo_error.
	 */
	public String getCodigo_error() {
		return codigo_error;
	}
	/**
	 * @param codigo_error El codigo_error a establecer.
	 */
	public void setCodigo_error(String codigo_error) {
		this.codigo_error = codigo_error;
	}
	/**
	 * @return Devuelve descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Devuelve id_actuacion.
	 */
	public String getId_actuacion() {
		return id_actuacion;
	}
	/**
	 * @param id_actuacion El id_actuacion a establecer.
	 */
	public void setId_actuacion(String id_actuacion) {
		this.id_actuacion = id_actuacion;
	}
	/**
	 * @return Devuelve notif_refresh.
	 */
	public String getNotif_refresh() {
		return notif_refresh;
	}
	/**
	 * @param notif_refresh El notif_refresh a establecer.
	 */
	public void setNotif_refresh(String notif_refresh) {
		this.notif_refresh = notif_refresh;
	}
	/**
	 * @return Devuelve id_sistema_origen.
	 */
	public String getId_sistema_origen() {
		return id_sistema_origen;
	}
	/**
	 * @param id_sistema_origen El id_sistema_origen a establecer.
	 */
	public void setId_sistema_origen(String id_sistema_origen) {
		this.id_sistema_origen = id_sistema_origen;
	}
}
