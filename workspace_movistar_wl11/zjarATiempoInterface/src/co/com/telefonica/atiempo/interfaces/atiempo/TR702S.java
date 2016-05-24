/*
 * Created on Aug 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR702S extends ResponseHeaderAgendaSC {
	private Collection schedules;
	
	public int hashCode() {
		return schedules.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR702S) {
			TR702S other = (TR702S) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(schedules, other.schedules);
			}
		return false;
	}
	
	public Collection getSchedules() {
		return schedules;
	}
	public void setSchedules(Collection schedules) {
		this.schedules = schedules;
	}
}
