package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Campo_rol
 */
public abstract class Campo_rolBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Campo_rolKey ejbCreate(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_camrol_2_rol,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_camrol_2_cam)
		throws javax.ejb.CreateException {
		co.com.telefonica.atiempo.ejb.eb.RolKey argFk_camrol_2_rolPK =
			(co.com.telefonica.atiempo.ejb.eb.RolKey) argFk_camrol_2_rol
				.getPrimaryKey();
		setFk_camrol_2_rol_rol_id(argFk_camrol_2_rolPK.rol_id);
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey argFk_camrol_2_camPK =
			(co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Campo_variableKey) argFk_camrol_2_cam
				.getPrimaryKey();
		setFk_camrol_2_cam_cv_id(argFk_camrol_2_camPK.cv_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_camrol_2_rol,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_camrol_2_cam)
		throws javax.ejb.CreateException {
		setFk_camrol_2_rol(argFk_camrol_2_rol);
		setFk_camrol_2_cam(argFk_camrol_2_cam);
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Campo_rolKey ejbCreate(
		java.lang.Long fk_camrol_2_rol_rol_id,
		java.lang.Short fk_camrol_2_cam_cv_id)
		throws javax.ejb.CreateException {
		setFk_camrol_2_rol_rol_id(fk_camrol_2_rol_rol_id);
		setFk_camrol_2_cam_cv_id(fk_camrol_2_cam_cv_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long fk_camrol_2_rol_rol_id,
		java.lang.Short fk_camrol_2_cam_cv_id)
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
	 * This method was generated for supporting the relationship role named fk_camrol_2_cam.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal getFk_camrol_2_cam();
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_cam.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_camrol_2_cam(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal aFk_camrol_2_cam);
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.RolLocal getFk_camrol_2_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_camrol_2_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_camrol_2_rol);
	/**
	 * Get accessor for persistent attribute: fk_camrol_2_cam_cv_id
	 */
	public abstract java.lang.Short getFk_camrol_2_cam_cv_id();
	/**
	 * Set accessor for persistent attribute: fk_camrol_2_cam_cv_id
	 */
	public abstract void setFk_camrol_2_cam_cv_id(
		java.lang.Short newFk_camrol_2_cam_cv_id);
	/**
	 * Get accessor for persistent attribute: fk_camrol_2_rol_rol_id
	 */
	public abstract java.lang.Long getFk_camrol_2_rol_rol_id();
	/**
	 * Set accessor for persistent attribute: fk_camrol_2_rol_rol_id
	 */
	public abstract void setFk_camrol_2_rol_rol_id(
		java.lang.Long newFk_camrol_2_rol_rol_id);
}
