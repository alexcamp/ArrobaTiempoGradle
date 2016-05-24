package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Accion_masiva
 */
public abstract class Accion_masivaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Accion_masivaKey ejbCreate(
		java.lang.Long acma_id)
		throws javax.ejb.CreateException {
		setAcma_id(acma_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long acma_id)
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
	 * Get accessor for persistent attribute: acma_id
	 */
	public abstract java.lang.Long getAcma_id();
	/**
	 * Set accessor for persistent attribute: acma_id
	 */
	public abstract void setAcma_id(java.lang.Long newAcma_id);
	/**
	 * Get accessor for persistent attribute: acma_descripcion
	 */
	public abstract java.lang.String getAcma_descripcion();
	/**
	 * Set accessor for persistent attribute: acma_descripcion
	 */
	public abstract void setAcma_descripcion(
		java.lang.String newAcma_descripcion);
	/**
	 * Get accessor for persistent attribute: acma_valor
	 */
	public abstract java.lang.String getAcma_valor();
	/**
	 * Set accessor for persistent attribute: acma_valor
	 */
	public abstract void setAcma_valor(java.lang.String newAcma_valor);
	/**
	 * Get accessor for persistent attribute: acma_cierre
	 */
	public abstract java.lang.String getAcma_cierre();
	/**
	 * Set accessor for persistent attribute: acma_cierre
	 */
	public abstract void setAcma_cierre(java.lang.String newAcma_cierre);
	/**
	 * This method was generated for supporting the relationship role named f_fk_rol_2_acma.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.RolLocal getF_fk_rol_2_acma();
	/**
	 * This method was generated for supporting the relationship role named f_fk_rol_2_acma.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_fk_rol_2_acma(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aF_fk_rol_2_acma);
}
