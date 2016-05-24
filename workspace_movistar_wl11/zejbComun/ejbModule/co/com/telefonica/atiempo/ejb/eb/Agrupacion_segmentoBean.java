package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Agrupacion_segmento
 */
public abstract class Agrupacion_segmentoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoKey ejbCreate(
		java.lang.Long codigo_segmento)
		throws javax.ejb.CreateException {
		setCodigo_segmento(codigo_segmento);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long codigo_segmento)
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
	 * Get accessor for persistent attribute: codigo_segmento
	 */
	public abstract java.lang.Long getCodigo_segmento();
	/**
	 * Set accessor for persistent attribute: codigo_segmento
	 */
	public abstract void setCodigo_segmento(java.lang.Long newCodigo_segmento);
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
}
