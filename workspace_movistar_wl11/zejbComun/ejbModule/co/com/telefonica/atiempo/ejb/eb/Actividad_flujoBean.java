package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Actividad_flujo
 */
public abstract class Actividad_flujoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey ejbCreate(
		java.lang.Integer acti_id)
		throws javax.ejb.CreateException {
		setActi_id(acti_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer acti_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey ejbCreate(
		java.lang.Integer acti_id,
		java.lang.String acti_codigo)
		throws javax.ejb.CreateException {
		setActi_id(acti_id);
		setActi_codigo(acti_codigo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer acti_id,
		java.lang.String acti_codigo)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: acti_id
	 */
	public abstract java.lang.Integer getActi_id();
	/**
	 * Set accessor for persistent attribute: acti_id
	 */
	public abstract void setActi_id(java.lang.Integer newActi_id);
	/**
	 * Get accessor for persistent attribute: acti_codigo
	 */
	public abstract java.lang.String getActi_codigo();
	/**
	 * Set accessor for persistent attribute: acti_codigo
	 */
	public abstract void setActi_codigo(java.lang.String newActi_codigo);
	/**
	 * Get accessor for persistent attribute: acti_descripcion
	 */
	public abstract java.lang.String getActi_descripcion();
	/**
	 * Set accessor for persistent attribute: acti_descripcion
	 */
	public abstract void setActi_descripcion(
		java.lang.String newActi_descripcion);
	/**
	 * Get accessor for persistent attribute: acti_orden
	 */
	public abstract java.lang.Integer getActi_orden();
	/**
	 * Set accessor for persistent attribute: acti_orden
	 */
	public abstract void setActi_orden(java.lang.Integer newActi_orden);
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
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCatalogo_causal(
		java.util.Collection aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad(java.util.Collection anActividad);
}
