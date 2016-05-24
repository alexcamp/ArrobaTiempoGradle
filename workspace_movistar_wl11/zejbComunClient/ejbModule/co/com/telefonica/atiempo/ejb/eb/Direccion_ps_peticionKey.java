package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Direccion_ps_peticion
 */
public class Direccion_ps_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: dir_ps_peti_id
	 */
	public java.lang.Long dir_ps_peti_id;
	/**
	 * Creates an empty key for Entity Bean: Direccion_ps_peticion
	 */
	public Direccion_ps_peticionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Direccion_ps_peticion
	 */
	public Direccion_ps_peticionKey(java.lang.Long dir_ps_peti_id) {
		this.dir_ps_peti_id = dir_ps_peti_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Direccion_ps_peticionKey) {
			co.com.telefonica.atiempo.ejb.eb.Direccion_ps_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Direccion_ps_peticionKey) otherKey;
			return ((this.dir_ps_peti_id.equals(o.dir_ps_peti_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (dir_ps_peti_id.hashCode());
	}
}
