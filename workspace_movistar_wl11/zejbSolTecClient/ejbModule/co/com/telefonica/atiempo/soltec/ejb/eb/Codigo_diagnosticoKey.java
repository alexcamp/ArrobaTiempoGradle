package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Codigo_diagnostico
 */
public class Codigo_diagnosticoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_def_aper
	 */
	public java.lang.String id_def_aper;
	/**
	 * Creates an empty key for Entity Bean: Codigo_diagnostico
	 */
	public Codigo_diagnosticoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_diagnosticoKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_diagnosticoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Codigo_diagnosticoKey) otherKey;
			return ((this.id_def_aper.equals(o.id_def_aper)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_def_aper.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Codigo_diagnostico
	 */
	public Codigo_diagnosticoKey(java.lang.String id_def_aper) {
		this.id_def_aper = id_def_aper;
	}
}
