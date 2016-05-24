package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Producto_servicio_peticion
 */
public interface Producto_servicio_peticionLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: pspe_cantidad
	 */
	public java.lang.Integer getPspe_cantidad();
	/**
	 * Set accessor for persistent attribute: pspe_cantidad
	 */
	public void setPspe_cantidad(java.lang.Integer newPspe_cantidad);
	/**
	 * Get accessor for persistent attribute: pspe_cargo_instalacion
	 */
	public java.lang.Double getPspe_cargo_instalacion();
	/**
	 * Set accessor for persistent attribute: pspe_cargo_instalacion
	 */
	public void setPspe_cargo_instalacion(
		java.lang.Double newPspe_cargo_instalacion);
	/**
	 * Get accessor for persistent attribute: pspe_renta_mensual
	 */
	public java.lang.Double getPspe_renta_mensual();
	/**
	 * Set accessor for persistent attribute: pspe_renta_mensual
	 */
	public void setPspe_renta_mensual(java.lang.Double newPspe_renta_mensual);
	/**
	 * Get accessor for persistent attribute: pspe_tgen_nodo
	 */
	public java.lang.Integer getPspe_tgen_nodo();
	/**
	 * Set accessor for persistent attribute: pspe_tgen_nodo
	 */
	public void setPspe_tgen_nodo(java.lang.Integer newPspe_tgen_nodo);
	/**
	 * Get accessor for persistent attribute: pspe_tgen_serv_msu
	 */
	public java.lang.Integer getPspe_tgen_serv_msu();
	/**
	 * Set accessor for persistent attribute: pspe_tgen_serv_msu
	 */
	public void setPspe_tgen_serv_msu(java.lang.Integer newPspe_tgen_serv_msu);
	/**
	 * Get accessor for persistent attribute: pspe_fecha_inicio
	 */
	public java.sql.Timestamp getPspe_fecha_inicio();
	/**
	 * Set accessor for persistent attribute: pspe_fecha_inicio
	 */
	public void setPspe_fecha_inicio(java.sql.Timestamp newPspe_fecha_inicio);
	/**
	 * Get accessor for persistent attribute: pspe_fecha_fin
	 */
	public java.sql.Timestamp getPspe_fecha_fin();
	/**
	 * Set accessor for persistent attribute: pspe_fecha_fin
	 */
	public void setPspe_fecha_fin(java.sql.Timestamp newPspe_fecha_fin);
	/**
	 * Get accessor for persistent attribute: nom_pro_ser_no
	 */
	public java.lang.String getNom_pro_ser_no();
	/**
	 * Set accessor for persistent attribute: nom_pro_ser_no
	 */
	public void setNom_pro_ser_no(java.lang.String newNom_pro_ser_no);
	/**
	 * Get accessor for persistent attribute: obs_sub_ds
	 */
	public java.lang.String getObs_sub_ds();
	/**
	 * Set accessor for persistent attribute: obs_sub_ds
	 */
	public void setObs_sub_ds(java.lang.String newObs_sub_ds);
	/**
	 * Get accessor for persistent attribute: tip_pro_ser_cd
	 */
	public java.lang.Long getTip_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_ser_cd
	 */
	public void setTip_pro_ser_cd(java.lang.Long newTip_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: cod_tip_uso
	 */
	public java.lang.Long getCod_tip_uso();
	/**
	 * Set accessor for persistent attribute: cod_tip_uso
	 */
	public void setCod_tip_uso(java.lang.Long newCod_tip_uso);
	/**
	 * Get accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public java.lang.Long getTip_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public void setTip_pro_cmr_cd(java.lang.Long newTip_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public java.lang.Long getSbt_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public void setSbt_pro_cmr_cd(java.lang.Long newSbt_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: pspe_realizado
	 */
	public java.lang.Short getPspe_realizado();
	/**
	 * Set accessor for persistent attribute: pspe_realizado
	 */
	public void setPspe_realizado(java.lang.Short newPspe_realizado);
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

	public Long getIdProductoServicio();
 
	public Long getIdOperacionComercial();		
}
