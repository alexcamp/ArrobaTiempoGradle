package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Habilidad_usuario
 */
public abstract class Habilidad_usuarioBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioKey ejbCreate(
		java.lang.Long husu_id)
		throws javax.ejb.CreateException {
		setHusu_id(husu_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long husu_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioKey ejbCreate(
		java.lang.Long husu_id,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_usua_2_husa)
		throws javax.ejb.CreateException {
		setHusu_id(husu_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long husu_id,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_usua_2_husa)
		throws javax.ejb.CreateException {
		setFk_usua_2_husa(argFk_usua_2_husa);
	}
	/**
	 * Get accessor for persistent attribute: husu_id
	 */
	public abstract java.lang.Long getHusu_id();
	/**
	 * Set accessor for persistent attribute: husu_id
	 */
	public abstract void setHusu_id(java.lang.Long newHusu_id);
	/**
	 * Get accessor for persistent attribute: husu_valor
	 */
	public abstract java.lang.String getHusu_valor();
	/**
	 * Set accessor for persistent attribute: husu_valor
	 */
	public abstract void setHusu_valor(java.lang.String newHusu_valor);
	/**
	 * This method was generated for supporting the relationship role named fk_usua_2_husa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_usua_2_husa();
	/**
	 * This method was generated for supporting the relationship role named fk_usua_2_husa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_usua_2_husa(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_usua_2_husa);
	/**
	 * This method was generated for supporting the relationship role named fk_habi_2_husu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.HabilidadLocal getFk_habi_2_husu();
	/**
	 * This method was generated for supporting the relationship role named fk_habi_2_husu.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_habi_2_husu(
		co.com.telefonica.atiempo.ejb.eb.HabilidadLocal aFk_habi_2_husu);
}
