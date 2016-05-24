package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.PsVsOcVO;

/**
 * Bean implementation class for Enterprise Bean: Producto_servicio_peticion
 */
public abstract class Producto_servicio_peticionBean
	implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}

	/**
	 * ejbCreate
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionKey ejbCreate(
		java.lang.Long correlativo,
		java.lang.Long fk_psp_pet_peti_numero)
		throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		setFk_psp_pet_peti_numero(fk_psp_pet_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long correlativo,
		java.lang.Long fk_psp_pet_peti_numero)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbCreate
	 */
	
	public Producto_servicio_peticionKey ejbCreate(Long correlativo,PeticionLocal pet,Operacion_comercialLocal op,Producto_servicioLocal ps) throws javax.ejb.CreateException
	{
		PeticionKey peticionKey=(PeticionKey) pet.getPrimaryKey();
		setFk_psp_pet_peti_numero(peticionKey.peti_numero);
		setCorrelativo(correlativo);
		setPspe_realizado(new Short(new Integer(0).shortValue()));
		return null;
	}
	
	public void ejbPostCreate(Long correlativo,PeticionLocal pet,Operacion_comercialLocal op,Producto_servicioLocal ps) throws javax.ejb.CreateException
	{
		setFk_psp_pet(pet);
		setOperacion_comercial(op);
		setProducto_servicio(ps);
	}
	
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * ejbCreate
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionKey ejbCreate(
		java.lang.Long correlativo,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argFk_psp_pet)
		throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argFk_psp_petPK =
			(co.com.telefonica.atiempo.ejb.eb.PeticionKey) argFk_psp_pet
				.getPrimaryKey();
		setFk_psp_pet_peti_numero(argFk_psp_petPK.peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long correlativo,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argFk_psp_pet)
		throws javax.ejb.CreateException {
		setFk_psp_pet(argFk_psp_pet);
	}
	/**
	 * Get accessor for persistent attribute: pspe_cantidad
	 */
	public abstract java.lang.Integer getPspe_cantidad();
	/**
	 * Set accessor for persistent attribute: pspe_cantidad
	 */
	public abstract void setPspe_cantidad(java.lang.Integer newPspe_cantidad);
	/**
	 * Get accessor for persistent attribute: pspe_cargo_instalacion
	 */
	public abstract java.math.BigDecimal getPspe_cargo_instalacion();
	/**
	 * Set accessor for persistent attribute: pspe_cargo_instalacion
	 */
	public abstract void setPspe_cargo_instalacion(
		java.math.BigDecimal newPspe_cargo_instalacion);
	/**
	 * Get accessor for persistent attribute: pspe_renta_mensual
	 */
	public abstract java.math.BigDecimal getPspe_renta_mensual();
	/**
	 * Set accessor for persistent attribute: pspe_renta_mensual
	 */
	public abstract void setPspe_renta_mensual(
		java.math.BigDecimal newPspe_renta_mensual);
	/**
	 * Get accessor for persistent attribute: pspe_tgen_nodo
	 */
	public abstract java.lang.Integer getPspe_tgen_nodo();
	/**
	 * Set accessor for persistent attribute: pspe_tgen_nodo
	 */
	public abstract void setPspe_tgen_nodo(java.lang.Integer newPspe_tgen_nodo);
	/**
	 * Get accessor for persistent attribute: pspe_tgen_serv_msu
	 */
	public abstract java.lang.Integer getPspe_tgen_serv_msu();
	/**
	 * Set accessor for persistent attribute: pspe_tgen_serv_msu
	 */
	public abstract void setPspe_tgen_serv_msu(
		java.lang.Integer newPspe_tgen_serv_msu);
	/**
	 * Get accessor for persistent attribute: pspe_codigo_familia
	 */
	public abstract java.lang.String getPspe_codigo_familia();
	/**
	 * Set accessor for persistent attribute: pspe_codigo_familia
	 */
	public abstract void setPspe_codigo_familia(
		java.lang.String newPspe_codigo_familia);
	/**
	 * Get accessor for persistent attribute: pspe_fecha_inicio
	 */
	public abstract java.sql.Timestamp getPspe_fecha_inicio();
	/**
	 * Set accessor for persistent attribute: pspe_fecha_inicio
	 */
	public abstract void setPspe_fecha_inicio(
		java.sql.Timestamp newPspe_fecha_inicio);
	/**
	 * Get accessor for persistent attribute: pspe_fecha_fin
	 */
	public abstract java.sql.Timestamp getPspe_fecha_fin();
	/**
	 * Set accessor for persistent attribute: pspe_fecha_fin
	 */
	public abstract void setPspe_fecha_fin(
		java.sql.Timestamp newPspe_fecha_fin);
	/**
	 * Get accessor for persistent attribute: nom_pro_ser_no
	 */
	public abstract java.lang.String getNom_pro_ser_no();
	/**
	 * Set accessor for persistent attribute: nom_pro_ser_no
	 */
	public abstract void setNom_pro_ser_no(java.lang.String newNom_pro_ser_no);
	/**
	 * Get accessor for persistent attribute: obs_sub_ds
	 */
	public abstract java.lang.String getObs_sub_ds();
	/**
	 * Set accessor for persistent attribute: obs_sub_ds
	 */
	public abstract void setObs_sub_ds(java.lang.String newObs_sub_ds);
	/**
	 * Get accessor for persistent attribute: tip_pro_ser_cd
	 */
	public abstract java.lang.Long getTip_pro_ser_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_ser_cd
	 */
	public abstract void setTip_pro_ser_cd(java.lang.Long newTip_pro_ser_cd);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: cod_tip_uso
	 */
	public abstract java.lang.Long getCod_tip_uso();
	/**
	 * Set accessor for persistent attribute: cod_tip_uso
	 */
	public abstract void setCod_tip_uso(java.lang.Long newCod_tip_uso);
	/**
	 * Get accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public abstract java.lang.Long getTip_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: tip_pro_cmr_cd
	 */
	public abstract void setTip_pro_cmr_cd(java.lang.Long newTip_pro_cmr_cd);
	/**
	 * Get accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public abstract java.lang.Long getSbt_pro_cmr_cd();
	/**
	 * Set accessor for persistent attribute: sbt_pro_cmr_cd
	 */
	public abstract void setSbt_pro_cmr_cd(java.lang.Long newSbt_pro_cmr_cd);
	/**
	 * This method was generated for supporting the relationship role named fk_psp_pet.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PeticionLocal getFk_psp_pet();
	/**
	 * This method was generated for supporting the relationship role named fk_psp_pet.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_psp_pet(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_psp_pet);
	/**
	 * This method was generated for supporting the relationship role named fk_01_subp_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFk_01_subp_atis(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Subpeticion_atisLocal aFk_01_subp_atis);
	/**
	 * Get accessor for persistent attribute: fk_psp_pet_peti_numero
	 */
	public abstract java.lang.Long getFk_psp_pet_peti_numero();
	/**
	 * Set accessor for persistent attribute: fk_psp_pet_peti_numero
	 */
	public abstract void setFk_psp_pet_peti_numero(
		java.lang.Long newFk_psp_pet_peti_numero);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setProducto_servicio(
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
	public abstract co
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
	public abstract void setOperacion_comercial(
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
	public abstract java.lang.Short getPspe_realizado();
	/**
	 * Set accessor for persistent attribute: pspe_realizado
	 */
	public abstract void setPspe_realizado(java.lang.Short newPspe_realizado);
	
//	 public Long getIdProductoServicio()
//	 {
//		Producto_servicioKey producto_servicioKey=(Producto_servicioKey) getProducto_servicio().getPrimaryKey();
//		return producto_servicioKey.ps_id; 	
//	 }
	 
	 public Long getIdOperacionComercial()
	 {
	 	Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) getOperacion_comercial().getPrimaryKey();
	 	return operacion_comercialKey.opco_id;
	 }
	 
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getEstado_ps_peticion();
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setEstado_ps_peticion(
		java.util.Collection anEstado_ps_peticion);
		
	public Long getPsId()
	{
		Producto_servicioKey producto_servicioKey=(Producto_servicioKey) getProducto_servicio().getPrimaryKey();
		return producto_servicioKey.ps_id;
	}
	
	public PsVsOcVO toPsVsOc()
	{
		PsVsOcVO psVsOcVO=new PsVsOcVO();
		psVsOcVO.setOpComId(getIdOperacionComercial());
		psVsOcVO.setPsId(getPsId());
		return psVsOcVO;
	}
	
	public Familia_producto_servicioKey getFamiliaKey()
	{
		return (Familia_producto_servicioKey)getProducto_servicio().getFamilia_producto_servicio().getPrimaryKey();
	}
	
	public Producto_servicioKey getPsKey()
	{
		return (Producto_servicioKey)getProducto_servicio().getPrimaryKey();
	}
	/**
	 * Get accessor for persistent attribute: estado_baja
	 */
	public abstract java.lang.Long getEstado_baja();
	/**
	 * Set accessor for persistent attribute: estado_baja
	 */
	public abstract void setEstado_baja(java.lang.Long newEstado_baja);
}
