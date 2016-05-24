/*
 * Created on Sep 14, 2010
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
public class TR701EProductService implements Serializable {
	private String psId;
	private String operationComercial;
	private String accion;//REQ migracion deco 
	public int hashCode() {
		return psId.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EProductService) {
			TR701EProductService other = (TR701EProductService) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(psId, other.psId)
				&& EqualityUtilities.equals(operationComercial, other.operationComercial)
				&& EqualityUtilities.equals(accion, other.accion);//REQ migracion deco 
			}
		return false;
	}
	public String getOperationComercial() {
		return operationComercial;
	}
	public void setOperationComercial(String operationComercial) {
		this.operationComercial = operationComercial;
	}
	public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	//REQ migracion deco dcardena 15/01/2015
	/**
	 * @return Devuelve accion.
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion El accion a establecer.
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	//FIN REQ migracion deco 
}
