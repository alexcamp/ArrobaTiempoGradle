package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Estados_TOA
 */
public abstract class Estados_TOABean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.Estados_TOAKey ejbCreate(
		java.lang.Integer correlativo) throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer correlativo)
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
	 * Get accessor for persistent attribute: cod_estado
	 */
	public abstract java.lang.Integer getCod_estado();
	/**
	 * Set accessor for persistent attribute: cod_estado
	 */
	public abstract void setCod_estado(java.lang.Integer newCod_estado);
	/**
	 * Get accessor for persistent attribute: descripcion_estado
	 */
	public abstract java.lang.String getDescripcion_estado();
	/**
	 * Set accessor for persistent attribute: descripcion_estado
	 */
	public abstract void setDescripcion_estado(
		java.lang.String newDescripcion_estado);
	/**
	 * Get accessor for persistent attribute: producto_instancia
	 */
	public abstract java.lang.String getProducto_instancia();
	/**
	 * Set accessor for persistent attribute: producto_instancia
	 */
	public abstract void setProducto_instancia(
		java.lang.String newProducto_instancia);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Integer getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Integer newCorrelativo);
}