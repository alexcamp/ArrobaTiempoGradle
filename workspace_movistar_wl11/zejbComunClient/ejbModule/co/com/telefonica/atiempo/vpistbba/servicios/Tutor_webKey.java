package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Tutor_web
 */
public class Tutor_webKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: peti_numero
	 */
	public java.lang.Long peti_numero;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Integer correlativo;
	/**
	 * Creates an empty key for Entity Bean: Tutor_web
	 */
	public Tutor_webKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tutor_web
	 */
	public Tutor_webKey(
		java.lang.Long peti_numero,
		java.lang.Integer correlativo) {
		this.peti_numero = peti_numero;
		this.correlativo = correlativo;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webKey) otherKey;
			return ((this.correlativo.equals(o.correlativo)) && (this.peti_numero
				.equals(o.peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (correlativo.hashCode() + peti_numero.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Tutor_web
	 */
	public Tutor_webKey(
		java.lang.Integer correlativo,
		java.lang.Long peti_numero) {
		this.correlativo = correlativo;
		this.peti_numero = peti_numero;
	}
}