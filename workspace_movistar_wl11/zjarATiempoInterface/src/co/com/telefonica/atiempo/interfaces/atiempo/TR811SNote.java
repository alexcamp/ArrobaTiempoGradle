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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR811SNote implements Serializable {
	private String note;
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SNote) {
			TR811SNote other = (TR811SNote) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(note, other.note);
				
			}
		return false;
	}
	



	/**
	 * @return Devuelve note.
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note El note a establecer.
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
