package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Flujo_cambio_producto
 */
public abstract class Flujo_cambio_productoBean
	implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Flujo_cambio_productoKey ejbCreate(
		java.lang.Long id)
		throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id)
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
	public abstract java.lang.Long getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Long newId);
	/**
	 * Get accessor for persistent attribute: prse_id_orig
	 */
	public abstract java.lang.Long getPrse_id_orig();
	/**
	 * Set accessor for persistent attribute: prse_id_orig
	 */
	public abstract void setPrse_id_orig(java.lang.Long newPrse_id_orig);
	/**
	 * Get accessor for persistent attribute: prse_id_dest
	 */
	public abstract java.lang.Long getPrse_id_dest();
	/**
	 * Set accessor for persistent attribute: prse_id_dest
	 */
	public abstract void setPrse_id_dest(java.lang.Long newPrse_id_dest);
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.FlujoLocal getFlujo();
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFlujo(
		co.com.telefonica.atiempo.ejb.eb.FlujoLocal aFlujo);
}
