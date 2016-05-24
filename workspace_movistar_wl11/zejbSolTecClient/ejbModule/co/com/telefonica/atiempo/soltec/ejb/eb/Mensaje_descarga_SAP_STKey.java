package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Mensaje_descarga_SAP_ST
 */
public class Mensaje_descarga_SAP_STKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: consecutivo
	 */
	public java.lang.Long consecutivo;
	/**
	 * Creates an empty key for Entity Bean: Mensaje_descarga_SAP_ST
	 */
	public Mensaje_descarga_SAP_STKey() {
	}
	/**
	 * Creates a key for Entity Bean: Mensaje_descarga_SAP_ST
	 */
	public Mensaje_descarga_SAP_STKey(java.lang.Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STKey o = (co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STKey) otherKey;
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