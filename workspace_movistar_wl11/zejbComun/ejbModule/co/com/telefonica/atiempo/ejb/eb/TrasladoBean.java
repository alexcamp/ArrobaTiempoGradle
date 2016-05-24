package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Traslado
 */
public abstract class TrasladoBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.TrasladoKey ejbCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioLocal argProducto_servicio,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal argOperacion_comercial)
		throws javax.ejb.CreateException {
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioKey argProducto_servicioPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Producto_servicioKey) argProducto_servicio
				.getPrimaryKey();
		setProducto_servicio_ps_id(argProducto_servicioPK.ps_id);
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialKey argOperacion_comercialPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Operacion_comercialKey) argOperacion_comercial
				.getPrimaryKey();
		setOperacion_comercial_opco_id(argOperacion_comercialPK.opco_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioLocal argProducto_servicio,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal argOperacion_comercial)
		throws javax.ejb.CreateException {
		setProducto_servicio(argProducto_servicio);
		setOperacion_comercial(argOperacion_comercial);
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicioLocal getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioLocal aProducto_servicio);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialLocal getOperacion_comercial();
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setOperacion_comercial(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal anOperacion_comercial);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.TrasladoKey ejbCreate(
		java.lang.Long producto_servicio_ps_id,
		java.lang.Long operacion_comercial_opco_id)
		throws javax.ejb.CreateException {
		setProducto_servicio_ps_id(producto_servicio_ps_id);
		setOperacion_comercial_opco_id(operacion_comercial_opco_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long producto_servicio_ps_id,
		java.lang.Long operacion_comercial_opco_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: producto_servicio_ps_id
	 */
	public abstract java.lang.Long getProducto_servicio_ps_id();
	/**
	 * Set accessor for persistent attribute: producto_servicio_ps_id
	 */
	public abstract void setProducto_servicio_ps_id(
		java.lang.Long newProducto_servicio_ps_id);
	/**
	 * Get accessor for persistent attribute: operacion_comercial_opco_id
	 */
	public abstract java.lang.Long getOperacion_comercial_opco_id();
	/**
	 * Set accessor for persistent attribute: operacion_comercial_opco_id
	 */
	public abstract void setOperacion_comercial_opco_id(
		java.lang.Long newOperacion_comercial_opco_id);
}
