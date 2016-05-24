package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Recursos_linea_basica
 */
public abstract class Recursos_linea_basicaBean
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
	public co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaKey ejbCreate(
		java.lang.Long id_conector) throws javax.ejb.CreateException {
		setId_conector(id_conector);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_conector)
		throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: id_conector
	 */
	public abstract java.lang.Long getId_conector();
	/**
	 * Set accessor for persistent attribute: id_conector
	 */
	public abstract void setId_conector(java.lang.Long newId_conector);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: dist_sec_asg
	 */
	public abstract java.lang.Long getDist_sec_asg();
	/**
	 * Set accessor for persistent attribute: dist_sec_asg
	 */
	public abstract void setDist_sec_asg(java.lang.Long newDist_sec_asg);
	/**
	 * Get accessor for persistent attribute: desc_dist_sec_adg
	 */
	public abstract java.lang.String getDesc_dist_sec_adg();
	/**
	 * Set accessor for persistent attribute: desc_dist_sec_adg
	 */
	public abstract void setDesc_dist_sec_adg(
		java.lang.String newDesc_dist_sec_adg);
	/**
	 * Get accessor for persistent attribute: armario_asg
	 */
	public abstract java.lang.String getArmario_asg();
	/**
	 * Set accessor for persistent attribute: armario_asg
	 */
	public abstract void setArmario_asg(java.lang.String newArmario_asg);
	/**
	 * Get accessor for persistent attribute: caja_asg
	 */
	public abstract java.lang.String getCaja_asg();
	/**
	 * Set accessor for persistent attribute: caja_asg
	 */
	public abstract void setCaja_asg(java.lang.String newCaja_asg);
	/**
	 * Get accessor for persistent attribute: desc_dist_prim_asg
	 */
	public abstract java.lang.String getDesc_dist_prim_asg();
	/**
	 * Set accessor for persistent attribute: desc_dist_prim_asg
	 */
	public abstract void setDesc_dist_prim_asg(
		java.lang.String newDesc_dist_prim_asg);
	/**
	 * Get accessor for persistent attribute: liston_asg
	 */
	public abstract java.lang.String getListon_asg();
	/**
	 * Set accessor for persistent attribute: liston_asg
	 */
	public abstract void setListon_asg(java.lang.String newListon_asg);
	/**
	 * Get accessor for persistent attribute: par_liston_asg
	 */
	public abstract java.lang.Long getPar_liston_asg();
	/**
	 * Set accessor for persistent attribute: par_liston_asg
	 */
	public abstract void setPar_liston_asg(java.lang.Long newPar_liston_asg);
	/**
	 * Get accessor for persistent attribute: cable
	 */
	public abstract java.lang.String getCable();
	/**
	 * Set accessor for persistent attribute: cable
	 */
	public abstract void setCable(java.lang.String newCable);
	/**
	 * Get accessor for persistent attribute: par_cable
	 */
	public abstract java.lang.Long getPar_cable();
	/**
	 * Set accessor for persistent attribute: par_cable
	 */
	public abstract void setPar_cable(java.lang.Long newPar_cable);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public abstract java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public abstract void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: telefono_asg
	 */
	public abstract java.lang.Long getTelefono_asg();
	/**
	 * Set accessor for persistent attribute: telefono_asg
	 */
	public abstract void setTelefono_asg(java.lang.Long newTelefono_asg);
	/**
	 * Get accessor for persistent attribute: len
	 */
	public abstract java.lang.String getLen();
	/**
	 * Set accessor for persistent attribute: len
	 */
	public abstract void setLen(java.lang.String newLen);
	/**
	 * Get accessor for persistent attribute: ods_apsc
	 */
	public abstract java.lang.Long getOds_apsc();
	/**
	 * Set accessor for persistent attribute: ods_apsc
	 */
	public abstract void setOds_apsc(java.lang.Long newOds_apsc);
	/**
	 * Get accessor for persistent attribute: dist_sec_ant
	 */
	public abstract java.lang.Long getDist_sec_ant();
	/**
	 * Set accessor for persistent attribute: dist_sec_ant
	 */
	public abstract void setDist_sec_ant(java.lang.Long newDist_sec_ant);
	/**
	 * Get accessor for persistent attribute: desc_dist_sec_ant
	 */
	public abstract java.lang.String getDesc_dist_sec_ant();
	/**
	 * Set accessor for persistent attribute: desc_dist_sec_ant
	 */
	public abstract void setDesc_dist_sec_ant(
		java.lang.String newDesc_dist_sec_ant);
	/**
	 * Get accessor for persistent attribute: liston_asg_ant
	 */
	public abstract java.lang.String getListon_asg_ant();
	/**
	 * Set accessor for persistent attribute: liston_asg_ant
	 */
	public abstract void setListon_asg_ant(java.lang.String newListon_asg_ant);
	/**
	 * Get accessor for persistent attribute: cable_ant
	 */
	public abstract java.lang.String getCable_ant();
	/**
	 * Set accessor for persistent attribute: cable_ant
	 */
	public abstract void setCable_ant(java.lang.String newCable_ant);
	/**
	 * Get accessor for persistent attribute: par_cable_ant
	 */
	public abstract java.lang.Long getPar_cable_ant();
	/**
	 * Set accessor for persistent attribute: par_cable_ant
	 */
	public abstract void setPar_cable_ant(java.lang.Long newPar_cable_ant);
	/**
	 * Get accessor for persistent attribute: central_ant
	 */
	public abstract java.lang.Long getCentral_ant();
	/**
	 * Set accessor for persistent attribute: central_ant
	 */
	public abstract void setCentral_ant(java.lang.Long newCentral_ant);
	/**
	 * Get accessor for persistent attribute: desc_central_ant
	 */
	public abstract java.lang.String getDesc_central_ant();
	/**
	 * Set accessor for persistent attribute: desc_central_ant
	 */
	public abstract void setDesc_central_ant(
		java.lang.String newDesc_central_ant);
	/**
	 * Get accessor for persistent attribute: telefono_ant
	 */
	public abstract java.lang.Long getTelefono_ant();
	/**
	 * Set accessor for persistent attribute: telefono_ant
	 */
	public abstract void setTelefono_ant(java.lang.Long newTelefono_ant);
	/**
	 * Get accessor for persistent attribute: len_anterior
	 */
	public abstract java.lang.String getLen_anterior();
	/**
	 * Set accessor for persistent attribute: len_anterior
	 */
	public abstract void setLen_anterior(java.lang.String newLen_anterior);
	/**
	 * Get accessor for persistent attribute: dir_distribuidor
	 */
	public abstract java.lang.String getDir_distribuidor();
	/**
	 * Set accessor for persistent attribute: dir_distribuidor
	 */
	public abstract void setDir_distribuidor(
		java.lang.String newDir_distribuidor);
	/**
	 * Get accessor for persistent attribute: dir_armario
	 */
	public abstract java.lang.String getDir_armario();
	/**
	 * Set accessor for persistent attribute: dir_armario
	 */
	public abstract void setDir_armario(java.lang.String newDir_armario);
	/**
	 * Get accessor for persistent attribute: cna
	 */
	public abstract java.lang.String getCna();
	/**
	 * Set accessor for persistent attribute: cna
	 */
	public abstract void setCna(java.lang.String newCna);
	/**
	 * Get accessor for persistent attribute: result_accion
	 */
	public abstract java.lang.Integer getResult_accion();
	/**
	 * Set accessor for persistent attribute: result_accion
	 */
	public abstract void setResult_accion(java.lang.Integer newResult_accion);
	/**
	 * Get accessor for persistent attribute: cod_central
	 */
	public abstract java.lang.Long getCod_central();
	/**
	 * Set accessor for persistent attribute: cod_central
	 */
	public abstract void setCod_central(java.lang.Long newCod_central);
	/**
	 * Get accessor for persistent attribute: armario_ant
	 */
	public abstract java.lang.String getArmario_ant();
	/**
	 * Set accessor for persistent attribute: armario_ant
	 */
	public abstract void setArmario_ant(java.lang.String newArmario_ant);
	/**
	 * Get accessor for persistent attribute: caja_asg_ant
	 */
	public abstract java.lang.String getCaja_asg_ant();
	/**
	 * Set accessor for persistent attribute: caja_asg_ant
	 */
	public abstract void setCaja_asg_ant(java.lang.String newCaja_asg_ant);
	/**
	 * Get accessor for persistent attribute: par_caja_ant
	 */
	public abstract java.lang.Long getPar_caja_ant();
	/**
	 * Set accessor for persistent attribute: par_caja_ant
	 */
	public abstract void setPar_caja_ant(java.lang.Long newPar_caja_ant);
	/**
	 * Get accessor for persistent attribute: dist_prim_ant
	 */
	public abstract java.lang.Long getDist_prim_ant();
	/**
	 * Set accessor for persistent attribute: dist_prim_ant
	 */
	public abstract void setDist_prim_ant(java.lang.Long newDist_prim_ant);
	/**
	 * Get accessor for persistent attribute: desc_dist_prim_ant
	 */
	public abstract java.lang.String getDesc_dist_prim_ant();
	/**
	 * Set accessor for persistent attribute: desc_dist_prim_ant
	 */
	public abstract void setDesc_dist_prim_ant(
		java.lang.String newDesc_dist_prim_ant);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_lineaLocal getMensaje_estado_linea();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_linea(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Mensaje_estado_lineaLocal aMensaje_estado_linea);
	/**
	 * This method was generated for supporting the relationship role named dslam_conec9_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getDslam_conec9_apsc();
	/**
	 * This method was generated for supporting the relationship role named dslam_conec9_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDslam_conec9_apsc(
		java.util.Collection aDslam_conec9_apsc);
	/**
	 * Get accessor for persistent attribute: par_caja_asg
	 */
	public abstract java.lang.Long getPar_caja_asg();
	/**
	 * Set accessor for persistent attribute: par_caja_asg
	 */
	public abstract void setPar_caja_asg(java.lang.Long newPar_caja_asg);
	/**
	 * Get accessor for persistent attribute: dist_prim_asg
	 */
	public abstract java.lang.Long getDist_prim_asg();
	/**
	 * Set accessor for persistent attribute: dist_prim_asg
	 */
	public abstract void setDist_prim_asg(java.lang.Long newDist_prim_asg);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * This method was generated for supporting the relationship role named servicio_basico_supl_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java
		.util
		.Collection getServicio_basico_supl_conec6_apsc_linea();
	/**
	 * This method was generated for supporting the relationship role named servicio_basico_supl_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setServicio_basico_supl_conec6_apsc_linea(
		java.util.Collection aServicio_basico_supl_conec6_apsc_linea);
	/**
	 * Get accessor for persistent attribute: dir_caja
	 */
	public abstract java.lang.String getDir_caja();
	/**
	 * Set accessor for persistent attribute: dir_caja
	 */
	public abstract void setDir_caja(java.lang.String newDir_caja);
	/**
	 * Get accessor for persistent attribute: cont_linea_act
	 */
	public abstract java.lang.Long getCont_linea_act();
	/**
	 * Set accessor for persistent attribute: cont_linea_act
	 */
	public abstract void setCont_linea_act(java.lang.Long newCont_linea_act);
	/**
	 * Get accessor for persistent attribute: cont_linea_nueva
	 */
	public abstract java.lang.Long getCont_linea_nueva();
	/**
	 * Set accessor for persistent attribute: cont_linea_nueva
	 */
	public abstract void setCont_linea_nueva(
		java.lang.Long newCont_linea_nueva);
	/**
	 * Get accessor for persistent attribute: par_liston_ant
	 */
	public abstract java.lang.Long getPar_liston_ant();
	/**
	 * Set accessor for persistent attribute: par_liston_ant
	 */
	public abstract void setPar_liston_ant(java.lang.Long newPar_liston_ant);
	/**
	 * Get accessor for persistent attribute: tiene_cna
	 */
	public abstract java.lang.String getTiene_cna();
	/**
	 * Set accessor for persistent attribute: tiene_cna
	 */
	public abstract void setTiene_cna(java.lang.String newTiene_cna);
	/**
	 * Get accessor for persistent attribute: posicion_horizontal_ant
	 */
	public abstract java.lang.String getPosicion_horizontal_ant();
	/**
	 * Set accessor for persistent attribute: posicion_horizontal_ant
	 */
	public abstract void setPosicion_horizontal_ant(
		java.lang.String newPosicion_horizontal_ant);
	/**
	 * Get accessor for persistent attribute: posicion_horizontal_asg
	 */
	public abstract java.lang.String getPosicion_horizontal_asg();
	/**
	 * Set accessor for persistent attribute: posicion_horizontal_asg
	 */
	public abstract void setPosicion_horizontal_asg(
		java.lang.String newPosicion_horizontal_asg);
	/**
	 * This method was generated for supporting the relationship role named zonas_atendimiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getZonas_atendimiento();
	/**
	 * This method was generated for supporting the relationship role named zonas_atendimiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setZonas_atendimiento(
		java.util.Collection aZonas_atendimiento);
	/**
	 * Get accessor for persistent attribute: secret_code
	 */
	public abstract java.lang.String getSecret_code();
	/**
	 * Set accessor for persistent attribute: secret_code
	 */
	public abstract void setSecret_code(java.lang.String newSecret_code);
	/**
	 * Get accessor for persistent attribute: caja_dedicado
	 */
	public abstract java.lang.String getCaja_dedicado();
	/**
	 * Set accessor for persistent attribute: caja_dedicado
	 */
	public abstract void setCaja_dedicado(java.lang.String newCaja_dedicado);
	/**
	 * Get accessor for persistent attribute: armario_dedicado
	 */
	public abstract java.lang.String getArmario_dedicado();
	/**
	 * Set accessor for persistent attribute: armario_dedicado
	 */
	public abstract void setArmario_dedicado(
		java.lang.String newArmario_dedicado);
	/**
	 * Get accessor for persistent attribute: cable_dedicado
	 */
	public abstract java.lang.String getCable_dedicado();
	/**
	 * Set accessor for persistent attribute: cable_dedicado
	 */
	public abstract void setCable_dedicado(java.lang.String newCable_dedicado);
	/**
	 * Get accessor for persistent attribute: dir_armario_dedicado
	 */
	public abstract java.lang.String getDir_armario_dedicado();
	/**
	 * Set accessor for persistent attribute: dir_armario_dedicado
	 */
	public abstract void setDir_armario_dedicado(
		java.lang.String newDir_armario_dedicado);
	/**
	 * Get accessor for persistent attribute: dir_caja_dedicado
	 */
	public abstract java.lang.String getDir_caja_dedicado();
	/**
	 * Set accessor for persistent attribute: dir_caja_dedicado
	 */
	public abstract void setDir_caja_dedicado(
		java.lang.String newDir_caja_dedicado);
	/**
	 * Get accessor for persistent attribute: tipo_caja
	 */
	public abstract java.lang.String getTipo_caja();
	/**
	 * Set accessor for persistent attribute: tipo_caja
	 */
	public abstract void setTipo_caja(java.lang.String newTipo_caja);
	/**
	 * Get accessor for persistent attribute: tipo_caja_dedicado
	 */
	public abstract java.lang.String getTipo_caja_dedicado();
	/**
	 * Set accessor for persistent attribute: tipo_caja_dedicado
	 */
	public abstract void setTipo_caja_dedicado(
		java.lang.String newTipo_caja_dedicado);
	/**
	 * Get accessor for persistent attribute: config_automatica
	 */
	public abstract java.lang.Short getConfig_automatica();
	/**
	 * Set accessor for persistent attribute: config_automatica
	 */
	public abstract void setConfig_automatica(
		java.lang.Short newConfig_automatica);
	/**
	 * Get accessor for persistent attribute: par_caja_dedicado
	 */
	public abstract java.lang.Long getPar_caja_dedicado();
	/**
	 * Set accessor for persistent attribute: par_caja_dedicado
	 */
	public abstract void setPar_caja_dedicado(
		java.lang.Long newPar_caja_dedicado);
	/**
	 * Get accessor for persistent attribute: par_cable_dedicado
	 */
	public abstract java.lang.Long getPar_cable_dedicado();
	/**
	 * Set accessor for persistent attribute: par_cable_dedicado
	 */
	public abstract void setPar_cable_dedicado(
		java.lang.Long newPar_cable_dedicado);
	/**
	 * Get accessor for persistent attribute: ind_dedicado
	 */
	public abstract java.lang.Short getInd_dedicado();
	/**
	 * Set accessor for persistent attribute: ind_dedicado
	 */
	public abstract void setInd_dedicado(java.lang.Short newInd_dedicado);
	/**
	 * Get accessor for persistent attribute: crea_ods_granite
	 */
	public abstract java.lang.Short getCrea_ods_granite();
	/**
	 * Set accessor for persistent attribute: crea_ods_granite
	 */
	public abstract void setCrea_ods_granite(java.lang.Short newCrea_ods_granite);
	/**
	 * Get accessor for persistent attribute: central_connection
	 */
	public abstract java.lang.Integer getCentral_connection();
	/**
	 * Set accessor for persistent attribute: central_connection
	 */
	public abstract void setCentral_connection(
		java.lang.Integer newCentral_connection);
	/**
	 * Get accessor for persistent attribute: distancia_caja
	 */
	public abstract java.lang.Integer getDistancia_caja();
	/**
	 * Set accessor for persistent attribute: distancia_caja
	 */
	public abstract void setDistancia_caja(java.lang.Integer newDistancia_caja);
	/**
	 * Get accessor for persistent attribute: longitud
	 */
	public abstract java.math.BigDecimal getLongitud();
	/**
	 * Set accessor for persistent attribute: longitud
	 */
	public abstract void setLongitud(java.math.BigDecimal newLongitud);
	/**
	 * Get accessor for persistent attribute: latitud
	 */
	public abstract java.math.BigDecimal getLatitud();
	/**
	 * Set accessor for persistent attribute: latitud
	 */
	public abstract void setLatitud(java.math.BigDecimal newLatitud);
	/**
	 * Get accessor for persistent attribute: cod_central_dedicado
	 */
	public abstract java.lang.Long getCod_central_dedicado();
	/**
	 * Set accessor for persistent attribute: cod_central_dedicado
	 */
	public abstract void setCod_central_dedicado(
		java.lang.Long newCod_central_dedicado);
	/**
	 * Get accessor for persistent attribute: est_linea
	 */
	public abstract java.lang.String getEst_linea();
	/**
	 * Set accessor for persistent attribute: est_linea
	 */
	public abstract void setEst_linea(java.lang.String newEst_linea);
	/**
	 * Get accessor for persistent attribute: cambio_zonas
	 */
	public abstract java.lang.String getCambio_zonas();
	/**
	 * Set accessor for persistent attribute: cambio_zonas
	 */
	public abstract void setCambio_zonas(java.lang.String newCambio_zonas);
	/**
	 * Get accessor for persistent attribute: reinstalacion_internet
	 */
	public abstract java.lang.String getReinstalacion_internet();
	/**
	 * Set accessor for persistent attribute: reinstalacion_internet
	 */
	public abstract void setReinstalacion_internet(
		java.lang.String newReinstalacion_internet);
	
	/**
	 * Get accessor for persistent attribute: fecha_asig_recurso
	 */
	public abstract java.sql.Timestamp getFecha_asig_recurso();
	/**
	 * Set accessor for persistent attribute: reinstalacion_internet
	 */
	public abstract void setFecha_asig_recurso(java.sql.Timestamp newFecha_asig_recurso);
	/**
	 * Get accessor for persistent attribute: reinstalacion_internet
	 */
	//Fin Req 14209
/**
	 * Get accessor for persistent attribute: fttciplb_asg
	 */
	public abstract java.lang.String getFttciplb_asg();
	/**
	 * Set accessor for persistent attribute: fttciplb_asg
	 */
	public abstract void setFttciplb_asg(java.lang.String newFttciplb_asg);
	/**
	 * Get accessor for persistent attribute: fttcusu_asg
	 */
	public abstract java.lang.String getFttcusu_asg();
	/**
	 * Set accessor for persistent attribute: fttcusu_asg
	 */
	public abstract void setFttcusu_asg(java.lang.String newFttcusu_asg);
	/**
	 * Get accessor for persistent attribute: fttcpwd_asg
	 */
	public abstract java.lang.String getFttcpwd_asg();
	/**
	 * Set accessor for persistent attribute: fttcpwd_asg
	 */
	public abstract void setFttcpwd_asg(java.lang.String newFttcpwd_asg);
	/**
	 * Get accessor for persistent attribute: fttcslot_asg
	 */
	public abstract java.lang.String getFttcslot_asg();
	/**
	 * Set accessor for persistent attribute: fttcslot_asg
	 */
	public abstract void setFttcslot_asg(java.lang.String newFttcslot_asg);
	/**
	 * Get accessor for persistent attribute: fttcfab_asg
	 */
	public abstract java.lang.String getFttcfab_asg();
	/**
	 * Set accessor for persistent attribute: fttcfab_asg
	 */
	public abstract void setFttcfab_asg(java.lang.String newFttcfab_asg);
	/**
	 * Get accessor for persistent attribute: rec_fttc_asg
	 */
	public abstract java.lang.String getRec_fttc_asg();
	/**
	 * Set accessor for persistent attribute: rec_fttc_asg
	 */
	public abstract void setRec_fttc_asg(java.lang.String newRec_fttc_asg);
	/**
	 * Get accessor for persistent attribute: fttciplb_ant
	 */
	public abstract java.lang.String getFttciplb_ant();
	/**
	 * Set accessor for persistent attribute: fttciplb_ant
	 */
	public abstract void setFttciplb_ant(java.lang.String newFttciplb_ant);
	/**
	 * Get accessor for persistent attribute: fttcusu_ant
	 */
	public abstract java.lang.String getFttcusu_ant();
	/**
	 * Set accessor for persistent attribute: fttcusu_ant
	 */
	public abstract void setFttcusu_ant(java.lang.String newFttcusu_ant);
	/**
	 * Get accessor for persistent attribute: fttcpwd_ant
	 */
	public abstract java.lang.String getFttcpwd_ant();
	/**
	 * Set accessor for persistent attribute: fttcpwd_ant
	 */
	public abstract void setFttcpwd_ant(java.lang.String newFttcpwd_ant);
	/**
	 * Get accessor for persistent attribute: fttcslot_ant
	 */
	public abstract java.lang.String getFttcslot_ant();
	/**
	 * Set accessor for persistent attribute: fttcslot_ant
	 */
	public abstract void setFttcslot_ant(java.lang.String newFttcslot_ant);
	/**
	 * Get accessor for persistent attribute: fttcfab_ant
	 */
	public abstract java.lang.String getFttcfab_ant();
	/**
	 * Set accessor for persistent attribute: fttcfab_ant
	 */
	public abstract void setFttcfab_ant(java.lang.String newFttcfab_ant);
	/**
	 * Get accessor for persistent attribute: rec_fttc_ant
	 */
	public abstract java.lang.String getRec_fttc_ant();
	/**
	 * Set accessor for persistent attribute: rec_fttc_ant
	 */
	public abstract void setRec_fttc_ant(java.lang.String newRec_fttc_ant);
	/**
	 * Get accessor for persistent attribute: password_fttc
	 */
	public abstract java.lang.String getPassword_fttc();
	/**
	 * Set accessor for persistent attribute: password_fttc
	 */
	public abstract void setPassword_fttc(java.lang.String newPassword_fttc);
	/**
	 * Get accessor for persistent attribute: fttcmode_ant
	 */
	public abstract java.lang.String getFttcmode_ant();
	/**
	 * Set accessor for persistent attribute: fttcmode_ant
	 */
	public abstract void setFttcmode_ant(java.lang.String newFttcmode_ant);
	/**
	 * Get accessor for persistent attribute: fttcmode_asg
	 */
	public abstract java.lang.String getFttcmode_asg();
	/**
	 * Set accessor for persistent attribute: fttcmode_asg
	 */
	public abstract void setFttcmode_asg(java.lang.String newFttcmode_asg);
	/**
	 * Get accessor for persistent attribute: rack
	 */
	public abstract java.lang.String getRack();
	/**
	 * Set accessor for persistent attribute: rack
	 */
	public abstract void setRack(java.lang.String newRack);
	/**
	 * Get accessor for persistent attribute: subRack
	 */
	public abstract java.lang.String getSubRack();
	/**
	 * Set accessor for persistent attribute: subRack
	 */
	public abstract void setSubRack(java.lang.String newSubRack);
	/**
	 * Get accessor for persistent attribute: rack_ant
	 */
	public abstract java.lang.String getRack_ant();
	/**
	 * Set accessor for persistent attribute: rack_ant
	 */
	public abstract void setRack_ant(java.lang.String newRack_ant);
	/**
	 * Get accessor for persistent attribute: subRack_ant
	 */
	public abstract java.lang.String getSubRack_ant();
	/**
	 * Set accessor for persistent attribute: subRack_ant
	 */
	public abstract void setSubRack_ant(java.lang.String newSubRack_ant);
}
