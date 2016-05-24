package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Estado_ps
 */
public abstract class Estado_psBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Estado_psKey ejbCreate(
		java.lang.Long cod_estado_cierre)
		throws javax.ejb.CreateException {
		setCod_estado_cierre(cod_estado_cierre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_estado_cierre)
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
	 * Get accessor for persistent attribute: cod_estado_cierre
	 */
	public abstract java.lang.Long getCod_estado_cierre();
	/**
	 * Set accessor for persistent attribute: cod_estado_cierre
	 */
	public abstract void setCod_estado_cierre(
		java.lang.Long newCod_estado_cierre);
	/**
	 * Get accessor for persistent attribute: descripcion_estado_cierre
	 */
	public abstract java.lang.String getDescripcion_estado_cierre();
	/**
	 * Set accessor for persistent attribute: descripcion_estado_cierre
	 */
	public abstract void setDescripcion_estado_cierre(
		java.lang.String newDescripcion_estado_cierre);
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_peticion(
		java.util.Collection aCausal_peticion);
}
