package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Producto_servicio_st
 */
public abstract class Producto_servicio_stBean
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
		.Producto_servicio_stKey ejbCreate(java.lang.Long ps_id)
		throws javax.ejb.CreateException {
		setPs_id(ps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ps_id)
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
	 * Get accessor for persistent attribute: ps_id
	 */
	public abstract java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public abstract void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: ps_nombre
	 */
	public abstract java.lang.String getPs_nombre();
	/**
	 * Set accessor for persistent attribute: ps_nombre
	 */
	public abstract void setPs_nombre(java.lang.String newPs_nombre);
	/**
	 * Get accessor for persistent attribute: ps_observacion
	 */
	public abstract java.lang.String getPs_observacion();
	/**
	 * Set accessor for persistent attribute: ps_observacion
	 */
	public abstract void setPs_observacion(java.lang.String newPs_observacion);
	/**
	 * Get accessor for persistent attribute: ps_vigente
	 */
	public abstract java.lang.Short getPs_vigente();
	/**
	 * Set accessor for persistent attribute: ps_vigente
	 */
	public abstract void setPs_vigente(java.lang.Short newPs_vigente);
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getFlujo_prod_serv_oper_com();
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFlujo_prod_serv_oper_com(
		java.util.Collection aFlujo_prod_serv_oper_com);
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion_flujo();
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_flujo(
		java.util.Collection aPeticion_flujo);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal getFamilia_producto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFamilia_producto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal aFamilia_producto_servicio_st);
	/**
	 * Get accessor for persistent attribute: fa_ps
	 */
	public abstract java.lang.String getFa_ps();
	/**
	 * Set accessor for persistent attribute: fa_ps
	 */
	public abstract void setFa_ps(java.lang.String newFa_ps);
}
