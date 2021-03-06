package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Actividad_flujo
 */
public interface Actividad_flujoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: acti_codigo
	 */
	public java.lang.String getActi_codigo();
	/**
	 * Set accessor for persistent attribute: acti_codigo
	 */
	public void setActi_codigo(java.lang.String newActi_codigo);
	/**
	 * Get accessor for persistent attribute: acti_descripcion
	 */
	public java.lang.String getActi_descripcion();
	/**
	 * Set accessor for persistent attribute: acti_descripcion
	 */
	public void setActi_descripcion(java.lang.String newActi_descripcion);
	/**
	 * Get accessor for persistent attribute: acti_orden
	 */
	public java.lang.Integer getActi_orden();
	/**
	 * Set accessor for persistent attribute: acti_orden
	 */
	public void setActi_orden(java.lang.Integer newActi_orden);
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
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCatalogo_causal(java.util.Collection aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setActividad(java.util.Collection anActividad);
}
