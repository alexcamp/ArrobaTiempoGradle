package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tipo_agenda
 */
public abstract class Tipo_agendaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Tipo_agendaKey ejbCreate(
		java.lang.Long tiag_id)
		throws javax.ejb.CreateException {
		setTiag_id(tiag_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long tiag_id)
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
	 * Get accessor for persistent attribute: tiag_id
	 */
	public abstract java.lang.Long getTiag_id();
	/**
	 * Set accessor for persistent attribute: tiag_id
	 */
	public abstract void setTiag_id(java.lang.Long newTiag_id);
	/**
	 * Get accessor for persistent attribute: tiag_descripcion
	 */
	public abstract java.lang.String getTiag_descripcion();
	/**
	 * Set accessor for persistent attribute: tiag_descripcion
	 */
	public abstract void setTiag_descripcion(
		java.lang.String newTiag_descripcion);
}
