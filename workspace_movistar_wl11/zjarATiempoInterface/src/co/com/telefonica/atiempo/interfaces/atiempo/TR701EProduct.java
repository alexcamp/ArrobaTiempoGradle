/*
 * Created on Aug 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR701EProduct implements Serializable {
	private Long productCode;
	private String atisRequestNumber;
	private String idPcTv;
	private String nombrePSProducto;
	private String caracteristicaProducto;
	private int plazoFinanciacion;
	
	/**
	 * @return Returns the caracteristicaProducto.
	 */
	public String getCaracteristicaProducto() {
		return caracteristicaProducto;
	}
	/**
	 * @param caracteristicaProducto The caracteristicaProducto to set.
	 */
	public void setCaracteristicaProducto(String caracteristicaProducto) {
		this.caracteristicaProducto = caracteristicaProducto;
	}
	/**
	 * @return Returns the nombrePSProducto.
	 */
	public String getNombrePSProducto() {
		return nombrePSProducto;
	}
	/**
	 * @param nombrePSProducto The nombrePSProducto to set.
	 */
	public void setNombrePSProducto(String nombrePSProducto) {
		this.nombrePSProducto = nombrePSProducto;
	}
	/**
	 * @return Returns the plazoFinanciacion.
	 */
	public int getPlazoFinanciacion() {
		return plazoFinanciacion;
	}
	/**
	 * @param plazoFinanciacion The plazoFinanciacion to set.
	 */
	public void setPlazoFinanciacion(int plazoFinanciacion) {
		this.plazoFinanciacion = plazoFinanciacion;
	}
	public int hashCode() {
		return productCode.intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EProduct) {
			TR701EProduct other = (TR701EProduct) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(productCode, other.productCode)
				&& EqualityUtilities.equals(atisRequestNumber, other.atisRequestNumber)
				&& EqualityUtilities.equals(idPcTv, other.idPcTv);
			}
		return false;
	}
	
	
	
	/**
	 * @return Returns the atisRequestNumber.
	 */
	public String getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(String atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Returns the productCode.
	 */
	public Long getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode The productCode to set.
	 */
	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return Returns the idpctv.
	 */
	public String getIdPcTv() {
		return idPcTv;
	}
	/**
	 * @param idpctv The idpctv to set.
	 */
	public void setIdPcTv(String idPcTv) {
		this.idPcTv = idPcTv;
	}
}
