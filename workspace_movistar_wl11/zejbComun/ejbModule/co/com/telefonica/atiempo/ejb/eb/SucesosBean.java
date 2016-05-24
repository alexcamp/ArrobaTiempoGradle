package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Sucesos
 */
public abstract class SucesosBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.SucesosKey ejbCreate(
		java.lang.Integer id) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: fecha_inicio
	 */
	public abstract java.sql.Timestamp getFecha_inicio();
	/**
	 * Set accessor for persistent attribute: fecha_inicio
	 */
	public abstract void setFecha_inicio(java.sql.Timestamp newFecha_inicio);
	/**
	 * Get accessor for persistent attribute: fecha_fin
	 */
	public abstract java.sql.Timestamp getFecha_fin();
	/**
	 * Set accessor for persistent attribute: fecha_fin
	 */
	public abstract void setFecha_fin(java.sql.Timestamp newFecha_fin);
	/**
	 * Get accessor for persistent attribute: mensaje
	 */
	public abstract java.lang.String getMensaje();
	/**
	 * Set accessor for persistent attribute: mensaje
	 */
	public abstract void setMensaje(java.lang.String newMensaje);
}