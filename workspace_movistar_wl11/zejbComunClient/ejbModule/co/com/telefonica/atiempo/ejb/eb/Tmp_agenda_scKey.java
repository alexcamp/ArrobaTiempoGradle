package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tmp_agenda_sc
 */
public class Tmp_agenda_scKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Long id;
	/**
	 * Creates an empty key for Entity Bean: Tmp_agenda_sc
	 */
	public Tmp_agenda_scKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tmp_agenda_sc
	 */
	public Tmp_agenda_scKey(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scKey) {
			co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scKey o = (co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scKey) otherKey;
			return ((this.id.equals(o.id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id.hashCode());
	}
}