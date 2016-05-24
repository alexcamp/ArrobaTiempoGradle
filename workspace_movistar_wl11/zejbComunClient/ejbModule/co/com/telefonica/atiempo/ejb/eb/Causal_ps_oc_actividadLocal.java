package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Causal_ps_oc_actividad
 */
public interface Causal_ps_oc_actividadLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Catalogo_causalLocal getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCatalogo_causal(
		co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialLocal getOperacion_comercial();
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setOperacion_comercial(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal anOperacion_comercial);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setActividad(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal anActividad);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Familia_producto_servicioLocal getFamilia_producto_servicio();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFamilia_producto_servicio(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Familia_producto_servicioLocal aFamilia_producto_servicio);
}
