package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Actividad_localizacion
 */
public abstract class Actividad_localizacionBean
	implements
		javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_localizacionKey ejbCreate(
		java.lang.Long act_id,
		java.lang.Integer loc_id) throws javax.ejb.CreateException {
		setAct_id(act_id);
		setLoc_id(loc_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long act_id, java.lang.Integer loc_id)
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
	 * Get accessor for persistent attribute: act_id
	 */
	public abstract java.lang.Long getAct_id();
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public abstract void setAct_id(java.lang.Long newAct_id);
	/**
	 * Get accessor for persistent attribute: loc_id
	 */
	public abstract java.lang.Integer getLoc_id();
	/**
	 * Set accessor for persistent attribute: loc_id
	 */
	public abstract void setLoc_id(java.lang.Integer newLoc_id);
	/**
	 * Get accessor for persistent attribute: desc_loc
	 */
	public abstract java.lang.String getDesc_loc();
	/**
	 * Set accessor for persistent attribute: desc_loc
	 */
	public abstract void setDesc_loc(java.lang.String newDesc_loc);
}
