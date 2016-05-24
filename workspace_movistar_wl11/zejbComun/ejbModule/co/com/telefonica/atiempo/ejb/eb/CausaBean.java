package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Causa
 */
public abstract class CausaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.CausaKey ejbCreate(
		java.lang.Long caus_id)
		throws javax.ejb.CreateException {
		setCaus_id(caus_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long caus_id)
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.CausaKey ejbCreate(
		java.lang.Long caus_id,
		java.lang.String caus_codigo,
		java.lang.String caus_nombre)
		throws javax.ejb.CreateException {
		setCaus_id(caus_id);
		setCaus_codigo(caus_codigo);
		setCaus_nombre(caus_nombre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long caus_id,
		java.lang.String caus_codigo,
		java.lang.String caus_nombre)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: caus_id
	 */
	public abstract java.lang.Long getCaus_id();
	/**
	 * Set accessor for persistent attribute: caus_id
	 */
	public abstract void setCaus_id(java.lang.Long newCaus_id);
	/**
	 * Get accessor for persistent attribute: caus_codigo
	 */
	public abstract java.lang.String getCaus_codigo();
	/**
	 * Set accessor for persistent attribute: caus_codigo
	 */
	public abstract void setCaus_codigo(java.lang.String newCaus_codigo);
	/**
	 * Get accessor for persistent attribute: caus_nombre
	 */
	public abstract java.lang.String getCaus_nombre();
	/**
	 * Set accessor for persistent attribute: caus_nombre
	 */
	public abstract void setCaus_nombre(java.lang.String newCaus_nombre);
	/**
	 * Get accessor for persistent attribute: caus_codigo_ivr
	 */
	public abstract java.lang.String getCaus_codigo_ivr();
	/**
	 * Set accessor for persistent attribute: caus_codigo_ivr
	 */
	public abstract void setCaus_codigo_ivr(
		java.lang.String newCaus_codigo_ivr);
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausa_cierre();
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausa_cierre(java.util.Collection aCausa_cierre);
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBitacora_peticion();
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBitacora_peticion(
		java.util.Collection aBitacora_peticion);
}
