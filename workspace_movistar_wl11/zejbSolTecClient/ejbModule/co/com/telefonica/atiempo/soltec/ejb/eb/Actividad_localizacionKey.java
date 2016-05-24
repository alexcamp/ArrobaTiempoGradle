package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Actividad_localizacion
 */
public class Actividad_localizacionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: act_id
	 */
	public java.lang.Long act_id;
	/**
	 * Implementation field for persistent attribute: loc_id
	 */
	public java.lang.Integer loc_id;
	/**
	 * Creates an empty key for Entity Bean: Actividad_localizacion
	 */
	public Actividad_localizacionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Actividad_localizacion
	 */
	public Actividad_localizacionKey(
		java.lang.Long act_id,
		java.lang.Integer loc_id) {
		this.act_id = act_id;
		this.loc_id = loc_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionKey o = (co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionKey) otherKey;
			return ((this.act_id.equals(o.act_id)) && (this.loc_id
				.equals(o.loc_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (act_id.hashCode() + loc_id.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: act_id
	 */
	public java.lang.Long getAct_id() {
		return act_id;
	}
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public void setAct_id(java.lang.Long newAct_id) {
		act_id = newAct_id;
	}
	/**
	 * Get accessor for persistent attribute: loc_id
	 */
	public java.lang.Integer getLoc_id() {
		return loc_id;
	}
	/**
	 * Set accessor for persistent attribute: loc_id
	 */
	public void setLoc_id(java.lang.Integer newLoc_id) {
		loc_id = newLoc_id;
	}
}
