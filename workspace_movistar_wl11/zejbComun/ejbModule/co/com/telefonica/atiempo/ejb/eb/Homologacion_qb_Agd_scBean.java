package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Homologacion_qb_Agd_sc
 */
public abstract class Homologacion_qb_Agd_scBean
	implements
		javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Homologacion_qb_Agd_scKey ejbCreate(
		java.lang.String hq_agd_cod_familia) throws javax.ejb.CreateException {
		setHq_agd_cod_familia(hq_agd_cod_familia);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String hq_agd_cod_familia)
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
	 * Get accessor for persistent attribute: hq_agd_cod_familia
	 */
	public abstract java.lang.String getHq_agd_cod_familia();
	/**
	 * Set accessor for persistent attribute: hq_agd_cod_familia
	 */
	public abstract void setHq_agd_cod_familia(
		java.lang.String newHq_agd_cod_familia);
	/**
	 * Get accessor for persistent attribute: hq_agd_id_familia_ps
	 */
	public abstract java.lang.String getHq_agd_id_familia_ps();
	/**
	 * Set accessor for persistent attribute: hq_agd_id_familia_ps
	 */
	public abstract void setHq_agd_id_familia_ps(
		java.lang.String newHq_agd_id_familia_ps);
}
