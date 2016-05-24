/*
 * Created on Aug 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author sfandino
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR811SNotes implements Serializable {
	private Collection notes;
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SNotes) {
			TR811SNotes other = (TR811SNotes) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(notes, other.notes);
				
			}
		return false;
	}
	



	/**
	 * @return Devuelve notes.
	 */
	public Collection getNotes() {
		return notes;
	}
	/**
	 * @param notes El notes a establecer.
	 */
	public void setNotes(Collection note) {
		this.notes = note;
	}
}
