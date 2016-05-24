package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Causa_demora
 */
public class Causa_demoraKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_caudem
	 */
	public java.lang.Long cod_caudem;
	/**
	 * Creates an empty key for Entity Bean: Causa_demora
	 */
	public Causa_demoraKey() {
	}
	/**
	 * Creates a key for Entity Bean: Causa_demora
	 */
	public Causa_demoraKey(java.lang.Long cod_caudem) {
		this.cod_caudem = cod_caudem;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey) {
			co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey o = (co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey) otherKey;
			return ((this.cod_caudem.equals(o.cod_caudem)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_caudem.hashCode());
	}
}