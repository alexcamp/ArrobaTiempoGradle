package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Usuario_rol
 */
public abstract class Usuario_rolBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey ejbCreate(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_usuarol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_usuarol_usua)
		throws javax.ejb.CreateException {
		co.com.telefonica.atiempo.ejb.eb.RolKey argFk_usuarol_rolPK =
			(co.com.telefonica.atiempo.ejb.eb.RolKey) argFk_usuarol_rol
				.getPrimaryKey();
		setFk_usuarol_rol_rol_id(argFk_usuarol_rolPK.rol_id);
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_usuarol_usuaPK =
			(co.com.telefonica.atiempo.ejb.eb.UsuarioKey) argFk_usuarol_usua
				.getPrimaryKey();
		setFk_usuarol_usua_usua_id(argFk_usuarol_usuaPK.usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_usuarol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_usuarol_usua)
		throws javax.ejb.CreateException {
		setFk_usuarol_rol(argFk_usuarol_rol);
		setFk_usuarol_usua(argFk_usuarol_usua);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Usuario_rolKey ejbCreate(
		java.lang.Long fk_usuarol_rol_rol_id,
		java.lang.Long fk_usuarol_usua_usua_id)
		throws javax.ejb.CreateException {
		setFk_usuarol_rol_rol_id(fk_usuarol_rol_rol_id);
		setFk_usuarol_usua_usua_id(fk_usuarol_usua_usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long fk_usuarol_rol_rol_id,
		java.lang.Long fk_usuarol_usua_usua_id)
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
	 * Get accessor for persistent attribute: usro_habilitado
	 */
	public abstract java.lang.Short getUsro_habilitado();
	/**
	 * Set accessor for persistent attribute: usro_habilitado
	 */
	public abstract void setUsro_habilitado(java.lang.Short newUsro_habilitado);
	/**
	 * Get accessor for persistent attribute: rol_supervisor
	 */
	public abstract java.lang.String getRol_supervisor();
	/**
	 * Set accessor for persistent attribute: rol_supervisor
	 */
	public abstract void setRol_supervisor(java.lang.String newRol_supervisor);
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.RolLocal getFk_usuarol_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_usuarol_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_usuarol_rol);
	/**
	 * This method was generated for supporting the relationship role named fk_supe_2_usro.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_supe_2_usro();
	/**
	 * This method was generated for supporting the relationship role named fk_supe_2_usro.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_supe_2_usro(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_supe_2_usro);
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_usuarol_usua();
	/**
	 * This method was generated for supporting the relationship role named fk_usuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_usuarol_usua(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_usuarol_usua);
	/**
	 * Get accessor for persistent attribute: fk_usuarol_rol_rol_id
	 */
	public abstract java.lang.Long getFk_usuarol_rol_rol_id();
	/**
	 * Set accessor for persistent attribute: fk_usuarol_rol_rol_id
	 */
	public abstract void setFk_usuarol_rol_rol_id(
		java.lang.Long newFk_usuarol_rol_rol_id);
	/**
	 * Get accessor for persistent attribute: fk_usuarol_usua_usua_id
	 */
	public abstract java.lang.Long getFk_usuarol_usua_usua_id();
	/**
	 * Set accessor for persistent attribute: fk_usuarol_usua_usua_id
	 */
	public abstract void setFk_usuarol_usua_usua_id(
		java.lang.Long newFk_usuarol_usua_usua_id);
}
