package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Localizacion
 */
public interface LocalizacionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: localizacion
	 */
	public java.lang.String getLocalizacion();
	/**
	 * Set accessor for persistent attribute: localizacion
	 */
	public void setLocalizacion(java.lang.String newLocalizacion);
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCodigo_cierre();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCodigo_cierre(java.util.Collection aCodigo_cierre);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal getFamilia_producto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFamilia_producto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal aFamilia_producto_servicio_st);
}
