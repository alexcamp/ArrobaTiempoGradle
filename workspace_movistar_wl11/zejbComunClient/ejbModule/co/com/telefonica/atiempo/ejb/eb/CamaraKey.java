package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Camara
 */
public class CamaraKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: petiNumero
	 */
	public java.lang.Long petiNumero;
	/**
	 * Implementation field for persistent attribute: cameraSerial
	 */
	public java.lang.String cameraSerial;
	/**
	 * Creates an empty key for Entity Bean: Camara
	 */
	public CamaraKey() {
	}
	/**
	 * Creates a key for Entity Bean: Camara
	 */
	public CamaraKey(java.lang.Long petiNumero, java.lang.String cameraSerial) {
		this.petiNumero = petiNumero;
		this.cameraSerial = cameraSerial;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.CamaraKey) {
			co.com.telefonica.atiempo.ejb.eb.CamaraKey o = (co.com.telefonica.atiempo.ejb.eb.CamaraKey) otherKey;
			return ((this.petiNumero.equals(o.petiNumero)) && (this.cameraSerial
				.equals(o.cameraSerial)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (petiNumero.hashCode() + cameraSerial.hashCode());
	}
}