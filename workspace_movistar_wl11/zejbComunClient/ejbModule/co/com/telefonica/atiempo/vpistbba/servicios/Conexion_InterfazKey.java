package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Conexion_Interfaz
 */
public class Conexion_InterfazKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: interfaz
	 */
	public java.lang.String interfaz;
	/**
	 * Implementation field for persistent attribute: propiedad
	 */
	public java.lang.String propiedad;
	/**
	 * Creates an empty key for Entity Bean: Conexion_Interfaz
	 */
	public Conexion_InterfazKey() {
	}
	/**
	 * Creates a key for Entity Bean: Conexion_Interfaz
	 */
	public Conexion_InterfazKey(
		java.lang.String interfaz,
		java.lang.String propiedad) {
		this.interfaz = interfaz;
		this.propiedad = propiedad;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey) otherKey;
			return ((this.interfaz.equals(o.interfaz)) && (this.propiedad
				.equals(o.propiedad)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (interfaz.hashCode() + propiedad.hashCode());
	}
}