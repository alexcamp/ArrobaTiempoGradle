package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Menu_perfil
 */
public abstract class Menu_perfilBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Menu_perfilKey ejbCreate(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal argFk_menperf_perf,
		co.com.telefonica.atiempo.ejb.eb.MenuLocal argFk_menperf_men)
		throws javax.ejb.CreateException {
		co.com.telefonica.atiempo.ejb.eb.PerfilKey argFk_menperf_perfPK =
			(co.com.telefonica.atiempo.ejb.eb.PerfilKey) argFk_menperf_perf
				.getPrimaryKey();
		setFk_menperf_perf_perf_id(argFk_menperf_perfPK.perf_id);
		co.com.telefonica.atiempo.ejb.eb.MenuKey argFk_menperf_menPK =
			(co.com.telefonica.atiempo.ejb.eb.MenuKey) argFk_menperf_men
				.getPrimaryKey();
		setFk_menperf_men_mn_id(argFk_menperf_menPK.mn_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal argFk_menperf_perf,
		co.com.telefonica.atiempo.ejb.eb.MenuLocal argFk_menperf_men)
		throws javax.ejb.CreateException {
		setFk_menperf_perf(argFk_menperf_perf);
		setFk_menperf_men(argFk_menperf_men);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Menu_perfilKey ejbCreate(
		java.lang.Long fk_menperf_perf_perf_id,
		java.lang.Long fk_menperf_men_mn_id)
		throws javax.ejb.CreateException {
		setFk_menperf_perf_perf_id(fk_menperf_perf_perf_id);
		setFk_menperf_men_mn_id(fk_menperf_men_mn_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long fk_menperf_perf_perf_id,
		java.lang.Long fk_menperf_men_mn_id)
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
	 * This method was generated for supporting the relationship role named fk_menperf_men.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.MenuLocal getFk_menperf_men();
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_men.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_menperf_men(
		co.com.telefonica.atiempo.ejb.eb.MenuLocal aFk_menperf_men);
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PerfilLocal getFk_menperf_perf();
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_menperf_perf(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal aFk_menperf_perf);
	/**
	 * Get accessor for persistent attribute: fk_menperf_men_mn_id
	 */
	public abstract java.lang.Long getFk_menperf_men_mn_id();
	/**
	 * Set accessor for persistent attribute: fk_menperf_men_mn_id
	 */
	public abstract void setFk_menperf_men_mn_id(
		java.lang.Long newFk_menperf_men_mn_id);
	/**
	 * Get accessor for persistent attribute: fk_menperf_perf_perf_id
	 */
	public abstract java.lang.Long getFk_menperf_perf_perf_id();
	/**
	 * Set accessor for persistent attribute: fk_menperf_perf_perf_id
	 */
	public abstract void setFk_menperf_perf_perf_id(
		java.lang.Long newFk_menperf_perf_perf_id);
}
