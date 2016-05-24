package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Agenda_sc
 */
public class Agenda_scKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_actuacion
	 */
	public java.lang.String id_actuacion;
	/**
	 * Creates an empty key for Entity Bean: Agenda_sc
	 */
	public Agenda_scKey() {
	}
	/**
	 * Creates a key for Entity Bean: Agenda_sc
	 */
	public Agenda_scKey(java.lang.String id_actuacion) {
		this.id_actuacion = id_actuacion;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Agenda_scKey) {
			co.com.telefonica.atiempo.ejb.eb.Agenda_scKey o = (co.com.telefonica.atiempo.ejb.eb.Agenda_scKey) otherKey;
			return ((this.id_actuacion.equals(o.id_actuacion)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_actuacion.hashCode());
	}
}