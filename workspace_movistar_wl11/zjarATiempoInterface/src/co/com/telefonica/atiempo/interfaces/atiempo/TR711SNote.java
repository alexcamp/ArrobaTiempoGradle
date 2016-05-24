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
public class TR711SNote implements Serializable {
	private String noteType;
	private String value;
	
	public int hashCode() {
		return value.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR711SNote) {
			TR711SNote other = (TR711SNote) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(noteType, other.noteType)
				&& EqualityUtilities.equals(value, other.value);
			}
		return false;
	}
	
	public String getNoteType() {
		return noteType;
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
