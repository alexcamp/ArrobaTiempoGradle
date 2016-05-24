package co.com.telefonica.atiempo.soltec.ejb.eb;
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
		.soltec
		.ejb
		.eb
		.Producto_servicio_peticionKey ejbCreate(
			java.lang.Long peti_numero,
			java.lang.Long correlativo,
			Operacion_comercial_stLocal oc,
			Producto_servicio_stLocal ps)
		throws javax.ejb.CreateException {
		setPeti_numero(peti_numero);
		setCorrelativo(correlativo);
		//TODO averigual q es este campo not null
		setPspe_realizado(new Short((short)0));
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long peti_numero,
		java.lang.Long correlativo,
		Operacion_comercial_stLocal oc,
		Producto_servicio_stLocal ps)
		throws javax.ejb.CreateException {
			setOperacion_comercial_st(oc);
			setProducto_servicio_st(ps);
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
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
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
	public abstract java.lang.Double getPspe_cargo_instalacion();
	/**
	 * Set accessor for persistent attribute: pspe_cargo_instalacion
	 */
	public abstract void setPspe_cargo_instalacion(
		java.lang.Double newPspe_cargo_instalacion);
	/**
	 * Get accessor for persistent attribute: pspe_renta_mensual
	 */
	public abstract java.lang.Double getPspe_renta_mensual();
	/**
	 * Set accessor for persistent attribute: pspe_renta_mensual
	 */
	public abstract void setPspe_renta_mensual(
		java.lang.Double newPspe_renta_mensual);
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
	 * Get accessor for persistent attribute: pspe_realizado
	 */
	public abstract java.lang.Short getPspe_realizado();
	/**
	 * Set accessor for persistent attribute: pspe_realizado
	 */
	public abstract void setPspe_realizado(java.lang.Short newPspe_realizado);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setOperacion_comercial_st(
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
	public abstract co
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
	public abstract void setProducto_servicio_st(
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
	public abstract co
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
	public abstract void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
		
	public Long getIdProductoServicio()
	{
	   Producto_servicio_stKey producto_servicioKey=(Producto_servicio_stKey) getProducto_servicio_st().getPrimaryKey();
	   return producto_servicioKey.ps_id; 	
	}
 
	public Long getIdOperacionComercial()
	{
	   Operacion_comercial_stKey operacion_comercialKey=(Operacion_comercial_stKey) getOperacion_comercial_st().getPrimaryKey();
	   return operacion_comercialKey.opco_id;
	}		
	/**
	 * ejbCreate
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_peticionKey ejbCreate(
			java.lang.Long peti_numero,
			java.lang.Long correlativo)
		throws javax.ejb.CreateException {
		setPeti_numero(peti_numero);
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long peti_numero,
		java.lang.Long correlativo)
		throws javax.ejb.CreateException {
	}
}
