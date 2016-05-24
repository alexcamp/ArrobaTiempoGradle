package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.PsVsOcVO;

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
	public java.math.BigDecimal getPspe_cargo_instalacion();
	/**
	 * Set accessor for persistent attribute: pspe_cargo_instalacion
	 */
	public void setPspe_cargo_instalacion(
		java.math.BigDecimal newPspe_cargo_instalacion);
	/**
	 * Get accessor for persistent attribute: pspe_renta_mensual
	 */
	public java.math.BigDecimal getPspe_renta_mensual();
	/**
	 * Set accessor for persistent attribute: pspe_renta_mensual
	 */
	public void setPspe_renta_mensual(
		java.math.BigDecimal newPspe_renta_mensual);
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
	 * Get accessor for persistent attribute: pspe_codigo_familia
	 */
	public java.lang.String getPspe_codigo_familia();
	/**
	 * Set accessor for persistent attribute: pspe_codigo_familia
	 */
	public void setPspe_codigo_familia(java.lang.String newPspe_codigo_familia);
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
	 * This method was generated for supporting the relationship role named fk_psp_pet.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getFk_psp_pet();
	/**
	 * This method was generated for supporting the relationship role named fk_psp_pet.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_psp_pet(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_psp_pet);
	/**
	 * This method was generated for supporting the relationship role named fk_01_subp_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_atisLocal getFk_01_subp_atis();
	/**
	 * This method was generated for supporting the relationship role named fk_01_subp_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_01_subp_atis(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Subpeticion_atisLocal aFk_01_subp_atis);
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
	 * Get accessor for persistent attribute: pspe_realizado
	 */
	public java.lang.Short getPspe_realizado();
	public void setPspe_realizado(java.lang.Short newPspe_realizado);
	public Long getIdOperacionComercial();
	public java.util.Collection getEstado_ps_peticion();
	public void setEstado_ps_peticion(java.util.Collection anEstado_ps_peticion);
	public Long getPsId();
	public PsVsOcVO toPsVsOc();
	public Familia_producto_servicioKey getFamiliaKey();
	public Producto_servicioKey getPsKey();
	/**
	 * Get accessor for persistent attribute: estado_baja
	 */
	public java.lang.Long getEstado_baja();
	/**
	 * Set accessor for persistent attribute: estado_baja
	 */
	public void setEstado_baja(java.lang.Long newEstado_baja);
}
