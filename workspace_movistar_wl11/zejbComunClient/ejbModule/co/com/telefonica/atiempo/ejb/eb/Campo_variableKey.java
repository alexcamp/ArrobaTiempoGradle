package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Campo_variable
 */
public class Campo_variableKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cv_id
	 */
	public java.lang.Short cv_id;
	/**
	 * Creates an empty key for Entity Bean: Campo_variable
	 */
	public Campo_variableKey() {
	}
	/**
	 * Creates a key for Entity Bean: Campo_variable
	 */
	public Campo_variableKey(java.lang.Short cv_id) {
		this.cv_id = cv_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Campo_variableKey) {
			co.com.telefonica.atiempo.ejb.eb.Campo_variableKey o =
				(co.com.telefonica.atiempo.ejb.eb.Campo_variableKey) otherKey;
			return ((this.cv_id.equals(o.cv_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cv_id.hashCode());
	}
}
