package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Grpe_Ps
 */
public abstract class Grpe_PsBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Grpe_PsKey ejbCreate(
		java.lang.Long ps_Id,
		java.lang.Long grpe_id)
		throws javax.ejb.CreateException {
		setPs_Id(ps_Id);
		setGrpe_id(grpe_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ps_Id, java.lang.Long grpe_id)
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
	 * Get accessor for persistent attribute: grpe_id
	 */
	public abstract java.lang.Long getGrpe_id();
	/**
	 * Set accessor for persistent attribute: grpe_id
	 */
	public abstract void setGrpe_id(java.lang.Long newGrpe_id);
	/**
	 * Get accessor for persistent attribute: ps_Id
	 */
	public abstract java.lang.Long getPs_Id();
	/**
	 * Set accessor for persistent attribute: ps_Id
	 */
	public abstract void setPs_Id(java.lang.Long newPs_Id);
}
