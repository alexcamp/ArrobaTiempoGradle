/*
 * Created on Aug 26, 2010
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
public class TR711STechnician implements Serializable {
	private String technicianId;
	private String technicianName;
	private String technicianLastname;
	private String contractCode;
	
	public int hashCode() {
		return technicianId.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR711STechnician) {
			TR711STechnician other = (TR711STechnician) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(technicianId, other.technicianId)
				&& EqualityUtilities.equals(technicianName, other.technicianName)
				&& EqualityUtilities.equals(technicianLastname, other.technicianLastname)
				&& EqualityUtilities.equals(contractCode, other.contractCode);
			}
		return false;
	}
	
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getTechnicianId() {
		return technicianId;
	}
	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}
	public String getTechnicianLastname() {
		return technicianLastname;
	}
	public void setTechnicianLastname(String technicianLastname) {
		this.technicianLastname = technicianLastname;
	}
	public String getTechnicianName() {
		return technicianName;
	}
	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}
}
