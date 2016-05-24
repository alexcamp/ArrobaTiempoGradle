package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Menu
 */
public class MenuKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: mn_id
	 */
	public java.lang.Long mn_id;
	/**
	 * Creates an empty key for Entity Bean: Menu
	 */
	public MenuKey() {
	}
	/**
	 * Creates a key for Entity Bean: Menu
	 */
	public MenuKey(java.lang.Long mn_id) {
		this.mn_id = mn_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.MenuKey) {
			co.com.telefonica.atiempo.ejb.eb.MenuKey o =
				(co.com.telefonica.atiempo.ejb.eb.MenuKey) otherKey;
			return ((this.mn_id.equals(o.mn_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (mn_id.hashCode());
	}
}
