package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Agrupacion_segmento
 */
public class Agrupacion_segmentoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: codigo_segmento
	 */
	public java.lang.Long codigo_segmento;
	/**
	 * Creates an empty key for Entity Bean: Agrupacion_segmento
	 */
	public Agrupacion_segmentoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Agrupacion_segmento
	 */
	public Agrupacion_segmentoKey(java.lang.Long codigo_segmento) {
		this.codigo_segmento = codigo_segmento;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoKey) {
			co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Agrupacion_segmentoKey) otherKey;
			return ((this.codigo_segmento.equals(o.codigo_segmento)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (codigo_segmento.hashCode());
	}
}
