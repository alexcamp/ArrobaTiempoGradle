package co.com.telefonica.atiempo.ejb.eb;
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
		.ejb
		.eb
		.Flujo_prod_serv_oper_comKey ejbCreate(
		java.lang.Integer flps_id)
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Flujo_prod_serv_oper_comKey ejbCreate(
		java.lang.Integer flps_id,
		java.lang.Long prse_id,
		java.lang.Long opco_id,
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException {
		setFlps_id(flps_id);
		setPrse_id(prse_id);
		setOpco_id(opco_id);
		setFluj_id(fluj_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer flps_id,
		java.lang.Long prse_id,
		java.lang.Long opco_id,
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: prse_id
	 */
	public abstract java.lang.Long getPrse_id();
	/**
	 * Set accessor for persistent attribute: prse_id
	 */
	public abstract void setPrse_id(java.lang.Long newPrse_id);
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public abstract java.lang.Long getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public abstract void setOpco_id(java.lang.Long newOpco_id);
	/**
	 * Get accessor for persistent attribute: fluj_id
	 */
	public abstract java.lang.Integer getFluj_id();
	/**
	 * Set accessor for persistent attribute: fluj_id
	 */
	public abstract void setFluj_id(java.lang.Integer newFluj_id);
}
