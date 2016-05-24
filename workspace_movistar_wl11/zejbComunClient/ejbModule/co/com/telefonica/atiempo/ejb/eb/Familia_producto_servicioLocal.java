package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Familia_producto_servicio
 */
public interface Familia_producto_servicioLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: faps_nombre
	 */
	public java.lang.String getFaps_nombre();
	/**
	 * Set accessor for persistent attribute: faps_nombre
	 */
	public void setFaps_nombre(java.lang.String newFaps_nombre);
	/**
	 * Get accessor for persistent attribute: faps_codigo
	 */
	public java.lang.String getFaps_codigo();
	/**
	 * Set accessor for persistent attribute: faps_codigo
	 */
	public void setFaps_codigo(java.lang.String newFaps_codigo);
	/**
	 * Get accessor for persistent attribute: faps_prioridad
	 */
	public java.lang.Integer getFaps_prioridad();
	/**
	 * Set accessor for persistent attribute: faps_prioridad
	 */
	public void setFaps_prioridad(java.lang.Integer newFaps_prioridad);
	/**
	 * Get accessor for persistent attribute: faps_id_padre
	 */
	public java.lang.Long getFaps_id_padre();
	/**
	 * Set accessor for persistent attribute: faps_id_padre
	 */
	public void setFaps_id_padre(java.lang.Long newFaps_id_padre);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio(java.util.Collection aProducto_servicio);
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
}
