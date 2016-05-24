package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Perfil_usuario
 */
public abstract class Perfil_usuarioBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioKey ejbCreate(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_perfusu_usu,
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal argFk_perfusu_perf)
		throws javax.ejb.CreateException {
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_perfusu_usuPK =
			(co.com.telefonica.atiempo.ejb.eb.UsuarioKey) argFk_perfusu_usu
				.getPrimaryKey();
		setFk_perfusu_usu_usua_id(argFk_perfusu_usuPK.usua_id);
		co.com.telefonica.atiempo.ejb.eb.PerfilKey argFk_perfusu_perfPK =
			(co.com.telefonica.atiempo.ejb.eb.PerfilKey) argFk_perfusu_perf
				.getPrimaryKey();
		setFk_perfusu_perf_perf_id(argFk_perfusu_perfPK.perf_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_perfusu_usu,
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal argFk_perfusu_perf)
		throws javax.ejb.CreateException {
		setFk_perfusu_usu(argFk_perfusu_usu);
		setFk_perfusu_perf(argFk_perfusu_perf);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Perfil_usuarioKey ejbCreate(
		java.lang.Long fk_perfusu_usu_usua_id,
		java.lang.Long fk_perfusu_perf_perf_id)
		throws javax.ejb.CreateException {
		setFk_perfusu_usu_usua_id(fk_perfusu_usu_usua_id);
		setFk_perfusu_perf_perf_id(fk_perfusu_perf_perf_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long fk_perfusu_usu_usua_id,
		java.lang.Long fk_perfusu_perf_perf_id)
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
	 * This method was generated for supporting the relationship role named fk_perfusu_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PerfilLocal getFk_perfusu_perf();
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_perfusu_perf(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal aFk_perfusu_perf);
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_usu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_perfusu_usu();
	/**
	 * This method was generated for supporting the relationship role named fk_perfusu_usu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_perfusu_usu(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_perfusu_usu);
	/**
	 * Get accessor for persistent attribute: fk_perfusu_perf_perf_id
	 */
	public abstract java.lang.Long getFk_perfusu_perf_perf_id();
	/**
	 * Set accessor for persistent attribute: fk_perfusu_perf_perf_id
	 */
	public abstract void setFk_perfusu_perf_perf_id(
		java.lang.Long newFk_perfusu_perf_perf_id);
	/**
	 * Get accessor for persistent attribute: fk_perfusu_usu_usua_id
	 */
	public abstract java.lang.Long getFk_perfusu_usu_usua_id();
	/**
	 * Set accessor for persistent attribute: fk_perfusu_usu_usua_id
	 */
	public abstract void setFk_perfusu_usu_usua_id(
		java.lang.Long newFk_perfusu_usu_usua_id);
}
