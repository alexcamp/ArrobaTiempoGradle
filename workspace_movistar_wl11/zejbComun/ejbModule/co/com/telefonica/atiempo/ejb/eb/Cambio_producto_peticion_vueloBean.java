package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Cambio_producto_peticion_vuelo
 */
public abstract class Cambio_producto_peticion_vueloBean
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
	public co.com.telefonica.atiempo.ejb.eb.Cambio_producto_peticion_vueloKey ejbCreate(
		java.lang.Double id) throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Double id)
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
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Double getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Double newId);
	/**
	 * Get accessor for persistent attribute: prse_id_orig
	 */
	public abstract java.lang.Double getPrse_id_orig();
	/**
	 * Set accessor for persistent attribute: prse_id_orig
	 */
	public abstract void setPrse_id_orig(java.lang.Double newPrse_id_orig);
	/**
	 * Get accessor for persistent attribute: prse_id_dest
	 */
	public abstract java.lang.Double getPrse_id_dest();
	/**
	 * Set accessor for persistent attribute: prse_id_dest
	 */
	public abstract void setPrse_id_dest(java.lang.Double newPrse_id_dest);
}