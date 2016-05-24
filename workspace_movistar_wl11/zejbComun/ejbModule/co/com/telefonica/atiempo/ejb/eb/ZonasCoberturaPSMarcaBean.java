package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: ZonasCoberturaPSMarca
 */
public abstract class ZonasCoberturaPSMarcaBean implements javax.ejb.EntityBean {
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
	 * Get accessor for persistent attribute: zonaAnterior
	 */
	public abstract String getZonaAnterior();
	/**
	 * Set accessor for persistent attribute: zonaAnterior
	 */
	public abstract void setZonaAnterior(String newZonaAnterior);
	/**
	 * Get accessor for persistent attribute: zonaNueva
	 */
	public abstract String getZonaNueva();
	/**
	 * Set accessor for persistent attribute: zonaNueva
	 */
	public abstract void setZonaNueva(String newZonaNueva);
	
	/**
	 * Get accessor for persistent attribute: localidad
	 */
	public abstract java.lang.String getLocalidad();
	/**
	 * Set accessor for persistent attribute: localidad
	 */
	public abstract void setLocalidad(java.lang.String newLocalidad);
	/**
	 * Get accessor for persistent attribute: psMarca
	 */
	public abstract long getPsMarca();
	/**
	 * Set accessor for persistent attribute: psMarca
	 */
	public abstract void setPsMarca(long newPsMarca);
}