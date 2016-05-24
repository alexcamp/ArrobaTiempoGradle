package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Estado_ps_peticion
 */
public interface Estado_ps_peticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_estado_cierre
	 */
	public java.lang.Integer getCod_estado_cierre();
	/**
	 * Set accessor for persistent attribute: cod_estado_cierre
	 */
	public void setCod_estado_cierre(java.lang.Integer newCod_estado_cierre);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicioLocal getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioLocal aProducto_servicio);
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
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionLocal getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_peticion(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicio_peticionLocal aProducto_servicio_peticion);
	/**
	 * Get accessor for persistent attribute: cod_causal
	 */
	public java.lang.Long getCod_causal();
	/**
	 * Set accessor for persistent attribute: cod_causal
	 */
	public void setCod_causal(java.lang.Long newCod_causal);
	/**
	 * Get accessor for persistent attribute: novedad
	 */
	public java.lang.String getNovedad();
	/**
	 * Set accessor for persistent attribute: novedad
	 */
	public void setNovedad(java.lang.String newNovedad);
	/**
	 * Get accessor for persistent attribute: cod_actividad
	 */
	public java.lang.Long getCod_actividad();
	/**
	 * Set accessor for persistent attribute: cod_actividad
	 */
	public void setCod_actividad(java.lang.Long newCod_actividad);
}
