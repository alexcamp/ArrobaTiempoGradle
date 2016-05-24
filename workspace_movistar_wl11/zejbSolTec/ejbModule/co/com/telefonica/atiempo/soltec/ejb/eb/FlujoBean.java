package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Flujo
 */
public abstract class FlujoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey ejbCreate(
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException {
		setFluj_id(fluj_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer fluj_id)
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoKey ejbCreate(
		java.lang.Integer fluj_id,
		java.lang.String fluj_codigo)
		throws javax.ejb.CreateException {
		setFluj_id(fluj_id);
		setFluj_codigo(fluj_codigo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer fluj_id,
		java.lang.String fluj_codigo)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: fluj_id
	 */
	public abstract java.lang.Integer getFluj_id();
	/**
	 * Set accessor for persistent attribute: fluj_id
	 */
	public abstract void setFluj_id(java.lang.Integer newFluj_id);
	/**
	 * Get accessor for persistent attribute: fluj_codigo
	 */
	public abstract java.lang.String getFluj_codigo();
	/**
	 * Set accessor for persistent attribute: fluj_codigo
	 */
	public abstract void setFluj_codigo(java.lang.String newFluj_codigo);
	/**
	 * Get accessor for persistent attribute: fluj_descripcion
	 */
	public abstract java.lang.String getFluj_descripcion();
	/**
	 * Set accessor for persistent attribute: fluj_descripcion
	 */
	public abstract void setFluj_descripcion(
		java.lang.String newFluj_descripcion);
	/**
	 * This method was generated for supporting the relationship role named flujo_definicion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getFlujo_definicion();
	/**
	 * This method was generated for supporting the relationship role named flujo_definicion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFlujo_definicion(
		java.util.Collection aFlujo_definicion);
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getFlujo_prod_serv_oper_com();
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFlujo_prod_serv_oper_com(
		java.util.Collection aFlujo_prod_serv_oper_com);
}
