package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Mensaje_descarga_SAP
 */
public class Mensaje_descarga_SAPKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: consecutivo
	 */
	public java.lang.Long consecutivo;
	/**
	 * Creates an empty key for Entity Bean: Mensaje_descarga_SAP
	 */
	public Mensaje_descarga_SAPKey() {
	}
	/**
	 * Creates a key for Entity Bean: Mensaje_descarga_SAP
	 */
	public Mensaje_descarga_SAPKey(java.lang.Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPKey) {
			co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPKey o = (co.com.telefonica.atiempo.ejb.eb.Mensaje_descarga_SAPKey) otherKey;
			return ((this.consecutivo.equals(o.consecutivo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (consecutivo.hashCode());
	}
}