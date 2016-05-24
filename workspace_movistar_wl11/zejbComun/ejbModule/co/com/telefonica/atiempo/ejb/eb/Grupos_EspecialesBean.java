package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Grupos_Especiales
 */
public abstract class Grupos_EspecialesBean implements javax.ejb.EntityBean {
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
	public java.lang.Long ejbCreate(java.lang.Long grpe_id)
		throws javax.ejb.CreateException {
		setGrpe_id(grpe_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long grpe_id)
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
	 * Get accessor for persistent attribute: grpe_tipo
	 */
	public abstract java.lang.String getGrpe_tipo();
	/**
	 * Set accessor for persistent attribute: grpe_tipo
	 */
	public abstract void setGrpe_tipo(java.lang.String newGrpe_tipo);
	/**
	 * Get accessor for persistent attribute: grpe_descripcion
	 */
	public abstract java.lang.String getGrpe_descripcion();
	/**
	 * Set accessor for persistent attribute: grpe_descripcion
	 */
	public abstract void setGrpe_descripcion(
		java.lang.String newGrpe_descripcion);
		
}
