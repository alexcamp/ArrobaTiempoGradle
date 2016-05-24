/*
 * Created on Nov 9, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JerUsuarioRol implements Serializable{
	private Long supID;
	private Long subID;
	private Long RolID;
	
	
	/**
	 * @return Returns the rolID.
	 */
	public Long getRolID() {
		return RolID;
	}
	/**
	 * @param rolID The rolID to set.
	 */
	public void setRolID(Long rolID) {
		RolID = rolID;
	}
	/**
	 * @return Returns the subID.
	 */
	public Long getSubID() {
		return subID;
	}
	/**
	 * @param subID The subID to set.
	 */
	public void setSubID(Long subID) {
		this.subID = subID;
	}
	/**
	 * @return Returns the supID.
	 */
	public Long getSupID() {
		return supID;
	}
	/**
	 * @param supID The supID to set.
	 */
	public void setSupID(Long supID) {
		this.supID = supID;
	}
	
	public int hashCode(){
		return subID.intValue()+supID.intValue()+RolID.intValue();
	}
}
