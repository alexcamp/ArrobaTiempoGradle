package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Limite
 */
public abstract class LimiteBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.LimiteKey ejbCreate(
		java.lang.Integer li_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argActividad)
		throws javax.ejb.CreateException {
		setLi_tipo_semaforo(li_tipo_semaforo);
		co.com.telefonica.atiempo.ejb.eb.ActividadKey argActividadPK =
			(co.com.telefonica.atiempo.ejb.eb.ActividadKey) argActividad
				.getPrimaryKey();
		setActividad_act_id(argActividadPK.act_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer li_tipo_semaforo,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argActividad)
		throws javax.ejb.CreateException {
		setActividad(argActividad);
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
	 * Get accessor for persistent attribute: li_tipo_semaforo
	 */
	public abstract java.lang.Integer getLi_tipo_semaforo();
	/**
	 * Set accessor for persistent attribute: li_tipo_semaforo
	 */
	public abstract void setLi_tipo_semaforo(
		java.lang.Integer newLi_tipo_semaforo);
	/**
	 * Get accessor for persistent attribute: li_limite_rojo
	 */
	public abstract java.lang.Integer getLi_limite_rojo();
	/**
	 * Set accessor for persistent attribute: li_limite_rojo
	 */
	public abstract void setLi_limite_rojo(java.lang.Integer newLi_limite_rojo);
	/**
	 * Get accessor for persistent attribute: li_limite_amarillo
	 */
	public abstract java.lang.Integer getLi_limite_amarillo();
	/**
	 * Set accessor for persistent attribute: li_limite_amarillo
	 */
	public abstract void setLi_limite_amarillo(
		java.lang.Integer newLi_limite_amarillo);
	/**
	 * Get accessor for persistent attribute: li_limite_negro
	 */
	public abstract java.lang.Integer getLi_limite_negro();
	/**
	 * Set accessor for persistent attribute: li_limite_negro
	 */
	public abstract void setLi_limite_negro(
		java.lang.Integer newLi_limite_negro);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ActividadLocal getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal anActividad);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.LimiteKey ejbCreate(
		java.lang.Integer li_tipo_semaforo,
		java.lang.Long actividad_act_id)
		throws javax.ejb.CreateException {
		setLi_tipo_semaforo(li_tipo_semaforo);
		setActividad_act_id(actividad_act_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer li_tipo_semaforo,
		java.lang.Long actividad_act_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: actividad_act_id
	 */
	public abstract java.lang.Long getActividad_act_id();
	/**
	 * Set accessor for persistent attribute: actividad_act_id
	 */
	public abstract void setActividad_act_id(
		java.lang.Long newActividad_act_id);
}
