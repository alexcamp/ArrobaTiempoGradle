package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Atiempo_Interfaz
 */
public class Atiempo_InterfazKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: nombre
	 */
	public java.lang.String nombre;
	/**
	 * Creates an empty key for Entity Bean: Atiempo_Interfaz
	 */
	public Atiempo_InterfazKey() {
	}
	/**
	 * Creates a key for Entity Bean: Atiempo_Interfaz
	 */
	public Atiempo_InterfazKey(java.lang.String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazKey) otherKey;
			return ((this.nombre.equals(o.nombre)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (nombre.hashCode());
	}
}