package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Causa_cierre
 */
public abstract class Causa_cierreBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Causa_cierreKey ejbCreate(
		java.lang.Long caci_id)
		throws javax.ejb.CreateException {
		setCaci_id(caci_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long caci_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Causa_cierreKey ejbCreate(
		java.lang.Long caci_id,
		java.lang.Integer ambi_id,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Cierre_actividadLocal argFk_ciac_2_caci,
		co.com.telefonica.atiempo.ejb.eb.CausaLocal argFk_caus_caci)
		throws javax.ejb.CreateException {
		setCaci_id(caci_id);
		setAmbi_id(ambi_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long caci_id,
		java.lang.Integer ambi_id,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Cierre_actividadLocal argFk_ciac_2_caci,
		co.com.telefonica.atiempo.ejb.eb.CausaLocal argFk_caus_caci)
		throws javax.ejb.CreateException {
		setFk_ciac_2_caci(argFk_ciac_2_caci);
		setFk_caus_caci(argFk_caus_caci);
	}
	/**
	 * Get accessor for persistent attribute: caci_id
	 */
	public abstract java.lang.Long getCaci_id();
	/**
	 * Set accessor for persistent attribute: caci_id
	 */
	public abstract void setCaci_id(java.lang.Long newCaci_id);
	/**
	 * Get accessor for persistent attribute: ambi_id
	 */
	public abstract java.lang.Integer getAmbi_id();
	/**
	 * Set accessor for persistent attribute: ambi_id
	 */
	public abstract void setAmbi_id(java.lang.Integer newAmbi_id);
	/**
	 * This method was generated for supporting the relationship role named fk_ciac_2_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Cierre_actividadLocal getFk_ciac_2_caci();
	/**
	 * This method was generated for supporting the relationship role named fk_ciac_2_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_ciac_2_caci(
		co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal aFk_ciac_2_caci);
	/**
	 * This method was generated for supporting the relationship role named fk_caus_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.CausaLocal getFk_caus_caci();
	/**
	 * This method was generated for supporting the relationship role named fk_caus_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_caus_caci(
		co.com.telefonica.atiempo.ejb.eb.CausaLocal aFk_caus_caci);
}
