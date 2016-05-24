package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Subsegmento
 */
public abstract class SubsegmentoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey ejbCreate(
		java.lang.Long subsegm_id)
		throws javax.ejb.CreateException {
		setSubsegm_id(subsegm_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long subsegm_id)
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
	 * Get accessor for persistent attribute: subsegm_id
	 */
	public abstract java.lang.Long getSubsegm_id();
	/**
	 * Set accessor for persistent attribute: subsegm_id
	 */
	public abstract void setSubsegm_id(java.lang.Long newSubsegm_id);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
	/**
	 * This method was generated for supporting the relationship role named segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.SegmentoLocal getSegmento();
	/**
	 * This method was generated for supporting the relationship role named segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setSegmento(
		co.com.telefonica.atiempo.ejb.eb.SegmentoLocal aSegmento);
}
