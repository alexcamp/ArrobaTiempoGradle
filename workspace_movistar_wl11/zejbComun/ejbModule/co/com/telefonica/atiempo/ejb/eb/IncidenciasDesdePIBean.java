package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: IncidenciasDesdePI
 */
public abstract class IncidenciasDesdePIBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePIKey ejbCreate(
		java.lang.Long nroIncidencia)
		throws javax.ejb.CreateException {
		setNroIncidencia(nroIncidencia);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long nroIncidencia)
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
	 * Get accessor for persistent attribute: nroIncidencia
	 */
	public abstract java.lang.Long getNroIncidencia();
	/**
	 * Set accessor for persistent attribute: nroIncidencia
	 */
	public abstract void setNroIncidencia(java.lang.Long newNroIncidencia);
	/**
	 * Get accessor for persistent attribute: destino
	 */
	public abstract java.lang.String getDestino();
	/**
	 * Set accessor for persistent attribute: destino
	 */
	public abstract void setDestino(java.lang.String newDestino);
}
