package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Actividad
 */
public interface ActividadLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: act_codigo
	 */
	public java.lang.String getAct_codigo();
	/**
	 * Set accessor for persistent attribute: act_codigo
	 */
	public void setAct_codigo(java.lang.String newAct_codigo);
	/**
	 * Get accessor for persistent attribute: act_descripcion
	 */
	public java.lang.String getAct_descripcion();
	/**
	 * Set accessor for persistent attribute: act_descripcion
	 */
	public void setAct_descripcion(java.lang.String newAct_descripcion);
	/**
	 * Get accessor for persistent attribute: act_orden_tc
	 */
	public java.lang.Integer getAct_orden_tc();
	/**
	 * Set accessor for persistent attribute: act_orden_tc
	 */
	public void setAct_orden_tc(java.lang.Integer newAct_orden_tc);
	/**
	 * Get accessor for persistent attribute: act_nombre_reversa
	 */
	public java.lang.String getAct_nombre_reversa();
	/**
	 * Set accessor for persistent attribute: act_nombre_reversa
	 */
	public void setAct_nombre_reversa(java.lang.String newAct_nombre_reversa);
	/**
	 * Get accessor for persistent attribute: act_manual
	 */
	public java.lang.Short getAct_manual();
	/**
	 * Set accessor for persistent attribute: act_manual
	 */
	public void setAct_manual(java.lang.Short newAct_manual);
	/**
	 * Get accessor for persistent attribute: act_ola
	 */
	public java.math.BigDecimal getAct_ola();
	/**
	 * Set accessor for persistent attribute: act_ola
	 */
	public void setAct_ola(java.math.BigDecimal newAct_ola);
	/**
	 * Get accessor for persistent attribute: act_nombre_flujo
	 */
	public java.lang.String getAct_nombre_flujo();
	/**
	 * Set accessor for persistent attribute: act_nombre_flujo
	 */
	public void setAct_nombre_flujo(java.lang.String newAct_nombre_flujo);
	/**
	 * This method was generated for supporting the relationship role named fk_activ_aplic.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal getFk_activ_aplic();
	/**
	 * This method was generated for supporting the relationship role named fk_activ_aplic.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_activ_aplic(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal aFk_activ_aplic);
	/**
	 * This method was generated for supporting the relationship role named fk_act_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal getFk_act_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_act_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_act_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_act_rol);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named cierre_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCierre_actividad();
	/**
	 * This method was generated for supporting the relationship role named cierre_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCierre_actividad(java.util.Collection aCierre_actividad);
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBitacora_peticion();
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBitacora_peticion(java.util.Collection aBitacora_peticion);
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCausal_ps_oc_actividad();
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausal_ps_oc_actividad(
		java.util.Collection aCausal_ps_oc_actividad);
	/**
	 * Get accessor for persistent attribute: act_quiebre
	 */
	public java.lang.Integer getAct_quiebre();
	/**
	 * Set accessor for persistent attribute: act_quiebre
	 */
	public void setAct_quiebre(java.lang.Integer newAct_quiebre);
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
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
	public void setActividad_flujo(
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
	public java.util.Collection getLimite();
	/**
	 * This method was generated for supporting the relationship role named limite.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLimite(java.util.Collection aLimite);
	/**
	 * Get accessor for persistent attribute: acti_id
	 */
	public java.lang.Integer getActi_id();
	/**
	 * Set accessor for persistent attribute: acti_id
	 */
	public void setActi_id(java.lang.Integer newActi_id);
}
