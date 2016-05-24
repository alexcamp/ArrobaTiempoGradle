package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Familia_producto_servicio
 */
public abstract class Familia_producto_servicioBean
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
		.ejb
		.eb
		.Familia_producto_servicioKey ejbCreate(
		java.lang.Long faps_id)
		throws javax.ejb.CreateException {
		setFaps_id(faps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long faps_id)
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Familia_producto_servicioKey ejbCreate(
		java.lang.Long faps_id,
		java.lang.String faps_nombre)
		throws javax.ejb.CreateException {
		setFaps_id(faps_id);
		setFaps_nombre(faps_nombre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long faps_id,
		java.lang.String faps_nombre)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: faps_id
	 */
	public abstract java.lang.Long getFaps_id();
	/**
	 * Set accessor for persistent attribute: faps_id
	 */
	public abstract void setFaps_id(java.lang.Long newFaps_id);
	/**
	 * Get accessor for persistent attribute: faps_nombre
	 */
	public abstract java.lang.String getFaps_nombre();
	/**
	 * Set accessor for persistent attribute: faps_nombre
	 */
	public abstract void setFaps_nombre(java.lang.String newFaps_nombre);
	/**
	 * Get accessor for persistent attribute: faps_codigo
	 */
	public abstract java.lang.String getFaps_codigo();
	/**
	 * Set accessor for persistent attribute: faps_codigo
	 */
	public abstract void setFaps_codigo(java.lang.String newFaps_codigo);
	/**
	 * Get accessor for persistent attribute: faps_prioridad
	 */
	public abstract java.lang.Integer getFaps_prioridad();
	/**
	 * Set accessor for persistent attribute: faps_prioridad
	 */
	public abstract void setFaps_prioridad(java.lang.Integer newFaps_prioridad);
	/**
	 * Get accessor for persistent attribute: faps_id_padre
	 */
	public abstract java.lang.Long getFaps_id_padre();
	/**
	 * Set accessor for persistent attribute: faps_id_padre
	 */
	public abstract void setFaps_id_padre(java.lang.Long newFaps_id_padre);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio(
		java.util.Collection aProducto_servicio);
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_ps_oc_actividad();
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_ps_oc_actividad(
		java.util.Collection aCausal_ps_oc_actividad);
}
