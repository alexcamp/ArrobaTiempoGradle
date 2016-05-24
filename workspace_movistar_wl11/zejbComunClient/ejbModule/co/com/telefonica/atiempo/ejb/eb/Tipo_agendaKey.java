package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tipo_agenda
 */
public class Tipo_agendaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: tiag_id
	 */
	public java.lang.Long tiag_id;
	/**
	 * Creates an empty key for Entity Bean: Tipo_agenda
	 */
	public Tipo_agendaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tipo_agenda
	 */
	public Tipo_agendaKey(java.lang.Long tiag_id) {
		this.tiag_id = tiag_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Tipo_agendaKey) {
			co.com.telefonica.atiempo.ejb.eb.Tipo_agendaKey o =
				(co.com.telefonica.atiempo.ejb.eb.Tipo_agendaKey) otherKey;
			return ((this.tiag_id.equals(o.tiag_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (tiag_id.hashCode());
	}
}
