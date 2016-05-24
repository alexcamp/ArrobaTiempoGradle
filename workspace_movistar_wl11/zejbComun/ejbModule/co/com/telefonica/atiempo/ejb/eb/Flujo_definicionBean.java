package co.com.telefonica.atiempo.ejb.eb;
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
	public co.com.telefonica.atiempo.ejb.eb.Flujo_definicionKey ejbCreate(
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Flujo_definicionKey ejbCreate(
		java.lang.Integer flde_id,
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException {
		setFlde_id(flde_id);
		setFluj_id(fluj_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer flde_id,
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: fluj_id
	 */
	public abstract java.lang.Integer getFluj_id();
	/**
	 * Set accessor for persistent attribute: fluj_id
	 */
	public abstract void setFluj_id(java.lang.Integer newFluj_id);
	/**
	 * Get accessor for persistent attribute: acti_id
	 */
	public abstract java.lang.Integer getActi_id();
	/**
	 * Set accessor for persistent attribute: acti_id
	 */
	public abstract void setActi_id(java.lang.Integer newActi_id);
	/**
	 * Get accessor for persistent attribute: act_acti_id
	 */
	public abstract java.lang.Integer getAct_acti_id();
	/**
	 * Set accessor for persistent attribute: act_acti_id
	 */
	public abstract void setAct_acti_id(java.lang.Integer newAct_acti_id);
}
