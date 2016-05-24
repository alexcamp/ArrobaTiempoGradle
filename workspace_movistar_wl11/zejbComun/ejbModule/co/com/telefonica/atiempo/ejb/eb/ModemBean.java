package co.com.telefonica.atiempo.ejb.eb;
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
	public co.com.telefonica.atiempo.ejb.eb.ModemKey ejbCreate(
		java.lang.String serial,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setSerial(serial);
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticionPK = 
			(co.com.telefonica.atiempo.ejb.eb.PeticionKey) argPeticion
			.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String serial,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setPeticion(argPeticion);
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
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.Short getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.Short newAccion);
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
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemKey ejbCreate(String serial,PeticionLocal peticion,Long nroTel,Short accion) throws javax.ejb.CreateException
	{
		setSerial(serial);
		PeticionKey argPeticionPK =(PeticionKey) peticion.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		setTelefono(nroTel);
		setAccion(accion);
		return null;
	}
	
	public void ejbPostCreate(String serial,PeticionLocal peticion,Long nroTel,Short accion) throws javax.ejb.CreateException
	{
		setPeticion(peticion);
	}
	
	public void ejbPostCreate(String serial,PeticionLocal peticion,Long nroTel,Short accion, String marca, String modelo, Integer tipo) throws javax.ejb.CreateException
	{
		setPeticion(peticion);
	}
	
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemKey ejbCreate(String serial,PeticionLocal peticion,Long nroTel,Short accion,String marca, String modelo, Integer tipo) throws javax.ejb.CreateException
	{
		setSerial(serial);
		PeticionKey argPeticionPK =(PeticionKey) peticion.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		setTelefono(nroTel);
		setAccion(accion);
		setModem_marca(marca);
		setModelo(modelo);
		setTipo(tipo);
		return null;
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ModemKey ejbCreate(
		java.lang.String serial,
		java.lang.Long peticion_peti_numero) 
		throws javax.ejb.CreateException {
		setSerial(serial);
		setPeticion_peti_numero(peticion_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String serial,
		java.lang.Long peticion_peti_numero) 
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract java.lang.Long getPeticion_peti_numero();
	/**
	 * Set accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract void setPeticion_peti_numero(
		java.lang.Long newPeticion_peti_numero);
	
	/**
	 * Get accessor for persistent attribute: modem_marca
	 */
	public abstract java.lang.String getModem_marca();
	/**
	 * Set accessor for persistent attribute: modem_marca
	 */
	public abstract void setModem_marca(java.lang.String newModem_marca);
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
	 * Get accessor for persistent attribute: codigo_material
	 */
	public abstract java.lang.String getCodigo_material();
	/**
	 * Set accessor for persistent attribute: codigo_material
	 */
	public abstract void setCodigo_material(java.lang.String newCodigo_material);
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
