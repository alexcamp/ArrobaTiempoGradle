package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Prueba_linea
 */
public class Prueba_lineaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_prueba
	 */
	public java.lang.Long id_prueba;
	/**
	 * Creates an empty key for Entity Bean: Prueba_linea
	 */
	public Prueba_lineaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Prueba_lineaKey) otherKey;
			return ((this.id_prueba.equals(o.id_prueba)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_prueba.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Prueba_linea
	 */
	public Prueba_lineaKey(java.lang.Long id_prueba) {
		this.id_prueba = id_prueba;
	}
}
