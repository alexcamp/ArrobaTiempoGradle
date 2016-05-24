package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Operacion_comercial_st
 */
public interface Operacion_comercial_stLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: opco_nombre
	 */
	public java.lang.String getOpco_nombre();
	/**
	 * Set accessor for persistent attribute: opco_nombre
	 */
	public void setOpco_nombre(java.lang.String newOpco_nombre);
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
}
