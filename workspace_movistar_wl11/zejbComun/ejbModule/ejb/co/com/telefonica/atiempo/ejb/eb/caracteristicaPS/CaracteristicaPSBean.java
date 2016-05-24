package ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS;
/**
 * Bean implementation class for Enterprise Bean: CaracteristicaPS
 */
public abstract class CaracteristicaPSBean implements javax.ejb.EntityBean {
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
	public java.lang.Integer ejbCreate(java.lang.Integer id)
		throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id)
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
	public abstract java.lang.Integer getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Integer newId);
	/**
	 * Get accessor for persistent attribute: caracteristicaPS
	 */
	public abstract java.lang.String getCaracteristicaPS();
	/**
	 * Set accessor for persistent attribute: caracteristicaPS
	 */
	public abstract void setCaracteristicaPS(
		java.lang.String newCaracteristicaPS);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio(
		co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal aProducto_servicio);
	/**
	 * Get accessor for persistent attribute: psPeticion
	 */
	public abstract java.lang.Long getPsPeticion();
	/**
	 * Set accessor for persistent attribute: psPeticion
	 */
	public abstract void setPsPeticion(java.lang.Long newPsPeticion);
	/**
	 * Get accessor for persistent attribute: psId
	 */
	public abstract java.lang.Long getPsId();
	/**
	 * Set accessor for persistent attribute: psId
	 */
	public abstract void setPsId(java.lang.Long newPsId);
}