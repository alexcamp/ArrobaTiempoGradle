package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Familia_producto_servicio_st
 */
public interface Familia_producto_servicio_stLocal
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
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProducto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_st(
		java.util.Collection aProducto_servicio_st);
	/**
	 * Get accessor for persistent attribute: tiempo
	 */
	public java.lang.Integer getTiempo();
	/**
	 * Set accessor for persistent attribute: tiempo
	 */
	public void setTiempo(java.lang.Integer newTiempo);
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getLocalizacion();
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalizacion(java.util.Collection aLocalizacion);
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCatalago_prueba_linea();
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCatalago_prueba_linea(
		java.util.Collection aCatalago_prueba_linea);
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getArea_gestion();
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setArea_gestion(java.util.Collection anArea_gestion);
}
