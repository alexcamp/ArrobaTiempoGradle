package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Dslam_conec6_apsc_linea
 */
public class Dslam_conec6_apsc_lineaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_dslam
	 */
	public java.lang.Long id_dslam;
	/**
	 * Creates an empty key for Entity Bean: Dslam_conec6_apsc_linea
	 */
	public Dslam_conec6_apsc_lineaKey() {
	}
	/**
	 * Creates a key for Entity Bean: Dslam_conec6_apsc_linea
	 */
	public Dslam_conec6_apsc_lineaKey(java.lang.Long id_dslam) {
		this.id_dslam = id_dslam;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Dslam_conec6_apsc_lineaKey) {
			co.com.telefonica.atiempo.ejb.eb.Dslam_conec6_apsc_lineaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Dslam_conec6_apsc_lineaKey) otherKey;
			return ((this.id_dslam.equals(o.id_dslam)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_dslam.hashCode());
	}
}
