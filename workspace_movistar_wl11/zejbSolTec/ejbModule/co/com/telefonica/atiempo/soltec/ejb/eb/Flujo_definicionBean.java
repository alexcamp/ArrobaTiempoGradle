package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Flujo_definicion
 */
public abstract class Flujo_definicionBean implements javax.ejb.EntityBean {
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
		.Flujo_definicionKey ejbCreate(
		java.lang.Integer flde_id)
		throws javax.ejb.CreateException {
		setFlde_id(flde_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer flde_id)
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
	 * Get accessor for persistent attribute: flde_id
	 */
	public abstract java.lang.Integer getFlde_id();
	/**
	 * Set accessor for persistent attribute: flde_id
	 */
	public abstract void setFlde_id(java.lang.Integer newFlde_id);
	/**
	 * Get accessor for persistent attribute: act_acti_id
	 */
	public abstract java.lang.Integer getAct_acti_id();
	/**
	 * Set accessor for persistent attribute: act_acti_id
	 */
	public abstract void setAct_acti_id(java.lang.Integer newAct_acti_id);
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
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Actividad_flujoLocal getActividad_flujo();
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad_flujo(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Actividad_flujoLocal anActividad_flujo);
}
