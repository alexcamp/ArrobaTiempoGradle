package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Elemento
 */
public abstract class ElementoBean implements javax.ejb.EntityBean {
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
	public java.lang.Integer ejbCreate(java.lang.Integer id_elemento)
		throws javax.ejb.CreateException {
		setId_elemento(id_elemento);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id_elemento)
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
	 * Get accessor for persistent attribute: id_elemento
	 */
	public abstract java.lang.Integer getId_elemento();
	/**
	 * Set accessor for persistent attribute: id_elemento
	 */
	public abstract void setId_elemento(java.lang.Integer newId_elemento);
	/**
	 * Get accessor for persistent attribute: desc_elemento
	 */
	public abstract java.lang.String getDesc_elemento();
	/**
	 * Set accessor for persistent attribute: desc_elemento
	 */
	public abstract void setDesc_elemento(java.lang.String newDesc_elemento);
}
