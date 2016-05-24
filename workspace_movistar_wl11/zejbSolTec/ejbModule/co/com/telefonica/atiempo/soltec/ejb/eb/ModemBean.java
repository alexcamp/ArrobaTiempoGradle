package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Modem
 */
public abstract class ModemBean implements javax.ejb.EntityBean {
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
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey ejbCreate(String serial,Peticion_stLocal peticion,Long nroTel,Short accion) throws javax.ejb.CreateException
		{
			setSerial(serial);
			Peticion_stKey argPeticionPK =(Peticion_stKey) peticion.getPrimaryKey();
			setPeticion_st_cod_ave_cd(argPeticionPK.cod_ave_cd);
			setTelefono(nroTel);
			setAccion(accion);
			return null;
		}
	
	public void ejbPostCreate(String serial,Peticion_stLocal peticion,Long nroTel,Short accion) throws javax.ejb.CreateException
	{
		setPeticion_st(peticion);
	}
		
		
		
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey ejbCreate(
		java.lang.String serial,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException {
		setSerial(serial);
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey argPeticion_stPK =
			(co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Peticion_stKey) argPeticion_st
				.getPrimaryKey();
		setPeticion_st_cod_ave_cd(argPeticion_stPK.cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String serial,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException {
		setPeticion_st(argPeticion_st);
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
	 * Get accessor for persistent attribute: serial
	 */
	public abstract java.lang.String getSerial();
	/**
	 * Set accessor for persistent attribute: serial
	 */
	public abstract void setSerial(java.lang.String newSerial);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public abstract java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public abstract void setTelefono(java.lang.Long newTelefono);
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
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey ejbCreate(
		java.lang.String serial,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException {
		setSerial(serial);
		setPeticion_st_cod_ave_cd(peticion_st_cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String serial,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: peticion_st_cod_ave_cd
	 */
	public abstract java.lang.Long getPeticion_st_cod_ave_cd();
	/**
	 * Set accessor for persistent attribute: peticion_st_cod_ave_cd
	 */
	public abstract void setPeticion_st_cod_ave_cd(
		java.lang.Long newPeticion_st_cod_ave_cd);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.Short getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.Short newAccion);
	/**
	 * Get accessor for persistent attribute: modem_marca
	 */
	public abstract java.lang.String getModem_marca();
	/**
	 * Set accessor for persistent attribute: modem_marca
	 */
	public abstract void setModem_marca(java.lang.String newModem_marca);
	/**
	 * Get accessor for persistent attribute: cod_material
	 */
	public abstract java.lang.String getCod_material();
	/**
	 * Set accessor for persistent attribute: cod_material
	 */
	public abstract void setCod_material(java.lang.String newCod_material);
	/**
	 * Get accessor for persistent attribute: modelo
	 */
	public abstract java.lang.String getModelo();
	/**
	 * Set accessor for persistent attribute: modelo
	 */
	public abstract void setModelo(java.lang.String newModelo);
	/**
	 * Get accessor for persistent attribute: tipo
	 */
	public abstract java.lang.Integer getTipo();
	/**
	 * Set accessor for persistent attribute: tipo
	 */
	public abstract void setTipo(java.lang.Integer newTipo);
	/**
	 * Get accessor for persistent attribute: fec_cont_sap
	 */
	public abstract java.lang.String getFec_cont_sap();
	/**
	 * Set accessor for persistent attribute: fec_cont_sap
	 */
	public abstract void setFec_cont_sap(java.lang.String newFec_cont_sap);
	/**
	 * Get accessor for persistent attribute: clase_mov_sap
	 */
	public abstract java.lang.String getClase_mov_sap();
	/**
	 * Set accessor for persistent attribute: clase_mov_sap
	 */
	public abstract void setClase_mov_sap(java.lang.String newClase_mov_sap);
	/**
	 * Get accessor for persistent attribute: pos_doc_sap
	 */
	public abstract int getPos_doc_sap();
	/**
	 * Set accessor for persistent attribute: pos_doc_sap
	 */
	public abstract void setPos_doc_sap(int newPos_doc_sap);
	/**
	 * Get accessor for persistent attribute: num_material_sap
	 */
	public abstract java.lang.String getNum_material_sap();
	/**
	 * Set accessor for persistent attribute: num_material_sap
	 */
	public abstract void setNum_material_sap(
		java.lang.String newNum_material_sap);
	/**
	 * Get accessor for persistent attribute: centro_sap
	 */
	public abstract java.lang.String getCentro_sap();
	/**
	 * Set accessor for persistent attribute: centro_sap
	 */
	public abstract void setCentro_sap(java.lang.String newCentro_sap);
	/**
	 * Get accessor for persistent attribute: almacen_sap
	 */
	public abstract java.lang.String getAlmacen_sap();
	/**
	 * Set accessor for persistent attribute: almacen_sap
	 */
	public abstract void setAlmacen_sap(java.lang.String newAlmacen_sap);
	/**
	 * Get accessor for persistent attribute: cod_lote_sap
	 */
	public abstract java.lang.String getCod_lote_sap();
	/**
	 * Set accessor for persistent attribute: cod_lote_sap
	 */
	public abstract void setCod_lote_sap(java.lang.String newCod_lote_sap);
	/**
	 * Get accessor for persistent attribute: und_medida_sap
	 */
	public abstract java.lang.String getUnd_medida_sap();
	/**
	 * Set accessor for persistent attribute: und_medida_sap
	 */
	public abstract void setUnd_medida_sap(java.lang.String newUnd_medida_sap);
	/**
	 * Get accessor for persistent attribute: centr_cost_sap
	 */
	public abstract java.lang.String getCentr_cost_sap();
	/**
	 * Set accessor for persistent attribute: centr_cost_sap
	 */
	public abstract void setCentr_cost_sap(java.lang.String newCentr_cost_sap);
	/**
	 * Get accessor for persistent attribute: area_func_sap
	 */
	public abstract java.lang.String getArea_func_sap();
	/**
	 * Set accessor for persistent attribute: area_func_sap
	 */
	public abstract void setArea_func_sap(java.lang.String newArea_func_sap);
	/**
	 * Get accessor for persistent attribute: element_pep_sap
	 */
	public abstract java.lang.String getElement_pep_sap();
	/**
	 * Set accessor for persistent attribute: element_pep_sap
	 */
	public abstract void setElement_pep_sap(java.lang.String newElement_pep_sap);
	/**
	 * Get accessor for persistent attribute: flag_pet_curso
	 */
	public abstract java.lang.String getFlag_pet_curso();
	/**
	 * Set accessor for persistent attribute: flag_pet_curso
	 */
	public abstract void setFlag_pet_curso(java.lang.String newFlag_pet_curso);
	/**
	 * Get accessor for persistent attribute: flag_mat_sap
	 */
	public abstract java.lang.String getFlag_mat_sap();
	/**
	 * Set accessor for persistent attribute: flag_mat_sap
	 */
	public abstract void setFlag_mat_sap(java.lang.String newFlag_mat_sap);
}
