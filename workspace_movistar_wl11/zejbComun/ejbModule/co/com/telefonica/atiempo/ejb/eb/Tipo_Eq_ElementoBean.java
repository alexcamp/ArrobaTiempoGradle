package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tipo_Eq_Elemento
 */
public abstract class Tipo_Eq_ElementoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey ejbCreate(
		java.lang.Integer id_tipo_eq,
		java.lang.Integer id_elemento) throws javax.ejb.CreateException {
		setId_tipo_eq(id_tipo_eq);
		setId_elemento(id_elemento);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer id_tipo_eq,
		java.lang.Integer id_elemento) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: id_tipo_eq
	 */
	public abstract java.lang.Integer getId_tipo_eq();
	/**
	 * Set accessor for persistent attribute: id_tipo_eq
	 */
	public abstract void setId_tipo_eq(java.lang.Integer newId_tipo_eq);
	/**
	 * Get accessor for persistent attribute: id_elemento
	 */
	public abstract java.lang.Integer getId_elemento();
	/**
	 * Set accessor for persistent attribute: id_elemento
	 */
	public abstract void setId_elemento(java.lang.Integer newId_elemento);
}
