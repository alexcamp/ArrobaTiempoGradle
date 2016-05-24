package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Dslam_conec9_apsc
 */
public class Dslam_conec9_apscKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_dslam
	 */
	public java.lang.Long id_dslam;
	/**
	 * Creates an empty key for Entity Bean: Dslam_conec9_apsc
	 */
	public Dslam_conec9_apscKey() {
	}
	/**
	 * Creates a key for Entity Bean: Dslam_conec9_apsc
	 */
	public Dslam_conec9_apscKey(java.lang.Long id_dslam) {
		this.id_dslam = id_dslam;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Dslam_conec9_apscKey) {
			co.com.telefonica.atiempo.ejb.eb.Dslam_conec9_apscKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Dslam_conec9_apscKey) otherKey;
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
