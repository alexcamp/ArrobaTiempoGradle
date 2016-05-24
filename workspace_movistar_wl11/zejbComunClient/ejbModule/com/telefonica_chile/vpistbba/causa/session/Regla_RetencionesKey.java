package com.telefonica_chile.vpistbba.causa.session;
/**
 * Key class for Entity Bean: Regla_Retenciones
 */
public class Regla_RetencionesKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: regla_id
	 */
	public java.lang.Long regla_id;
	/**
	 * Creates an empty key for Entity Bean: Regla_Retenciones
	 */
	public Regla_RetencionesKey() {
	}
	/**
	 * Creates a key for Entity Bean: Regla_Retenciones
	 */
	public Regla_RetencionesKey(java.lang.Long regla_id) {
		this.regla_id = regla_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof com.telefonica_chile.vpistbba.causa.session.Regla_RetencionesKey) {
			com.telefonica_chile.vpistbba.causa.session.Regla_RetencionesKey o = (com.telefonica_chile.vpistbba.causa.session.Regla_RetencionesKey) otherKey;
			return ((this.regla_id.equals(o.regla_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (regla_id.hashCode());
	}
}