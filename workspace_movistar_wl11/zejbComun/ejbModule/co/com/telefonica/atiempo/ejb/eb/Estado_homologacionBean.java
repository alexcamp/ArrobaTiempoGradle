package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Estado_homologacion
 */
public abstract class Estado_homologacionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Estado_homologacionKey ejbCreate(
		java.lang.String cod_app,
		java.lang.String cod_estado) throws javax.ejb.CreateException {
		setCod_app(cod_app);
		setCod_estado(cod_estado);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String cod_app,
		java.lang.String cod_estado) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: cod_app
	 */
	public abstract java.lang.String getCod_app();
	/**
	 * Set accessor for persistent attribute: cod_app
	 */
	public abstract void setCod_app(java.lang.String newCod_app);
	/**
	 * Get accessor for persistent attribute: cod_estado
	 */
	public abstract java.lang.String getCod_estado();
	/**
	 * Set accessor for persistent attribute: cod_estado
	 */
	public abstract void setCod_estado(java.lang.String newCod_estado);
	/**
	 * Get accessor for persistent attribute: cod_homologado
	 */
	public abstract java.lang.String getCod_homologado();
	/**
	 * Set accessor for persistent attribute: cod_homologado
	 */
	public abstract void setCod_homologado(java.lang.String newCod_homologado);
}