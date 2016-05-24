package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Elemento_Peticion
 */
public interface Elemento_PeticionLocal extends javax.ejb.EJBLocalObject {
    /**
     * Get accessor for persistent attribute: serial
     */
    public java.lang.String getSerial();
    /**
     * Set accessor for persistent attribute: serial
     */
    public void setSerial(java.lang.String newSerial);
    /**
     * Get accessor for persistent attribute: telefono
     */
    public java.lang.Long getTelefono();
    /**
     * Set accessor for persistent attribute: telefono
     */
    public void setTelefono(java.lang.Long newTelefono);
    /**
     * Get accessor for persistent attribute: accion
     */
    public java.lang.Short getAccion();
    /**
     * Set accessor for persistent attribute: accion
     */
    public void setAccion(java.lang.Short newAccion);
    /**
     * Get accessor for persistent attribute: tipo_equipo
     */
    public java.lang.String getTipo_equipo();
    /**
     * Set accessor for persistent attribute: tipo_equipo
     */
    public void setTipo_equipo(java.lang.String newTipo_equipo);
    /**
     * Get accessor for persistent attribute: tipo_elemento
     */
    public java.lang.Long getTipo_elemento();
    /**
     * Set accessor for persistent attribute: tipo_elemento
     */
    public void setTipo_elemento(java.lang.Long newTipo_elemento);
    /**
     * Get accessor for persistent attribute: tipo_inventario
     */
    public java.lang.String getTipo_inventario();
    /**
     * Set accessor for persistent attribute: tipo_inventario
     */
    public void setTipo_inventario(java.lang.String newTipo_inventario);
    /**
     * This method was generated for supporting the relationship role named peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
    /**
     * This method was generated for supporting the relationship role named peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public void setPeticion(
        co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
    /**
     * Get accessor for persistent attribute: ps_id
     */
    public java.lang.Long getPs_id();
    /**
     * Set accessor for persistent attribute: ps_id
     */
    public void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: marca
	 */
	public java.lang.String getMarca();
	/**
	 * Set accessor for persistent attribute: marca
	 */
	public void setMarca(java.lang.String newMarca);
	/**
	 * Get accessor for persistent attribute: modelo
	 */
	public java.lang.String getModelo();
	/**
	 * Set accessor for persistent attribute: modelo
	 */
	public void setModelo(java.lang.String newModelo);
	/**
	 * Get accessor for persistent attribute: num_celular
	 */
	public java.lang.String getNum_celular();
	/**
	 * Set accessor for persistent attribute: num_celular
	 */
	public void setNum_celular(java.lang.String newNum_celular);
	/**
	 * Get accessor for persistent attribute: num_doc_sap
	 */
	public java.lang.String getNum_doc_sap();
	/**
	 * Set accessor for persistent attribute: num_doc_sap
	 */
	public void setNum_doc_sap(java.lang.String newNum_doc_sap);
	/**
	 * Get accessor for persistent attribute: fec_cont_sap
	 */
	public java.lang.String getFec_cont_sap();
	/**
	 * Set accessor for persistent attribute: fec_cont_sap
	 */
	public void setFec_cont_sap(java.lang.String newFec_cont_sap);
	/**
	 * Get accessor for persistent attribute: clase_mov_sap
	 */
	public java.lang.String getClase_mov_sap();
	/**
	 * Set accessor for persistent attribute: clase_mov_sap
	 */
	public void setClase_mov_sap(java.lang.String newClase_mov_sap);
	/**
	 * Get accessor for persistent attribute: pos_doc_sap
	 */
	public int getPos_doc_sap();
	/**
	 * Set accessor for persistent attribute: pos_doc_sap
	 */
	public void setPos_doc_sap(int newPos_doc_sap);
	/**
	 * Get accessor for persistent attribute: num_material_sap
	 */
	public java.lang.String getNum_material_sap();
	/**
	 * Set accessor for persistent attribute: num_material_sap
	 */
	public void setNum_material_sap(java.lang.String newNum_material_sap);
	/**
	 * Get accessor for persistent attribute: centro_sap
	 */
	public java.lang.String getCentro_sap();
	/**
	 * Set accessor for persistent attribute: centro_sap
	 */
	public void setCentro_sap(java.lang.String newCentro_sap);
	/**
	 * Get accessor for persistent attribute: almacen_sap
	 */
	public java.lang.String getAlmacen_sap();
	/**
	 * Set accessor for persistent attribute: almacen_sap
	 */
	public void setAlmacen_sap(java.lang.String newAlmacen_sap);
	/**
	 * Get accessor for persistent attribute: cod_lote_sap
	 */
	public java.lang.String getCod_lote_sap();
	/**
	 * Set accessor for persistent attribute: cod_lote_sap
	 */
	public void setCod_lote_sap(java.lang.String newCod_lote_sap);
	/**
	 * Get accessor for persistent attribute: und_medida_sap
	 */
	public java.lang.String getUnd_medida_sap();
	/**
	 * Set accessor for persistent attribute: und_medida_sap
	 */
	public void setUnd_medida_sap(java.lang.String newUnd_medida_sap);
	/**
	 * Get accessor for persistent attribute: centr_cost_sap
	 */
	public java.lang.String getCentr_cost_sap();
	/**
	 * Set accessor for persistent attribute: centr_cost_sap
	 */
	public void setCentr_cost_sap(java.lang.String newCentr_cost_sap);
	/**
	 * Get accessor for persistent attribute: area_func_sap
	 */
	public java.lang.String getArea_func_sap();
	/**
	 * Set accessor for persistent attribute: area_func_sap
	 */
	public void setArea_func_sap(java.lang.String newArea_func_sap);
	/**
	 * Get accessor for persistent attribute: element_pep_sap
	 */
	public java.lang.String getElement_pep_sap();
	/**
	 * Set accessor for persistent attribute: element_pep_sap
	 */
	public void setElement_pep_sap(java.lang.String newElement_pep_sap);
	/**
	 * Get accessor for persistent attribute: flag_pet_curso
	 */
	public java.lang.String getFlag_pet_curso();
	/**
	 * Set accessor for persistent attribute: flag_pet_curso
	 */
	public void setFlag_pet_curso(java.lang.String newFlag_pet_curso);
	/**
	 * Get accessor for persistent attribute: flag_mat_sap
	 */
	public java.lang.String getFlag_mat_sap();
	/**
	 * Set accessor for persistent attribute: flag_mat_sap
	 */
	public void setFlag_mat_sap(java.lang.String newFlag_mat_sap);
	/**
	 * Get accessor for persistent attribute: caracteristica
	 */
	public java.lang.String getCaracteristica();
	/**
	 * Set accessor for persistent attribute: caracteristica
	 */
	public void setCaracteristica(java.lang.String newCaracteristica);
	/**
	 * Get accessor for persistent attribute: guiaAutoInst
	 */
	public java.lang.String getGuiaAutoInst();
	/**
	 * Set accessor for persistent attribute: guiaAutoInst
	 */
	public void setGuiaAutoInst(java.lang.String newGuiaAutoInst);
	/**
	 * Get accessor for persistent attribute: fecha_guia_auto_inst
	 */
	public java.sql.Timestamp getFecha_guia_auto_inst();
	/**
	 * Set accessor for persistent attribute: fecha_guia_auto_inst
	 */
	public void setFecha_guia_auto_inst(
		java.sql.Timestamp newFecha_guia_auto_inst);
}
