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
 * @author sfandino
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR811STechnician implements Serializable {

	private String technicianName;

	private String documentNumber;

	private String duration;

	public int hashCode() {
		return technicianName.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811STechnician) {
			TR811STechnician other = (TR811STechnician) arg0;
			return super.equals(arg0)

					&& EqualityUtilities.equals(technicianName,
							other.technicianName)
					&& EqualityUtilities.equals(documentNumber,
							other.documentNumber)
					&& EqualityUtilities.equals(duration, other.duration);
		}
		return false;
	}

	/**
	 * @return Devuelve documentNumber.
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * @param documentNumber
	 *            El documentNumber a establecer.
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	/**
	 * @return Devuelve duration.
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            El duration a establecer.
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return Devuelve technicianName.
	 */
	public String getTechnicianName() {
		return technicianName;
	}

	/**
	 * @param technicianName
	 *            El technicianName a establecer.
	 */
	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}
}