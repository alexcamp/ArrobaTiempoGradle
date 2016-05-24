package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Localidad_agenda_sc
 */
public class Localidad_agenda_scKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_loc
	 */
	public java.lang.String cod_loc;
	/**
	 * Creates an empty key for Entity Bean: Localidad_agenda_sc
	 */
	public Localidad_agenda_scKey() {
	}
	/**
	 * Creates a key for Entity Bean: Localidad_agenda_sc
	 */
	public Localidad_agenda_scKey(java.lang.String cod_loc) {
		this.cod_loc = cod_loc;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey) {
			co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey o = (co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey) otherKey;
			return ((this.cod_loc.equals(o.cod_loc)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_loc.hashCode());
	}
}