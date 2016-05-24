package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Rol_habilidad
 */
public abstract class Rol_habilidadBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey ejbCreate(
		java.lang.Long roha_id)
		throws javax.ejb.CreateException {
		setRoha_id(roha_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long roha_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Rol_habilidadKey ejbCreate(
		java.lang.Long roha_id,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_rol_2_roha)
		throws javax.ejb.CreateException {
		setRoha_id(roha_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long roha_id,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_rol_2_roha)
		throws javax.ejb.CreateException {
		setFk_rol_2_roha(argFk_rol_2_roha);
	}
	/**
	 * Get accessor for persistent attribute: roha_id
	 */
	public abstract java.lang.Long getRoha_id();
	/**
	 * Set accessor for persistent attribute: roha_id
	 */
	public abstract void setRoha_id(java.lang.Long newRoha_id);
	/**
	 * This method was generated for supporting the relationship role named f_fk_habi_2_roha.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.HabilidadLocal getF_fk_habi_2_roha();
	/**
	 * This method was generated for supporting the relationship role named f_fk_habi_2_roha.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_fk_habi_2_roha(
		co.com.telefonica.atiempo.ejb.eb.HabilidadLocal aF_fk_habi_2_roha);
	/**
	 * This method was generated for supporting the relationship role named fk_rol_2_roha.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.RolLocal getFk_rol_2_roha();
	/**
	 * This method was generated for supporting the relationship role named fk_rol_2_roha.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_rol_2_roha(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_rol_2_roha);
}
