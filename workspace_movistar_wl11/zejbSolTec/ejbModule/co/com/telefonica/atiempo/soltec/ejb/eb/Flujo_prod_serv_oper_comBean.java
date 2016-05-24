package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Flujo_prod_serv_oper_com
 */
public abstract class Flujo_prod_serv_oper_comBean
	implements javax.ejb.EntityBean {
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
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Flujo_prod_serv_oper_comKey ejbCreate(java.lang.Integer flps_id)
		throws javax.ejb.CreateException {
		setFlps_id(flps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer flps_id)
		throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: flps_id
	 */
	public abstract java.lang.Integer getFlps_id();
	/**
	 * Set accessor for persistent attribute: flps_id
	 */
	public abstract void setFlps_id(java.lang.Integer newFlps_id);
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public abstract java.lang.Integer getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public abstract void setOpco_id(java.lang.Integer newOpco_id);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_stLocal getProducto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Producto_servicio_stLocal aProducto_servicio_st);
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.FlujoLocal getFlujo();
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFlujo(
		co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal aFlujo);
}
