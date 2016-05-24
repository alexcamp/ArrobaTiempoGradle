package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Jer_usuario_rol
 */
public abstract class Jer_usuario_rolBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey ejbCreate(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_jerrol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_jerusuarol_usu2,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_jerusuarol_usua)
		throws javax.ejb.CreateException {
		co.com.telefonica.atiempo.ejb.eb.RolKey argFk_jerrol_rolPK =
			(co.com.telefonica.atiempo.ejb.eb.RolKey) argFk_jerrol_rol
				.getPrimaryKey();
		setFk_jerrol_rol_rol_id(argFk_jerrol_rolPK.rol_id);
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_jerusuarol_usu2PK =
			(co.com.telefonica.atiempo.ejb.eb.UsuarioKey) argFk_jerusuarol_usu2
				.getPrimaryKey();
		setFk_jerusuarol_usu2_usua_id(argFk_jerusuarol_usu2PK.usua_id);
		co.com.telefonica.atiempo.ejb.eb.UsuarioKey argFk_jerusuarol_usuaPK =
			(co.com.telefonica.atiempo.ejb.eb.UsuarioKey) argFk_jerusuarol_usua
				.getPrimaryKey();
		setFk_jerusuarol_usua_usua_id(argFk_jerusuarol_usuaPK.usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_jerrol_rol,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_jerusuarol_usu2,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_jerusuarol_usua)
		throws javax.ejb.CreateException {
		setFk_jerrol_rol(argFk_jerrol_rol);
		setFk_jerusuarol_usu2(argFk_jerusuarol_usu2);
		setFk_jerusuarol_usua(argFk_jerusuarol_usua);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolKey ejbCreate(
		java.lang.Long fk_jerrol_rol_rol_id,
		java.lang.Long fk_jerusuarol_usu2_usua_id,
		java.lang.Long fk_jerusuarol_usua_usua_id)
		throws javax.ejb.CreateException {
		setFk_jerrol_rol_rol_id(fk_jerrol_rol_rol_id);
		setFk_jerusuarol_usu2_usua_id(fk_jerusuarol_usu2_usua_id);
		setFk_jerusuarol_usua_usua_id(fk_jerusuarol_usua_usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long fk_jerrol_rol_rol_id,
		java.lang.Long fk_jerusuarol_usu2_usua_id,
		java.lang.Long fk_jerusuarol_usua_usua_id)
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
	 * This method was generated for supporting the relationship role named fk_jerrol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.RolLocal getFk_jerrol_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_jerrol_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_jerrol_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_jerrol_rol);
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usu2.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_jerusuarol_usu2();
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usu2.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_jerusuarol_usu2(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_jerusuarol_usu2);
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_jerusuarol_usua();
	/**
	 * This method was generated for supporting the relationship role named fk_jerusuarol_usua.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_jerusuarol_usua(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_jerusuarol_usua);
	/**
	 * Get accessor for persistent attribute: fk_jerrol_rol_rol_id
	 */
	public abstract java.lang.Long getFk_jerrol_rol_rol_id();
	/**
	 * Set accessor for persistent attribute: fk_jerrol_rol_rol_id
	 */
	public abstract void setFk_jerrol_rol_rol_id(
		java.lang.Long newFk_jerrol_rol_rol_id);
	/**
	 * Get accessor for persistent attribute: fk_jerusuarol_usu2_usua_id
	 */
	public abstract java.lang.Long getFk_jerusuarol_usu2_usua_id();
	/**
	 * Set accessor for persistent attribute: fk_jerusuarol_usu2_usua_id
	 */
	public abstract void setFk_jerusuarol_usu2_usua_id(
		java.lang.Long newFk_jerusuarol_usu2_usua_id);
	/**
	 * Get accessor for persistent attribute: fk_jerusuarol_usua_usua_id
	 */
	public abstract java.lang.Long getFk_jerusuarol_usua_usua_id();
	/**
	 * Set accessor for persistent attribute: fk_jerusuarol_usua_usua_id
	 */
	public abstract void setFk_jerusuarol_usua_usua_id(
		java.lang.Long newFk_jerusuarol_usua_usua_id);
}
