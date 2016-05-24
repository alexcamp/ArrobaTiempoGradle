package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Operacion_comercial
 */
public interface Operacion_comercialLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: titr_id
	 */
	public java.lang.Integer getTitr_id();
	/**
	 * Set accessor for persistent attribute: titr_id
	 */
	public void setTitr_id(java.lang.Integer newTitr_id);
	/**
	 * Get accessor for persistent attribute: opco_nombre
	 */
	public java.lang.String getOpco_nombre();
	/**
	 * Set accessor for persistent attribute: opco_nombre
	 */
	public void setOpco_nombre(java.lang.String newOpco_nombre);
	/**
	 * Get accessor for persistent attribute: opco_tipo
	 */
	public java.lang.String getOpco_tipo();
	/**
	 * Set accessor for persistent attribute: opco_tipo
	 */
	public void setOpco_tipo(java.lang.String newOpco_tipo);
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion_flujo();
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_flujo(java.util.Collection aPeticion_flujo);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
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
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTraslado();
	/**
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTraslado(java.util.Collection aTraslado);
	/**
	 * Get accessor for persistent attribute: opco_tras
	 */
	public java.lang.String getOpco_tras();
	/**
	 * Set accessor for persistent attribute: opco_tras
	 */
	public void setOpco_tras(java.lang.String newOpco_tras);
	/**
	 * This method was generated for supporting the relationship role named contadores.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getContadores();
	/**
	 * This method was generated for supporting the relationship role named contadores.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setContadores(java.util.Collection aContadores);
	/**
	 * Get accessor for persistent attribute: opco_rev
	 */
	public java.lang.Long getOpco_rev();
	/**
	 * Set accessor for persistent attribute: opco_rev
	 */
	public void setOpco_rev(java.lang.Long newOpco_rev);
	/**
	 * Get accessor for persistent attribute: opco_web_tutor
	 */
	public java.lang.String getOpco_web_tutor();
	/**
	 * Set accessor for persistent attribute: opco_web_tutor
	 */
	public void setOpco_web_tutor(java.lang.String newOpco_web_tutor);
	/**
	 * Get accessor for persistent attribute: opco_sva
	 */
	public java.lang.String getOpco_sva();
	/**
	 * Set accessor for persistent attribute: opco_sva
	 */
	public void setOpco_sva(java.lang.String newOpco_sva);
}
