package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Canal
 */
public abstract class CanalBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.CanalKey ejbCreate(
		java.lang.Long cod_cnl_ven_cd)
		throws javax.ejb.CreateException {
		setCod_cnl_ven_cd(cod_cnl_ven_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_cnl_ven_cd)
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
	 * Get accessor for persistent attribute: cod_cnl_ven_cd
	 */
	public abstract java.lang.Long getCod_cnl_ven_cd();
	/**
	 * Set accessor for persistent attribute: cod_cnl_ven_cd
	 */
	public abstract void setCod_cnl_ven_cd(java.lang.Long newCod_cnl_ven_cd);
	/**
	 * Get accessor for persistent attribute: dsc_cnl_ven_cd
	 */
	public abstract java.lang.String getDsc_cnl_ven_cd();
	/**
	 * Set accessor for persistent attribute: dsc_cnl_ven_cd
	 */
	public abstract void setDsc_cnl_ven_cd(java.lang.String newDsc_cnl_ven_cd);
}
