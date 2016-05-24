package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Area_gestion
 */
public interface Area_gestionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_area_ges
	 */
	public java.lang.Long getCod_area_ges();
	/**
	 * Set accessor for persistent attribute: cod_area_ges
	 */
	public void setCod_area_ges(java.lang.Long newCod_area_ges);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
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
	/**
	 * This method was generated for supporting the relationship role named regla_area_ges_cod_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRegla_area_ges_cod_estado();
	/**
	 * This method was generated for supporting the relationship role named regla_area_ges_cod_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRegla_area_ges_cod_estado(
		java.util.Collection aRegla_area_ges_cod_estado);
}
