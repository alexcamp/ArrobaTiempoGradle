package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Elemento_agenda_sc
 */
public class Elemento_agenda_scKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_correlativo
	 */
	public java.lang.Integer id_correlativo;
	/**
	 * Creates an empty key for Entity Bean: Elemento_agenda_sc
	 */
	public Elemento_agenda_scKey() {
	}
	/**
	 * Creates a key for Entity Bean: Elemento_agenda_sc
	 */
	public Elemento_agenda_scKey(java.lang.Integer id_correlativo) {
		this.id_correlativo = id_correlativo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey) {
			co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey o = (co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey) otherKey;
			return ((this.id_correlativo.equals(o.id_correlativo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_correlativo.hashCode());
	}
}