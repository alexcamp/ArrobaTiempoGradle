package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Parametro
 */
public abstract class ParametroBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ParametroKey ejbCreate(
		java.lang.Long para_id)
		throws javax.ejb.CreateException {
		setPara_id(para_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long para_id)
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
	public co.com.telefonica.atiempo.ejb.eb.ParametroKey ejbCreate(
		java.lang.Long para_id,
		java.lang.String para_nombre,
		java.lang.String para_valor)
		throws javax.ejb.CreateException {
		setPara_id(para_id);
		setPara_nombre(para_nombre);
		setPara_valor(para_valor);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long para_id,
		java.lang.String para_nombre,
		java.lang.String para_valor)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: para_id
	 */
	public abstract java.lang.Long getPara_id();
	/**
	 * Set accessor for persistent attribute: para_id
	 */
	public abstract void setPara_id(java.lang.Long newPara_id);
	/**
	 * Get accessor for persistent attribute: para_nombre
	 */
	public abstract java.lang.String getPara_nombre();
	/**
	 * Set accessor for persistent attribute: para_nombre
	 */
	public abstract void setPara_nombre(java.lang.String newPara_nombre);
	/**
	 * Get accessor for persistent attribute: para_valor
	 */
	public abstract java.lang.String getPara_valor();
	/**
	 * Set accessor for persistent attribute: para_valor
	 */
	public abstract void setPara_valor(java.lang.String newPara_valor);
}
