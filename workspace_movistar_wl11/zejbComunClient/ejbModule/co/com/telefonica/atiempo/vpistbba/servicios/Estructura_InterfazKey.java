package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Key class for Entity Bean: Estructura_Interfaz
 */
public class Estructura_InterfazKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: aci_tr
	 */
	public java.lang.String aci_tr;
	/**
	 * Implementation field for persistent attribute: aci_ap_id
	 */
	public java.lang.Long aci_ap_id;
	/**
	 * Implementation field for persistent attribute: aci_tag
	 */
	public java.lang.String aci_tag;
	/**
	 * Creates an empty key for Entity Bean: Estructura_Interfaz
	 */
	public Estructura_InterfazKey() {
	}
	/**
	 * Creates a key for Entity Bean: Estructura_Interfaz
	 */
	public Estructura_InterfazKey(
		java.lang.String aci_tr,
		java.lang.Long aci_ap_id,
		java.lang.String aci_tag) {
		this.aci_tr = aci_tr;
		this.aci_ap_id = aci_ap_id;
		this.aci_tag = aci_tag;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey) {
			co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey o = (co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey) otherKey;
			return ((this.aci_tr.equals(o.aci_tr))
				&& (this.aci_ap_id.equals(o.aci_ap_id)) && (this.aci_tag
				.equals(o.aci_tag)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (aci_tr.hashCode() + aci_ap_id.hashCode() + aci_tag.hashCode());
	}
}