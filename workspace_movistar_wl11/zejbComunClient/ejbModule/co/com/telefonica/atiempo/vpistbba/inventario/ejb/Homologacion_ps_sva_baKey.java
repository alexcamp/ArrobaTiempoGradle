package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Key class for Entity Bean: Homologacion_ps_sva_ba
 */
public class Homologacion_ps_sva_baKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_ba
	 */
	public java.lang.Long ps_ba;
	/**
	 * Implementation field for persistent attribute: ps_sva
	 */
	public java.lang.Long ps_sva;
	/**
	 * Creates an empty key for Entity Bean: Homologacion_ps_sva_ba
	 */
	public Homologacion_ps_sva_baKey() {
	}
	/**
	 * Creates a key for Entity Bean: Homologacion_ps_sva_ba
	 */
	public Homologacion_ps_sva_baKey(java.lang.Long ps_ba, java.lang.Long ps_sva) {
		this.ps_ba = ps_ba;
		this.ps_sva = ps_sva;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baKey) {
			co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baKey o = (co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baKey) otherKey;
			return ((this.ps_ba.equals(o.ps_ba)) && (this.ps_sva
				.equals(o.ps_sva)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ps_ba.hashCode() + ps_sva.hashCode());
	}
}