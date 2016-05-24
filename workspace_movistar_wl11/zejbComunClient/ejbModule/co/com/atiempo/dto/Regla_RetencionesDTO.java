/*
 * Created on Apr 15, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.atiempo.dto;

/**
 * @author 804226
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Regla_RetencionesDTO implements Comparable{

	private Long Regla_id;
	private String Descripcion;
	
	
	/**
	 * @return Returns the getDescripcion.
	 */
	public String getDescripcion() {
		return Descripcion;
	}
	/**
	 * @param getDescripcion The getDescripcion to set.
	 */
	public void setDescripcion(String Descripcion) {
		this.Descripcion = Descripcion;
	}
	/**
	 * @return Returns the getRegla_id.
	 */
	public Long getRegla_id() {
		return Regla_id;
	}
	/**
	 * @param getRegla_id The getRegla_id to set.
	 */
	public void setRegla_id(Long Regla_id) {
		this.Regla_id = Regla_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
