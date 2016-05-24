/*
 * Created on Dec 6, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.director.dto;

import java.io.Serializable;

/**
 * @author dfiguer
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProductoServicio implements Comparable,Serializable {
	private Long idProductoServicio;
	private String codigoProductoServicio;
	private String descripcionProductoServicio;
	private Long idOperacionComercial;
	private String tipoOperacionComercial;
	private String descripcionOperacionComercial;
	
	private String codigoTipoPC = "";
	private ProductoServicio psBajaCP = null;	

	/**
	 * @param integer
	 */
	public void setIdOperacionComercial(Long integer) {
		idOperacionComercial = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdProductoServicio(Long integer) {
		idProductoServicio = integer;
	}

	/**
	 * @return
	 */
	public Long getIdOperacionComercial() {
		return idOperacionComercial;
	}

	/**
	 * @return
	 */
	public Long getIdProductoServicio() {
		return idProductoServicio;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(idProductoServicio);
		sb.append("-");
		sb.append(idOperacionComercial);
		return sb.toString();
	}

	/**
	 * Compara 2 Objetos ProductoServicio(Para poder ser usados en una Treeset)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
		ProductoServicio ps = (ProductoServicio) arg0;
		if (this.idProductoServicio.compareTo(ps.idProductoServicio) == 0) {
			if (this.idOperacionComercial == null
				&& ps.idOperacionComercial == null) {
				return 0;
			} else if (this.idOperacionComercial == null) {
				return -1;
			} else if (ps.idOperacionComercial == null) {
				return 1;
			}
			if (this.idOperacionComercial.compareTo(ps.idOperacionComercial)
				== 0) {
				return 0;
			} else if (
				this.idOperacionComercial.compareTo(ps.idOperacionComercial)
					< 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (
			this.idProductoServicio.compareTo(ps.idProductoServicio) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * @param string
	 */
	public void setCodigoProductoServicio(String string) {
		codigoProductoServicio = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionProductoServicio(String string) {
		descripcionProductoServicio = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionOperacionComercial(String string) {
		descripcionOperacionComercial = string;
	}

	/**
	 * @return
	 */
	public String getDescripcionOperacionComercial() {
		return descripcionOperacionComercial;
	}

	/**
	 * @return
	 */
	public String getDescripcionProductoServicio() {
		return descripcionProductoServicio;
	}

	/**
	 * @return
	 */
	public String getCodigoTipoPC() {
		return codigoTipoPC;
	}

	/**
	 * @return
	 */
	public ProductoServicio getPsBajaCP() {
		return psBajaCP;
	}

	/**
	 * @return
	 */
	public String getTipoOperacionComercial() {
		return tipoOperacionComercial;
	}

	/**
	 * @param string
	 */
	public void setCodigoTipoPC(String string) {
		codigoTipoPC = string;
	}

	/**
	 * @param servicio
	 */
	public void setPsBajaCP(ProductoServicio servicio) {
		psBajaCP = servicio;
	}

	/**
	 * @param string
	 */
	public void setTipoOperacionComercial(String string) {
		tipoOperacionComercial = string;
	}

}
