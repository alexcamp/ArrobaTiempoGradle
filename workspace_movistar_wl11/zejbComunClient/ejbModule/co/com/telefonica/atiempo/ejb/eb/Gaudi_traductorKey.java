package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Gaudi_traductor
 */
public class Gaudi_traductorKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_correlativo
	 */
	public java.lang.Long id_correlativo;
	/**
	 * Creates an empty key for Entity Bean: Gaudi_traductor
	 */
	public Gaudi_traductorKey() {
	}
	/**
	 * Creates a key for Entity Bean: Gaudi_traductor
	 */
	public Gaudi_traductorKey(java.lang.Long id_correlativo) {
		this.id_correlativo = id_correlativo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Gaudi_traductorKey) {
			co.com.telefonica.atiempo.ejb.eb.Gaudi_traductorKey o =
				(co.com.telefonica.atiempo.ejb.eb.Gaudi_traductorKey) otherKey;
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
