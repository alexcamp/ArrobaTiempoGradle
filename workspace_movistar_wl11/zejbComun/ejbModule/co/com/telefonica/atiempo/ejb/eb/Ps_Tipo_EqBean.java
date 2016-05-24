package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Ps_Tipo_Eq
 */
public abstract class Ps_Tipo_EqBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey ejbCreate(
		java.lang.Integer ps_id,
		java.lang.Integer id_tipo_eq) throws javax.ejb.CreateException {
		setPs_id(ps_id);
		setId_tipo_eq(id_tipo_eq);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer ps_id,
		java.lang.Integer id_tipo_eq) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: ps_id
	 */
	public abstract java.lang.Integer getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public abstract void setPs_id(java.lang.Integer newPs_id);
	/**
	 * Get accessor for persistent attribute: id_tipo_eq
	 */
	public abstract java.lang.Integer getId_tipo_eq();
	/**
	 * Set accessor for persistent attribute: id_tipo_eq
	 */
	public abstract void setId_tipo_eq(java.lang.Integer newId_tipo_eq);
	/**
	 * Get accessor for persistent attribute: id_elemento_agenda
	 */
	public abstract java.lang.Integer getId_elemento_agenda();
	/**
	 * Set accessor for persistent attribute: id_elemento_agenda
	 */
	public abstract void setId_elemento_agenda(
		java.lang.Integer newId_elemento_agenda);
}
