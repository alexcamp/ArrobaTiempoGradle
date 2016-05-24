package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Grpe_Ps
 */
public class Grpe_PsKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: ps_Id
	 */
	public java.lang.Long ps_Id;
	/**
	 * Implementation field for persistent attribute: grpe_id
	 */
	public java.lang.Long grpe_id;
	/**
	 * Creates an empty key for Entity Bean: Grpe_Ps
	 */
	public Grpe_PsKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Grpe_PsKey) {
			co.com.telefonica.atiempo.ejb.eb.Grpe_PsKey o =
				(co.com.telefonica.atiempo.ejb.eb.Grpe_PsKey) otherKey;
			return (
				(this.ps_Id.equals(o.ps_Id))
					&& (this.grpe_id.equals(o.grpe_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (ps_Id.hashCode() + grpe_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Grpe_Ps
	 */
	public Grpe_PsKey(java.lang.Long ps_Id, java.lang.Long grpe_id) {
		this.ps_Id = ps_Id;
		this.grpe_id = grpe_id;
	}
}
