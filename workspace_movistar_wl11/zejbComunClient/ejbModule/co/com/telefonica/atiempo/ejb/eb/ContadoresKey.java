package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Contadores
 */
public class ContadoresKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: operacion_comercial_opco_id
	 */
	public java.lang.Long operacion_comercial_opco_id;
	/**
	 * Creates an empty key for Entity Bean: Contadores
	 */
	public ContadoresKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.ContadoresKey) {
			co.com.telefonica.atiempo.ejb.eb.ContadoresKey o =
				(co.com.telefonica.atiempo.ejb.eb.ContadoresKey) otherKey;
			return (
				(this
					.operacion_comercial_opco_id
					.equals(o.operacion_comercial_opco_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (operacion_comercial_opco_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Contadores
	 */
	public ContadoresKey(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialKey argOperacion_comercial) {
		privateSetOperacion_comercialKey(argOperacion_comercial);
	}
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialKey getOperacion_comercialKey() {
		co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey();
		boolean operacion_comercial_NULLTEST = true;
		operacion_comercial_NULLTEST &= (operacion_comercial_opco_id == null);
		temp.opco_id = operacion_comercial_opco_id;
		if (operacion_comercial_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetOperacion_comercialKey(
		co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey inKey) {
		boolean operacion_comercial_NULLTEST = (inKey == null);
		operacion_comercial_opco_id =
			(operacion_comercial_NULLTEST) ? null : inKey.opco_id;
	}
}
