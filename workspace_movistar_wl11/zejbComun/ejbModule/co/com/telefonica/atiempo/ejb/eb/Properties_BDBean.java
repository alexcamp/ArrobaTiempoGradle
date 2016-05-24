package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Properties_BD
 */
public abstract class Properties_BDBean implements javax.ejb.EntityBean {
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
	public java.lang.String ejbCreate(java.lang.String codigo)
		throws javax.ejb.CreateException {
		setCodigo(codigo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String codigo)
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
	 * Get accessor for persistent attribute: codigo
	 */
	public abstract java.lang.String getCodigo();
	/**
	 * Set accessor for persistent attribute: codigo
	 */
	public abstract void setCodigo(java.lang.String newCodigo);
	/**
	 * Get accessor for persistent attribute: valor
	 */
	public abstract java.lang.String getValor();
	/**
	 * Set accessor for persistent attribute: valor
	 */
	public abstract void setValor(java.lang.String newValor);
}