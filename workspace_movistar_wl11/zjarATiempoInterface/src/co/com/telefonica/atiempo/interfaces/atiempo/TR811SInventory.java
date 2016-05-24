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
 * @author damartinezv
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TR811SInventory implements Serializable {
	private Collection materials;

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object arg0) {
		if (arg0 instanceof TR811SInventory) {
			TR811SInventory other = (TR811SInventory) arg0;
			return super.equals(arg0)
					&& EqualityUtilities.equals(materials, other.materials);

		}
		return false;
	}


	/**
	 * @return Devuelve materials.
	 */
	public Collection getMaterials() {
		return materials;
	}
	/**
	 * @param materials El materials a establecer.
	 */
	public void setMaterials(Collection materials) {
		this.materials = materials;
	}
}