package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Habilidad
 */
public abstract class HabilidadBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.HabilidadKey ejbCreate(
		java.lang.Long habi_id)
		throws javax.ejb.CreateException {
		setHabi_id(habi_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long habi_id)
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
	 * Get accessor for persistent attribute: habi_id
	 */
	public abstract java.lang.Long getHabi_id();
	/**
	 * Set accessor for persistent attribute: habi_id
	 */
	public abstract void setHabi_id(java.lang.Long newHabi_id);
	/**
	 * Get accessor for persistent attribute: habi_nombre
	 */
	public abstract java.lang.String getHabi_nombre();
	/**
	 * Set accessor for persistent attribute: habi_nombre
	 */
	public abstract void setHabi_nombre(java.lang.String newHabi_nombre);
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getHabilidad_usuario();
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setHabilidad_usuario(
		java.util.Collection aHabilidad_usuario);
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRol_habilidad();
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRol_habilidad(java.util.Collection aRol_habilidad);
}
