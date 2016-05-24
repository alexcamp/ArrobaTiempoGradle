package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tipo_Eq_Elemento
 */
public class Tipo_Eq_ElementoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_tipo_eq
	 */
	public java.lang.Integer id_tipo_eq;
	/**
	 * Implementation field for persistent attribute: id_elemento
	 */
	public java.lang.Integer id_elemento;
	/**
	 * Creates an empty key for Entity Bean: Tipo_Eq_Elemento
	 */
	public Tipo_Eq_ElementoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tipo_Eq_Elemento
	 */
	public Tipo_Eq_ElementoKey(
		java.lang.Integer id_tipo_eq,
		java.lang.Integer id_elemento) {
		this.id_tipo_eq = id_tipo_eq;
		this.id_elemento = id_elemento;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey) {
			co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey o = (co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey) otherKey;
			return ((this.id_tipo_eq.equals(o.id_tipo_eq)) && (this.id_elemento
				.equals(o.id_elemento)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_tipo_eq.hashCode() + id_elemento.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: id_tipo_eq
	 */
	public java.lang.Integer getId_tipo_eq() {
		return id_tipo_eq;
	}
	/**
	 * Set accessor for persistent attribute: id_tipo_eq
	 */
	public void setId_tipo_eq(java.lang.Integer newId_tipo_eq) {
		id_tipo_eq = newId_tipo_eq;
	}
	/**
	 * Get accessor for persistent attribute: id_elemento
	 */
	public java.lang.Integer getId_elemento() {
		return id_elemento;
	}
	/**
	 * Set accessor for persistent attribute: id_elemento
	 */
	public void setId_elemento(java.lang.Integer newId_elemento) {
		id_elemento = newId_elemento;
	}
}
