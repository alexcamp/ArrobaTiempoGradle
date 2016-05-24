package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Actividad
 */
public abstract class ActividadBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ActividadKey ejbCreate(
		java.lang.Long act_id)
		throws javax.ejb.CreateException {
		setAct_id(act_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long act_id)
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
	public co.com.telefonica.atiempo.ejb.eb.ActividadKey ejbCreate(
		java.lang.Long act_id,
		java.lang.String act_codigo,
		java.lang.String act_descripcion,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argFk_activ_aplic,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_act_rol)
		throws javax.ejb.CreateException {
		setAct_id(act_id);
		setAct_codigo(act_codigo);
		setAct_descripcion(act_descripcion);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long act_id,
		java.lang.String act_codigo,
		java.lang.String act_descripcion,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argFk_activ_aplic,
		co.com.telefonica.atiempo.ejb.eb.RolLocal argFk_act_rol)
		throws javax.ejb.CreateException {
		setFk_activ_aplic(argFk_activ_aplic);
		setFk_act_rol(argFk_act_rol);
	}
	/**
	 * Get accessor for persistent attribute: act_id
	 */
	public abstract java.lang.Long getAct_id();
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public abstract void setAct_id(java.lang.Long newAct_id);
	/**
	 * Get accessor for persistent attribute: act_codigo
	 */
	public abstract java.lang.String getAct_codigo();
	/**
	 * Set accessor for persistent attribute: act_codigo
	 */
	public abstract void setAct_codigo(java.lang.String newAct_codigo);
	/**
	 * Get accessor for persistent attribute: act_descripcion
	 */
	public abstract java.lang.String getAct_descripcion();
	/**
	 * Set accessor for persistent attribute: act_descripcion
	 */
	public abstract void setAct_descripcion(
		java.lang.String newAct_descripcion);
	/**
	 * Get accessor for persistent attribute: act_orden_tc
	 */
	public abstract java.lang.Integer getAct_orden_tc();
	/**
	 * Set accessor for persistent attribute: act_orden_tc
	 */
	public abstract void setAct_orden_tc(java.lang.Integer newAct_orden_tc);
	/**
	 * Get accessor for persistent attribute: act_nombre_reversa
	 */
	public abstract java.lang.String getAct_nombre_reversa();
	/**
	 * Set accessor for persistent attribute: act_nombre_reversa
	 */
	public abstract void setAct_nombre_reversa(
		java.lang.String newAct_nombre_reversa);
	/**
	 * Get accessor for persistent attribute: act_manual
	 */
	public abstract java.lang.Short getAct_manual();
	/**
	 * Set accessor for persistent attribute: act_manual
	 */
	public abstract void setAct_manual(java.lang.Short newAct_manual);
	/**
	 * Get accessor for persistent attribute: act_ola
	 */
	public abstract java.math.BigDecimal getAct_ola();
	/**
	 * Set accessor for persistent attribute: act_ola
	 */
	public abstract void setAct_ola(java.math.BigDecimal newAct_ola);
	/**
	 * Get accessor for persistent attribute: act_nombre_flujo
	 */
	public abstract java.lang.String getAct_nombre_flujo();
	/**
	 * Set accessor for persistent attribute: act_nombre_flujo
	 */
	public abstract void setAct_nombre_flujo(
		java.lang.String newAct_nombre_flujo);
	/**
	 * This method was generated for supporting the relationship role named fk_activ_aplic.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.AplicacionLocal getFk_activ_aplic();
	/**
	 * This method was generated for supporting the relationship role named fk_activ_aplic.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_activ_aplic(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal aFk_activ_aplic);
	/**
	 * This method was generated for supporting the relationship role named fk_act_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.RolLocal getFk_act_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_act_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_act_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_act_rol);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named cierre_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCierre_actividad();
	/**
	 * This method was generated for supporting the relationship role named cierre_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCierre_actividad(
		java.util.Collection aCierre_actividad);
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBitacora_peticion();
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBitacora_peticion(
		java.util.Collection aBitacora_peticion);
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_ps_oc_actividad();
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_ps_oc_actividad(
		java.util.Collection aCausal_ps_oc_actividad);
	/**
	 * Get accessor for persistent attribute: act_quiebre
	 */
	public abstract java.lang.Integer getAct_quiebre();
	/**
	 * Set accessor for persistent attribute: act_quiebre
	 */
	public abstract void setAct_quiebre(java.lang.Integer newAct_quiebre);
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Actividad_flujoLocal getActividad_flujo();
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad_flujo(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Actividad_flujoLocal anActividad_flujo);
	/**
	 * This method was generated for supporting the relationship role named limite.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getLimite();
	/**
	 * This method was generated for supporting the relationship role named limite.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLimite(java.util.Collection aLimite);
	/**
	 * Get accessor for persistent attribute: acti_id
	 */
	public abstract java.lang.Integer getActi_id();
	/**
	 * Set accessor for persistent attribute: acti_id
	 */
	public abstract void setActi_id(java.lang.Integer newActi_id);
}
