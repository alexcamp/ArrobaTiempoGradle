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
public class TR719S extends ResponseHeaderAgendaSC {
	
	private String id_sistema_origen;
	private String id_actuacion;
	private String id_pedido_atis;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR719S){
			TR719S other = (TR719S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(id_sistema_origen, other.id_sistema_origen)
				&& EqualityUtilities.equals(id_actuacion, other.id_actuacion)
				&& EqualityUtilities.equals(id_pedido_atis, other.id_pedido_atis);
		}else{
			return false;
		}
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
	 * @return Devuelve id_pedido_atis.
	 */
	public String getId_pedido_atis() {
		return id_pedido_atis;
	}
	/**
	 * @param id_pedido_atis El id_pedido_atis a establecer.
	 */
	public void setId_pedido_atis(String id_pedido_atis) {
		this.id_pedido_atis = id_pedido_atis;
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
