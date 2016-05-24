package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Horario
 */
public abstract class HorarioBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.HorarioKey ejbCreate(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argAplicacion,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argRol)
		throws javax.ejb.CreateException {
		setHr_dia(hr_dia);
		setHr_tipo_semaforo(hr_tipo_semaforo);
		co.com.telefonica.atiempo.ejb.eb.AplicacionKey argAplicacionPK =
			(co.com.telefonica.atiempo.ejb.eb.AplicacionKey) argAplicacion
				.getPrimaryKey();
		setAplicacion_ap_id(argAplicacionPK.ap_id);
		co.com.telefonica.atiempo.ejb.eb.RolKey argRolPK =
			(co.com.telefonica.atiempo.ejb.eb.RolKey) argRol.getPrimaryKey();
		setRol_rol_id(argRolPK.rol_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argAplicacion,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argRol)
		throws javax.ejb.CreateException {
		setAplicacion(argAplicacion);
		setRol(argRol);
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
	 * Get accessor for persistent attribute: hr_dia
	 */
	public abstract java.lang.String getHr_dia();
	/**
	 * Set accessor for persistent attribute: hr_dia
	 */
	public abstract void setHr_dia(java.lang.String newHr_dia);
	/**
	 * Get accessor for persistent attribute: hr_hora_ini
	 */
	public abstract java.lang.Integer getHr_hora_ini();
	/**
	 * Set accessor for persistent attribute: hr_hora_ini
	 */
	public abstract void setHr_hora_ini(java.lang.Integer newHr_hora_ini);
	/**
	 * Get accessor for persistent attribute: hr_minu_ini
	 */
	public abstract java.lang.Integer getHr_minu_ini();
	/**
	 * Set accessor for persistent attribute: hr_minu_ini
	 */
	public abstract void setHr_minu_ini(java.lang.Integer newHr_minu_ini);
	/**
	 * Get accessor for persistent attribute: hr_hora_fin
	 */
	public abstract java.lang.Integer getHr_hora_fin();
	/**
	 * Set accessor for persistent attribute: hr_hora_fin
	 */
	public abstract void setHr_hora_fin(java.lang.Integer newHr_hora_fin);
	/**
	 * Get accessor for persistent attribute: hr_minu_fin
	 */
	public abstract java.lang.Integer getHr_minu_fin();
	/**
	 * Set accessor for persistent attribute: hr_minu_fin
	 */
	public abstract void setHr_minu_fin(java.lang.Integer newHr_minu_fin);
	/**
	 * Get accessor for persistent attribute: hr_hh_trabajo
	 */
	public abstract java.lang.Integer getHr_hh_trabajo();
	/**
	 * Set accessor for persistent attribute: hr_hh_trabajo
	 */
	public abstract void setHr_hh_trabajo(java.lang.Integer newHr_hh_trabajo);
	/**
	 * This method was generated for supporting the relationship role named aplicacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.AplicacionLocal getAplicacion();
	/**
	 * This method was generated for supporting the relationship role named aplicacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAplicacion(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal anAplicacion);
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.RolLocal getRol();
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRol(co.com.telefonica.atiempo.ejb.eb.RolLocal aRol);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.HorarioKey ejbCreate(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		java.lang.Long aplicacion_ap_id,
		java.lang.Long rol_rol_id)
		throws javax.ejb.CreateException {
		setHr_dia(hr_dia);
		setHr_tipo_semaforo(hr_tipo_semaforo);
		setAplicacion_ap_id(aplicacion_ap_id);
		setRol_rol_id(rol_rol_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String hr_dia,
		java.lang.Integer hr_tipo_semaforo,
		java.lang.Long aplicacion_ap_id,
		java.lang.Long rol_rol_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: aplicacion_ap_id
	 */
	public abstract java.lang.Long getAplicacion_ap_id();
	/**
	 * Set accessor for persistent attribute: aplicacion_ap_id
	 */
	public abstract void setAplicacion_ap_id(
		java.lang.Long newAplicacion_ap_id);
	/**
	 * Get accessor for persistent attribute: rol_rol_id
	 */
	public abstract java.lang.Long getRol_rol_id();
	/**
	 * Set accessor for persistent attribute: rol_rol_id
	 */
	public abstract void setRol_rol_id(java.lang.Long newRol_rol_id);
	/**
	 * Get accessor for persistent attribute: hr_tipo_semaforo
	 */
	public abstract java.lang.Integer getHr_tipo_semaforo();
	/**
	 * Set accessor for persistent attribute: hr_tipo_semaforo
	 */
	public abstract void setHr_tipo_semaforo(
		java.lang.Integer newHr_tipo_semaforo);
}
