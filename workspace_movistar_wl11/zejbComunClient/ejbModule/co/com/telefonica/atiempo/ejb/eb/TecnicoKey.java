package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tecnico
 */
public class TecnicoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_tecnico
	 */
	public java.lang.String cod_tecnico;
	/**
	 * Creates an empty key for Entity Bean: Tecnico
	 */
	public TecnicoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.TecnicoKey) {
			co.com.telefonica.atiempo.ejb.eb.TecnicoKey o =
				(co.com.telefonica.atiempo.ejb.eb.TecnicoKey) otherKey;
			return ((this.cod_tecnico.equals(o.cod_tecnico)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_tecnico.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Tecnico
	 */
	public TecnicoKey(java.lang.String cod_tecnico) {
		this.cod_tecnico = cod_tecnico;
	}
}
