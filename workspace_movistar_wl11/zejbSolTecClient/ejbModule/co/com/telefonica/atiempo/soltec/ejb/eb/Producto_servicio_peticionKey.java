package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Producto_servicio_peticion
 */
public class Producto_servicio_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: peti_numero
	 */
	public java.lang.Long peti_numero;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Creates an empty key for Entity Bean: Producto_servicio_peticion
	 */
	public Producto_servicio_peticionKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Producto_servicio_peticionKey) {
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Producto_servicio_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Producto_servicio_peticionKey) otherKey;
			return (
				(this.peti_numero.equals(o.peti_numero))
					&& (this.correlativo.equals(o.correlativo)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (peti_numero.hashCode() + correlativo.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Producto_servicio_peticion
	 */
	public Producto_servicio_peticionKey(
		java.lang.Long peti_numero,
		java.lang.Long correlativo) {
		this.peti_numero = peti_numero;
		this.correlativo = correlativo;
	}
}
