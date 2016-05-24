package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Ps_Tipo_Eq
 */
public class Ps_Tipo_EqKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_id
	 */
	public java.lang.Integer ps_id;
	/**
	 * Implementation field for persistent attribute: id_tipo_eq
	 */
	public java.lang.Integer id_tipo_eq;
	/**
	 * Creates an empty key for Entity Bean: Ps_Tipo_Eq
	 */
	public Ps_Tipo_EqKey() {
	}
	/**
	 * Creates a key for Entity Bean: Ps_Tipo_Eq
	 */
	public Ps_Tipo_EqKey(java.lang.Integer ps_id, java.lang.Integer id_tipo_eq) {
		this.ps_id = ps_id;
		this.id_tipo_eq = id_tipo_eq;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey) {
			co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey o = (co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey) otherKey;
			return ((this.ps_id.equals(o.ps_id)) && (this.id_tipo_eq
				.equals(o.id_tipo_eq)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ps_id.hashCode() + id_tipo_eq.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: ps_id
	 */
	public java.lang.Integer getPs_id() {
		return ps_id;
	}
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public void setPs_id(java.lang.Integer newPs_id) {
		ps_id = newPs_id;
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
}
