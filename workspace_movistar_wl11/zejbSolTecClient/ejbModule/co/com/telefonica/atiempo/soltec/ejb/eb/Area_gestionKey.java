package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Area_gestion
 */
public class Area_gestionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_area_ges
	 */
	public java.lang.Long id_area_ges;
	/**
	 * Creates an empty key for Entity Bean: Area_gestion
	 */
	public Area_gestionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Area_gestion
	 */
	public Area_gestionKey(java.lang.Long id_area_ges) {
		this.id_area_ges = id_area_ges;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Area_gestionKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Area_gestionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Area_gestionKey) otherKey;
			return ((this.id_area_ges.equals(o.id_area_ges)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_area_ges.hashCode());
	}
}
