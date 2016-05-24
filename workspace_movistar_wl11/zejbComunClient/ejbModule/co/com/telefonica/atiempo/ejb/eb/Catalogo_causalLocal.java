package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Catalogo_causal
 */
public interface Catalogo_causalLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: quiebre
	 */
	public java.lang.Integer getQuiebre();
	/**
	 * Set accessor for persistent attribute: quiebre
	 */
	public void setQuiebre(java.lang.Integer newQuiebre);
	/**
	 * Get accessor for persistent attribute: descripcion_causal
	 */
	public java.lang.String getDescripcion_causal();
	/**
	 * Set accessor for persistent attribute: descripcion_causal
	 */
	public void setDescripcion_causal(java.lang.String newDescripcion_causal);
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
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausal_peticion(java.util.Collection aCausal_peticion);
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
	 * This method was generated for supporting the relationship role named errorlegado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal getErrorlegado();
	/**
	 * This method was generated for supporting the relationship role named errorlegado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setErrorlegado(
		co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal anErrorlegado);
	/**
	 * Get accessor for persistent attribute: gestionable
	 */
	public java.lang.Short getGestionable();
	/**
	 * Set accessor for persistent attribute: gestionable
	 */
	public void setGestionable(java.lang.Short newGestionable);
}
