package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Operacion_comercial
 */
public abstract class Operacion_comercialBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey ejbCreate(
		java.lang.Long opco_id)
		throws javax.ejb.CreateException {
		setOpco_id(opco_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long opco_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey ejbCreate(
		java.lang.Long opco_id,
		java.lang.Integer titr_id)
		throws javax.ejb.CreateException {
		setOpco_id(opco_id);
		setTitr_id(titr_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long opco_id,
		java.lang.Integer titr_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public abstract java.lang.Long getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public abstract void setOpco_id(java.lang.Long newOpco_id);
	/**
	 * Get accessor for persistent attribute: titr_id
	 */
	public abstract java.lang.Integer getTitr_id();
	/**
	 * Set accessor for persistent attribute: titr_id
	 */
	public abstract void setTitr_id(java.lang.Integer newTitr_id);
	/**
	 * Get accessor for persistent attribute: opco_nombre
	 */
	public abstract java.lang.String getOpco_nombre();
	/**
	 * Set accessor for persistent attribute: opco_nombre
	 */
	public abstract void setOpco_nombre(java.lang.String newOpco_nombre);
	/**
	 * Get accessor for persistent attribute: opco_tipo
	 */
	public abstract java.lang.String getOpco_tipo();
	/**
	 * Set accessor for persistent attribute: opco_tipo
	 */
	public abstract void setOpco_tipo(java.lang.String newOpco_tipo);
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
	/**
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTraslado();
	/**
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTraslado(java.util.Collection aTraslado);
	/**
	 * Get accessor for persistent attribute: opco_tras
	 */
	public abstract java.lang.String getOpco_tras();
	/**
	 * Set accessor for persistent attribute: opco_tras
	 */
	public abstract void setOpco_tras(java.lang.String newOpco_tras);
	/**
	 * This method was generated for supporting the relationship role named contadores.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getContadores();
	/**
	 * This method was generated for supporting the relationship role named contadores.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setContadores(java.util.Collection aContadores);
	/**
	 * Get accessor for persistent attribute: opco_rev
	 */
	public abstract java.lang.Long getOpco_rev();
	/**
	 * Set accessor for persistent attribute: opco_rev
	 */
	public abstract void setOpco_rev(java.lang.Long newOpco_rev);
	/**
	 * Get accessor for persistent attribute: opco_web_tutor
	 */
	public abstract java.lang.String getOpco_web_tutor();
	/**
	 * Set accessor for persistent attribute: opco_web_tutor
	 */
	public abstract void setOpco_web_tutor(java.lang.String newOpco_web_tutor);
	/**
	 * Get accessor for persistent attribute: opco_sva
	 */
	public abstract java.lang.String getOpco_sva();
	/**
	 * Set accessor for persistent attribute: opco_sva
	 */
	public abstract void setOpco_sva(java.lang.String newOpco_sva);
}
