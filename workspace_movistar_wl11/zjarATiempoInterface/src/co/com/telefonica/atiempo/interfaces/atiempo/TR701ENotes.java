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
public class TR701ENotes implements Serializable {
	private String noteDescription;
	private String noteDetail;
	private String noteType;
	
	public int hashCode() {
		return noteDescription.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701ENotes) {
			TR701ENotes other = (TR701ENotes) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(noteDescription, other.noteDescription)
				&& EqualityUtilities.equals(noteDetail, other.noteDetail)
				&& EqualityUtilities.equals(noteType, other.noteType);
			}
		return false;
	}
	
	
	public String getNoteDescription() {
		return noteDescription;
	}
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}
	public String getNoteDetail() {
		return noteDetail;
	}
	public void setNoteDetail(String noteDetail) {
		this.noteDetail = noteDetail;
	}
	public String getNoteType() {
		return noteType;
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
}
