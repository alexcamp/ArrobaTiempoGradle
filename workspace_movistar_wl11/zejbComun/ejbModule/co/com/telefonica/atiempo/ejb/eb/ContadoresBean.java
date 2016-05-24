package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Contadores
 */
public abstract class ContadoresBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ContadoresKey ejbCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal argOperacion_comercial)
		throws javax.ejb.CreateException {
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialKey argOperacion_comercialPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Operacion_comercialKey) argOperacion_comercial
				.getPrimaryKey();
		setOperacion_comercial_opco_id(argOperacion_comercialPK.opco_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal argOperacion_comercial)
		throws javax.ejb.CreateException {
		setOperacion_comercial(argOperacion_comercial);
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
	 * Get accessor for persistent attribute: desde
	 */
	public abstract java.lang.Short getDesde();
	/**
	 * Set accessor for persistent attribute: desde
	 */
	public abstract void setDesde(java.lang.Short newDesde);
	/**
	 * Get accessor for persistent attribute: hasta
	 */
	public abstract java.lang.Short getHasta();
	/**
	 * Set accessor for persistent attribute: hasta
	 */
	public abstract void setHasta(java.lang.Short newHasta);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialLocal getOperacion_comercial();
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setOperacion_comercial(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal anOperacion_comercial);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ContadoresKey ejbCreate(
		java.lang.Long operacion_comercial_opco_id)
		throws javax.ejb.CreateException {
		setOperacion_comercial_opco_id(operacion_comercial_opco_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long operacion_comercial_opco_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: operacion_comercial_opco_id
	 */
	public abstract java.lang.Long getOperacion_comercial_opco_id();
	/**
	 * Set accessor for persistent attribute: operacion_comercial_opco_id
	 */
	public abstract void setOperacion_comercial_opco_id(
		java.lang.Long newOperacion_comercial_opco_id);
}
