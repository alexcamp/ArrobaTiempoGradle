package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Estado_homologacion
 */
public class Estado_homologacionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_app
	 */
	public java.lang.String cod_app;
	/**
	 * Implementation field for persistent attribute: cod_estado
	 */
	public java.lang.String cod_estado;
	/**
	 * Creates an empty key for Entity Bean: Estado_homologacion
	 */
	public Estado_homologacionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Estado_homologacion
	 */
	public Estado_homologacionKey(
		java.lang.String cod_app,
		java.lang.String cod_estado) {
		this.cod_app = cod_app;
		this.cod_estado = cod_estado;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey) {
			co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey o = (co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey) otherKey;
			return ((this.cod_app.equals(o.cod_app)) && (this.cod_estado
				.equals(o.cod_estado)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_app.hashCode() + cod_estado.hashCode());
	}
}