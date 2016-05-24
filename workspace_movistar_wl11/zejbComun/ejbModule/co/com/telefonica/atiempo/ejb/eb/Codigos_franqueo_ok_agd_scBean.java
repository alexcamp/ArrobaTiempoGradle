package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Codigos_franqueo_ok_agd_sc
 */
public abstract class Codigos_franqueo_ok_agd_scBean
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
	public co.com.telefonica.atiempo.ejb.eb.Codigos_franqueo_ok_agd_scKey ejbCreate(
		java.lang.String codigo_franqueo) throws javax.ejb.CreateException {
		setCodigo_franqueo(codigo_franqueo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String codigo_franqueo)
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
	 * Get accessor for persistent attribute: codigo_franqueo
	 */
	public abstract java.lang.String getCodigo_franqueo();
	/**
	 * Set accessor for persistent attribute: codigo_franqueo
	 */
	public abstract void setCodigo_franqueo(java.lang.String newCodigo_franqueo);
	/**
	 * Get accessor for persistent attribute: bandeja
	 */
	public abstract java.lang.String getBandeja();
	/**
	 * Set accessor for persistent attribute: bandeja
	 */
	public abstract void setBandeja(java.lang.String newBandeja);
}