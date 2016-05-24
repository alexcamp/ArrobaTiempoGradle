package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Causa_demora
 */
public abstract class Causa_demoraBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey ejbCreate(
		java.lang.Long cod_caudem) throws javax.ejb.CreateException {
		setCod_caudem(cod_caudem);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_caudem)
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
	 * Get accessor for persistent attribute: cod_caudem
	 */
	public abstract java.lang.Long getCod_caudem();
	/**
	 * Set accessor for persistent attribute: cod_caudem
	 */
	public abstract void setCod_caudem(java.lang.Long newCod_caudem);
	/**
	 * Get accessor for persistent attribute: descripcion_caudem
	 */
	public abstract java.lang.String getDescripcion_caudem();
	/**
	 * Set accessor for persistent attribute: descripcion_caudem
	 */
	public abstract void setDescripcion_caudem(
		java.lang.String newDescripcion_caudem);
}