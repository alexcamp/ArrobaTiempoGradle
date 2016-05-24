package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Tipo_agendamiento_segmento
 */
public class Tipo_agendamiento_segmentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: tasg_id
	 */
	public java.lang.Long tasg_id;
	/**
	 * Creates an empty key for Entity Bean: Tipo_agendamiento_segmento
	 */
	public Tipo_agendamiento_segmentoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tipo_agendamiento_segmento
	 */
	public Tipo_agendamiento_segmentoKey(java.lang.Long tasg_id) {
		this.tasg_id = tasg_id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoKey) {
			co.com.telefonica.atiempo.ejb.eb.Tipo_agendamiento_segmentoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Tipo_agendamiento_segmentoKey) otherKey;
			return ((this.tasg_id.equals(o.tasg_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (tasg_id.hashCode());
	}
}
