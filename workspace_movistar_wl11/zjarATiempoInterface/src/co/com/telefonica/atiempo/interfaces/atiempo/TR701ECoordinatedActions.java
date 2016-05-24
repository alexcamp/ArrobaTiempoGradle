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
public class TR701ECoordinatedActions implements Serializable {
	private String dependenceType;
	private Long dependeceId;
	
	public int hashCode() {
		return dependeceId.intValue();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701ECoordinatedActions) {
			TR701ECoordinatedActions other = (TR701ECoordinatedActions) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(dependenceType, other.dependenceType)
				&& EqualityUtilities.equals(dependeceId, other.dependeceId);
			}
		return false;
	}
	
	public Long getDependeceId() {
		return dependeceId;
	}
	public void setDependeceId(Long dependeceId) {
		this.dependeceId = dependeceId;
	}
	public String getDependenceType() {
		return dependenceType;
	}
	public void setDependenceType(String dependenceType) {
		this.dependenceType = dependenceType;
	}
}
