package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Campo_usuario
 */
public abstract class Campo_usuarioBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Campo_usuarioKey ejbCreate(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_camusu_camvar,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_campousua_usua)
		throws javax.ejb.CreateException {
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableKey argFk_camusu_camvarPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Campo_variableKey) argFk_camusu_camvar
				.getPrimaryKey();
		setFk_camusu_camvar_cv_id(argFk_camusu_camvarPK.cv_id);
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_campousua_usuaPK =
			(co.com.telefonica.atiempo.ejb.eb.UsuarioKey) argFk_campousua_usua
				.getPrimaryKey();
		setFk_campousua_usua_usua_id(argFk_campousua_usuaPK.usua_id);
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
			.Campo_variableLocal argFk_camusu_camvar,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_campousua_usua)
		throws javax.ejb.CreateException {
		setFk_camusu_camvar(argFk_camusu_camvar);
		setFk_campousua_usua(argFk_campousua_usua);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_usuarioKey ejbCreate(
		java.lang.Short fk_camusu_camvar_cv_id,
		java.lang.Long fk_campousua_usua_usua_id)
		throws javax.ejb.CreateException {
		setFk_camusu_camvar_cv_id(fk_camusu_camvar_cv_id);
		setFk_campousua_usua_usua_id(fk_campousua_usua_usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Short fk_camusu_camvar_cv_id,
		java.lang.Long fk_campousua_usua_usua_id)
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
	 * This method was generated for supporting the relationship role named fk_camusu_camvar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal getFk_camusu_camvar();
	/**
	 * This method was generated for supporting the relationship role named fk_camusu_camvar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_camusu_camvar(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal aFk_camusu_camvar);
	/**
	 * This method was generated for supporting the relationship role named fk_campousua_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_campousua_usua();
	/**
	 * This method was generated for supporting the relationship role named fk_campousua_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_campousua_usua(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_campousua_usua);
	/**
	 * Get accessor for persistent attribute: fk_camusu_camvar_cv_id
	 */
	public abstract java.lang.Short getFk_camusu_camvar_cv_id();
	/**
	 * Set accessor for persistent attribute: fk_camusu_camvar_cv_id
	 */
	public abstract void setFk_camusu_camvar_cv_id(
		java.lang.Short newFk_camusu_camvar_cv_id);
	/**
	 * Get accessor for persistent attribute: fk_campousua_usua_usua_id
	 */
	public abstract java.lang.Long getFk_campousua_usua_usua_id();
	/**
	 * Set accessor for persistent attribute: fk_campousua_usua_usua_id
	 */
	public abstract void setFk_campousua_usua_usua_id(
		java.lang.Long newFk_campousua_usua_usua_id);
}
