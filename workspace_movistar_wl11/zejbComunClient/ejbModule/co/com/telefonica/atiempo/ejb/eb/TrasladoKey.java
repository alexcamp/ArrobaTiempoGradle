package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Traslado
 */
public class TrasladoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: producto_servicio_ps_id
	 */
	public java.lang.Long producto_servicio_ps_id;
	/**
	 * Implementation field for persistent attribute: operacion_comercial_opco_id
	 */
	public java.lang.Long operacion_comercial_opco_id;
	/**
	 * Creates an empty key for Entity Bean: Traslado
	 */
	public TrasladoKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.TrasladoKey) {
			co.com.telefonica.atiempo.ejb.eb.TrasladoKey o =
				(co.com.telefonica.atiempo.ejb.eb.TrasladoKey) otherKey;
			return (
				(this
					.producto_servicio_ps_id
					.equals(o.producto_servicio_ps_id))
					&& (this
						.operacion_comercial_opco_id
						.equals(o.operacion_comercial_opco_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			producto_servicio_ps_id.hashCode()
				+ operacion_comercial_opco_id.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Traslado
	 */
	public TrasladoKey(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioKey argProducto_servicio,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialKey argOperacion_comercial) {
		privateSetProducto_servicioKey(argProducto_servicio);
		privateSetOperacion_comercialKey(argOperacion_comercial);
	}
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicioKey getProducto_servicioKey() {
		co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey();
		boolean producto_servicio_NULLTEST = true;
		producto_servicio_NULLTEST &= (producto_servicio_ps_id == null);
		temp.ps_id = producto_servicio_ps_id;
		if (producto_servicio_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetProducto_servicioKey(
		co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey inKey) {
		boolean producto_servicio_NULLTEST = (inKey == null);
		producto_servicio_ps_id =
			(producto_servicio_NULLTEST) ? null : inKey.ps_id;
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
