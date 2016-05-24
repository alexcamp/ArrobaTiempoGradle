package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Peticion_flujo
 */
public interface Peticion_flujoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: pefl_estado
	 */
	public java.lang.String getPefl_estado();
	/**
	 * Set accessor for persistent attribute: pefl_estado
	 */
	public void setPefl_estado(java.lang.String newPefl_estado);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_stLocal getProducto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Producto_servicio_stLocal aProducto_servicio_st);
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
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
			.soltec
			.ejb
			.eb
			.Actividad_flujoLocal anActividad_flujo);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Operacion_comercial_stLocal getOperacion_comercial_st();
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setOperacion_comercial_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Operacion_comercial_stLocal anOperacion_comercial_st);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: pefl_orden
	 */
	public java.lang.Integer getPefl_orden();
	/**
	 * Set accessor for persistent attribute: pefl_orden
	 */
	public void setPefl_orden(java.lang.Integer newPefl_orden);
}
