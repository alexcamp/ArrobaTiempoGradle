package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Caracteristicas_linea
 */
public abstract class Caracteristicas_lineaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Caracteristicas_lineaKey ejbCreate(
		java.lang.Long ps_id) throws javax.ejb.CreateException {
		setPs_id(ps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ps_id)
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
	 * Get accessor for persistent attribute: ps_id
	 */
	public abstract java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public abstract void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: caracteristica
	 */
	public abstract java.lang.Long getCaracteristica();
	/**
	 * Set accessor for persistent attribute: caracteristica
	 */
	public abstract void setCaracteristica(java.lang.Long newCaracteristica);
	/**
	 * Get accessor for persistent attribute: sub_caracteristica
	 */
	public abstract java.lang.Long getSub_caracteristica();
	/**
	 * Set accessor for persistent attribute: sub_caracteristica
	 */
	public abstract void setSub_caracteristica(
		java.lang.Long newSub_caracteristica);
}